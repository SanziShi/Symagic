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
	 * 返回所有订单，要分页
	 * @param page	标示显示在第几页(>=1)
	 * @param lines	标示一页显示多少行(>=1)
	 * @return	List<BeanComment> 封装评论信息的类
	 */
	public List<BeanComment> getAllComment(int page, int lines)
	{
		List<BeanComment> list	= null;
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from comment order by id asc limit ?, ?");
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
		BeanComment	bc;
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from comment where ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<BeanComment>();
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
	
}
