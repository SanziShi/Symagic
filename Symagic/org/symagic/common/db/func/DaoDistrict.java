package org.symagic.common.db.func;

import org.symagic.common.db.bean.BeanDistrict;

/**
 * 用于处理District表相关操作
 * 可以提供省、市、区的名字和三者之间的关系
 * @author wanran
 *
 */
public class DaoDistrict {
	
	public BeanDistrict getDistrict()
	{
		return new BeanDistrict();
	}
}
