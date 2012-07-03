package org.symagic.user.action.order;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;

public class OrderDetailAction extends OrderBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4789666860979026366L;

	private String deliverWay;

	private String payment;

	private String receiverAddres;

	private String orderID;

	private String orderStatus;

	private OrderService orderService;

	private DaoOrder daoOrder;

	private List<ItemTinyBean> items;

	public List<ItemTinyBean> getItems() {
		return items;
	}

	public void setItems(List<ItemTinyBean> items) {
		this.items = items;
	}

	public String getDeliverWay() {
		return deliverWay;
	}

	public void setDeliverWay(String deliverWay) {
		this.deliverWay = deliverWay;
	}

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

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderId) {
		this.orderID = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DaoOrder getDaoOrder() {
		return daoOrder;
	}

	public void setDaoOrder(DaoOrder daoOrder) {
		this.daoOrder = daoOrder;
	}

	public String execute() throws Exception {
		if(orderID == null)
			return ERROR;
		BeanOrder order = daoOrder.getOrderDetail(Integer.parseInt(orderID));
		if (order == null)
			return ERROR;
		if (order.getDeliveryWay() == "0")
			setDeliverWay("送货上门");
		OrderService.Address address = OrderService.deserializerAddress(order
				.getAddrDetail());
		String receiverAddres =  new String();
		if(address.level1District.getName() != null)
			receiverAddres += address.level1District.getName();
		if(address.level2District.getName() != null)
			receiverAddres += address.level2District.getName();
		if(address.level3District.getName() != null)
			receiverAddres += address.level3District.getName();
		if(address.districtDetail != null)
			receiverAddres += address.districtDetail;
		setReceiverAddres(receiverAddres);
		setMobileNum(order.getMobilenum());
		setOrderTime(order.getOrderDate());
		setOrderID(Integer.toString(order.getOrderId()));
		int state = Integer.parseInt(order.getOrderState());
		switch (state) {
		case 0:
			setOrderStatus("已下单");
			break;
		case 1:
			setOrderStatus("已审核");
			break;
		case 2:
			setOrderStatus("交易成功");
			break;
		case 3:
			setOrderStatus("交易失败");
			break;
		}
		if (order.getPayment() == "0")
			setPayment("货到付款");
		setPhoneNum(order.getPhonenum());
		setReceiverName(order.getReceiverName());
		items = new ArrayList<ItemTinyBean>();
		List<BeanOrderDetail> orderList = order.getList();
		for (int i = 0; i < orderList.size(); i++) {
			ItemTinyBean item = new ItemTinyBean();
			item.setItemNumber(orderList.get(i).getAmount());
			item.setName(orderList.get(i).getBookName());
			item.setPrice(String.format("%.2f", orderList.get(i)
					.getMarketPrice() * orderList.get(i).getDiscount()));
			item.setItemTotalPrice(String.format("%.2f", orderList.get(i)
					.getMarketPrice()
					* orderList.get(i).getDiscount()
					* orderList.get(i).getMarketPrice()
					* orderList.get(i).getAmount()));
			items.add(item);
		}
		return super.execute();
	}
}
