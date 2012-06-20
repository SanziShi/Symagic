package org.symagic.common.db.func;

/**
 * 封装所有搜索条件的类
 * 所有属性都为public,同c种struct
 * @author wanran
 *
 */
public class OrderRequire {
	// 内部结构待定
	
	startTime:开始索引时间(year:年，month:月;day:日）;
	endTime:结束索引时间（year：年，month:月，day:日），
	orderState:订单状态(0：未指定，1：已下单，2：已审核，3：交易成功，4：交易失败）,
	page:分页
	每页显示条数
}
