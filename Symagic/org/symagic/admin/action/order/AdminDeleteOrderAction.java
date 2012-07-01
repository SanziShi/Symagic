package org.symagic.admin.action.order;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.service.MailService;

import com.opensymphony.xwork2.ActionSupport;

public class AdminDeleteOrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3490439026928167021L;

	private Integer orderID;
	private Boolean deleteResult;

	private DaoOrder daoOrder;

	@Override
	public String execute() throws Exception {

		deleteResult = false;

		BeanOrder order = daoOrder.getOrderDetail(orderID);

		if (order == null)
			return super.execute();

		if (order.getOrderState().equals("2"))
			return super.execute();

		if (!daoOrder.deleteOrder(orderID))
			return super.execute();

		deleteResult = true;

		MailService.sendDeleteOrder(order);

		return super.execute();
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

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

}
