package com.asiainfo.crm.blueprint.intf;

import java.util.List;

import org.springframework.httpservice.annotation.Center;

import com.asiainfo.crm.blueprint.model.OrdProdResInstRel;
import com.asiainfo.crm.blueprint.model.QryOrdUimReqVo;


@Center("so_order")
public interface IOrderQuerySmo {

	/**
     * 根据apply_obj_spec和service_offer_id查询相关的uim卡的关例信息
     * @param oprir
     * @return
     */
    public List<OrdProdResInstRel> qryOrdUimInstRel(QryOrdUimReqVo reqVo);
}
