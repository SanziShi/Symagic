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
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoLevel;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.db.func.DaoUser;
import org.symagic.common.service.OrderService;
import org.symagic.common.service.RecommandService;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class OrderSubmitAction extends OrderBase{
	
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
	private Integer level1ID;
	
	private Integer Level2ID;
	
	private Integer level3ID;
	
	private String addressDetail;
	
	private DaoOrder daoOrder;
	
	private DaoDistrict daoDistrict;
	
	private RecommandService recommandService;
	
	private DaoBook daoBook;
	
	private DaoUser daoUser;
	
	private DaoLevel daoLevel;
	
	private Integer orderID;
	
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
	
	public DaoOrder getDaoOrder(){
		return this.daoOrder;
	}
	
	public void setDaoOrder(DaoOrder order){
		this.daoOrder = order;
	}
	
	@Override
	public String execute() throws Exception{ 
		String username = UserSessionUtilty.getUsername();
		if(!isValidate)
			return ERROR;
		BeanOrder order = new BeanOrder();
		//order地址处理
		OrderService.Address address = new OrderService.Address();
		address.districtDetail = this.addressDetail;
		address.level1District = new DistrictBean();
		address.level1District.setID(this.level1ID);
		BeanDistrict level1 = daoDistrict.getDistrictById(level1ID);
		if(level1 != null){
			address.level1District.setName(level1.getName());
			address.level1District.setID(level1.getId());
		}
		else {
			return ERROR;
		}
		address.level2District = new DistrictBean();
		address.level2District.setID(this.Level2ID);
		BeanDistrict level2 = daoDistrict.getDistrictById(Level2ID);
		if(level2 != null)
			address.level2District.setName(level2.getName());
		else{
			return ERROR;
		}
		address.level3District = new DistrictBean();
		address.level3District.setID(this.level3ID);
		BeanDistrict level3 = daoDistrict.getDistrictById(level3ID);
		if(level3 != null)
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
		Iterator<Entry<Integer, Integer>> ordersIterator = orders.entrySet().iterator();
		while(ordersIterator.hasNext()){
			Map.Entry<Integer, Integer> itemMap = ordersIterator.next();
			ItemTinyBean itemTinyBean = new ItemTinyBean();
			itemTinyBean.setItemID(itemMap.getKey());
			itemTinyBean.setItemNumber(itemMap.getValue());
			if(daoBook.getInventory(itemTinyBean.getItemID()) < itemTinyBean.getItemNumber())
				return ERROR;
			items.add(itemTinyBean);
		}

		float totalPrice = 0;
		
		for(int i = 0; i < items.size(); i ++){
			if(daoBook.getInventory(items.get(i).getItemID()) < items.get(i).getItemNumber())
				daoBook.setInventory(items.get(i).getItemID(),
						daoBook.getInventory(items.get(i).getItemID())
						- items.get(i).getItemNumber());
			
			BeanBook book = daoBook.getDetail(items.get(i).getItemID());
			if(book == null)
				continue;
			BeanOrderDetail orderDetail = new BeanOrderDetail();
			orderDetail.setAmount(items.get(i).getItemNumber());
			orderDetail.setBookId(items.get(i).getItemID());
			orderDetail.setBookName(book.getBookName());
			orderDetail.setDiscount(book.getDiscount());
			orderDetail.setIsbn(book.getIsbn());
			orderDetail.setMarketPrice(book.getMarketPrice());
			order.getList().add(orderDetail);
			totalPrice += book.getMarketPrice() - book.getDiscount();
			String URL = "/itemDetail?itemID=" + book.getBookId();
			//通知推荐系统用户购买
			for(int j = 0; j < items.get(i).getItemNumber(); j ++){
				recommandService.buy(UserSessionUtilty.getSessionID(),
						Integer.toString(items.get(i).getItemID()),
						book.getBookDesc(), URL, UserSessionUtilty.getUsername());
			}
			
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		order.setOrderDate(sdf.format(date));
		
		order.setTotalprice(totalPrice);
		BeanLevel level = daoLevel.judgeLevel(daoUser.getScore(username));
		if(level != null)
			order.setScore((int)(totalPrice * level.getRate()));
		order.setUsername(username);
		order.setZipcode(getZipcode());
		orderID = daoOrder.addOrder(order);
		if(orderID > 0){
			UserSessionUtilty.removeOrder();
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	public void validate(){
		if(daoUser.getScore(UserSessionUtilty.getUsername()) < score){
			isValidate = false;
			return;
		}
		if(getZipcode() == null || getAddressDetail() == null || getLevel1ID() == null||
				getLevel2ID() == null || getMobileNum() == null||
				getPhoneNum() == null){
			isValidate = false;
			return;
		}
			
		isValidate = true;
	}

	public Integer getLevel1ID() {
		return level1ID;
	}

	public void setLevel1ID(Integer level1id) {
		level1ID = level1id;
	}

	public Integer getLevel2ID() {
		return Level2ID;
	}

	public void setLevel2ID(Integer level2id) {
		Level2ID = level2id;
	}

	public Integer getLevel3ID() {
		return level3ID;
	}

	public void setLevel3ID(Integer level3id) {
		level3ID = level3id;
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

	public RecommandService getRecommandService() {
		return recommandService;
	}

	public void setRecommandService(RecommandService recommandService) {
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
	
}
