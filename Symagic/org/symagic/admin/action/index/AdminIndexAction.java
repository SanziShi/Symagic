package org.symagic.admin.action.index;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.db.func.DaoUser;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.OrderBean;

public class AdminIndexAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5870638099267465657L;

	private Integer totalSalesAmount;// :总销售量
	private Float totalSalesRevenue;// ：销售总额；
	private Integer userNum;// ：注册用户数；
	private Integer productNum;// ：产品数量；
	private Integer totalOrderAmout;// :总订单数；
	private Integer unauditedOrderAmount;// ：待审核订单数；
	private Integer finishOrderAmount;// ：完成订单数；
	private List<OrderBean> LatestOrders;// ：包含订单信息对象的一维数组（每个数组成员是一个订单信息类的对象：它的成员包括：orderID：订单号，userName：用户名，orderTime：下单时间，orderStatus：订单状态）
	private Integer todayOrderAmount;

	private DaoOrder daoOrder;
	private DaoUser daoUser;
	private DaoBook daoBook;

	private OrderService orderService;

	

	@Override
	public String execute() throws Exception {

		totalSalesAmount = daoOrder.getTotalSaleAmount();
		totalSalesRevenue = daoOrder.getTotalSalesRevenue();
		userNum = daoUser.getUserNum();
		productNum = daoBook.getProductNum();
		totalOrderAmout = daoOrder.getTotalOrderAmount();
		unauditedOrderAmount = daoOrder.getUnauditedOrderAmount();
		finishOrderAmount = daoOrder.getFinishOrderAmount();
		todayOrderAmount = daoOrder.getTodayOrderNum();
		List<BeanOrder> orderList = daoOrder.getLatestOrders();
		LatestOrders = new ArrayList<OrderBean>();

		if (orderList != null) {
			for (BeanOrder beanOrder : orderList) {
				LatestOrders.add(orderService
						.convertBeanOrderToOrderBean(beanOrder));

			}
		}
		return super.execute();
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public Integer getTotalSalesAmount() {
		return totalSalesAmount;
	}

	public void setTotalSalesAmount(Integer totalSalesAmount) {
		this.totalSalesAmount = totalSalesAmount;
	}

	public Float getTotalSalesRevenue() {
		return totalSalesRevenue;
	}

	public void setTotalSalesRevenue(Float totalSalesRevenue) {
		this.totalSalesRevenue = totalSalesRevenue;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public Integer getTotalOrderAmout() {
		return totalOrderAmout;
	}

	public void setTotalOrderAmout(Integer totalOrderAmout) {
		this.totalOrderAmout = totalOrderAmout;
	}

	public Integer getFinishOrderAmount() {
		return finishOrderAmount;
	}

	public void setFinishOrderAmount(Integer finishOrderAmount) {
		this.finishOrderAmount = finishOrderAmount;
	}

	public Integer getUnauditedOrderAmount() {
		return unauditedOrderAmount;
	}

	public void setUnauditedOrderAmount(Integer unauditedOrderAmount) {
		this.unauditedOrderAmount = unauditedOrderAmount;
	}

	public List<OrderBean> getLatestOrders() {
		return LatestOrders;
	}

	public void setLatestOrders(List<OrderBean> latestOrders) {
		LatestOrders = latestOrders;
	}

	public DaoOrder getDaoOrder() {
		return daoOrder;
	}

	public void setDaoOrder(DaoOrder daoOrder) {
		this.daoOrder = daoOrder;
	}

	public DaoUser getDaoUser() {
		return daoUser;
	}

	public void setDaoUser(DaoUser daoUser) {
		this.daoUser = daoUser;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

	public Integer getTodayOrderAmount() {
		return todayOrderAmount;
	}

	public void setTodayOrderAmount(Integer todayOrderAmount) {
		this.todayOrderAmount = todayOrderAmount;
	}

}
