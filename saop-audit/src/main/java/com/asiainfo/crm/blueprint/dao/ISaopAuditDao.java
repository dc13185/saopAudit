package com.asiainfo.crm.blueprint.dao;

import java.sql.Clob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.asiainfo.crm.blueprint.model.AuditDataOrignEntity;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;

@Service("saopAuditDao")
public interface ISaopAuditDao {
	/**
	 * 通过prvncInstId查询buscode和servicecode
	 * @param saopPrvcInstId
	 * @return
	 */
	public Map<String,String> querySaopAuditBuscode(String saopPrvcInstId);
	
	//批量查询待处理消息
	public List<AuditInputEntity> querySaopAuditOrignList();
	
	/**
	 * 里面只有一个KEY 就是SQL,SQL是读取配置
	 * @param param
	 * @return
	 */
	public String getValue(java.util.HashMap<String, String> param) throws Exception;
	
	/**
	 * 里面只有一个KEY 就是SQL,SQL是程序动态生成的
	 * @param param
	 * @return
	 */
	public void saveAudit(java.util.HashMap<String, String> param) throws Exception;
	/**
	 * 里面只有一个KEY 就是SQL,SQL是程序动态生成的
	 * @param param
	 * @return
	 */
	public void updateAudit(java.util.HashMap<String, String> param) throws Exception;
	
	/**
	 * 更新SAOP状态
	 * @param params
	 * @throws Exception
	 */
	public void updateAuditDataOrign(Map<String, String> params) throws Exception;
	
	/**
	 * 查询映射表
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryCodeMapping(String domain) throws Exception;
	
	/**
	 * 一点收费增量稽核保存表
	 * @param map
	 * @throws Exception
	 */
	public void saveYdsfIncrAudit(Map<String,String> map) throws Exception;
	/**
	 * 一点收费全量稽核如表
	 * @param map
	 * @throws Exception
	 */
	public void saveYdsfWholeAudit(Map<String,String> map) throws Exception;
	
	/**
	 * 一点收费Item表
	 * @param map
	 * @throws Exception
	 */
	public void saveYdsfChargeIncrItem(Map<String,String> map) throws Exception;
	
	/**
	 * 根据号码查询一点收费全量表的信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> queryYdsfWholeAudit(Map<String,String> map) throws Exception;
	
    public Map<String,String> queryWholeYdsfByNbr(Map<String,String> map) throws Exception;

    /**
     * 更新一点收费稽核表
     * @param param
     */
	public void updateYdsfWholeAudit(HashMap<String, String> param);
	
	/**
	 * 带宽型稽核相关 存储数据
	 * @param map
	 */
	public void saveDdnAuditDataAdd(Map<String,String> map);
	
	/**
	 * 查询带宽型增量表
	 * @return
	 */
	public List<Map<String,String>> queryDdnAuditAdd();
	
	/**
	 * 查询省内购物车ID
	 * @param tranId
	 * @return
	 */
	public String queryOlIdByTranId(String tranId);
	
	/**
	 * 查询是否已经存在
	 * @param olId
	 * @return
	 */
	public Integer queryCntD2bOrderOffer(String olId);
	
	/**
	 * 插入稽核表
	 * @param param
	 */
	public void insertD2bOrderOffer(Map<String,String> param);
	
	/**
	 *  集团电渠订单给计费的信息稽核
	 * @param offerNbr
	 * @return
	 */
	public String queryOfferNbr(String offerNbr);

	/**
	 * 查询集团订单费用稽核表记录数
	 * @param sqlParam
	 * @return
	 */
	public int queryOrderChargeCount(HashMap<String, String> sqlParam);

	/**
	 * 插入集团订单费用稽核表
	 * @param param
	 */
	public void insertOrderCharge(Map<String, String> param);

	/**
	 * 更新集团订单费用稽核
	 * @param aram
	 */
	public void updateOrderChargeAudit(HashMap<String, String> param);

	/**
	 * 查询订单费用稽核表费用
	 * @param sqlParam
	 * @return
	 */
	public String queryOrderChargeAmount(HashMap<String, String> sqlParam);

	/**
	 * 插入稽核错误日志表
	 * @param param
	 */
	public void insertAuditFailLog(Map<String, String> param);

	/**
	 * 新增订单信息稽核
	 * @param param
	 */
	public void insertOrderInfoAudit(Map<String, String> param);
	
	/**
	 * 查询电渠订单信息
	 * @param insertParam
	 * @return
	 */
	public int queryOrderInfoAudit(Map<String, String> insertParam);
	
	/**
	 * 3G产品状态激活稽核
	 * @param inserParam
	 */
	public void insertActiveAudit(Map<String, String> inserParam);

	/**
	 * 保存4G 订单信息稽核表
	 * @param insertParam
	 */
	public void insertOrderInfoAudit4G(Map<String, String> insertParam);

