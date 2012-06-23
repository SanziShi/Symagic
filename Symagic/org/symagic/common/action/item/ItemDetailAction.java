package org.symagic.common.action.item;

import java.util.List;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.utilty.inventory.InventoryUtility;
import org.symagic.common.utilty.presentation.bean.CatalogBean;
import org.symagic.common.utilty.presentation.bean.CommentBean;

import com.opensymphony.xwork2.ActionSupport;

public class ItemDetailAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1237374602453411556L;

	/**
	 * 输入的itemID
	 */
	private Integer itemID;

	/**
	 * 
	 */
	private String ISBN;

	/**
	 * 
	 */
	private String itemName;

	/**
	 * 
	 */
	private String itemAuthor;

	/**
	 * 
	 */
	private String itemPublisher;

	/**
	 * 
	 */
	private String itemPublishingTime;

	/**
	 * 
	 */
	private Integer itemEdition;

	/**
	 * 
	 */
	private Integer itemPage;

	/**
	 * 
	 */
	private String itemBinding;

	/**
	 * 
	 */
	private String itemFormat;

	/**
	 * 
	 */
	private Float itemPublishingPrice;
	
	private Float itemPrice;

	/**
	 * 
	 */
	private Float itemDiscount;

	/**
	 * 
	 */
	private Boolean itemInventory;

	/**
	 * 
	 */
	private String itemBookDescribe;

	/**
	 * 
	 */
	private List<CatalogBean> itemBookClass;

	/**
	 * 
	 */
	private Integer itemComprehensiveRate;

	/**
	 * 
	 */
	private List<CommentBean> commentList;

	private Integer page;

	private Integer totalPage;
	private Integer currentPage;

	/**
	 * 
	 */
	private List<CatalogBean> catalog;

	private DaoBook daoBook;

	@Override
	public String execute() throws Exception {

		BeanBook book = daoBook.getDeatil(itemID);

		ISBN = book.getIsbn();
		itemName = book.getBookName();
		itemAuthor = book.getAuthor();

		itemPublisher = book.getPublisher();

		itemPublishingTime = book.getPublisherDate();

		itemEdition = book.getVersion();

		itemPage = book.getPage();

		itemBinding = book.getBinding();

		itemFormat = book.getFolio();

		itemPublishingPrice = book.getMarketPrice();

		itemDiscount = book.getDiscount();

		itemInventory = InventoryUtility.checkInventory(book.getInventory());

		itemBookDescribe = book.getBookDesc();

		itemPrice = itemPublishingPrice * itemDiscount;
		
//		book.get
//		
//		/**
//		 * 
//		 */
//		private List<CatalogBean> itemBookClass;
//
//		/**
//		 * 
//		 */
//		private Integer itemComprehensiveRate;
//
//		/**
//		 * 
//		 */
//		private List<CommentBean> commentList;
//
//		/**
//		 * 
//		 */
//		private List<CatalogBean> catalog;
//
//		private Integer page;
//
//		private Integer totalPage;
//		private Integer currentPage;

		return SUCCESS;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemAuthor() {
		return itemAuthor;
	}

	public void setItemAuthor(String itemAuthor) {
		this.itemAuthor = itemAuthor;
	}

	public String getItemPublisher() {
		return itemPublisher;
	}

	public void setItemPublisher(String itemPublisher) {
		this.itemPublisher = itemPublisher;
	}

	public String getItemPublishingTime() {
		return itemPublishingTime;
	}

	public void setItemPublishingTime(String itemPublishingTime) {
		this.itemPublishingTime = itemPublishingTime;
	}

	public Integer getItemEdition() {
		return itemEdition;
	}

	public void setItemEdition(Integer itemEdition) {
		this.itemEdition = itemEdition;
	}

	public Integer getItemPage() {
		return itemPage;
	}

	public void setItemPage(Integer itemPage) {
		this.itemPage = itemPage;
	}

	public String getItemBinding() {
		return itemBinding;
	}

	public void setItemBinding(String itemBinding) {
		this.itemBinding = itemBinding;
	}

	public String getItemFormat() {
		return itemFormat;
	}

	public void setItemFormat(String itemFormat) {
		this.itemFormat = itemFormat;
	}

	public Float getItemDiscount() {
		return itemDiscount;
	}

	public void setItemDiscount(Float itemDiscount) {
		this.itemDiscount = itemDiscount;
	}

	public String getItemBookDescribe() {
		return itemBookDescribe;
	}

	public void setItemBookDescribe(String itemBookDescribe) {
		this.itemBookDescribe = itemBookDescribe;
	}

	public List<CatalogBean> getItemBookClass() {
		return itemBookClass;
	}

	public void setItemBookClass(List<CatalogBean> itemBookClass) {
		this.itemBookClass = itemBookClass;
	}

	public Integer getItemComprehensiveRate() {
		return itemComprehensiveRate;
	}

	public void setItemComprehensiveRate(Integer itemComprehensiveRate) {
		this.itemComprehensiveRate = itemComprehensiveRate;
	}

	public List<CommentBean> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentBean> commentList) {
		this.commentList = commentList;
	}

	public List<CatalogBean> getCatalog() {
		return catalog;
	}

	public void setCatalog(List<CatalogBean> catalog) {
		this.catalog = catalog;
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

	public Boolean getItemInventory() {
		return itemInventory;
	}

	public void setItemInventory(Boolean itemInventory) {
		this.itemInventory = itemInventory;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

}
