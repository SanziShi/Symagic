package org.symagic.user.action.order;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.service.AddressService;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.AddressDetailBean;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class OrderEditOrderAction extends OrderBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1426889985949981821L;

	private List<AddressDetailBean> addressList;

	private AddressDetailBean defaultAddressList;

	private Integer orderID;

	private String userName;

	private String price;

	private String payment;

	private String deliverWay;

	private boolean isValidate;

	public List<AddressDetailBean> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressDetailBean> addressList) {
		this.addressList = addressList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<ItemTinyBean> getBuyItems() {
		return buyItems;
	}

	public void setBuyItems(List<ItemTinyBean> buyItems) {
		this.buyItems = buyItems;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

	public List<DistrictBean> getLevel1Districts() {
		return level1Districts;
	}

	public void setLevel1Districts(List<DistrictBean> level1Districts) {
		this.level1Districts = level1Districts;
	}

	private List<ItemTinyBean> buyItems;

	private AddressService addressService;

	private DaoBook daoBook;

	private List<DistrictBean> level1Districts;

	private DaoOrder daoOrder;
	
	private Integer score;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
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

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderId) {
		this.orderID = orderId;
	}

	public DaoOrder getDaoOrder() {
		return daoOrder;
	}

	public void setDaoOrder(DaoOrder daoOrder) {
		this.daoOrder = daoOrder;
	}

	public String execute() throws Exception {
		if (orderID == null)
			return ERROR;
		BeanOrder order = daoOrder.getOrderDetail(orderID);
		userName = UserSessionUtilty.getUsername();
		if (userName == null)
			return ERROR;
		if (order == null || order.getUsername() == null || !order.getUsername().equals(userName))
			return ERROR;
		if (!order.getOrderState().equals("0")) {
			return ERROR;
		}
		if (order.getDeliveryWay().equals("0"))
			setDeliverWay("送货上门");
		addressList = addressService.getAddressDetail(userName);
		OrderService.Address address = OrderService.deserializerAddress(order
				.getAddrDetail());

		defaultAddressList = new AddressDetailBean();
		defaultAddressList.setLevel1Districts(addressService.getDistricts(0));
		defaultAddressList.setLevel2Districts(addressService.getDistricts(address.level1District.getID()));
		defaultAddressList.setLevel3Districts(addressService.getDistricts(address.level2District.getID()));
		defaultAddressList.setLevel1DistrictDefaultID(address.level1District.getID());
		defaultAddressList.setLevel2DistrictDefaultID(address.level2District.getID());
		defaultAddressList.setLevel3DistrictDefaultID(address.level3District.getID());
		
		defaultAddressList.setAddressDetail(address.districtDetail);
		defaultAddressList.setMobileNum(order.getMobilenum());
		defaultAddressList.setPhoneNum(order.getPhonenum());
		defaultAddressList.setZipcode(order.getZipcode());
		defaultAddressList.setAddressSummary(address.level1District.getName()
				+ address.level2District.getName()
				+ address.level3District.getName());
		defaultAddressList.setReceiverName(order.getReceiverName());

		setOrderID(order.getOrderId());
		
		score = order.getScore();

		if (order.getPayment() == "0")
			setPayment("货到付款");

		buyItems = new ArrayList<ItemTinyBean>();
		Float totalPrice = 0.0f;
		List<BeanOrderDetail> orderList = order.getList();
		for (int i = 0; i < orderList.size(); i++) {
			ItemTinyBean item = new ItemTinyBean();
			item.setItemNumber(orderList.get(i).getAmount());
			item.setName(orderList.get(i).getBookName());
			item.setPrice(String.format("%.2f", orderList.get(i)
					.getMarketPrice() * orderList.get(i).getDiscount()));
			item.setItemTotalPrice(String.format("%.2f",
					orderList.get(i).getDiscount()
					* orderList.get(i).getMarketPrice()
					* orderList.get(i).getAmount()));
			totalPrice += orderList.get(i).getDiscount()
					* orderList.get(i).getMarketPrice()
					* orderList.get(i).getAmount();
			item.setScore((int) (orderList.get(i).getDiscount() * orderList.get(i).getAmount() * orderList.get(i).getMarketPrice()));
			buyItems.add(item);
		}
		totalPrice -= score * 0.1f;
		price = String.format("%.2f", totalPrice);
		return super.execute();
	}

	public AddressDetailBean getDefaultAddressList() {
		return defaultAddressList;
	}

	public void setDefaultAddressList(AddressDetailBean defaultAddressList) {
		this.defaultAddressList = defaultAddressList;
	}
}
