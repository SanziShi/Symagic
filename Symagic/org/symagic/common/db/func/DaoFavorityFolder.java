package org.symagic.common.db.func;

import java.util.List;

/**
 * 封装有关收藏夹操作的类
 * @author wanran
 *
 */
public class DaoFavorityFolder {
	
	/**
	 * 添加书籍到指定用户的收藏夹
	 * @param userid	指定用户ID
	 * @param bookID	定制要添加书籍的ID
	 * @return	true 添加成功	false 添加失败
	 */
	public boolean add(String userid, int bookID)
	{
		return false;
	}
	
	/**
	 * 在制定用户的收藏夹中删除指定ID的书籍（支持批量删除）
	 * @param username	指定用户名
	 * @param bookIDList	指定书籍ID列表
	 * @return	true 删除成功	false 删除失败
	 */
	public boolean delete(String username, List<Integer>bookIDList)
	{
		return false;
	}
}
