package org.symagic.common.service;

import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.db.func.OrderRequire;
import org.symagic.common.utilty.presentation.bean.TimeBean;

public class OrderService {
	
	private DaoOrder daoOrder;
	
	public void orderList(TimeBean startTime,TimeBean endTime, Integer orderState, Integer page) {
		
		OrderRequire require = new OrderRequire();
		
	}
}
