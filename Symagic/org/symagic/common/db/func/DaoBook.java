package org.symagic.common.db.func;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanComment;

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
	
	/**
	 * 按照给定条件搜索书籍
	 * @param req	封装书籍搜索条件的对象
	 * @return	List<BeanBook> 存储着BeanBook对象的列表
	 */
	public List<BeanBook> search(BookRequire req)
	{
		return new ArrayList<BeanBook>();
	}
	
	/**
	 * 获取符合书籍搜索条件的书籍条数
	 * @param req	条件
	 * @return	int 符合的条数
	 */
	public int getSearchRowNumber(BookRequire req)
	{
		return 0;
	}
	
	/**
	 * 获取给定书籍ID的评论条数
	 * @param bookID
	 * @return 返回评论数目
	 */
	public int getCommnetNumber(int bookID)
	{
		return 0;
	}
	
	
	/**
	 * 根据给定条件获取给定书籍的部分评论（由于要分页，所以时部分）
	 * @param bookID	给定书籍ID
	 * @param page	标示要第几页
	 * @param lines	标示一页要多少行（一页显示多少条记录）
	 * @return	List<BeanComment>存储平路详细信息的BeanComment实例列表
	 */
	public List<BeanComment> getComment(int bookID, int page, int lines)
	{
		return new ArrayList<BeanComment>();
	}
	
	
	
	
}
