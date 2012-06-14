package org.symagic.common.db.func;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanCatalog;

/**
 * 封装书籍分类相关操作的类
 * @author wanran
 *
 */
public class DaoCatalog {
	
	/**
	 * 获取所有书籍分类
	 * @return	List<BeanCatalog> 返回存储BeanCatalog(封装分类详细信息的对象)的列表
	 */
	public List<BeanCatalog> getCatalog()
	{
		return new ArrayList<BeanCatalog>();
	}
}
