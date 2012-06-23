package org.symagic.admin.action.index;

import java.util.List;

import org.symagic.common.utilty.presentation.bean.CatalogBean;

import com.opensymphony.xwork2.ActionSupport;

public class AdminIndexAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5870638099267465657L;
	
	/**
	 * 
	 */
	private List<CatalogBean> catalog;
	
	private Integer totalSalesAmount;//:总销售量
	private Float totalSalesRevenue;//：销售总额；
	private Integer userNum;//：注册用户数；
	private Integer productNum;//：产品数量；
	private Integer totalOrderAmout;//:总订单数；u
	private Integer nauditedOrderAmount;//：待审核订单数；
	private Integer finishOrderAmount;//：完成订单数；
	private List<CatalogBean> LatestOrders;//：包含订单信息对象的一维数组（每个数组成员是一个订单信息类的对象：它的成员包括：orderID：订单号，userName：用户名，orderTime：下单时间，orderStatus：订单状态）
	
	public List<CatalogBean> getCatalog() {
		return catalog;
	}
	public void setCatalog(List<CatalogBean> catalog) {
		this.catalog = catalog;
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
	public Integer getNauditedOrderAmount() {
		return nauditedOrderAmount;
	}
	public void setNauditedOrderAmount(Integer nauditedOrderAmount) {
		this.nauditedOrderAmount = nauditedOrderAmount;
	}
	public Integer getFinishOrderAmount() {
		return finishOrderAmount;
	}
	public void setFinishOrderAmount(Integer finishOrderAmount) {
		this.finishOrderAmount = finishOrderAmount;
	}
	public List<CatalogBean> getLatestOrders() {
		return LatestOrders;
	}
	public void setLatestOrders(List<CatalogBean> latestOrders) {
		LatestOrders = latestOrders;
	}
	
	
	@Override
	public String execute() throws Exception {
		
		
		
		return SUCCESS;
	}
	
	

}
