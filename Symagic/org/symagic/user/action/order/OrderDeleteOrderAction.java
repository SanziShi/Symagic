package org.symagic.user.action.order;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.func.DaoOrder;

public class OrderDeleteOrderAction extends CatalogBase {

	public Boolean getDeleteResult() {
		return deleteResult;
	}

	public void setDeleteResult(Boolean deleteResult) {
		this.deleteResult = deleteResult;
	}

	public DaoOrder getDaoOrder() {
		return daoOrder;
	}

	public void setDaoOrder(DaoOrder daoOrder) {
		this.daoOrder = daoOrder;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1986743364200997665L;
	
	private Integer orderID;
	
	private Boolean deleteResult;
	
	private DaoOrder daoOrder;
	
	public String execute() throws Exception{
		deleteResult = daoOrder.deleteOrder(orderID);
		return SUCCESS;
	}

}
