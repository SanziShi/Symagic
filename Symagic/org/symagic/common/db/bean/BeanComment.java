package org.symagic.common.db.bean;

/**
 * 书籍评论
 * @author wanran
 *
 */
public class BeanComment {

	private String username = "";
	
	/**
	 * 注释对应的Bookid
	 */
	private int bookID	= 0;
	
	/**
	 * 评论内容
	 */
	private String	content	= "";	
	
	/**
	 * 书籍被评论等级,共五个等级可选,1、2、3、4、5
	 */
	private String rating	= "";	
	
	/**
	 * 评论日期,带时间
	 * yyyy-MM-dd HH:mm:ss
	 */
	private String commentDate	= "";



	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getCotent() {
		return content;
	}

	public void setCotent(String cotent) {
		this.content = cotent;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	
	
	
	
}
