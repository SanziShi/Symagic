package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanCatalog;
import org.symagic.common.db.pool.ConnectionPool;

/**
 * 封装书籍分类相关操作的类
 * @author wanran
 *
 */
public class DaoCatalog {
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;
	private int count = 0;
	
	/**
	 * 获取所有书籍分类
	 * @return	List<BeanCatalog> 返回存储BeanCatalog(封装分类详细信息的对象)的列表
	 */
	public List<BeanCatalog> getCatalog()
	{
		ArrayList<BeanCatalog> list	= null;
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from book_catalog order by level");
			rs	= ps.executeQuery();
			if (rs.next()) {
				list	= new ArrayList<BeanCatalog>();
				BeanCatalog bc	= new BeanCatalog();
				bc.setCatalogDesc(rs.getString("catalogdesc"));
				bc.setCatalogID(rs.getInt("catalogid"));
				bc.setCatalogName(rs.getString("catalogname"));
				bc.setLevel(rs.getString("level"));
				bc.setUpID(rs.getInt("upid"));
				list.add(bc);
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
	 * 添加书籍分类
	 * @param catalog 封装着分类信息的BeanCatalog对象
	 * @return	true 添加成功	false 添加失败
	 */
	public boolean addCatalog(BeanCatalog catalog)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("insert into book_catalog " +
					"(catalogname, level, " +
					" upid, catalogdesc) values" +
					"(?, ?, ?, ?)");
			ps.setString(1, catalog.getCatalogName());
			ps.setString(2, catalog.getLevel());
			ps.setInt(3, catalog.getUpID());
			ps.setString(4, catalog.getCatalogDesc());
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
	 * 修改书籍分类
	 * @param catalog 封装书籍分类信息的BeanCatalog对象
	 * @return	true 修改成功	false 修改失败
	 */
	public  boolean modifyCatalog(BeanCatalog catalog)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("update book_catalog set catalogname=?, " +
					"level=?, upid=?, catalogdesc=? where catalogid=?");
			ps.setString(1, catalog.getCatalogName());
			ps.setString(2, catalog.getLevel());
			ps.setInt(3, catalog.getUpID());
			ps.setString(4, catalog.getCatalogDesc());
			ps.setInt(5, catalog.getCatalogID());
			
			if (ps.executeUpdate() == 1) {
				return true;
			}
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
