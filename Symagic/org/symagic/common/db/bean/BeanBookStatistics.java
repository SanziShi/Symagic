package org.symagic.common.db.bean;

public class BeanBookStatistics {
	/**
	 * 总销售量
	 */
	private int totalSaleAmount	= 0;
	
	/**
	 * 总销售价格
	 */
	private float totalSaleRevenue	= 0.0f;
	
	/**
	 * 书籍ID
	 */
	private int	bookid	= 0;
	
	/**
	 * 书籍名
	 */
	private String bookname	= null;
	
	
	public int getTotalSaleAmount() {
		return totalSaleAmount;
	}
	public void setTotalSaleAmount(int totalSaleAmount) {
		this.totalSaleAmount = totalSaleAmount;
	}
	public float getTotalSaleRevenue() {
		return totalSaleRevenue;
	}
	public void setTotalSaleRevenue(float totalSaleRevenue) {
		this.totalSaleRevenue = totalSaleRevenue;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
	
	
}
