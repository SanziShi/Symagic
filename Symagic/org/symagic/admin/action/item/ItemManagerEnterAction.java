package org.symagic.admin.action.item;

import org.symagic.common.db.func.BookRequire;
import org.symagic.common.db.func.DaoBook;

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
	private String name;
	
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
	private Integer publishTime;

	
	/**
	 * 书籍的版次
	 */
	private Integer edition;
	
	/**
	 * 搜索的页数范围
	 */
	private Integer searchPage;//(0:0~200,1:200~400;2:400~600,3:>600)
	
	/**
	 * 搜索书籍的装帧
	 */
	private String binding;
	
	/**
	 * 搜索数据的开本
	 */
	private String booksize;
	
	private Integer price;//（0:0`10,1:10`30,2:30`50,3:50`100,4:>100）;
	private Integer discount;//(0：所有；1:3折以下;2:3-5折；3：5-7折；4：7折以上）
	
	private String author;
	
	private String description;
	
	private Integer page;//（第几页)
	private Integer totalPage;
	private Integer currentPage;
	
	private DaoBook daoBook;
	
	@Override
	public String execute() throws Exception {
		
		//建立书籍搜索的require
		BookRequire require = new BookRequire();
		require.setItemName(name);
		require.setPublisher(publisher);
		require.setCatalogID(catalogID.toString());
		//require.setYear();
		

		
		
		
		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
		this.edition = edition;
	}

	public Integer getSearchPage() {
		return searchPage;
	}

	public void setSearchPage(Integer searchPage) {
		this.searchPage = searchPage;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}


	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

}
