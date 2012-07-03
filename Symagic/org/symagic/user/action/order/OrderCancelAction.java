package org.symagic.user.action.order;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.service.MailService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author hao
 *
 */
public class OrderCancelAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5187395666876015869L;
	
	private Integer orderID;
	
	private DaoOrder daoOrder;
	
	private Boolean cancelResult;
	
	private String resultInfo;

	@Override
	public String execute() throws Exception {
		
		cancelResult = false;
		
		if( orderID == null ) 
		{
			resultInfo = "订单号错误";
			return super.execute();
		}
		
		BeanOrder order = daoOrder.getOrderDetail(orderID);
		
		if( order == null )
		{
			resultInfo = "订单号错误";
			return super.execute();
		}
		
		if( !order.getOrderState().equals("0") ){
			resultInfo = "订单已不允许修改";
			return super.execute();
		}
		
		order.setOrderState("3");
		
		if( !daoOrder.updateOrder(order) ) return super.execute();
		
		cancelResult = true;
		
		return super.execute();
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public DaoOrder getDaoOrder() {
		return daoOrder;
	}

	public void setDaoOrder(DaoOrder daoOrder) {
		this.daoOrder = daoOrder;
	}

	public Boolean getCancelResult() {
		return cancelResult;
	}

	public void setCancelResult(Boolean cancelResult) {
		this.cancelResult = cancelResult;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
	
	

}
