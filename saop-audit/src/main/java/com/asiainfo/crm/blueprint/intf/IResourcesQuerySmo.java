package com.asiainfo.crm.blueprint.intf;

import org.springframework.httpservice.annotation.Center;

import com.asiainfo.crm.blueprint.model.UIMResourceRespVo;
import com.asiainfo.crm.blueprint.model.UimResourceReqVo;

@Center("so_sr")
public interface IResourcesQuerySmo {
	/**
	 * 查询终端串码信息.
	 * @param uimResourceReqVo 对象
	 * @return UIMResourceRespVo 对象
	 */
	public UIMResourceRespVo getTerminalDevice(UimResourceReqVo uimResourceReqVo);
	
	public String qryDevNumByTerminalCode(String inputJsonStr);
}
