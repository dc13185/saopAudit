 package com.asiainfo.crm.blueprint.smo.impl;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.asiainfo.angel.utils.ObjectUtils;
import com.asiainfo.crm.blueprint.common.AuditConstant;
import com.asiainfo.crm.blueprint.common.XmlUtil;
import com.asiainfo.crm.blueprint.dao.ISaopAuditDao;
import com.asiainfo.crm.blueprint.model.AuditDataOrignEntity;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;
import com.asiainfo.crm.blueprint.smo.DataExtractServiceSmo;
import com.asiainfo.crm.blueprint.smo.ISaopAuditSmo;
import com.asiainfo.crm.blueprint.smo.base.SaopAuditBase;

/**
 * httpservice 侦听数据抽取
 * 
 * @author chenchao
 *
 */
@Service
public class DataExtractServiceSmoImpl implements DataExtractServiceSmo {

	private static final Logger LOG = LoggerFactory.getLogger(DataExtractServiceSmoImpl.class);
	
	@Autowired
	Map<String, ISaopAuditSmo> saopDataAuditSmoMap;

	@Autowired
	ISaopAuditDao saopAuditDao;

	@Autowired
	SaopAuditBase saopAuditBase;

	@PostConstruct
	public void init() {
		saopAuditBase.init();
	}
	
	/**
	 * 数据抽取入口方法
	 * 根据saop侦听送过来的auditOriginDataId从稽核数据来源表（AUDIT_DATA_ORIGIN）查询serviceId、LogId（如果是报竣还需要获取primaryID）
	 * 根据serviceId获取buscode跟svccode，然后分析是是哪种稽核项获取beanName
	 * 根据LogId从交互日志表中获取报文
	 * 最后根据beanName调用具体的实现类去接解析报文写入稽核中间表
	 */
	public String execute(String auditOriginDataXml) {
		String msg;
		String resultCd;
		String auditOriginDataId = "";
		Map<String, String> params = new HashMap<String, String>();
		try {
			//根据入参获取auditOriginDataId
			auditOriginDataId = getAuditOriginDataIdFromInputParam(auditOriginDataXml);
			params.put("auditOriginDataId", auditOriginDataId);
			//根据auditOriginDataId获取auditDataOrignEntity
			AuditDataOrignEntity auditDataOrignEntity = getAuditDataOrignEntity(auditOriginDataId);
			//通过auditDataOrignEntity的SrvcInstId获取busCode跟serviceCode
			Map<String, String> codeMap = saopAuditDao
					.querySaopAuditBusServicecode(auditDataOrignEntity.getSaopSrvcInstId());
			//根据auditDataOrignEntity的logId获取报文
			AuditInputEntity auditInputEntity = getAuditInputEntity(auditDataOrignEntity,codeMap);
			// 根据svcCode、busCode映射到具体的数据抽取实现类
			List<String> beanNameList = busCodeMapping(auditInputEntity.getBuscode(), auditInputEntity.getSvcCode());
			auditInputEntity.setAuditOriginDataId(auditOriginDataId);
			LOG.debug("beanName=" + beanNameList);
			// 判断是否在上传
			if (isFtpUpload(beanNameList)) {
				msg = "beanName:" + beanNameList + "正在上传文件。";
				// 将上传期间不做处理的稽核来源数据表状态改成S
				params.put("processState", "S");
				params.put("processResult", "文件上传中，暂不处理");
				saopAuditDao.updateAuditDataOrign(params);
				resultCd = "1";
				LOG.debug(msg);
				return getReturnMsg(msg, resultCd, auditOriginDataId);
			}
			// 遍历beanNameList根据beanName获取实现类
			for (String beanName : beanNameList) {
				ISaopAuditSmo saopDataAuditSmo = (ISaopAuditSmo) ObjectUtils.getBean(beanName);
				// 执行具体业务实现类的数据抽取
				saopDataAuditSmo.dataExtraction(auditInputEntity);
			}
			// 将成功落地的稽核来源数据表状态改成C7
			params.put("processState", "C7");
			params.put("processResult", "数据稽核成功");
			saopAuditDao.updateAuditDataOrign(params);
			msg = "成功";
			resultCd = "0";
			LOG.debug("侦听送的auditOriginDataId：" + auditOriginDataId + ",数据抽取成功。");
		} catch (Exception e) {
			LOG.error("数据抽取异常:", e);
			msg = e.getMessage();
			resultCd = "1";	//"0"成功，其他失败
			//错误日志保存
			insertAuditFailLog(auditOriginDataId, e);
			params.put("processState", "E7");
			params.put("processResult", "数据稽核失败");
			try {
				saopAuditDao.updateAuditDataOrign(params);
			} catch (Exception e1) {
				LOG.error("更新稽核来源数据表失败:", e.getMessage());
			}
		}
		return getReturnMsg(msg, resultCd, auditOriginDataId);
	}
	
