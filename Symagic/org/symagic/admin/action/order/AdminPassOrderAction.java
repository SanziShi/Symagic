package org.symagic.admin.action.order;

import java.util.List;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.service.MailService;

import com.opensymphony.xwork2.ActionSupport;

public class AdminPassOrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2766337819712072649L;
	private List<Integer> orderIDList;
	private Boolean checkResult;

	private DaoOrder daoOrder;

	@Override
	public String execute() throws Exception {

		checkResult = false;
		
		if( orderIDList == null ) return SUCCESS;

		// 检查状态是否符合
		for (int i = 0; i < orderIDList.size(); i++) {
			BeanOrder order = daoOrder.getOrderDetail(orderIDList.get(i));
			if (!order.getOrderState().equals("0")) {
				checkResult = false;
				return super.execute();
			}
		}

		for (int i = 0; i < orderIDList.size(); i++) {
			BeanOrder order = daoOrder.getOrderDetail(orderIDList.get(i));
			if (order != null) {
				order.setOrderState("1");
				daoOrder.updateOrder(order);
				MailService.sendPassOrder(order);
			}
		}

		checkResult = true;

		return super.execute();
	}


	public Boolean getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(Boolean checkResult) {
		this.checkResult = checkResult;
	}

	public DaoOrder getDaoOrder() {
		return daoOrder;
	}

	public void setDaoOrder(DaoOrder daoOrder) {
		this.daoOrder = daoOrder;
	}


	public List<Integer> getOrderIDList() {
		return orderIDList;
	}


	public void setOrderIDList(List<Integer> orderIDList) {
		this.orderIDList = orderIDList;
	}

}
