package org.symagic.common.action.catalog;

import java.util.List;

import org.symagic.common.service.CatalogService;
import org.symagic.common.utilty.presentation.bean.CatalogBean;

import com.opensymphony.xwork2.ActionSupport;

public class CatalogBase extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4271428878228663805L;
	
	protected List<CatalogBean> catalog;

	protected CatalogService catalogService;
	

	@Override
	public String execute() throws Exception {
		
		catalog = catalogService.getCatalog();
		
		return super.execute();
	}


	public List<CatalogBean> getCatalog() {
		return catalog;
	}


	public void setCatalog(List<CatalogBean> catalog) {
		this.catalog = catalog;
	}


	public CatalogService getCatalogService() {
		return catalogService;
	}


	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	
}
