package org.symagic.common.db.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanOrder {
	/**
	 * 存储订单中每一本书的信息
	 */
	private List<BeanOrderDetail>	list	= new ArrayList<BeanOrderDetail>();
	
	/**
	 * 订单id
	 */
	private int orderId	= 0;	
	
	/**
	 * 下单日期 包含事件
	 * yyyy-MM-dd hh:mm:ss
	 */
	private String orderDate = "";	
	
	/**
	 * 订单状态	目前有四种状态。0：已下单	1：已审核	2：交易成功	3：交易失败
	 */
	private String orderState = "";	
	
	/**
	 * 付款方式，目前只有货到付款。 即 0:货到付款
	 */
	private String payment = "";	
	
	/**
	 * 送货方式，目前只有一种送货方式，即 0:快递
	 */
	private String deliveryWay = "";	
	
	/**
	 * 用户名
	 */
	private String	username = null;	
	
	/**
	 * 收件人姓名
	 */
	private String receiverName	= "";	
	
	/**
	 * 收件地址详情
	 */
	private String addrDetail	= "";
	
	/**
	 * 收件地址邮编
	 */
	private String zipcode	= "";	
	
	/**
	 * 收件人座机号
	 */
	private String phonenum	= "";	
	
	/**
	 * 收件人手机号
	 */
	private String mobilenum	= "";	
	
	/**
	 * 商品总价
	 */
	private float totalprice	= 0.0f;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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

	public List<BeanOrderDetail> getList() {
		return list;
	}

	public void setList(List<BeanOrderDetail> list) {
		this.list = list;
	}

	public float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}
	
	
}
