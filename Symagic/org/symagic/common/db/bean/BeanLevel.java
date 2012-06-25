package org.symagic.common.db.bean;

/**
 * 用户等级表操作封装类
 * @author wanran
 *
 */
public class BeanLevel {
	/**
	 * 等级ID,默认为null,Score_Level表主键
	 */
	Integer	id	= null;
	
	/**
	 * 等级名，可自定义，不写死
	 */
	String name	= null;
	
	/**
	 * 等级上界
	 */
	Integer upLimit	= null;
	
	/**
	 * 等级下界
	 */
	Integer lowLimit	= null;
	
	Integer rate	= null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUpLimit() {
		return upLimit;
	}

	public void setUpLimit(Integer upLimit) {
		this.upLimit = upLimit;
	}

	public Integer getLowLimit() {
		return lowLimit;
	}

	public void setLowLimit(Integer lowLimit) {
		this.lowLimit = lowLimit;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
	
	
}
