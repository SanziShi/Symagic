package org.symagic.common.db.bean;

/**
 * 收藏夹bean
 * @author wanran
 *
 */
public class BeanFavorityFolder {
	private int	favorityID	= 0;	// 收藏ID
	private int	userID	= 0;		// 用户ID
	private int	bookID	= 0;		// 书籍ID
	private String isbn	= "";		// 书籍isbn号，20bit
	private String publisher	= "";	// 出版社名
	private String publisherDate	= "";	// 出版日期
	private float	marketPrice	= 0.0f;	// 书籍市场价格
	
	
	
	public int getFavorityID() {
		return favorityID;
	}
	public void setFavorityID(int favorityID) {
		this.favorityID = favorityID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublisherDate() {
		return publisherDate;
	}
	public void setPublisherDate(String publisherDate) {
		this.publisherDate = publisherDate;
	}
	public float getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(float marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	
	
}