	/**
	 * 稽核错误日志记录
	 * @param auditOriginDataId
	 * @param e
	 */
	private void insertAuditFailLog(String auditOriginDataId, Exception e) {
		Map<String, String> errorParam = new HashMap<String, String>();
		String errm = e.getMessage();
		// 由于数据库字段长度的限制，所以错误信息长度大于600需要截取
		if (null != errm && errm.length() < 600) {
			errorParam.put("errM", errm);
		} else {
			errorParam.put("errM", errm.substring(1, 600));
		}
		errorParam.put("auditOriginDataId", auditOriginDataId);
		saopAuditDao.insertUniteAuditFailLog(errorParam);
		LOG.debug("保存信息稽核错误日志表成功 param=" + errorParam);
	}

	private AuditDataOrignEntity getAuditDataOrignEntity(String auditOriginDataId) throws Exception {
		String msg;
		// 根据auditOriginDataId查询稽核数据来源表,获取serviceID、logID。
		AuditDataOrignEntity auditDataOrignEntity = saopAuditDao.queryAuditDataOrignEntity(auditOriginDataId);
		if(auditDataOrignEntity == null){
			msg = "不能通过auditOriginDataId找到对应的稽核数据来源表记录！";
			throw new Exception(msg);
		}
		//去掉毫秒
		auditDataOrignEntity.setCreateTime(auditDataOrignEntity.getCreateTime().replaceAll("\\.\\d{1,3}", ""));
		LOG.debug("通过侦听送的auditOriginDataId查询出AuditDataOrignEntity：", auditDataOrignEntity);
		return auditDataOrignEntity;
	}

	private String getAuditOriginDataIdFromInputParam(String auditOriginDataXml) throws Exception {
		String msg = null;
		// 如果侦听传入的参数为空则直接返回失败
		if (StringUtils.isEmpty(auditOriginDataXml)) {
			msg = "侦听传入的参数为空！";
			throw new Exception(msg);
		}
		Document doc = null;
		try {
			//入参转换为XML格式
			doc = XmlUtil.parseXml(auditOriginDataXml);
		} catch (Exception e) {
			msg = "侦听传入的参数转换XML错误！";
			throw new Exception(msg);
		}
		//获取msgNode节点
		Node msgNode = doc.selectSingleNode("/messages/message/msgContent");
		String msgContent = msgNode != null ? msgNode.getText() : "";
		if(StringUtils.isBlank(msgContent)){
			msg = "侦听传入的参数节点msgContent为空";
			throw new Exception(msg);
		}
		
		String auditOriginDataId = "";
		//将msgNode节点的值转换为jason格式并获取元素ID的值，就是稽核来源数据表的auditOriginDataId
		try {
			auditOriginDataId = JSONPath.eval(JSON.parseObject(msgContent), "$.AUDIT_DATA_ORIGIN_ID").toString();
		} catch (Exception e) {
			msg = "从传入的参数msgContent节点json转换异常！";
			throw new Exception(msg);
		}
		//判断auditOriginDataId是否为空
		if(StringUtils.isEmpty(auditOriginDataId)){
			msg = "从传入的参数获取auditOriginDataId为空！";
			throw new Exception(msg);
		}
		LOG.debug("通过侦听送的auditOriginDataId：" + auditOriginDataId);
		return auditOriginDataId;
	}

