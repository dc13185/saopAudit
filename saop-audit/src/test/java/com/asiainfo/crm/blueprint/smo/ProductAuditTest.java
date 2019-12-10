package com.asiainfo.crm.blueprint.smo;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;

import com.asiainfo.angel.utils.ObjectUtils;
import com.asiainfo.crm.bcomm.BCommBoot;
import com.asiainfo.crm.blueprint.common.XmlUtil;
import com.asiainfo.crm.blueprint.dao.ISaopAuditDao;

public class ProductAuditTest {

	public static void main(String[] args) {
		/*BCommBoot.initAngel();
    	try {
    		ApplicationContext ctx = ObjectUtils.getApplicationContext();

    		ISaopAuditDao saopAuditDao = (ISaopAuditDao) ctx.getBean("saopAuditDao");

    		ISaopAuditSmo saopAuditSmo = (ISaopAuditSmo) ctx.getBean("saopAuditSmo");
    		
    		String bjXml = getXml("src/test/resources/测试报文/报竣报文.xml");
    		
    		String inXml = getXml("src/test/resources/测试报文/集团下发报文.xml");
    		
    		saopAuditSmo.depNewAudits4G(inXml, bjXml, "110", null);;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    	
    	String bjXml = getXml("C:/Users/Lenovo/Desktop/稽核/协同通信/入参报文样例.xml");
        try {
			//System.out.println(XmlUtil.parseXml(bjXml).selectNodes("./GcInfo[ProdSpecId='2' or ProdSpecId='415' or ProdSpecId='379']"));
        	Document document = XmlUtil.parseXml(bjXml);
        	List<Node> nodes1 = document.selectNodes(".././GcInfo[ProdSpecId='2' or ProdSpecId='864' or ProdSpecId='379']");
			Element rootElmt = XmlUtil.parseXml(bjXml).getRootElement();
			List<Node> nodes = rootElmt.selectNodes("./.././GcInfo[ProdSpecId='2' or ProdSpecId='864' or ProdSpecId='379']");
			
			for (Node node : nodes1) {
				System.out.println(node.getText());
			}
			//System.out.println(rootElmt.asXML());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String getXml(String path) {
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
