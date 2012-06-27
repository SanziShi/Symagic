package org.symagic.user.action.order;

import org.symagic.user.utilty.UserSessionUtilty;

public class OrderDetailAction extends OrderBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4789666860979026366L;

	private String deliverWay;
	
	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getReceiverAddres() {
		return receiverAddres;
	}

	public void setReceiverAddres(String receiverAddres) {
		this.receiverAddres = receiverAddres;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	private String payment;
	
	private String receiverAddres;
	
	private String orderId;
	
	private String orderStatus;

	public String getDeliverWay() {
		return deliverWay;
	}

	public void setDeliverWay(String deliverWay) {
		this.deliverWay = deliverWay;
	}
	
	public String execute() throws Exception{
		if(!UserSessionUtilty.isLogin())
			return ERROR;
		
		return SUCCESS;
	}
}
