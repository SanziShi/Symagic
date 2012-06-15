package org.symagic.common.db.func;

/**
 * 封装购物车操作的类
 * @author wanran
 *
 */
public class DaoCart {
	
	/**
	 * 向购物车添加书籍
	 * @param username	指定用户名
	 * @param bookID	指定书籍ID
	 * @param bookNumber	购买此书籍的个数
	 * @return	true 添加书籍成功	false 添加书籍失败
	 */
	public boolean addBook(String username, int bookID, int bookNumber)
	{
		return true;
	}
	
	/**
	 * 删除指定用户购物车中指定书籍
	 * @param username	指定用户名
	 * @param bookID	指定删除书籍ID
	 * @return	true 删除成功	false 删除失败
	 */
	public boolean deleteBook(String username, int bookID)
	{
		return true;
	}
	
	/**
	 * 修改指定用户购物车中指定书籍的购买信息（只有购买数量）
	 * @param username	指定用户名
	 * @param bookID	指定书籍ID
	 * @param bookNumber	指定要修改的购买数量
	 * @return	true 修改成功	false 修改失败
	 */
	public boolean modifyBook(String username, int bookID, int bookNumber)
	{
		return true;
	}
	
	/**
	 * 清空给定用户的购物车
	 * @param username	指定用户名
	 * @return true 清空成功	false 清空失败
	 */
	public boolean clean(String username)
	{
		return true;
	}
}
