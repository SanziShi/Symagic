package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.pool.ConnectionPool;

/**
 * 封装有关收藏夹操作的类
 * @author wanran
 *
 */
public class DaoFavorityFolder {
	
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;
	private int count = 0;
	
	/**
	 * 添加书籍到指定用户的收藏夹
	 * @param username	指定用户名
	 * @param bookID	定制要添加书籍的ID
	 * @return	true 添加成功	false 添加失败
	 */
	public boolean add(String username, int bookID)
	{
		try {
			BeanBook book	= new BeanBook();
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from book where bookid=?");
			ps.setInt(1, bookID);
			rs	= ps.executeQuery();
			rs.next();
			
			ps	= conn.prepareStatement("insert into favority_folder (username, bookid," +
					"isbn, publisher, " +
					"publishdate, marketprice) values (" +
					"?, ?, ?, ?, ?, ?)");
			ps.setString(1, username);
			ps.setInt(2, bookID);
			ps.setString(3, rs.getString("isbn"));
			ps.setString(4, rs.getString("publisher"));
			ps.setString(5, rs.getString("publishdate"));
			ps.setFloat(6, rs.getFloat("marketprice"));
			
			ps.execute();
			
			if (ps.getUpdateCount() == 1)
				return true;
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("delete from favority_folder where username=? and " +
					"bookid=?");
			ps.setString(1, username);
			
			for (int i=0; i<bookIDList.size(); i++) {
				ps.setInt(2, bookIDList.get(i));
				ps.execute();	// 后期这里需要处理
				count++;
			}
			if (count == bookIDList.size())
				return true;
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
