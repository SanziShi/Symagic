package org.symagic.common.db.bean;

/**
 * 收藏夹bean
 * @author wanran
 *
 */
public class BeanFavorityFolder {
	/**
	 * 收藏ID
	 */
	private int	favorityID	= 0;	
	
	/**
	 * 用户ID
	 */
	private String	username	= null;		
	
	/**
	 * 书籍ID
	 */
	private int	bookID	= 0;

	public int getFavorityID() {
		return favorityID;
	}

	public void setFavorityID(int favorityID) {
		this.favorityID = favorityID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}		
	
	
	
	
	
}
