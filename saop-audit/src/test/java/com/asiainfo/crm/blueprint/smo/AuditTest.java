package com.asiainfo.crm.blueprint.smo;

import java.io.File;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.asiainfo.angel.utils.ObjectUtils;
import com.asiainfo.crm.audit.vo.prodinst.QryAccProdInstDetailReq;
import com.asiainfo.crm.audit.vo.prodinst.QryAccProdInstDetailRsp;
import com.asiainfo.crm.bcomm.BCommBoot;
import com.asiainfo.crm.blueprint.BaseTest;
import com.asiainfo.crm.blueprint.intf.IInstIntfQuerySmo;
import com.asiainfo.crm.blueprint.intf.IQueryProdInstMutilSmo;
import com.asiainfo.crm.blueprint.model.AuditInputEntity;


public class AuditTest extends BaseTest{
	
	@Autowired
	IQueryProdInstMutilSmo custIntfQuerySmo;
	
	@Autowired
	Map<String, ISaopAuditSmo> auditSmoMap;
	
	@Autowired
	IInstIntfQuerySmo instIntfQuerySmo;
	
	@Test
	public void testQryProdInstRelList(){
		QryAccProdInstDetailReq req = new QryAccProdInstDetailReq();
		req.setProdInstId(1000L);
		QryAccProdInstDetailRsp rsp = custIntfQuerySmo.qryAccProdInstDetail(req);
		System.out.println(rsp.getProdInstDetail().getBeginRentDate());
	}
	
	@Test
	public void testProdInstIdByExtId(){
		String prodInstId2 = instIntfQuerySmo.getProdInstIdByExtId("", "", "", "");
		System.out.println(prodInstId2);
	}
	
	@Test
	public void testCustIdByAccNbr(){
		String custId = instIntfQuerySmo.getCustIdByAccNbr("", "", "17781211359", "");
		System.out.println(custId);
	}
	
	@Test
	public void testDdnAuditAdd() throws Exception{
		String inXml = getXml("src/test/resources/测试报文/带宽型稽核.xml");
		AuditInputEntity auditInfo = new AuditInputEntity();
		auditInfo.setBuscode("BUS35001");
		auditInfo.setCreateTime("20170101121212");
		auditInfo.setDownXml(inXml);
		ISaopAuditSmo saopDataAuditSmo = auditSmoMap.get("BUS35000");
		saopDataAuditSmo.dataExtraction(auditInfo);
	}
	
	public static void main(String[] args) throws Exception{
		BCommBoot.initAngel();
    	ApplicationContext ctx = ObjectUtils.getApplicationContext();
		String inXml = getXml("src/test/resources/测试报文/带宽型稽核.xml");
		AuditInputEntity auditInfo = new AuditInputEntity();
		auditInfo.setBuscode("BUS35001");
		auditInfo.setCreateTime("20170101121212");
		auditInfo.setDownXml(inXml);
		ISaopAuditSmo saopDataAuditSmo = (ISaopAuditSmo) ctx.getBean("BUS35000");
		saopDataAuditSmo.dataExtraction(auditInfo);
	}
	
	@Test
	public void depNewAudits4GTest() throws Exception{
		String bjXml = getXml("src/test/resources/测试报文/报竣报文.xml");
		String inXml = getXml("src/test/resources/测试报文/集团下发报文.xml");
		AuditInputEntity auditInfo = new AuditInputEntity();
		auditInfo.setBuscode("110");
		auditInfo.setCreateTime("20170101121212");
		auditInfo.setReportXml(bjXml);
		auditInfo.setDownXml(inXml);
		ISaopAuditSmo saopDataAuditSmo = auditSmoMap.get("depNewAudits4G");
		saopDataAuditSmo.dataExtraction(auditInfo);
	}
	
	@Test
	public void depNewAudits4GTest1() throws Exception{
		String bjXml = getXml("src/test/resources/测试报文/保持接入产品报竣报文.xml");
		
		String inXml = getXml("src/test/resources/测试报文/保持接入产品集团下发报文.xml");
		
		AuditInputEntity auditInfo = new AuditInputEntity();
		auditInfo.setBuscode("110");
		auditInfo.setCreateTime("20170101121212");
		auditInfo.setReportXml(bjXml);
		auditInfo.setDownXml(inXml);
		ISaopAuditSmo saopDataAuditSmo = auditSmoMap.get("depNewAudits4G");
		saopDataAuditSmo.dataExtraction(auditInfo);
	}
	
	@Test
	public void depNewAudits4GTest2() throws Exception{
		String bjXml = getXml("src/test/resources/测试报文/新增产品报竣报文.xml");
		
		String inXml = getXml("src/test/resources/测试报文/新增产品集团下发报文.xml");
		
		AuditInputEntity auditInfo = new AuditInputEntity();
		auditInfo.setBuscode("110");
		auditInfo.setCreateTime("20170101121212");
		auditInfo.setReportXml(bjXml);
		auditInfo.setDownXml(inXml);
		ISaopAuditSmo saopDataAuditSmo = auditSmoMap.get("depNewAudits4G");
		saopDataAuditSmo.dataExtraction(auditInfo);
	}
	
	@Test
	public void depNewAudits4GTest3() throws Exception{
		String bjXml = getXml("src/test/resources/测试报文/修改产品报竣报文.xml");
		
		String inXml = getXml("src/test/resources/测试报文/修改产品集团下发报文.xml");
		
		AuditInputEntity auditInfo = new AuditInputEntity();
		auditInfo.setBuscode("110");
		auditInfo.setCreateTime("20170101121212");
		auditInfo.setReportXml(bjXml);
		auditInfo.setDownXml(inXml);
		ISaopAuditSmo saopDataAuditSmo = auditSmoMap.get("depNewAudits4G");
		saopDataAuditSmo.dataExtraction(auditInfo);
	}
	
	@Test
	public void depNewAudits4GTest4() throws Exception{
		
		String inXml = getXml("src/test/resources/测试报文/产品实例稽核集团下省报文2.xml");
		
		AuditInputEntity auditInfo = new AuditInputEntity();
		auditInfo.setBuscode("110");
		auditInfo.setCreateTime("20170101121212");
		auditInfo.setDownXml(inXml);
		ISaopAuditSmo saopDataAuditSmo = auditSmoMap.get("depNewAudits4G");
		saopDataAuditSmo.dataExtraction(auditInfo);
	}
	
	@Test
	public void depNewAudits4GTest5() throws Exception{
		
		String inXml = getXml("src/test/resources/测试报文/产品实例稽核集团下发报文2.xml");
		
		AuditInputEntity auditInfo = new AuditInputEntity();
		auditInfo.setBuscode("110");
		auditInfo.setCreateTime("20170101121212");
		auditInfo.setDownXml(inXml);
		ISaopAuditSmo saopDataAuditSmo = auditSmoMap.get("depNewAudits4G");
		saopDataAuditSmo.dataExtraction(auditInfo);
	}
	
	public static String getXml(String path) {
		File f = new File(path);
    	SAXReader reader = new SAXReader();
    	Document doc = null;
		try {
			doc = reader.read(f);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	String xml = doc.asXML();
		return xml;
	}
}
