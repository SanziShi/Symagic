package org.symagic.admin.action.catalog;

import org.symagic.admin.utilty.AdminUtility;
import org.symagic.common.db.bean.BeanCatalog;
import org.symagic.common.db.func.DaoCatalog;

import com.opensymphony.xwork2.ActionSupport;

public class CatalogAddAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4166666737908734729L;
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
	private Integer upID;// :父目录的ID(0表示根目录）

	private boolean validateResult;

	private DaoCatalog daoCatalog;

	@Override
	public String execute() throws Exception {

		if (!validateResult)
			return ERROR;

		BeanCatalog catalog = new BeanCatalog();
		catalog.setCatalogName(catalogName);
		catalog.setCatalogDesc(catalogDesc);
		if (upID == 0)
			catalog.setLevel("1");
		else {
			catalog.setLevel("2");
		}
		catalog.setUpID(upID);
		daoCatalog.addCatalog(catalog);

		return SUCCESS;
	}

	@Override
	public void validate() {
		if ( AdminUtility.isEmpty(catalogName) || AdminUtility.isEmpty(catalogDesc) || upID == null)
			validateResult = false;
		else
			validateResult = true;
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

	public Integer getUpID() {
		return upID;
	}

	public void setUpID(Integer upID) {
		this.upID = upID;
	}


}
