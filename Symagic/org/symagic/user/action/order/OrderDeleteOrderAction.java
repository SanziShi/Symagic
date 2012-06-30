package org.symagic.user.action.order;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.func.DaoOrder;

public class OrderDeleteOrderAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1986743364200997665L;
	
	private Integer itemID;
	
	private Boolean deleteResult;
	
	private DaoOrder daoOrder;
	
	public String execute() throws Exception{
		deleteResult = daoOrder.deleteOrder(itemID);
		return SUCCESS;
	}

}
