package org.symagic.common.db.bean;

/**
 * 书籍信息表
 * @author wanran
 *
 */
public class BeanBook {
	/**
	 * 书籍id
	 */
	private int bookId	= 0;	
	
	/**
	 * 书籍封面图片存储路径
	 */
	private String picture	= "";	
	
	/**
	 * 书籍名称
	 */
	private String bookName	= "";	
	
	/**
	 * 书籍作者
	 */
	private String author	= "";	
	
	/**
	 * 书籍出版社
	 */
	private String publisher = "";	
	
	/**
	 * 书籍出版日期(只有Date无Time)
	 * 格式yyy-MM-dd
	 */
	private String publishDate	= "";	
	
	/**
	 * 书籍出版版次
	 */
	private int version	= 1;	
	
	/**
	 * 书籍页数
	 */
	private int page	= 1;	
	
	/**
	 * 书籍的装帧，有两个选择（精装，平装）默认为NULL, 必须是这两个字符串之一
	 */
	private String binding	= "";	
	
	/**
	 * 书开本，是2的倍数。
	 */
	private String folio	= "";	
	
	/**
	 * 书籍市场价格
	 */
	private float marketPrice	= 0.0f;	
	
	/**
	 * 书籍价格折扣，百分比
	 */
	private int	discount	= 100;	
	
	/**
	 * 书籍库存量
	 */
	private int	inventory	= 0;	
	
	/**
	 * 书籍描述
	 */
	private String	bookDesc	= "";	
	
	/**
	 * 书籍isbn号
	 */
	private String	isbn	= "";	
	
	/**
	 * (在架、下架)必须是这两个字符串之一
	 */
	private String	offline	= "";	
	
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublisherDate() {
		return publishDate;
	}
	public void setPublisherDate(String publisherDate) {
		this.publishDate = publisherDate;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getBinding() {
		return binding;
	}
	public void setBinding(String binding) {
		this.binding = binding;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public float getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(float marketPrice) {
		this.marketPrice = marketPrice;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getOffline() {
		return offline;
	}
	public void setOffline(String offline) {
		this.offline = offline;
	}
	
	
}
