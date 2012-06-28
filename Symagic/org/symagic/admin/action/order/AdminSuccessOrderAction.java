package org.symagic.admin.action.order;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

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
	private String orderIDList;
	private Boolean changeResult;

	private DaoOrder daoOrder;
	private DaoUser daoUser;

	@Override
	public String execute() throws Exception {

		changeResult = false;

		JSON json = JSONSerializer.toJSON(orderIDList);

		if (json.isArray()) {

			// 检查状态是否符合(是否全部是已审核状态）
			JSONArray ids = (JSONArray) json;
			for (int i = 0; i < ids.size(); i++) {
				BeanOrder order = daoOrder.getOrderDetail(ids.getInt(i));
				if (!order.getOrderState().equals("1")) {
					changeResult = false;
					return super.execute();
				}
			}

			// 设置为交易成功状态，并给相应用户添加积分
			for (int i = 0; i < ids.size(); i++) {
				BeanOrder order = daoOrder.getOrderDetail(ids.getInt(i));
				if (order != null) {
					order.setOrderState("2");
					daoOrder.updateOrder(order);
					BeanUser user = daoUser.getUser(order.getUsername());
					daoUser.updateScore(user.getScore() + order.getScore(), order.getUsername());
					MailService.sendSuccessOrder(order);
				}
			}

			changeResult = true;

		}

		return super.execute();
	}

	public String getOrderIDList() {
		return orderIDList;
	}

	public void setOrderIDList(String orderIDList) {
		this.orderIDList = orderIDList;
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
}
