package org.symagic.admin.action.order;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoOrder;

import com.opensymphony.xwork2.ActionSupport;

public class AdminDeleteOrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3490439026928167021L;
	
	private Integer orderID;
	private Boolean deleteReslult;
	
	private DaoOrder daoOrder;

	@Override
	public String execute() throws Exception {
		
		deleteReslult = false;
		
		BeanOrder order = daoOrder.getOrderDetail(orderID);
		
		if( order.getOrderState().equals("2") ) return super.execute();
		
		//if( daoOrder.deleteOrder(username, orderID))
		
		return super.execute();
	}
	

}
