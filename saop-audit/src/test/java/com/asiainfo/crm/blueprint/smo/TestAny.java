package com.asiainfo.crm.blueprint.smo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.asiainfo.angel.utils.ObjectUtils;
import com.asiainfo.crm.blueprint.BaseTest;
import com.asiainfo.crm.blueprint.dao.ISaopAuditDao;



public class TestAny extends BaseTest{
	
	@Autowired
	Map<String, ISaopAuditSmo> saopDataAuditSmoMap;

	@Test
	public void test() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		System.out.println(sdf.format(new Date()));
	}
	
	@Test
	public void saopDataAuditSmoMapTest(){
		ISaopAuditSmo smo = saopDataAuditSmoMap.get("depNewAudits4G");
		System.out.println(smo.getClass().getName());
		
	}
	
}
