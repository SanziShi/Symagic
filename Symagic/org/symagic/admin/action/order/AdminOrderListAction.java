package org.symagic.admin.action.order;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.service.OrderService;
import org.symagic.common.service.OrderService.OrderListResult;
import org.symagic.common.utilty.presentation.bean.OrderBean;
import org.symagic.common.utilty.presentation.bean.TimeBean;

public class AdminOrderListAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -701411314034058583L;
	
	
	private TimeBean startTime;//:开始索引时间(year:年，month:月;day:日）;
	private TimeBean endTime;//结束索引时间（year：年，month:月，day:日），
	private Integer orderState;//:订单状态(0：未指定，1：已下单，2：已审核，3：交易成功，4：交易失败）,
	private Integer page;//:分页
	private String userName;
	
	private List<OrderBean> orderList;
	private Integer totalPage;
	
	private OrderService orderService;
	
	private Integer lines;
	
	
	private Integer searchStartYear;
	private Integer searchEndYear;
	private Integer searchYearRange;
	
	@Override
	public String execute() throws Exception {
		
		GregorianCalendar calendar = new GregorianCalendar();
		searchEndYear = calendar.get(Calendar.YEAR);
		searchStartYear = calendar.get(Calendar.YEAR) - searchYearRange;
		
		GregorianCalendar startCalendar = null;
		GregorianCalendar endCalendar = null;
		if (startTime != null) {
			startCalendar = new GregorianCalendar(startTime.getYear(),
					startTime.getMonth() - 1, startTime.getDay());
		}
		if (endTime != null) {
			endCalendar = new GregorianCalendar(
					endTime.getYear(), endTime.getMonth() - 1, endTime.getDay());

			if (startCalendar != null
					&& endCalendar.getTime().before(startCalendar.getTime())) {
				return ERROR;
			}
		}
		
		if( page == null )
			page = 1;
		
		OrderListResult result = orderService.orderList(userName, lines, page, startCalendar == null ? null : startCalendar.getTime() , endCalendar == null ? null : endCalendar.getTime(), orderState);
		
		if( result == null ) return ERROR;
		
		orderList = result.orders;
		totalPage = result.totalPage;
		
		return super.execute();
	}


	public TimeBean getStartTime() {
		return startTime;
	}


	public void setStartTime(TimeBean startTime) {
		this.startTime = startTime;
	}


	public TimeBean getEndTime() {
		return endTime;
	}


	public void setEndTime(TimeBean endTime) {
		this.endTime = endTime;
	}


	public Integer getOrderState() {
		return orderState;
	}


	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}


	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public List<OrderBean> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<OrderBean> orderList) {
		this.orderList = orderList;
	}


	public Integer getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}


	public OrderService getOrderService() {
		return orderService;
	}


	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	public Integer getLines() {
		return lines;
	}


	public void setLines(Integer lines) {
		this.lines = lines;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Integer getSearchStartYear() {
		return searchStartYear;
	}


	public void setSearchStartYear(Integer searchStartYear) {
		this.searchStartYear = searchStartYear;
	}


	public Integer getSearchEndYear() {
		return searchEndYear;
	}


	public void setSearchEndYear(Integer searchEndYear) {
		this.searchEndYear = searchEndYear;
	}


	public Integer getSearchYearRange() {
		return searchYearRange;
	}


	public void setSearchYearRange(Integer searchYearRange) {
		this.searchYearRange = searchYearRange;
	}
	
}
