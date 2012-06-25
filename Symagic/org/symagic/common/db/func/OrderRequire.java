package org.symagic.common.db.func;

/**
 * 封装所有搜索条件的类
 * 所有属性都为public,同c种struct
 * @author wanran
 *
 */
public class OrderRequire {
	// 内部结构待定
	
	/**
	 * startTime:开始索引时间(year:年，month:月;day:日）;
	 */
	private String startTime	= null;
	
	/**
	 * endTime:结束索引时间（year：年，month:月，day:日），
	 */
	private String endTime	= null;
	
	/**
	 * orderState:订单状态(0：未指定，1：已下单，2：已审核，3：交易成功，4：交易失败）,
	 */
	private String orderState	= null;
	
	/**
	 * page:分页
	 */
	private Integer	page	= null;
	
	/**
	 * 每页显示条数
	 */
	private Integer	lines	= null;
	
	 /**
	  * 销售量下线
	  */
	private Integer lowSales	= null;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLines() {
		return lines;
	}

	public void setLines(Integer lines) {
		this.lines = lines;
	}

	public Integer getLowSales() {
		return lowSales;
	}

	public void setLowSales(Integer lowSales) {
		this.lowSales = lowSales;
	}
	
	
}
