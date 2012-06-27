package org.symagic.admin.action.catalog;

import org.symagic.common.db.bean.BeanCatalog;
import org.symagic.common.db.func.DaoCatalog;

import com.opensymphony.xwork2.ActionSupport;

public class CatalogEditSubmitAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2915979976243924367L;
	
	/**
	 * 
	 */
	private Integer catalogID;// :目录ID

	/**
	 * 
	 */
	private String catalogName;// :目录名，
	/**
	 * 
	 */
	private String catalogDesc;// ：目录描述，
	/**
	 * 
	 */
	private Integer upID;// :父目录的ID(-1表示根目录）

	private boolean validateResult;

	private DaoCatalog daoCatalog;

	@Override
	public String execute() throws Exception {
		
		if( !validateResult ){
			return ERROR;
		}
		
		BeanCatalog catalog = new BeanCatalog();
		catalog.setCatalogID(catalogID);
		catalog.setCatalogName(catalogName);
		catalog.setCatalogDesc(catalogDesc);
		catalog.setUpID(upID);
		if( upID == -1 ){
			catalog.setLevel("1");
		}
		else{
			catalog.setLevel("2");
		}
		daoCatalog.modifyCatalog(catalog);
		
		return super.execute();
	}

	@Override
	public void validate() {
		if( catalogID == null || catalogName == null || catalogDesc == null || upID == null ){
			validateResult = false;
		}
		else{
			validateResult = true;
		}
		super.validate();
	}

	public Integer getCatalogID() {
		return catalogID;
	}

	public void setCatalogID(Integer catalogID) {
		this.catalogID = catalogID;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getCatalogDesc() {
		return catalogDesc;
	}

	public void setCatalogDesc(String catalogDesc) {
		this.catalogDesc = catalogDesc;
	}

	public Integer getUpID() {
		return upID;
	}

	public void setUpID(Integer upID) {
		this.upID = upID;
	}

	public boolean isValidateResult() {
		return validateResult;
	}

	public void setValidateResult(boolean validateResult) {
		this.validateResult = validateResult;
	}

	public DaoCatalog getDaoCatalog() {
		return daoCatalog;
	}

	public void setDaoCatalog(DaoCatalog daoCatalog) {
		this.daoCatalog = daoCatalog;
	}

}