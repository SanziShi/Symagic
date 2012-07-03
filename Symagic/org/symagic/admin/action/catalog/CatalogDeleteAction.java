package org.symagic.admin.action.catalog;

import java.util.List;

import org.symagic.common.db.func.DaoCatalog;

import com.opensymphony.xwork2.ActionSupport;

public class CatalogDeleteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2227925653961764991L;
	
	private List<Integer> catalogIDList;
	
	private Boolean deleteResult;
	
	private DaoCatalog daoCatalog;

	@Override
	public String execute() throws Exception {
		
		deleteResult = false;
		
		if( catalogIDList != null ){
			
			for( int i = 0; i < catalogIDList.size(); i++ ){
				if( !daoCatalog.deleteCatalog(catalogIDList.get(i)) )
					return super.execute();
			}
			
			deleteResult = true;
		}
		
		return super.execute();
	}

	

	public List<Integer> getCatalogIDList() {
		return catalogIDList;
	}



	public void setCatalogIDList(List<Integer> catalogIDList) {
		this.catalogIDList = catalogIDList;
	}



	public Boolean getDeleteResult() {
		return deleteResult;
	}

	public void setDeleteResult(Boolean deleteResult) {
		this.deleteResult = deleteResult;
	}

	public DaoCatalog getDaoCatalog() {
		return daoCatalog;
	}

	public void setDaoCatalog(DaoCatalog daoCatalog) {
		this.daoCatalog = daoCatalog;
	}
	
	

}
