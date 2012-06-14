package org.symagic.common.db.func;

import org.symagic.common.db.bean.BeanBook;

/**
 * 封装有关书籍表操作的类
 * @author wanran
 *
 */
public class DaoBook {

	/**
	 * 获取给定书籍的库存
	 * @param bookID	指定书籍
	 * @return	int 库存量
	 */
	public int getInventory(int bookID)
	{
		return 0;
	}
	
	/**
	 * 添加书籍
	 * @param book	封装者书籍详细信息的BeanBook实例
	 * @return	true 添加成功	false 添加失败
	 */
	public boolean addBook(BeanBook book)
	{
		return true;
	}
	
	/**
	 * 设置给定书籍的库存量
	 * @param bookID	指定特定书籍
	 * @param inventory	指定库存量
	 * @return	true 设置成功	false 设置失败
	 */
	public boolean setInventory(int bookID, int inventory)
	{
		return true;
	}
	
	/**
	 * 获得指定书籍的书籍详细信息
	 * @param bookID	指定书籍ID
	 * @return	BeanBook实例，封装着书籍的详细信息
	 */
	public BeanBook getDeatil(int bookID)
	{
		return new BeanBook();
	}
	
	
}
