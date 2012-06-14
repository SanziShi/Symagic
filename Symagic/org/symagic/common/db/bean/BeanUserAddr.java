package org.symagic.common.db.bean;

/**
 * 用户预存地址
 * @author wanran
 *
 */
public class BeanUserAddr {
	/**
	 * 地址ID
	 */
	private int	addrID	= 0;	
	
	/**
	 * 地址所属用户ID
	 */
	private int	userID	= 0;	
	
	/**
	 * 收件人姓名
	 */
	private String	receiverName	= "";	
	
	/**
	 * 收件地址详细信息
	 */
	private String	addrDetail	= "";	
	
	/**
	 * 收件地址邮编
	 */
	private String	zipcode	= "";		
	
	/**
	 * 收件人座机号
	 */
	private String 	phoneNum	= "";	
	
	/**
	 * 收件手机号
	 */
	private String	mobileNum	= "";	
	
	public int getAddrID() {
		return addrID;
	}
	public void setAddrID(int addrID) {
		this.addrID = addrID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getAddrDetail() {
		return addrDetail;
	}
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	
	
}
