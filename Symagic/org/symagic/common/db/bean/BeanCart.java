package org.symagic.common.db.bean;

/**
 * 封装购物车字段
 * @author wanran
 *
 */
public class BeanCart {
	/**
	 * 购物车所属用户用户名
	 */
	private String username	= null;
	
	/**
	 * 添加购物车时间
	 */
	private String addDate	= null;
	
	/**
	 * 书籍ID
	 */
	private Integer bookID	= null;
	
	/**
	 * 欲购买书籍数量
	 */
	private Integer amount	= null;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public Integer getBookID() {
		return bookID;
	}

	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
	
}
