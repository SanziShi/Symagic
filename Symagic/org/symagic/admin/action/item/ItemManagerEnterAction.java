package org.symagic.admin.action.item;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 控制进入商品管理页面的Action
 * @author hao
 *
 */
public class ItemManagerEnterAction extends ActionSupport {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7734758591630202798L;
	
	/**
	 * 搜索用的书名
	 */
	private String itemName;
	
	/**
	 * 出版社名字
	 */
	private String publisher;
	
	/**
	 * 类别的ID
	 */
	private Integer catalogID;
	
	/**
	 * 书籍出版的年份
	 */
	private Integer publishYear;

	
	/**
	 * 书籍的版次
	 */
	private Integer version;
	
	/**
	 * 搜索的页数范围
	 */
	private String searchPage;
	
	/**
	 * 搜索书籍的装帧
	 */
	private String binding;
	
	/**
	 * 搜索数据的开本
	 */
	private String booksize;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getCatalogID() {
		return catalogID;
	}

	public void setCatalogID(Integer catalogID) {
		this.catalogID = catalogID;
	}

	public Integer getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(Integer publishYear) {
		this.publishYear = publishYear;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getSearchPage() {
		return searchPage;
	}

	public void setSearchPage(String searchPage) {
		this.searchPage = searchPage;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public String getBooksize() {
		return booksize;
	}

	public void setBooksize(String booksize) {
		this.booksize = booksize;
	}

}
