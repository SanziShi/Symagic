package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanDistrict;
import org.symagic.common.db.pool.ConnectionPool;

/**
 * 用于处理District表相关操作
 * 可以提供省、市、区的名字和三者之间的关系
 * @author wanran
 *
 */
public class DaoDistrict {
	
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;
	private int count = 0;
	
	/**
	 * 获取地区upID等于指定upID的地区详细信息
	 * @param upID	上积极地址ID
	 * @return	List<BeanDistrict> 存储者BeanDistrict对象的列表
	 */
	public List<BeanDistrict> getDistrict(Integer upID)
	{
		List<BeanDistrict> list	= null;
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			if (upID == null)
				ps = conn.prepareStatement("select * from district where level=1");
			else {
				ps	= conn.prepareStatement("select * from district where upid=?");
				ps.setInt(1, upID);
			}
				
			rs	= ps.executeQuery();
			list	= new ArrayList<BeanDistrict>();
			while (rs.next()) {
				
				BeanDistrict	bd	= new BeanDistrict();
				bd.setId(rs.getInt("id"));
				bd.setUpid(rs.getInt("upid"));
				bd.setName(rs.getString("name"));
				bd.setLevel(rs.getInt("level"));
				list.add(bd);
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
}
