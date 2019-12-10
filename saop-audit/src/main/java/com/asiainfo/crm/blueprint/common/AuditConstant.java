package com.asiainfo.crm.blueprint.common;

public class AuditConstant {
	// 集团4G订单的业务码
		public static final String TLE_DEP_ORDER_AUDIT_BUSCODE = "BUS80001,BUS80004";
		// 集团4G订单的服务码
		public static final String TLE_DEP_ORDER_AUDIT_SVCCODE = "SVC80003,SVC80004,SVC80011,SVC80027,SVC80037,SVC80032,SVC80074";
		// 集团4G产销品稽核集团下发，集团4G集约系统(含集团电渠通过4G集约系统下发的订单)所有正式单
		public static final String TLE_DEP_NEW_AUDITS_DOWN_SVCCODE = "SVC80003,SVC80037,SVC80032,SVC80074";
		// 集团4G产销品稽核集团下发，集团4G集约系统(含集团电渠通过4G集约系统下发的订单)所有正式单
		public static final String TLE_DEP_NEW_AUDITS_DOWN_BUSCODE = "BUS80001,BUS80004";
		// 集团4G报竣
		public static final String TLE_DEP_AUDITS_BJ_SVCCODE = "SVC80015";
		// 集团4G产销品稽核省内上传（停复机，活卡激活，欠费拆机）
		public static final String TLE_DEP_NEW_AUDITS_UP_SVCCODE = "SVC80017,SVC80035";
		// 集团4G产销品稽核省内上传（停复机，活卡激活，欠费拆机）
		public static final String TLE_DEP_NEW_AUDITS_UP_BUSCODE = "BUS80001";
		// 国际漫游
		public static final String GC_AUDIT_UP_BUSCODE = "BUS13003";
		// 国际漫游
		public static final String GC_AUDIT_UP_SVCCODE = "SVC13001";
		// 终端补贴
		public static final String TERMINAL_AUDIT_UP_BUSCODE = "BUS16002";
		// 终端补贴
		public static final String TERMINAL_AUDIT_UP_SVCCODE = "SVC16021";
		// 电渠
		public static final String ELECTRONICCHANNEL_AUDIT_UP_BUSCODE = "BUS33001";
		// 电渠
		public static final String ELECTRONICCHANNEL_AUDIT_UP_SVCCODE = "SVC33006";
		// 云卡
		public static final String CLOUDCARD_AUDITS_UP_BUSCODE = "BUS33001";
		// 云卡
		public static final String CLOUDCARD_AUDITS_UP_SVCCODE = "SVC14003";
		// c网
		public static final String CCARD_AUDITS_UP_BUSCODE = "BUS13001";
		// c网
		public static final String CCARD_AUDITS_UP_SVCCODE = "SVC13001";
		// 一点收费报竣
		public static final String YDSF_AUDITS_BJ_BUSCODE = "BUS41000";
		// 一点收费报竣
		public static final String YDSF_AUDITS_BJ_SVCCODE = "SVC33030";
		// 政企业务
		public static final String GOVERNMENTENTERPRISE_AUDITS_UP_BUSCODE = "BUS50001,BUS50002,BUS50003";
		// 政企业务
		public static final String GOVERNMENTENTERPRISE_AUDITS_UP_SVCCODE = "SVC50001";
		// IVPN
		public static final String IVPN_AUDITS_UP_BUSCODE = "SVC14001";
		// IVPN
		public static final String IVPN_AUDITS_UP_SVCCODE = "SVC14001";
		// 带宽型
		public static final String DDN_AUDITS_UP_BUSCODE = "BUS35003";
		// 带宽型
		public static final String DDN_AUDITS_UP_SVCCODE = "SVC33006";

		// 0表示开关状态为开启,表示文件没在上传
		public static final String AUDIT_OPEN = "0";

		// 1表示开关状态为关闭,表示文件正在上传中,不能解析报文
		public static final String AUDIT_CLOSE = "1";
		
		//当前报文的流水号小于已存在的流水号，不能更新数据
		public static final String NOT_UPDATE_DATA = "当前报文的流水号不大于已存在的流水号，不能更新数据。";
}
