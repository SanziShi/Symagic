package org.symagic.common.utility.presentation.bean;

import java.util.List;

public class CatalogBean {
	
	private Integer id;
	private String name;
	private String desc;
	private List<CatalogBean> chlid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<CatalogBean> getChlid() {
		return chlid;
	}
	public void setChlid(List<CatalogBean> chlid) {
		this.chlid = chlid;
	}
	
}
