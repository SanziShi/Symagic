package org.symagic.common.db.bean;

public class BeanOrderDetail {
	/**
	 * 订单id
	 */
	private int orderId	= 0;	 
	
	/**
	 * 书籍id
	 */
	private int	bookId 	= 0;	 
	
	/**
	 * 书籍isbn号
	 */
	private String isbn	= "";	 
	
	/**
	 * 书籍名称
	 */
	private String bookName	= "";	
	
	/**
	 * 书籍市场价格，原价
	 */
	private float marketPrice	= 0.0f;	
	
	/**
	 * 书籍折扣（百分率）
	 */
	private int	discount	= 100;	
	
	/**
	 * 数量，等于0就直接删除订单
	 */
	private int amount	= 1;	
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
