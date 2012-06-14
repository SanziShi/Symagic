package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.symagic.common.db.bean.BeanUser;
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
	
	/**
	 * 验证给定用户名是否可用
	 * @param username	要验证的用户名
	 * @return true: 数据库中不存在此用户名	false: 数据库中存在此用户名
	 */
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
	
	/**
	 * 添加用户，涉及两张表user和secret,先要插入user后获得userid,
	 * 再将password、userid插入secret表
	 * @param user	BeanUser对象，封装者所有有关用户的信息
	 * @return	true:	插入成功	
	 * 			false:	插入失败
	 */
	public boolean addUser(BeanUser user) 
	{
		if (!this.validateUserName(user.getUsername()))
			return false;
		try {
			conn	= ConnectionPool.getConnection();
			ps	= conn.prepareStatement("insert into user (" +
					"username, nickname, score, question, answer)," +
					"values (" +
					"?, ?, ?, ?, ?" +
					")");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getNickname());
			ps.setInt(3, user.getScore());
			ps.setString(4, user.getQuestion());
			ps.setString(5, Util.getMD5(user.getAnswer().getBytes()));
			// 执行用户插入
			if (!ps.execute()) {
				conn.close();
				return false;			
			}

			
			// 如果用户基础信息插入成功，则查询插入记录的id,将用户密码插入secret表
			ps	= conn.prepareStatement("select userid from user where username=?");	// 获取给定用户ID
			ps.setString(1, user.getUsername());
			rs	= ps.executeQuery();
			if (!rs.next()) {
				conn.close();
				return false;
			}
			ps	= conn.prepareStatement("insert into secret (" +
					"userid, password)" +
					"values (" +
					"?, ?)");
			ps.setInt(1, rs.getInt("userid"));
			ps.setString(2, Util.getMD5(user.getPassword().getBytes()));
			conn.close();
			if (!ps.execute())
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
