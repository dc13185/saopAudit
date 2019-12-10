package com.asiainfo.crm.audit.vo.prodinst;

import java.util.List;

import com.asiainfo.crm.audit.model.prodinst.ProdInst;
import com.asiainfo.crm.audit.model.prodinst.ProdInstRel;

/**
 * @author fortune
 */
public class ProdInstRelVoExtend extends ProdInstRel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8862575288596799363L;

	private Long aProdId;
	private Long zProdId;
	private String statusCd;
	private String roleName;
	private String aAccNum;
	private String zAccNum;

	private List<ProdInstRelAttrVoExtend> prodInstRelAttrs;
	private ProdInst aProdInst;
	private ProdInst zProdInst;

	public Long getaProdId(){
		return aProdId;
	}

	public void setaProdId(Long aProdId){
		this.aProdId = aProdId;
	}

	public Long getzProdId(){
		return zProdId;
	}

	public void setzProdId(Long zProdId){
		this.zProdId = zProdId;
	}

	public List<ProdInstRelAttrVoExtend> getProdInstRelAttrs(){
		return prodInstRelAttrs;
	}

	public void setProdInstRelAttrs(List<ProdInstRelAttrVoExtend> prodInstRelAttrs){
		this.prodInstRelAttrs = prodInstRelAttrs;
	}

	public String getStatusCd(){
		return statusCd;
	}

	public void setStatusCd(String statusCd){
		this.statusCd = statusCd;
	}

	public ProdInst getaProdInst(){
		return aProdInst;
	}

	public void setaProdInst(ProdInst aProdInst){
		this.aProdInst = aProdInst;
	}

	public ProdInst getzProdInst(){
		return zProdInst;
	}

	public void setzProdInst(ProdInst zProdInst){
		this.zProdInst = zProdInst;
	}

	public String getRoleName(){
		return roleName;
	}

	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	public String getaAccNum(){
		return aAccNum;
	}

	public void setaAccNum(String aAccNum){
		this.aAccNum = aAccNum;
	}

	public String getzAccNum(){
		return zAccNum;
	}

	public void setzAccNum(String zAccNum){
		this.zAccNum = zAccNum;
	}

}
