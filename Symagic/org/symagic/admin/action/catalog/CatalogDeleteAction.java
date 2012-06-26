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
		
		if( catalogIDList != null ){
			
			JSON json = JSONSerializer.toJSON(catalogIDList);
			if( !json.isArray() ){
				deleteResult = false;
				return super.execute();
			}
			
			JSONArray array = (JSONArray) json;
			
			for( int i = 0; i < array.size(); i++ ){
				int id = Integer.parseInt(array.get(i).toString());
				//daoCatalog.
			}
			
			deleteResult = true;
		}
		else{
			deleteResult = false;
		}
		
		return super.execute();
	}
	
	

}
