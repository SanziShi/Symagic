package org.symagic.common.db.bean;

/**
 * 书籍分类信息
 * @author wanran
 *
 */
public class BeanCatalog {
	/**
	 * 分类ID
	 */
	private int	catalogID	= 0;	
	
	/**
	 * 分类名
	 */
	private String	catalogName	= "";	
	
	/**
	 * 层级，1， 2
	 */
	private String	level	= "";		
	
	/**
	 * 上一层ID
	 */
	private int	upID	= 0;
	
	/**
	 * 分类描述
	 */
	private String catalogDesc	= "";	
	
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
