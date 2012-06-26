package org.symagic.admin.action.order;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoOrder;

import com.opensymphony.xwork2.ActionSupport;

public class AdminPassOrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2766337819712072649L;
	private String orderIDList;
	private Boolean checkResult;

	private DaoOrder daoOrder;

	@Override
	public String execute() throws Exception {

		checkResult = false;

		JSON json = JSONSerializer.toJSON(orderIDList);

		if (json.isArray()) {

			// 检查状态是否符合
			JSONArray ids = (JSONArray) json;
			for (int i = 0; i < ids.size(); i++) {
				BeanOrder order = daoOrder.getOrderDetail(ids.getInt(i));
				if (!order.getOrderState().equals("0")) {
					checkResult = false;
					return super.execute();
				}
			}

			for (int i = 0; i < ids.size(); i++) {
				BeanOrder order = daoOrder.getOrderDetail(ids.getInt(i));
				if (order != null) {
					order.setOrderState("1");
					daoOrder.updateOrder(order);
				}
			}

			checkResult = true;

		}

		return super.execute();
	}

	public String getOrderIDList() {
		return orderIDList;
	}

	public void setOrderIDList(String orderIDList) {
		this.orderIDList = orderIDList;
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

}
