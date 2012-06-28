package org.symagic.admin.action.order;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.symagic.common.db.bean.BeanOrder;
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
					daoUser.getUser(username);
					// daoUser.order.getScore();(用户改积分）
					MailService.sendSuccessOrder(order);
				}
			}

			changeResult = true;

		}

		return super.execute();
	}
}
