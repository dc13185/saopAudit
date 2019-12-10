package com.asiainfo.crm.blueprint.intf;

import org.springframework.httpservice.annotation.Center;

import com.asiainfo.crm.audit.vo.offerinst.QryOfferInstDetaiListlReqVo;
import com.asiainfo.crm.audit.vo.offerinst.QryOfferInstDetailListRspVo;
import com.asiainfo.crm.audit.vo.offerinst.QryOfferProdInstRelListReqVo;
import com.asiainfo.crm.audit.vo.offerinst.QryOfferProdInstRelRspVo;
import com.asiainfo.crm.audit.vo.prodinst.ProdInstListMutilRsp;
import com.asiainfo.crm.audit.vo.prodinst.QryAccProdInstDetailReq;
import com.asiainfo.crm.audit.vo.prodinst.QryAccProdInstDetailRsp;
import com.asiainfo.crm.audit.vo.prodinst.QryFuncProdInstDetailListReqVo;
import com.asiainfo.crm.audit.vo.prodinst.QryFuncProdInstDetailListRspVo;
import com.asiainfo.crm.audit.vo.prodinst.QryProdInstRelReqVo;
import com.asiainfo.crm.audit.vo.prodinst.QryProdInstRelRspVo;
import com.asiainfo.crm.audit.vo.prodinst.QueryProdInstMutilReqVo;

@Center("cust_inst")
public interface IQueryProdInstMutilSmo {
	/**
	 * 查询接入类产品实例详情.
	 * 产品实例起租时间、拆机时间、产品状态(INST_QRY_00008查询接入类实例详情)
	 * @param reqVo
	 *            对象
	 * @return response
	 */
	public QryAccProdInstDetailRsp qryAccProdInstDetail(QryAccProdInstDetailReq req);
	
	/**
	 * INST_QRY_00068查询接入类产品实例列表历史
	 */
	public ProdInstListMutilRsp qryHisAccProdInstListLocal(QueryProdInstMutilReqVo queryProdInstMutilReqVo);
	
	/**
	 * 查询销售品实例详情
	 * INST_COMM_QRY_00001_查询销售品实例详情 ---  销售品外部实例id查客户id的
	 * @param reqVo
	 *            对象
	 * @return response
	 */
	public QryOfferInstDetailListRspVo qryOfferInstDetail(QryOfferInstDetaiListlReqVo reqVo);
	
	/**
	 * 通过条件查询产品实例关系详情列表.
	 * INST_QRY_00012查询产品实例关系列表(不带A、Z端信息) --- 带宽型稽核      组合产品ID查询查询prod_ID
	 * @param reqVo
	 *            对象
	 * @return response
	 */
	public QryProdInstRelRspVo qryProdInstRelList(QryProdInstRelReqVo reqVo);
	
	/**
	 * 获取销售品产品实例关系列表.
	 * INST_COMM_QRY_00008_查询销售品实例列表 ---  协同通信
	 * @param reqVo
	 *            对象
	 * @return response
	 */
	public QryOfferProdInstRelRspVo getOfferProdInstRelInfo(QryOfferProdInstRelListReqVo reqVo);
	
	/**
	 * 支撑查询反馈多条功能类产品实例详情.
	 * 
	 * @param reqVo
	 *            对象
	 * @return response
	 */
	public QryFuncProdInstDetailListRspVo qryFuncProdInstDetailList(QryFuncProdInstDetailListReqVo reqVo);
	
}
