package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	private int count = 0;
	
	public boolean validateAdmin(String adminName, String pwd)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select count(*) from admin where adminname=?, password=?");
			ps.setString(1, adminName);
			//ps.setString(parameterIndex, x)
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
