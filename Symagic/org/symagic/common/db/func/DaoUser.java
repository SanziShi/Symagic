package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.symagic.common.db.pool.ConnectionPool;

/**
 * 用于User相关数据库操作的功能封装
 * @author wanran
 *
 */
public class DaoUser {
	private Connection	conn;
	private PreparedStatement	ps;
	private Statement	st;
	private ResultSet	rs;
	
	/**
	 * 验证用户名与密码是否对应
	 * @param username	用户名
	 * @param password	密码（未加密状态）
	 * @return	true:用户名、密码对应	false:用户名密码不对应
	 */
	public boolean validateUser(String username, String password)
	{
		try {
			conn	= ConnectionPool.getConnection();
			ps	= conn.prepareStatement("select userid, username from user where username=?");	// 获取给定用户名在对应的记录信息
			ps.setString(1, username);
			rs	= ps.executeQuery();
			if (!rs.next()) {
				conn.close();
				return false;	// 如果没有给定用户名的记录，返回false
			}
			
			int id	= rs.getInt("userid");
			
			ps	= conn.prepareStatement("select password from secret where userid=?");	// 获取给定用户名对应password
			ps.setInt(1, id);
			rs	= ps.executeQuery();
			conn.close();	// 连接使用完关闭
			rs.next();
			if (password.equals(rs.getString("password")))
				return true;		// 如果给定的密码和从数据库中得到的密码相同，返回true
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validateUserName(String username)
	{
		try {
			conn = ConnectionPool.getConnection();
			ps	= conn.prepareStatement("select count(*) from user where username=?");	// 获取给定用户名在User中的个数
			ps.setString(1, username);
			rs	= ps.executeQuery();
			conn.close();	// 连接用完关闭（必要）
			
			while(rs.next()) {
				// 如果表中此名字个数不为0，则返回false，否则返回true
				if (rs.getInt(1) != 0)
					return false;
				else 
					return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}
