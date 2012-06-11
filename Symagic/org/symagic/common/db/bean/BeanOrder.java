package org.symagic.common.db.bean;

public class BeanOrder {
	private int orderId	= 0;	// 订单id
	private String orderDate = "";	// 下单日期
	private String orderState = "";	// 订单状态	目前有四种状态。0：已下单	1：已审核	2：交易成功	3：交易失败
	private String payment = "";	// 付款方式，目前只有货到付款。 即 0:货到付款
	private String deliveryWay = "";	// 送货方式，目前只有一种送货方式，即 0:快递
	private int	userId = 0;	// 用户id
	private String receiverName	= "";	// 收件人姓名
	private String addrDetail	= "";	// 收件地址详情
	private String zipcode	= "";	// 收件地址邮编
	private String phonenum	= "";	// 收件人座机号
	private String mobilenum	= "";	// 收件人手机号
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getDeliveryWay() {
		return deliveryWay;
	}
	public void setDeliveryWay(String deliveryWay) {
		this.deliveryWay = deliveryWay;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