	/**
	 * 判断是否在上传
	 * @param beanNameList 
	 * @return true:在上传 false：没在上传
	 */
	private boolean isFtpUpload(List<String> beanNameList) {
		for(String beanName : beanNameList){
			if(AuditConstant.AUDIT_CLOSE.equals(saopAuditDao.queryAuditSwitch(beanName))){
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param auditInputEntity 报文解析入参
	 * @param entity 稽核侦听实体类
	 * @param busCode 业务编码
	 * @param serviceCode 服务编码
	 * @param downXml 集团下发报文
	 * @param reportXml 报竣报文
	 * @throws SQLException 
	 */
	private AuditInputEntity getAuditInputEntity(AuditDataOrignEntity auditDataOrignEntity, Map<String, String> codeMap) throws SQLException {
		String busCode = codeMap.get("BUS_CODE");
		String serviceCode = codeMap.get("SERVICE_CODE");
		LOG.debug("svccode：" + serviceCode + "，buscode：" + busCode);
		// 集团下发报文
		String downXml = "";
		// 报竣报文
		String reportXml = "";
		Object reportXmlObj;
		Object downXmlObj;
		// 如果是要等报竣完送稽核，需要获取报竣报文
		if (AuditConstant.TLE_DEP_AUDITS_BJ_SVCCODE.equals(serviceCode) || AuditConstant.YDSF_AUDITS_BJ_SVCCODE.equals(serviceCode)) {
			//报竣报文
			reportXmlObj = saopAuditDao.getXmlByLogid(auditDataOrignEntity.getSaopLogId());
			reportXml = ClobToString(reportXmlObj);
			//下发报文
			downXmlObj = saopAuditDao.getXmlByLogid(auditDataOrignEntity.getSaopPrimaryLogId());
			downXml = ClobToString(downXmlObj);
		} else {
			downXmlObj = saopAuditDao.getXmlByLogid(auditDataOrignEntity.getSaopLogId());
			downXml = ClobToString(downXmlObj);
		}
		AuditInputEntity auditInputEntity = new AuditInputEntity();
		auditInputEntity.setBuscode(busCode);
		auditInputEntity.setSvcCode(serviceCode);
		auditInputEntity.setDownXml(downXml);
		auditInputEntity.setReportXml(reportXml);
		auditInputEntity.setCreateTime(auditDataOrignEntity.getCreateTime());
		return auditInputEntity;
	}

	private String ClobToString(Object reportXmlObj) throws SQLException {
		if(reportXmlObj instanceof java.sql.Clob){
			Clob colbGetXmlByLogid = (java.sql.Clob)reportXmlObj;
			return colbGetXmlByLogid.getSubString(1, (int) colbGetXmlByLogid.length());
		}
		return "";
	}
	
	/**
	 * 拼接返回结果
	 * @param msg 结果信息
	 * @param resultCd 0：成功 其他：失败
	 * @param auditOriginDataId 稽核侦听ID
	 * @return
	 */
	private String getReturnMsg(String msg, String resultCd, String auditOriginDataId) {
		return "<results><result><msgId></msgId><resultCd>" + resultCd + "</resultCd><msg>"
				+ msg + "</msg></result></results>";
	}

	/**
	 * busCode映射到具体的数据抽取实现类beanName
	 * 
	 * @param busCode
	 *            业务码
	 * @param serviceCode
	 *            服务码
	 * @return
	 */
	private List<String> busCodeMapping(String busCode, String serviceCode) {
		List<String> beanNameList = new ArrayList<String>();
		if ("SVC80015".equals(serviceCode)) {
			// 集团4G产销品稽核
			beanNameList.add("depNewAudits4G");
		}
		if(AuditConstant.TLE_DEP_NEW_AUDITS_UP_BUSCODE.contains(busCode) && AuditConstant.TLE_DEP_NEW_AUDITS_UP_SVCCODE.contains(serviceCode)){
			// 4G上传(停复机、活卡激活、欠费拆机)
			beanNameList.add("depNewAudits4G");
			// 活卡激活、4G产品状态变更稽核
			if("SVC80035".equals(serviceCode)){
				beanNameList.add("lteActiveTimeAudit");
			}
		}
		if (AuditConstant.TLE_DEP_ORDER_AUDIT_BUSCODE.contains(busCode) && AuditConstant.TLE_DEP_ORDER_AUDIT_SVCCODE.contains(serviceCode)) {
			// 集团4G订单信息费用稽核
			beanNameList.add("depOrderAudit4G");
		}
		// if(AuditConstant.GC_AUDIT_UP_BUSCODE.contains(busCode) && AuditConstant.GC_AUDIT_UP_SVCCODE.contains(serviceCode)){
			// 国漫
		//	beanNameList.add("cgInternationalRoamAudit");
		//  }
		if(AuditConstant.TERMINAL_AUDIT_UP_BUSCODE.contains(busCode) && AuditConstant.TERMINAL_AUDIT_UP_SVCCODE.contains(serviceCode)){
			// 终端补贴日稽核
			// beanNameList.add("terminalSubsidyDayAudit");
			beanNameList.add("terminalSubsidyAllAudit");
			
		}
		if(AuditConstant.ELECTRONICCHANNEL_AUDIT_UP_BUSCODE.contains(busCode) && AuditConstant.ELECTRONICCHANNEL_AUDIT_UP_SVCCODE.contains(serviceCode)){
			// 电渠
			beanNameList.add("electronicChannelOrderChargeAudit");
			beanNameList.add("electronicChannelOrderInfoAudit");
		}
		if(AuditConstant.CLOUDCARD_AUDITS_UP_BUSCODE.contains(busCode) && AuditConstant.CLOUDCARD_AUDITS_UP_SVCCODE.contains(serviceCode)){
			// 云卡
			beanNameList.add("cloudCardAudit");
		}
		if(AuditConstant.CCARD_AUDITS_UP_BUSCODE.contains(busCode) && AuditConstant.CCARD_AUDITS_UP_SVCCODE.contains(serviceCode)){
			// c网稽核
			beanNameList.add("cProdAudit");
		}
		if(AuditConstant.YDSF_AUDITS_BJ_BUSCODE.contains(busCode) && AuditConstant.YDSF_AUDITS_BJ_SVCCODE.contains(serviceCode)){
			// 一点收费
			beanNameList.add("ydsfAudit");
		}
		if(AuditConstant.GOVERNMENTENTERPRISE_AUDITS_UP_BUSCODE.contains(busCode) && AuditConstant.GOVERNMENTENTERPRISE_AUDITS_UP_SVCCODE.contains(serviceCode)){
			// 政企业务
			beanNameList.add("governmentEnterpriseAudit");
		}
		if(AuditConstant.IVPN_AUDITS_UP_BUSCODE.contains(busCode) && AuditConstant.IVPN_AUDITS_UP_SVCCODE.contains(serviceCode)){
			// IVPN
			beanNameList.add("ivpnAudit");
		}
		if(AuditConstant.DDN_AUDITS_UP_BUSCODE.contains(busCode) && AuditConstant.DDN_AUDITS_UP_SVCCODE.contains(serviceCode)){
			// 带宽型
			beanNameList.add("bandwidthAddAudit");
		}
		return beanNameList;
	}
}
