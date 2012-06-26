package org.symagic.admin.action.catalog;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.symagic.common.db.func.DaoCatalog;

import com.opensymphony.xwork2.ActionSupport;

public class CatalogDeleteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2227925653961764991L;
	
	private String catalogIDList;
	
	private Boolean deleteResult;
	
	private DaoCatalog daoCatalog;

	@Override
	public String execute() throws Exception {
		
		deleteResult = false;
		
		if( catalogIDList != null ){
			
			JSON json = JSONSerializer.toJSON(catalogIDList);
			if( !json.isArray() ){
				deleteResult = false;
				return super.execute();
			}
			
			JSONArray array = (JSONArray) json;
			
			for( int i = 0; i < array.size(); i++ ){
				if( !daoCatalog.deleteCatalog(array.getInt(i)) )
					return super.execute();
			}
			
			deleteResult = true;
		}
		
		return super.execute();
	}

	public String getCatalogIDList() {
		return catalogIDList;
	}

	public void setCatalogIDList(String catalogIDList) {
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