	/**
	 * 4G 订单信息稽核表 查询个数判断记录是否存在
	 * @param qCountParam
	 * @return
	 */
	public int queryOrderChargeCount4G(Map<String, String> qCountParam);

	/**
	 * 4G 保存订单费用稽核
	 * @param insertParam
	 */
	public void insertOrderCharge4G(Map<String, String> insertParam);

	/**
	 * 4G 查询订单费用稽核表费用
	 * @param sqlParam
	 * @return
	 */
	public String queryOrderChargeAmount4G(Map<String, String> qAmountParam);

	/**
	 * 更新4G集团订单费用稽核
	 * @param aram
	 */
	public void updateOrderChargeAudit4G(Map<String, String> uAmountParam);
	
	/**
	 * 4G号码激活查询
	 * @param phoneNumer
	 * @return
	 */
	public int queryActiveAudit4G(String phoneNumer);

	/**
	 * 4G产品状态激活稽核
	 * @param inserParam
	 */
	public void insertActiveAudit4G(Map<String, String> inserParam);

	/**
	 * 产品实例日稽核表
	 * @param insertParam 
	 */
	public void insertDepProdDailyAudit(Map<String, String> insertParam);

	/**
	 * 产品实例月稽核表
	 * @param insertParam 
	 */
	public void insertDepProdMonthAudit(Map<String, String> insertParam);

	/**
	 * 查询产品日稽核表
	 * @param queryParam
	 * @return
	 */
	public String queryProdDailyAudit(Map<String, String> queryParam);

	/**
	 * 查询产品月稽核表
	 * @param queryParam
	 * @return
	 */
	public String queryProdMonthAudit(Map<String, String> queryParam);

	/**
	 * 更新产品日稽核表
	 * @param updateParam
	 */
	public void updateProdDailyAudit(Map<String, String> updateParam);

	/**
	 * 更新产品月稽核表
	 * @param updateParam
	 */
	public void updateProdMonthAudit(Map<String, String> updateParam);

	/**
	 * 查询产品名称
	 * @param params
	 * @return
	 */
	public Map<String, String> queryAccProductName(Map<String, String> params);

	/**
	 * 查询产品资源信息
	 * @param params
	 * @return
	 */
	public Map<String, String> getProdResInfo(Map<String, String> params);

	/**
	 * 查询产品日稽核是否有相同的记录
	 * @param queryParams
	 * @return
	 */
	public String queryDepOfferDailyAudit4G(Map<String, String> queryParams);

	/**
	 * 插入销售品日稽核表
	 * @param insertParam
	 */
	public void insertDepOfferDailyAudit(Map<String, String> insertParam);

	/**
	 * 插入销售品月稽核表
	 * @param insertParam
	 */
	public void insertDepOfferMonthAudit(Map<String, String> insertParam);

	/**
	 * 插入稽核错误日志
	 * @param errParams
	 */
	public void insertAuditErrLog(Map<String, String> errParams);

	/**
	 * 查询销售品月稽核表
	 * @param queryParams
	 * @return
	 */
	public String queryDepOfferMonthAudit4G(Map<String, String> queryParams);

	/**
	 * 更新产品日稽核表
	 * @param insertParam
	 */
	public void updateDepOfferDailyAudit(Map<String, String> insertParam);

	/**
	 * 更新产品月稽核表
	 * @param insertParam
	 */
	public void updateDepOfferMonthAudit(Map<String, String> insertParam);

	/**
	 * 删除国际漫游稽核
	 * @param params
	 */
	public void delIntalRoamAudit(Map<String, String> params);
	
	/**
	 * 新增国际漫游稽核
	 * @param params
	 */
	public void insertIntalRoamAudit(Map<String, String> params);

	/**
	 * 更新国际漫游稽核
	 * @param params
	 */
	public void updateIntalRoamAudit(Map<String, String> params);
	
	/**
	 * 国际漫游，通过mdn查询
	 * @param params
	 * @return
	 */
	public String queryIntalRoamAudit(Map<String, String> params);

	/**
	 * 协同通信稽核
	 * @param params
	 */
	public void saveXttxAudit(Map<String, String> params);

	/**
	 * 删除协同通信稽核
	 * @param params
	 */
	public void delXttxAudit(Map<String, String> params);

	/**
	 * 通过extOfferInstId查询effDate,expDate
	 * @param extOfferInstId
	 * @return
	 */
	public Map<String, String> queryEffDateByExtOfferInstId(String extOfferInstId);

	/**
	 * 通过phoneNumber，extProdInstId查询beginRentTime，finishTime
	 * @param paramRentTime
	 * @return
	 */
	public Map<String, String> queryBeginRentTime(Map<String, String> paramRentTime);

	/**
	 * 通过服务实例ID查busCode和serviceCode
	 * @param saopSrvcInstId
	 * @return
	 */
	public Map<String, String> querySaopAuditBusServicecode(String saopSrvcInstId);

