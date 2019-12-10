package com.asiainfo.crm.ftp.common;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author 周勇
 *
 */
public class XmlUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);
	
	public static  String getTextByDom(Document doc,String xmlPath){
		Node node = doc.selectSingleNode(xmlPath);
		return (node == null) ? "" : node.getText(); //获取code，根据不同的code调用不同的接口
	}
	
	public static Document parseXml(String xml) throws Exception{
		try {
			return DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			logger.error("解析xml异常",e);
			throw new Exception("解析xml异常"+e.getMessage());
		}
	}
}
