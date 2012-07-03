package org.symagic.user.action.order;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.AddressDetailBean;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;

public class OrderDetailAction extends OrderBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4789666860979026366L;

	private String deliverWay;

	private String payment;
	
	private AddressDetailBean addressList;

	private String orderID;

	private String orderStatus;

	private OrderService orderService;

	private DaoOrder daoOrder;
	
	private Integer score;
	
	private String price;

	private List<ItemTinyBean> buyItems;

	public List<ItemTinyBean> getBuyItems() {
		return buyItems;
	}

	public void setBuyItems(List<ItemTinyBean> buyItems) {
		this.buyItems = buyItems;
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
		if(orderID == null || orderID.isEmpty())
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

		addressList = new AddressDetailBean();
		OrderService.Address orderAddress = OrderService.deserializerAddress(order.getAddrDetail());
		addressList.setLevel1Districts(new ArrayList<DistrictBean>());
		addressList.setLevel2Districts(new ArrayList<DistrictBean>());
		addressList.setLevel3Districts(new ArrayList<DistrictBean>());
		addressList.getLevel1Districts().add(address.level1District);
		addressList.getLevel2Districts().add(address.level2District);
		addressList.setAddressDetail(orderAddress.districtDetail);
		addressList.setMobileNum(order.getMobilenum());
		addressList.setPhoneNum(order.getPhonenum());
		addressList.setZipcode(order.getZipcode());
		addressList.setAddressSummary(receiverAddres);
		addressList.setReceiverName(order.getReceiverName());
		
		setScore(order.getScore());
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
		buyItems = new ArrayList<ItemTinyBean>();
		List<BeanOrderDetail> orderList = order.getList();
		float totalPrice = 0;
		for (int i = 0; i < orderList.size(); i++) {
			ItemTinyBean item = new ItemTinyBean();
			item.setItemNumber(orderList.get(i).getAmount());
			item.setName(orderList.get(i).getBookName());
			item.setPrice(String.format("%.2f", orderList.get(i)
					.getMarketPrice() * orderList.get(i).getDiscount()));
			item.setItemTotalPrice(String.format("%.2f", orderList.get(i).getDiscount()
					* orderList.get(i).getMarketPrice()
					* orderList.get(i).getAmount()));
			totalPrice += orderList.get(i).getDiscount()* orderList.get(i).getMarketPrice()
					*orderList.get(i).getAmount();
			buyItems.add(item);
		}
		price = String.format("%.2f", totalPrice);
		return super.execute();
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public AddressDetailBean getAddressList() {
		return addressList;
	}

	public void setAddressList(AddressDetailBean addressList) {
		this.addressList = addressList;
	}
}