	/**
	 * 更新ddn_audit_data_add表的prodInstId
	 * @param updateParams
	 */
	public void updateDdnAuditDataAdd(Map<String, String> updateParams);

	/**
	 * 根据ext_prod_inst_id 查prod_id 
	 * @param params
	 */
	public void queryProdIdByPExt(Map<String, String> params);

	/**
	 * 删除DDN_AUDIT
	 */
	public void deleteDdnAudit();

	/**
	 * 删除DDN_AUDIT_DATA_ADD
	 * @param string
	 */
	public void deleteDdnAuditDataAdd(String string);

	/**
	 * DDN_AUDIT_DATA_ADD 移到 DDN_AUDIT表里
	 */
	public void insertDdnAudit();

	/**
	 * 爱音乐cep_prod_lx 删除
	 */
	public void deleteAyyAudit(Map<String, String> params);

	/**
	 * 爱音乐cep_prod_lx 新增
	 */
	public void insertAyyAudit(Map<String, String> params);

	/**
	 * 爱音乐cep_prod_lx 更新
	 */
	public void updateAyyAudit(Map<String, String> params);

	/**
	 * 终端补贴日增上传稽核
	 */
	public Map<String, String> queryDataTerminalCode(Map<String, String> param);

	/**
	 * 终端补贴日增上传稽核
	 */
	public void insertCepAuditTerminalDay(Map<String, String> param);

	/**
	 * 通过auditOriginDataId 查稽核来源数据表的其他字段
	 * @param auditOriginDataId 稽核数据来源ID
	 * @return
	 */
	public AuditDataOrignEntity queryAuditDataOrignEntity(String auditOriginDataId);

	/**
	 * 通过logid查询正式单交互报文
	 * @param saopLogId
	 * @return
	 */
	public Object getXmlByLogid(String saopLogId);

	/**
	 * 数据抽取成功修改稽核数据来源表状态为C7
	 * @param auditOriginDataId
	 */
	public void updateAuditDataOrign(String auditOriginDataId);

	/**
	 * 保存C网稽核数据
	 * @param params
	 */
	public void saveCProdAudit(Map<String, String> params);

	/**
	 * 更新c网稽核数据
	 * @param params
	 */
	public void updateCProdAudit(Map<String, String> params);

	/**
	 * 删除c网稽核数据
	 * @param params
	 */
	public void delCProdAudit(Map<String, String> params);
	
	/**
	 * 查询c网稽核数据
	 * @param params
	 * @return
	 */
	public int queryCProdAudit(Map<String, String> params);

	/**
	 * 查询订单信息稽核单号是否存在
	 * @param orderId
	 * @return
	 */
	public int queryOrderInfoCount4G(String orderId);

	/**
	 * 云卡日稽核更新
	 * @param params
	 */
	public void updateCloudcardDay(Map<String, String> params);

	/**
	 * 云卡日稽核新增
	 * @param params
	 */
	public void insertDepCloudDayAudit(Map<String, String> params);
	
	/**
	 * 云卡日稽核查询是否存在
	 * @param params
	 */
	public String queryCntCloudcardDay(String phoneNbr);

	/**
	 * 云卡月稽核更新
	 * @param params
	 */
	public void updateCloudcardMonth(Map<String, String> params);

	/**
	 * 云卡月稽核新增
	 * @param params
	 */
	public void insertDepCloudMonthAudit(Map<String, String> params);

	/**
	 * 云卡月稽核查询是否存在
	 * @param params
	 */
	public String queryCntCloudcardMonth(String phoneNbr);

	/**
	 * 集团卡日同步新增
	 * @param insertParams
	 */
	public void saveDepCardDailySyn(Map<String, Object> insertParams);
	
	/**
	 * 政企查询
	 * @param extProdInstId
	 */
	public String queryInternetBusProdAudit(String extProdInstId);

	/**
	 * 政企新增
	 * @param params
	 */
	public void saveInternetBusProdAudit(Map<String, String> params);

	/**
	 * 政企更新
	 * @param params
	 */
	public void updateInternetBusProdAudit(Map<String, String> params);

	/**
	 * 统一的稽核错误日志记录
	 * @param errorParam
	 */
	public void insertUniteAuditFailLog(Map<String, String> errorParam);

	/**
	 * 查询稽核开关状态
	 * @param beanName
	 * @return
	 */
	public String queryAuditSwitch(String beanName);

	/**
	 * 终端补贴串码信息定期上传
	 * @param param
	 */
	public void insertCepAuditTerminalAll(Map<String, String> param);

	/**
	 * IVPN总量稽核
	 * @param param
	 */
	public void insertIvpnAllAudit(Map<String, String> param);

	/**
	 * 新增EXT_PROD_INST_VOLTE_ID
	 * @param paras
	 */
	public void updateProdMonVotel(Map<String, String> paras);
	
}
