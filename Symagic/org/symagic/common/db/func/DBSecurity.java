package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.Statement;

import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.db.pool.ConnectionPool;

public class DBSecurity {
	
	public boolean verify(BeanUser user)
	{
		try {
			Connection conn = ConnectionPool.getConnection();
			Statement st = conn.createStatement();
			if (st.execute("select * from user where username='" + user.getUsername() + "'")) {
				conn.close();
				return true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
