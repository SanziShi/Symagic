package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.symagic.common.db.pool.ConnectionPool;

/**
 * 封装购物车操作的类
 * @author wanran
 *
 */
public class DaoCart {
	
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;
	private int count = 0;
	
	
	/**
	 * 向购物车添加书籍
	 * @param username	指定用户名
	 * @param bookID	指定书籍ID
	 * @param bookNumber	购买此书籍的个数
	 * @return	true 添加书籍成功	false 添加书籍失败
	 */
	public boolean addBook(String username, int bookID, int bookNumber)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("insert into cart " +
					"(username, adddate" +
					",bookid, amount) values" +
					"(?, date(now()), ?, ?)");
			ps.setString(1, username);
			ps.setInt(2, bookID);
			ps.setInt(3, bookNumber);
			
			ps.execute();
			
			if (ps.getUpdateCount() == 1) {
				conn.close();
				return true;
			}
				
			conn.close();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("delete from cart where username=? and bookid=?");
			ps.setString(1, username);
			ps.setInt(2, bookID);
			
			ps.execute();
			
			if (ps.getUpdateCount() == 1) {
				conn.close();
				return true;
			}
			conn.close();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 修改指定用户购物车中指定书籍的购买信息（只有购买数量）
	 * 如果bookNumber为0，就直接删除这条记录
	 * @param username	指定用户名
	 * @param bookID	指定书籍ID
	 * @param bookNumber	指定要修改的购买数量
	 * @return	true 修改成功	false 修改失败
	 */
	public boolean modifyBook(String username, int bookID, int bookNumber)
	{
		// 如果设置的书籍订购数目为零，就直接删除这条记录
		if (bookNumber == 0) {
			if (this.deleteBook(username, bookID))
				return true;
			return false;
		}
		// 不为0执行
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("update cart set amount=? where username=? and bookid=?");
			ps.setInt(1, bookNumber);
			ps.setString(2, username);
			ps.setInt(3, bookID);
			
			if (ps.executeUpdate() == 1) {
				conn.close();
				return true;
			}
			conn.close();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
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
