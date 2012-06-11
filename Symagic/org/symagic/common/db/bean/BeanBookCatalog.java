package org.symagic.common.db.bean;

/**
 * 书籍分类信息
 * @author wanran
 *
 */
public class BeanBookCatalog {
	private int	catalogID	= 0;	// 分类ID
	private String	catalogName	= "";	// 分类名
	private String	level	= "";		// 层级，1， 2
	private int	upID	= 0;	// 上一层ID
	private String catalogDesc	= "";	// 分类描述
	public int getCatalogID() {
		return catalogID;
	}
	public void setCatalogID(int catalogID) {
		this.catalogID = catalogID;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getUpID() {
		return upID;
	}
	public void setUpID(int upID) {
		this.upID = upID;
	}
	public String getCatalogDesc() {
		return catalogDesc;
	}
	public void setCatalogDesc(String catalogDesc) {
		this.catalogDesc = catalogDesc;
	}
	
	
}
