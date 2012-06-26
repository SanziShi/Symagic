package org.symagic.admin.action.order;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoOrder;

import com.opensymphony.xwork2.ActionSupport;

public class AdminFailOrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3790488288537580693L;
	private String orderIDList;
	private Boolean changeResult;
	
	private DaoOrder daoOrder;
	private DaoBook daoBook;
	
	@Override
	public String execute() throws Exception {

		changeResult = false;

		JSON json = JSONSerializer.toJSON(orderIDList);

		if (json.isArray()) {

			// 检查状态是否符合
			JSONArray ids = (JSONArray) json;
			for (int i = 0; i < ids.size(); i++) {
				BeanOrder order = daoOrder.getOrderDetail(ids.getInt(i));
				if (!order.getOrderState().equals("1")) {
					changeResult = false;
					return super.execute();
				}
			}

			for (int i = 0; i < ids.size(); i++) {
				BeanOrder order = daoOrder.getOrderDetail(ids.getInt(i));
				order.setOrderState("3");
				daoOrder.updateOrder(order);
			}

			changeResult = true;

		}

		return super.execute();
	}

}
