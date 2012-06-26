package org.symagic.admin.action.order;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
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
				if (order != null) {
					order.setOrderState("3");
					daoOrder.updateOrder(order);
					List<BeanOrderDetail> items = order.getList();
					for (BeanOrderDetail detail : items) {
						BeanBook bookDetail = daoBook.getDetail(detail
								.getBookId());
						if (bookDetail == null)
							return super.execute();
						bookDetail.setInventory(bookDetail.getInventory()
								+ detail.getAmount());
						if (!daoBook.modifyBook(bookDetail))
							return super.execute();
					}
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

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

}
