package org.symagic.common.service;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanCatalog;
import org.symagic.common.db.func.DaoCatalog;
import org.symagic.common.utilty.presentation.bean.CatalogBean;

public class CatalogService {
	
	private DaoCatalog daoCatalog;
	
	public List<CatalogBean> getCatalog(){
		
		List<CatalogBean> catalog = new ArrayList<CatalogBean>();
		
		List<BeanCatalog> daoCatalogList = daoCatalog.getCatalog();
		
		for( int i = 0; i < daoCatalogList.size(); i++ ){
			CatalogBean bean = new CatalogBean();
		}
		
		return catalog;
	}
}
