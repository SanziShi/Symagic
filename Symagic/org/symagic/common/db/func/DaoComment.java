package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.db.pool.ConnectionPool;

/**
 * 用户评论表相关操作封装类
 * @author wanran
 *
 */
public class DaoComment {
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;
	private int count = 0;
	
	/**
	 * 返回所有评论，要分页
	 * @param page	标示显示在第几页(>=1)
	 * @param lines	标示一页显示多少行(>=1)
	 * @return	List<BeanComment> 封装评论信息的类
	 */
	public List<BeanComment> getAllComment(int page, int lines)
	{
		List<BeanComment> list	= null;
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from comment order by commentdate asc limit ?, ?");
			ps.setInt(1, (page-1)*lines);
			ps.setInt(2, lines);
			rs	= ps.executeQuery();
			list	= new ArrayList<BeanComment>();
			while (rs.next()) {
				BeanComment comment	= new BeanComment();
				comment.setBookID(rs.getInt("bookid"));
				comment.setCommentDate(rs.getString("commentdate"));
				comment.setContent(rs.getString("content"));
				comment.setRating(rs.getString("rating"));
				comment.setUsername(rs.getString("username"));
				list.add(comment);
			}
			return list;
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
		
		return list;
	}
	
	/**
	 * 根据给定条件获取给定书籍的部分评论（由于要分页，所以时部分）
	 * 
	 * @param bookID
	 *            给定书籍ID
	 * @param page
	 *            标示要第几页
	 * @param lines
	 *            标示一页要多少行（一页显示多少条记录）
	 * @return List<BeanComment>存储平路详细信息的BeanComment实例列表
	 */
	public List<BeanComment> getComment(int bookID, int page, int lines) {
		List<BeanComment> list	= null;
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from comment where bookid=? order by commentdate asc limit ?,?");
			ps.setInt(1, bookID);
			ps.setInt(2, (page - 1)*lines);
			ps.setInt(3, lines);
			rs	= ps.executeQuery();
			list	= new ArrayList<BeanComment>();
			while (rs.next()) {
				BeanComment	comment	= new BeanComment();
				comment.setBookID(rs.getInt("bookid"));
				comment.setCommentDate(rs.getString("commentdate"));
				comment.setContent(rs.getString("comment"));
				comment.setRating(rs.getString("rating"));
				comment.setUsername(rs.getString("username"));
				list.add(comment);
			}
			return list;
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
		return list;
	}
	
	/**
	 * 发表评论，向comment表中插入一条记录
	 * @param comment	评论的详细信息
	 * @return	true 插入成功	false 插入失败
	 */
	public boolean publishComment(BeanComment comment)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("insert into comment " +
					"(username, bookid, " +
					" content, rating, commentdate)" +
					"values " +
					"(?, ?, ?, ?, ?)");
			ps.setString(1, comment.getUsername());
			ps.setInt(2, comment.getBookID());
			ps.setString(3, comment.getContent());
			ps.setString(4, comment.getRating());
			ps.setString(5, comment.getCommentDate());
			
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
		return false;
	}
	
	
	/**
	 * 获取给定书籍ID的评论条数
	 * 
	 * @param bookID
	 * @return >=0 成功成功，评论条数	-1 查询失败 
	 */
	public int getCommnetNumber(int bookID) {
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select count(*) from comment where bookid=?");
			ps.setInt(1, bookID);
			rs	= ps.executeQuery();
			
			// 如果查询成功，则返回评论数目
			if (rs.next()) {
				conn.close();
				return rs.getInt(1);
			}
			
			// 若查询失败,则返回-1
			conn.close();
			return -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	/**
	 * 获取所有用户对指定书籍的平均评分
	 * @param bookID	指定用户ID
	 * @return	-1 查询失败	>=0 查询成功
	 */
	public int getAverageRating(int bookID)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select rating from comment where bookid=? ");
			ps.setInt(1, bookID);
			rs	= ps.executeQuery();
			int sum	= 0;
			while(rs.next()) {
				sum += rs.getInt(1);
				count++;
			}
			if (count != 0)
				return sum / count;
			
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
		return -1;
	}
	
	/**
	 * 获取所有订单数量
	 * @return	-1 查询出错	>=0 查询成功
	 */
	public int getAllCommmentRowNum()
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select count(*) from comment");
			rs	= ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
			return -1;
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
		return -1;
	}
	
	
	/**
	 * 删除指定用户对指定书籍的评论
	 * @param bookid	指定书籍ID
	 * @param username	指定用户名
	 * @return	true 删除成功	false 删除失败
	 */
	public boolean delete(int bookid, String username)
	{
		return false;
	}
	
}
