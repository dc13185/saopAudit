package com.asiainfo.crm.blueprint.smo;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.asiainfo.crm.blueprint.common.CallCenterServiceUtil;

public class XttxTest {

	public static void main(String[] args){
		String userType = "100";
		String reqJson = "{\"requestObject\": {\"prodInstId\": \"272192349345\"}}";
		Object object = getValueByJPath("$.resultObject.offerProdInstRels", reqJson, "http://192.168.1.60:9010/inst-service/service/cust_inst_getOfferProdInstRelInfo");
        System.out.println(object.toString());
		List<Map<String, ?>> offerProdInstRels = (List<Map<String, ?>>)object;
        for(Map<String, ?> offerProdInstRel : offerProdInstRels){
        	//offerId=300509001807，userType=16
        	System.out.println(offerProdInstRel.get("offerId").toString());
        	if( "300509031305".equals(offerProdInstRel.get("offerId").toString())){
        		userType = "16";
        		break;
			}
        }
        System.out.println(userType);
	}
	
	private static Object getValueByJPath(String path, String reqJson, String url) {
		JSONObject jsonResult = CallCenterServiceUtil.getResultByCenterService(url, reqJson);
		Object object = JSONPath.eval(jsonResult, path);
		return object;
	}
}
