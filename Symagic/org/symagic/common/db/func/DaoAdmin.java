package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.symagic.common.db.pool.ConnectionPool;

/**
 * 管理员表操作类
 * @author wanran
 *
 */
public class DaoAdmin {
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;
	
	/**
	 * 验证管理员
	 * @param adminName	管理员名
	 * @param pwd	管理员密码
	 * @return	true 系统存在指定管理员	false 系统不存在指定管理员
	 */
	public boolean validateAdmin(String adminName, String pwd)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select count(*) from admin where adminname=? and password=?");
			ps.setString(1, adminName);
			ps.setString(2, Util.getMD5(pwd.getBytes()));
			rs	= ps.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) == 1)  {
					conn.close();
					return true;
				}
			}
			conn.close();
			return false;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return true;
	}
	
	public boolean updatePasswrod(String adminName, String oldPwd, String newPwd)
	{
		if (!this.validateAdmin(adminName, oldPwd))
			return false;
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("update admin set password=?");
			ps.setString(1, Util.getMD5(newPwd.getBytes()));
			if (ps.executeUpdate()==1)
				return true;
			return false;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
