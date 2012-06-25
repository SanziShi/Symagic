package org.symagic.admin.action.order;

import org.symagic.common.action.order.OrderDetailAction;

public class AdminOrderEditEnterAction extends OrderDetailAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -47361816799225643L;

	@Override
	public String execute() throws Exception {
		
		String result = super.execute();
		
		if( order == null || order.getOrderState().equals("2") || order.getOrderState().equals("3") )
			return ERROR;
		
		return result;
		
	}
	

}
