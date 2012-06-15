package org.symagic.common.db.func;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanDistrict;

/**
 * 用于处理District表相关操作
 * 可以提供省、市、区的名字和三者之间的关系
 * @author wanran
 *
 */
public class DaoDistrict {
	
	/**
	 * 获取指定地区列表
	 * @return	List<BeanDistrict> 存储者BeanDistrict对象的列表
	 */
	public List<BeanDistrict> getDistrict()
	{
		return new ArrayList<BeanDistrict>();
	}
}
