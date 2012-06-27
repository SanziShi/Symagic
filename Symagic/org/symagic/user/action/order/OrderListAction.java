package org.symagic.user.action.order;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.db.func.OrderRequire;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.OrderBean;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.validator.annotations.DateRangeFieldValidator;

public class OrderListAction extends CatalogBase{
	
	private static final int ITEM_PER_PAGE = 10;
	
	private List<OrderBean> orderList;
	
	private Integer time;
	
	private Integer totalPage;
	
	private Integer orderStatus;
	
	private Integer page;
	
	private Integer currentPage;
	
	private boolean isValidate;
	
	private OrderService orderService;

	public List<OrderBean> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderBean> orderList) {
		this.orderList = orderList;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatusString) {
		this.orderStatus = orderStatusString;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer pageInteger) {
		this.page = pageInteger;
	}
	
	public void setCurrentPage(Integer currentPageInt){
		this.currentPage = currentPageInt;
	}
	
	public Integer getCurrentpage(){
		return this.currentPage;
	}
	
	public OrderService getOrderService(){
		return this.orderService;
	}
	
	public void setOrderService(OrderService orderService){
		this.orderService = orderService;
	}
	
	/**
	 * 注意：天知道这个的dao调用时怎么样的。。。
	 */
	public String execute() throws Exception{
		//获得catalog
		super.execute();
		orderList = new ArrayList<OrderBean>();
		String username = UserSessionUtilty.getUsername();
		Calendar calender = Calendar.getInstance();
		Date end = calender.getTime();
		int amount = -1;
		switch(time){
		case 0:
			amount = 0;
			calender.set(1970, 1, 1);
			break;
		case 1:
			amount = -1;
			break;
		case 2:
			amount = -3;
			break;
		case 3:
			amount = -6;
			break;
		}
		calender.add(Calendar.MONTH, amount);
		Date start = calender.getTime();
		String orderState = orderStatus.toString();
		orderService.orderList(username, ITEM_PER_PAGE, page, start, end, orderState);
		currentPage = page;
		return SUCCESS;
	}
	
	public void validate(){
		if(!UserSessionUtilty.isLogin()){
			isValidate = false;
			return;
		}
		if(time < 0 || time > 4){
			isValidate = false;
			return;
		}
		if(page > totalPage){
			isValidate = false;
			return;
		}
		if(orderStatus < 0 || orderStatus > 4){
			isValidate = false;
			return;
		}
		isValidate = true;
	}
}
