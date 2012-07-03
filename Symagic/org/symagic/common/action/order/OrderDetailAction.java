package org.symagic.common.action.order;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;

public class OrderDetailAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3862168970037657551L;

	private Integer orderID;// :订单号;
	private String zipcode;
	private String userName;// 用户名(username);
	private String orderTime;// 下单时间(orderTime字符串);
	private String price;//
	private String payment;// 支付方式(payment字符串）；
	private String deliverWay;// 送货方式(deliverWay送货方式）；
	private String receiver;// 收货人(receiver);
	private String receiverAddress;// 收货人地址(receiverAddress),
	private String orderStatus;// ),收货人手机(
	private String receiverMobile;// ),收货人电话(
	private String receiverPhone;// ),items:一维数组（一个包含商品ID（itemId),商品名字（itemName),商品单价(price),商品小计(itemTotalPrice),商品数量（itemNumber));catalog:一维数组（一个含有名字(name)和简介(desc)id,目录的ID，二级目录的一维数组（含有名字(name)和简介(desc)id,目录的ID，一个空的一维数组））；
	private List<ItemTinyBean> items;

	private OrderService orderService;
	private DaoBook daoBook;

	/**
	 * 提供其子类访问数据库获取的原始数据
	 */
	protected BeanOrder order = null;

	protected OrderService.Address address = null;

	@Override
	public String execute() throws Exception {
		
		if( orderID == null ) return ERROR;

		order = orderService.orderDetail(orderID);
		if( order != null ) return ERROR;
		userName = order.getUsername();
		orderTime = order.getOrderDate();
		price = String.format("%.2f", order.getTotalprice());
		switch (Integer.parseInt(order.getPayment())) {
		case 0:
			payment = "货到付款";
			break;
		}
		switch (Integer.parseInt(order.getDeliveryWay())) {
		case 0:
			deliverWay = "快递";
			break;
		}
		receiver = order.getReceiverName();

		address = OrderService.deserializerAddress(order.getAddrDetail());
		receiverAddress = new String();
		if (address.level1District != null) {
			receiverAddress += address.level1District.getName();
		}
		if (address.level2District != null) {
			receiverAddress += address.level2District.getName();
		}
		if (address.level3District != null) {
			receiverAddress += address.level3District.getName();
		}
		receiverAddress += address.districtDetail;

		switch (Integer.parseInt(order.getOrderState())) {
		case 0:
			orderStatus = "已下单";
			break;
		case 1:
			orderStatus = "已审核";
			break;
		case 2:
			orderStatus = "交易成功";
			break;
		case 3:
			orderStatus = "交易失败";
			break;
		}

		receiverMobile = order.getMobilenum();
		receiverPhone = order.getPhonenum();
		zipcode = order.getZipcode();

		items = new ArrayList<ItemTinyBean>();

		List<BeanOrderDetail> itemsList = order.getList();
		for (BeanOrderDetail detail : itemsList) {
			ItemTinyBean itemBean = new ItemTinyBean();
			itemBean.setItemID(detail.getBookId());
			itemBean.setItemNumber(detail.getAmount());
			itemBean.setItemTotalPrice(String.format(
					"%.2f",
					detail.getMarketPrice() * detail.getAmount()
							* detail.getDiscount()));
			itemBean.setName(detail.getBookName());
			itemBean.setPicturePath(null);
			itemBean.setPrice(String.format("%.2f", detail.getMarketPrice()
					* detail.getDiscount()));
			itemBean.setSavePrice(String.format("%.2f",
					(1 - detail.getDiscount()) * detail.getMarketPrice()
							* detail.getAmount()));
			BeanBook book = daoBook.getDetail(detail.getBookId());
			if (book != null) {
				if (book.getOffline().equals("下架"))
					itemBean.setOffline(true);
				else
					itemBean.setOffline(false);
			}
			items.add(itemBean);
		}

		return super.execute();
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getDeliverWay() {
		return deliverWay;
	}

	public void setDeliverWay(String deliverWay) {
		this.deliverWay = deliverWay;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public List<ItemTinyBean> getItems() {
		return items;
	}

	public void setItems(List<ItemTinyBean> items) {
		this.items = items;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

}
