package org.symagic.common.service;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.utilty.presentation.bean.OrderBean;
import org.symagic.common.utilty.presentation.bean.TimeBean;

public class OrderService {

	private DaoOrder daoOrder;

	/**
	 * 列出指定用户的order列表，当userName为空则列出所有。
	 * 
	 * @param startTime
	 * @param endTime
	 * @param orderState
	 * @param page
	 * @param userName
	 */
	public void orderList(TimeBean startTime, TimeBean endTime,
			Integer orderState, Integer page, String userName) {

		// OrderRequire require = new OrderRequire();

	}

	public OrderBean orderDetail(Integer orderID) {

		BeanOrder bean = daoOrder.getOrderDetail(orderID);

		return convertBeanOrder( bean );
	}

	public OrderBean convertBeanOrder(BeanOrder bean) {
		OrderBean result = new OrderBean();

		result.setOrderId(Integer.toString(bean.getOrderId()));
		switch (Integer.parseInt(bean.getOrderState())) {
		case 0:
			result.setOrderStatus("已下单");
			break;
		case 1:
			result.setOrderStatus("已审核");
			break;
		case 2:
			result.setOrderStatus("交易成功");
			break;
		case 3:
			result.setOrderStatus("交易失败");
			break;
		}
		result.setOrderTime(bean.getOrderDate());
		result.setReceiverName(bean.getReceiverName());
		// result.setScore();
		// result.setTotalPrice(bean.get)
		return result;
	}

	public DaoOrder getDaoOrder() {
		return daoOrder;
	}

	public void setDaoOrder(DaoOrder daoOrder) {
		this.daoOrder = daoOrder;
	}

}
