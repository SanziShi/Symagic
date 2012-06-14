package org.symagic.common.db.bean;

/**
 * 书籍评论
 * @author wanran
 *
 */
public class BeanBookComment {
	/**
	 * 用户ID
	 */
	private int	userID	= 0;	
	
	/**
	 * 注释对应的Bookid
	 */
	private int bookID	= 0;
	
	/**
	 * 评论内容
	 */
	private String	cotent	= "";	
	
	/**
	 * 书籍被评论等级,共五个等级可选,1、2、3、4、5
	 */
	private String rating	= "";	
	
	/**
	 * 评论日期
	 */
	private String commentDate	= "";

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

	public String getCotent() {
		return cotent;
	}

	public void setCotent(String cotent) {
		this.cotent = cotent;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}	
	
	
	
}
