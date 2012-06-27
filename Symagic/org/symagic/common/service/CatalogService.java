package org.symagic.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.symagic.common.db.bean.BeanCatalog;
import org.symagic.common.db.func.DaoCatalog;
import org.symagic.common.utilty.presentation.bean.CatalogBean;

public class CatalogService {

	private DaoCatalog daoCatalog;

	public List<CatalogBean> getCatalog() {

		List<CatalogBean> catalog = new ArrayList<CatalogBean>();

		List<BeanCatalog> daoCatalogList = daoCatalog.getCatalog();
		
		if( daoCatalogList == null ) return null;

		Map<Integer, CatalogBean> topLevelCatalogMap = new HashMap<Integer, CatalogBean>();

		Map<Integer, CatalogBean> backupList = new HashMap<Integer, CatalogBean>();

		for (BeanCatalog beanCatalog : daoCatalogList) {
			CatalogBean bean = new CatalogBean();
			bean.setID(beanCatalog.getCatalogID());
			bean.setDescription(beanCatalog.getCatalogDesc());
			bean.setName(beanCatalog.getCatalogName());

			if (beanCatalog.getLevel().equals("1")) {
				topLevelCatalogMap.put(bean.getID(), bean);
			} else {
				CatalogBean upBean = topLevelCatalogMap.get(beanCatalog
						.getUpID());
				if (upBean != null) {
					if (upBean.getChildCatalog() == null) {
						upBean.setChildCatalog(new ArrayList<CatalogBean>());
					}
					upBean.getChildCatalog().add(bean);
				} else {
					backupList.put(beanCatalog.getUpID(), bean);
				}
			}

		}
		
		

		Iterator<Entry<Integer, CatalogBean>> iterator = backupList.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, CatalogBean> entry = iterator.next();
			CatalogBean upBean = topLevelCatalogMap.get(entry.getKey());
			if (upBean.getChildCatalog() == null) {
				upBean.setChildCatalog(new ArrayList<CatalogBean>());
			}
			upBean.getChildCatalog().add(entry.getValue());
		}
		
		iterator = topLevelCatalogMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, CatalogBean> entry = iterator.next();
			catalog.add(entry.getValue());
		}

		return catalog;
	}

	public DaoCatalog getDaoCatalog() {
		return daoCatalog;
	}

	public void setDaoCatalog(DaoCatalog daoCatalog) {
		this.daoCatalog = daoCatalog;
	}
}
