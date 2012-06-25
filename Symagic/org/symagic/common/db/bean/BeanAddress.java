package org.symagic.common.db.bean;

/**
 * 封装用户预存地址信息
 * @author wanran
 *
 */
public class BeanAddress {
	/**
	 * 用户地址ID,唯一标示一个地址
	 */
	private Integer	addrid	= null;
	
	/**
	 * 用户名，外键，指向用户表
	 */
	private String username	= null;
	
	/**
	 * 收件人姓名
	 */
	private String receivername	= null;
	
	/**
	 * 地址详情，为Json格式
	 */
	private String addrdetail	= null;
	
	/**
	 * 收件地址邮编
	 */
	private String zipcode	= null;
	
	/**
	 * 家庭电话
	 */
	private String phonenum	= null;
	
	/**
	 * 移动电话
	 */
	private String mobilenum	= null;

	public Integer getAddrid() {
		return addrid;
	}

	public void setAddrid(Integer addrid) {
		this.addrid = addrid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReceivername() {
		return receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}

	public String getAddrdetail() {
		return addrdetail;
	}

	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getMobilenum() {
		return mobilenum;
	}

	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}
	
	
}
