package org.symagic.admin.action.catalog;

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
	private String catalogName;//:目录名，
	/**
	 * 
	 */
	private String catalogDesc;//：目录描述，
	/**
	 * 
	 */
	private Integer upID;//:父目录的ID(-1表示根目录）
	
	private boolean validateResult;
	
	private DaoCatalog daoCatalog;
	
	@Override
	public String execute() throws Exception {
		
		if( !validateResult )
			return ERROR;
		
		//daoCatalog.
		
		return SUCCESS;
	}
	@Override
	public void validate() {
		if( catalogName == null || catalogDesc == null || upID == null )
			validateResult = false;
		else
			validateResult = true;
	}
	
	
	
}
