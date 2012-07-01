package org.symagic.admin.action.order;

import java.util.List;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.db.func.DaoUser;
import org.symagic.common.service.MailService;

import com.opensymphony.xwork2.ActionSupport;

public class AdminSuccessOrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3198437970465011919L;
	private List<Integer> orderIDList;
	private Boolean changeResult;

	private DaoOrder daoOrder;
	private DaoUser daoUser;

	@Override
	public String execute() throws Exception {

		changeResult = false;

		if (orderIDList == null)
			return super.execute();

		// 检查状态是否符合(是否全部是已审核状态）
		for (int i = 0; i < orderIDList.size(); i++) {
			BeanOrder order = daoOrder.getOrderDetail(orderIDList.get(i));
			if (order == null || !order.getOrderState().equals("1")) {
				return super.execute();
			}
		}

		// 设置为交易成功状态，并给相应用户添加积分
		for (int i = 0; i < orderIDList.size(); i++) {
			BeanOrder order = daoOrder.getOrderDetail(orderIDList.get(i));
			if (order != null) {
				order.setOrderState("2");
				daoOrder.updateOrder(order);
				BeanUser user = daoUser.getUser(order.getUsername());
				daoUser.updateScore(user.getScore() + order.getScore(),
						order.getUsername());
				MailService.sendSuccessOrder(order);
			}
		}

		changeResult = true;

		return super.execute();
	}

	

	public Boolean getChangeResult() {
		return changeResult;
	}

	public void setChangeResult(Boolean changeResult) {
		this.changeResult = changeResult;
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



	public List<Integer> getOrderIDList() {
		return orderIDList;
	}



	public void setOrderIDList(List<Integer> orderIDList) {
		this.orderIDList = orderIDList;
	}
}
