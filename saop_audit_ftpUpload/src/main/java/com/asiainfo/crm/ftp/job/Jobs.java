package com.asiainfo.crm.ftp.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.asiainfo.crm.ftp.dao.ISaopFtpDao;
import com.asiainfo.crm.ftp.smo.ISaopFtpSmo;
import com.asiainfo.crm.ftp.smo.LteCardResAudit;
import com.asiainfo.crm.ftp.smo.LtePhoneNumberAudit;

/**
 * 各种定时调度任务
 * 
 * @author yong
 *
 */
public class Jobs {

	@Autowired
	ISaopFtpSmo saopFtpSmo;

	@Autowired
	ISaopFtpDao saopFtpDao;

	@Autowired
	LtePhoneNumberAudit ltePhoneNumberAudit;

	@Autowired
	LteCardResAudit lteCardResAudit;

	// 4G订单信息稽核
	public void orderInfoAudit4G() {
		saopFtpSmo.execute("BUS60016");
	}

	// 4G订单费用稽核
	public void orderchargeAudit4G() {
		saopFtpSmo.execute("BUS60017");
	}

	// 4G产品实例日稽核
	public void prodInstDailyAudit4G() {
		saopFtpSmo.execute("BUS60024");
	}

	// 4G产品实例月稽核
	public void prodInstMonthAudit4G() {
		saopFtpSmo.execute("BUS60025");
	}

	// 4G销售品实例日稽核
	public void offerDailyAudit4G() {
		saopFtpSmo.execute("BUS60026");
	}

	// 4G销售品实例月稽核
	public void offerMonthAudit4G() {
		saopFtpSmo.execute("BUS60027");
	}

	// 4G产品状态激活
	public void prodActivationAudit4G() {
		saopFtpSmo.execute("BUS60018");
	}
	
	// 4G卡号
	public void phoneNumberAudit() {
		ltePhoneNumberAudit.dataExtraction();
	}

	// 4G卡资源
	public void cardResourceAudit() {
		lteCardResAudit.dataExtraction();
	}
	// c网稽核
	public void cprodAudit() {
		saopFtpSmo.execute("BUS24001");
	}

	// 省crm受理集团卡日同步稽核
	public void depCardDailySynAudit() {
		saopFtpSmo.execute("BUS80009");
	}

	// 3G订单信息稽核
	public void orderInfoAudit3G() {
		saopFtpSmo.execute("BUS36007");
	}

	// 3G订单信息稽核
	public void orderChargeAudit3G() {
		saopFtpSmo.execute("BUS36008");
	}

	// 3G产品状态激活稽核
	public void prodActivationAudit3G() {
		saopFtpSmo.execute("BUS36009");
	}

	// 云卡日稽核
	public void cloudCardDailyAudit() {
		saopFtpSmo.execute("BUS37050");
	}

	// 云卡月稽核
	public void cloudCardMonthlyAudit() {
		saopFtpSmo.execute("BUS37051");
	}

	// 一点收费全量稽核
	public void ydsfWholeAudit() {
		saopFtpSmo.execute("BUS23001");
	}

	// IVPN全量稽核
	public void ivpnAllAudit() {
		saopFtpSmo.execute("BUS23001_1");
	}

	// IVPN总机服务增量稽核
	public void ivpnDailyAudit() {
		saopFtpSmo.execute("BUS23001_2");
	}

	// 国际漫游
	public void gcAudit() {
		saopFtpSmo.execute("BUS36001");
	}

	// 终端补贴
	public void terminalDailyAudit() {
		saopFtpSmo.execute("BUS16102");
	}

	// 终端补贴销售串码信息定时上传
	public void terminalAllAudit() {
		saopFtpSmo.execute("BUS16108");
	}

	// 政企业务
	public void governmentEnterpriseAudit() {
		saopFtpSmo.execute("BUS50004");
	}

	// 带宽型
	public void ddnAudit() {
		saopFtpSmo.execute("BUS35000");
	}

	// uim卡上传
	public void uimCardAudit() {
		saopFtpSmo.execute("BUS37057");
	}

	// 号码资源增量
	public void numberResourceAddAudit() {
		saopFtpSmo.execute("BUS37010");
	}

	// 将错误为unique constraint侦听调用失败的数据刷为D状态 重新跑
	public void refreshListenAudit() {
		saopFtpDao.refreshListenAudit();
	}
}
