package org.symagic.user.action.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.ServletActionContext;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanDistrict;
import org.symagic.common.db.bean.BeanLevel;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoCart;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoLevel;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.db.func.DaoUser;
import org.symagic.common.service.OrderService;
import org.symagic.common.service.RecommendService;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class OrderSubmitAction extends OrderBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4501362220836715613L;

	/*
	 * 订单获取的积分
	 */
	private Integer score;

	/*
	 * 地址代码
	 */
	private Integer districtLevel1ID;

	private Integer districtLevel2ID;

	private Integer districtLevel3ID;

	public Integer getDistrictLevel1ID() {
		return districtLevel1ID;
	}

	public void setDistrictLevel1ID(Integer districtLevel1ID) {
		this.districtLevel1ID = districtLevel1ID;
	}

	public Integer getDistrictLevel2ID() {
		return districtLevel2ID;
	}

	public void setDistrictLevel2ID(Integer districtLevel2ID) {
		this.districtLevel2ID = districtLevel2ID;
	}

	public Integer getDistrictLevel3ID() {
		return districtLevel3ID;
	}

	public void setDistrictLevel3ID(Integer districtLevel3ID) {
		this.districtLevel3ID = districtLevel3ID;
	}

	private String addressDetail;

	private DaoOrder daoOrder;

	private DaoDistrict daoDistrict;

	private RecommendService recommandService;

	private DaoBook daoBook;

	private DaoUser daoUser;

	private DaoLevel daoLevel;

	private Integer orderID;

	private DaoCart daoCart;

	public DaoLevel getDaoLevel() {
		return daoLevel;
	}

	public void setDaoLevel(DaoLevel daoLevel) {
		this.daoLevel = daoLevel;
	}

	private boolean isValidate;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public DaoOrder getDaoOrder() {
		return this.daoOrder;
	}

	public void setDaoOrder(DaoOrder order) {
		this.daoOrder = order;
	}

	@Override
	public String execute() throws Exception {
		String username = UserSessionUtilty.getUsername();
		if (!isValidate)
			return ERROR;
		BeanOrder order = new BeanOrder();
		// order地址处理
		OrderService.Address address = new OrderService.Address();
		address.districtDetail = this.addressDetail;
		address.level1District = new DistrictBean();
		address.level1District.setID(this.districtLevel1ID);
		BeanDistrict level1 = daoDistrict.getDistrictById(districtLevel1ID);
		if (level1 != null) {
			address.level1District.setName(level1.getName());
			address.level1District.setID(level1.getId());
		} else {
			return ERROR;
		}
		address.level2District = new DistrictBean();
		address.level2District.setID(this.districtLevel2ID);
		BeanDistrict level2 = daoDistrict.getDistrictById(districtLevel2ID);
		if (level2 != null)
			address.level2District.setName(level2.getName());
		else {
			return ERROR;
		}
		address.level3District = new DistrictBean();
		address.level3District.setID(this.districtLevel3ID);
		BeanDistrict level3 = daoDistrict.getDistrictById(districtLevel3ID);
		if (level3 != null)
			address.level3District.setName(level3.getName());
		else
			return ERROR;
		order.setAddrDetail(OrderService.serializerAddress(address));
		order.setMobilenum(getMobileNum());
		order.setPhonenum(getPhoneNum());
		order.setReceiverName(getReceiverName());
		order.setZipcode(getZipcode());
		order.setDeliveryWay("0");
		order.setPayment("0");

		order.setList(new ArrayList<BeanOrderDetail>());

		List<ItemTinyBean> items = new ArrayList<ItemTinyBean>();
		HashMap<Integer, Integer> orders = UserSessionUtilty.getOrder();
		Iterator<Entry<Integer, Integer>> ordersIterator = orders.entrySet()
				.iterator();
		while (ordersIterator.hasNext()) {
			Map.Entry<Integer, Integer> itemMap = ordersIterator.next();
			ItemTinyBean itemTinyBean = new ItemTinyBean();
			itemTinyBean.setItemID(itemMap.getKey());
			itemTinyBean.setItemNumber(itemMap.getValue());
			if (daoBook.getInventory(itemTinyBean.getItemID()) < itemTinyBean
					.getItemNumber())
				return ERROR;
			items.add(itemTinyBean);
		}

		float totalPrice = 0;

		for (int i = 0; i < items.size(); i++) {
			if (daoBook.getInventory(items.get(i).getItemID()) < items.get(i)
					.getItemNumber())
				daoBook.setInventory(items.get(i).getItemID(),
						daoBook.getInventory(items.get(i).getItemID())
								- items.get(i).getItemNumber());

			BeanBook book = daoBook.getDetail(items.get(i).getItemID());
			if (book == null)
				continue;
			BeanOrderDetail orderDetail = new BeanOrderDetail();
			orderDetail.setAmount(items.get(i).getItemNumber());
			orderDetail.setBookId(items.get(i).getItemID());
			orderDetail.setBookName(book.getBookName());
			orderDetail.setDiscount(book.getDiscount());
			orderDetail.setIsbn(book.getIsbn());
			orderDetail.setMarketPrice(book.getMarketPrice());
			order.getList().add(orderDetail);
			totalPrice += book.getMarketPrice() * book.getDiscount() * items.get(i).getItemNumber();
			String URL = "itemDetail?itemID=" + book.getBookId();
			// 通知推荐系统用户购买
			for (int j = 0; j < items.get(i).getItemNumber(); j++) {
				recommandService.buy(UserSessionUtilty.getSessionID(),
						Integer.toString(items.get(i).getItemID()),
						book.getBookDesc(), URL,
						UserSessionUtilty.getUsername());
			}

		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		order.setOrderDate(sdf.format(date));

		order.setTotalprice(totalPrice - score * 0.1f);
		BeanLevel level = daoLevel.judgeLevel(daoUser.getScore(username));
		if (level != null)
			order.setScore((int) (totalPrice * level.getRate()));
		order.setUsername(username);
		order.setZipcode(getZipcode());
		int inventory = 0;
		for (int i = 0; i < order.getList().size(); i++) {
			inventory = daoBook
					.getInventory(order.getList().get(i).getBookId());
			if (inventory > items.get(i).getItemNumber()) {
				if (!daoBook.setInventory(order.getList().get(i).getBookId(),
						inventory - items.get(i).getItemNumber())) {
					for (int j = 0; j < i; j++) {
						daoBook.setInventory(
								order.getList().get(j).getBookId(),
								daoBook.getInventory(order.getList().get(j)
										.getBookId())
										+ order.getList().get(i).getAmount());
					}
					return ERROR;
				}
			}
			if (!daoCart.modifyBook(UserSessionUtilty.getUsername(), order
					.getList().get(i).getBookId(), UserSessionUtilty.getCart()
					.get(order.getList().get(i).getBookId())
					- order.getList().get(i).getAmount())) {
				return ERROR;
			}
		}
		orderID = daoOrder.addOrder(order);
		if (orderID > 0) {
			UserSessionUtilty.removeOrder();
			daoUser.updateScore(
					daoUser.getScore(UserSessionUtilty.getUsername()) - score,
					UserSessionUtilty.getUsername());
			for (int i = 0; i < order.getList().size(); i++) {
				for(int j = 0; j < order.getList().get(i).getAmount(); j ++)
					UserSessionUtilty.deleteFromCart(order.getList().get(i)
						.getBookId());
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public void validate() {
		if (score == null)
			score = 0;
		if (getDistrictLevel1ID() == null || getDistrictLevel2ID() == null
				|| getDistrictLevel3ID() == null || getMobileNum() == null
				|| getPhoneNum() == null || getReceiverName() == null
				|| getZipcode() == null) {
			isValidate = false;
			return;
		}
		if (daoUser.getScore(UserSessionUtilty.getUsername()) < score) {
			isValidate = false;
			return;
		}

		isValidate = true;

		isValidate = true;
		clearErrorsAndMessages();
		if (getDistrictLevel1ID() == null || getDistrictLevel1ID() <= 0
				|| getDistrictLevel2ID() == null || getDistrictLevel2ID() <= 0
				|| getDistrictLevel3ID() == null || getDistrictLevel3ID() <= 0) {
			isValidate = false;
			return;
		}

		if (getMobileNum() == null || getMobileNum().isEmpty()
				|| !getMobileNum().matches("[1]{1}[3,5,8,6]{1}[0-9]{9}")) {
			if (getPhoneNum() == null || getPhoneNum().isEmpty()
					|| !getPhoneNum().matches("^[0]\\d{2,3}\\d{7,8}")) {
				isValidate = false;
				return;
			}
		}

		if (!getZipcode().matches("^[1-9]\\d{5}")) {
			isValidate = false;
			return;
		}

		if (getAddressDetail() == null || getAddressDetail().isEmpty()) {
			isValidate = false;
			return;
		}

		if (getReceiverName() == null || getReceiverName().isEmpty()) {
			isValidate = false;
			return;
		}
	}

	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
	}

	public void setDaoDistrict(DaoDistrict daoDistrict) {
		this.daoDistrict = daoDistrict;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

	public RecommendService getRecommandService() {
		return recommandService;
	}

	public void setRecommandService(RecommendService recommandService) {
		this.recommandService = recommandService;
	}

	public DaoUser getDaoUser() {
		return daoUser;
	}

	public void setDaoUser(DaoUser daoUser) {
		this.daoUser = daoUser;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public DaoCart getDaoCart() {
		return daoCart;
	}

	public void setDaoCart(DaoCart daoCart) {
		this.daoCart = daoCart;
	}

}
