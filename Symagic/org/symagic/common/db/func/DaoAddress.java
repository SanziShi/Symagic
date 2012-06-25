package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanAddress;
import org.symagic.common.db.pool.ConnectionPool;
/**
 * 封装地址表操作接口的类
 * @author wanran
 *
 */
public class DaoAddress {
	
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;
	private int count = 0;
	
	/**
	 * 添加用户预存地址
	 * @param addr	封装地址详细信息的BeanAddress类实例
	 * @return	true 添加成功	false 添加失败
	 */
	public boolean addAddress(BeanAddress addr)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("insert into user_addr (" +
					"username, receivername," +
					"addrdetail, zipcode," +
					"phonenum, mobilenum) values (" +
					"?, ?, ?, ?, ?, ?)");
			ps.setString(1, addr.getUsername());
			ps.setString(2, addr.getReceivername());
			ps.setString(3, addr.getAddrdetail());
			ps.setString(4, addr.getZipcode());
			ps.setString(5, addr.getPhonenum());
			ps.setString(6, addr.getMobilenum());
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
	 * 删除预存地址
	 * @param addrID	地址ID
	 * @return	true 删除成功	false 删除失败
	 */
	public boolean deleteAddress(int addrID) 
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("delete from user_addr where addrid=?");
			ps.setInt(1, addrID);
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
	 * 用户修改预存地址信息
	 * @param addr	封装地址详细信息的BeanAddress类实例
	 * @return
	 */
	public boolean modifyAddress(BeanAddress addr)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("update user_addr set receivername=?, " +
					"addrdetail=?, zipcode=?, phonenum=?, mobilenum=? where username=?");
			ps.setString(1, addr.getReceivername());
			ps.setString(2, addr.getAddrdetail());
			ps.setString(3, addr.getZipcode());
			ps.setString(4, addr.getPhonenum());
			ps.setString(5, addr.getMobilenum());
			ps.setString(6, addr.getUsername());
			
			if (ps.executeUpdate() == 1) 
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
	 * 获取指定用户预存地址数量
	 * @param username	指定用户名
	 * @return	Integer	地址数量
	 */
	public Integer getAddressNumber(String username)
	{
		Integer	num	= 0;
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select count(*) from user_addr where username=?");
			ps.setString(1, username);
			rs	= ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
				return num;
			}
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
		return num;
	}

	/**
	 * 获取指定用户的所有预存地址详细信息
	 * @param username	指定用户名
	 * @return	null 获取出错	not null 获取成功
	 */
	public List<BeanAddress> listAddress(String username)
	{
		List<BeanAddress> list	= null;
		try {
			list	= new ArrayList<BeanAddress>();
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from user_addr where username=?");
			ps.setString(1, username);
			rs	= ps.executeQuery();
			
			while (rs.next()) {
				BeanAddress	ba	= new BeanAddress();
				ba.setAddrid(rs.getInt("addrid"));
				ba.setUsername(rs.getString("username"));
				ba.setReceivername(rs.getString("receivername"));;
				ba.setAddrdetail(rs.getString("addrdetail"));
				ba.setZipcode(rs.getString("zipcode"));
				ba.setPhonenum(rs.getString("phonenum"));
				ba.setMobilenum(rs.getString("mobilenum"));
				list.add(ba);
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
	 * 获取指定用户的指定预存地址详细信息
	 * @param username	指定用户名
	 * @param addrid	指定地址ID
	 * @return	BeanAddress 对象，封装地址信息
	 */
	public BeanAddress getAddressDetail(String username, int addrid) 
	{
		BeanAddress	addr	= null;
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from user_addr where addrid=? and username=?");
			ps.setInt(1, addrid);
			ps.setString(2, username);
			
			rs	= ps.executeQuery();
			if (rs.next()) {
				addr	= new BeanAddress();
				addr.setAddrid(rs.getInt("addrid"));
				addr.setUsername(rs.getString("username"));
				addr.setReceivername(rs.getString("receivername"));
				addr.setZipcode(rs.getString("zipcode"));
				addr.setAddrdetail(rs.getString("addrdetail"));
				addr.setMobilenum(rs.getString("mobilenum"));
				addr.setPhonenum(rs.getString("phonenum"));
				return addr;
			}
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
		return addr;
	}

}
