package com.asiainfo.crm.audit.model.tmptime;

import com.asiainfo.crm.bcomm.base.bmo.bo.BaseEntity;

import java.util.Date;

/**
 *
 * @author jianghao
 *
 */
public class TmpTimeOfferObjRelTurn extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 记录处理的实例ID
     */
    private Long instId;

    /**
     * 记录处理的实例表
     */
    private Integer type;

    /**
     * 记录对应的地区
     */
    private Long regionId;

    /**
     * 记录处理的状态
     */
    private String statusCd;

    /**
     * 记录数据的生成时间
     */
    private Date createDate;

    /**
     * 记录数据的生成时间
     */
    private Date statusDate;

    /**
     * 记录数据的反馈消息
     */
    private String remark;

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}