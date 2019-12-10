package com.asiainfo.crm.blueprint.intf;


import org.springframework.httpservice.annotation.Center;

/**
 * 资产辅助函数查询SMO.
 * @author 陈超
 *
 */
@Center("inst_intf")
public interface IInstIntfQuerySmo {

	/**
	 * 通过外部编码获取产品实例ID.
	 * 1.用于保存产品实例类操作,此时，客户端并不知道CRM内部是否存在相应的产品实例。
	 * 2.该产品实例必须是集团产品实例。
	 * 3.如果能够根据EXT_PROD_INST_ID找到，那么返回EXT_PROD_INST_ID对应的内部产品实例ID，如果找不到，那么返回PROD_INST_ID
	 * 4.举例说明 getProdInstIdByExtId(200000, $-1000$)
	 * @param lanId 本地网ID
	 * @param areaNbr 区号
	 * @param extProdInstId 集团产品实例ID
	 * @param prodInstId 虚拟产品实例ID
	 * @return 产品实例ID
	 */
	public String getProdInstIdByExtId(String lanId, String areaNbr, String extProdInstId, String prodInstId);
	
	/**
	 * 通过外部编码获取销售品实例ID.
	 * 1.用于保存销售品实例类操作,此时，客户端并不知道CRM内部是否存在相应的销售品实例。
	 * 2.该销售品实例必须是销售产品实例。
	 * 3.如果能够根据EXT_PROD_OFFER_INST_ID找到，那么返回EXT_PROD_OFFER_INST_ID对应的内部产品实例ID，如果找不到，那么返回PROD_OFFER_INST_ID
	 * 4.举例说明 getProdOfferInstIdByExtId(1101, 010, 200000, $-1000$)
	 * @param lanId 本地网ID
	 * @param areaNbr 区号
	 * @param extProdOfferInstId 集团销售品实例ID
	 * @param prodOfferInstId  虚拟产品实例ID
	 * @return 销售品实例ID
	 */
	public String getProdOfferInstIdByExtId(String lanId, String areaNbr, String extProdOfferInstId,
			String prodOfferInstId);
	
	/**
	 * 通过号码获取相应产权客户ID.
	 * @param lanId 本地网ID
	 * @param areaNbr 区号
	 * @param accNbr 号码
	 * @param prodClass 产品大类【固话、小灵通、宽带、移动手机、虚拟帐号】
	 * @return 产权客户ID
	 */
	public String getCustIdByAccNbr(String lanId, String areaNbr, String accNbr, String prodClass);

}
