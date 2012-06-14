package org.symagic.common.db.bean;

/**
 * 封装District表中必要信息
 * @author wanran
 *
 */
public class BeanDistrict {
	
	/**
	 * 地区ID
	 */
	private int id	= 0;
	
	/**
	 * 地区名
	 */
	private String name	= "";
	
	/**
	 * 地区等级，共分为1、2、3个级别
	 */
	private int	level	= 0;	 
	
	/**
	 * 此地区对应的上一级地区ID
	 */
	private int upid	= 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUpid() {
		return upid;
	}

	public void setUpid(int upid) {
		this.upid = upid;
	}	
	
	
}
