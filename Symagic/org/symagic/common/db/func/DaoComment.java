package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
