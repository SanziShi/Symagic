package org.symagic.user.action.order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.service.OrderService;
import org.symagic.common.service.OrderService.OrderListResult;
import org.symagic.common.utilty.presentation.bean.OrderBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class OrderListAction extends CatalogBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 399162968035693232L;

	private static final int ITEM_PER_PAGE = 10;
	
	private List<OrderBean> orderList;
	
	private Integer time;
	
	private Integer totalPage;
	
	private Integer orderStatus;
	
	private Integer page;
	
	private Integer currentPage;
	
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

	public String execute() throws Exception{
		orderList = new ArrayList<OrderBean>();
		String username = UserSessionUtilty.getUsername();
		if(username == null)
			return ERROR;
		Calendar calender = Calendar.getInstance();
		Date end = calender.getTime();
		int amount = -1;
		if(time == null)
			time = 0;
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
		//为了让修订后orderList工作
		if(page == null || page == 0)
			page = 1;
		
		OrderListResult result = orderService.orderList(username, ITEM_PER_PAGE, 1, start, end, orderStatus);
		orderList = result.orders;
		for(int i = 0; i < orderList.size(); i ++){
			if(orderList.get(i).getOrderStatus().equals("已下单"))
				orderList.get(i).setEditable(true);
			else {
				orderList.get(i).setEditable(false);
			}
		}
		
		totalPage = result.totalPage;
		currentPage = page;
		return super.execute();
	}
}
