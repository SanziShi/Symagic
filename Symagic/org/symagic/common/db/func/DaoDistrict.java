package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public List<BeanDistrict> getDistrict(int upID)
	{
		try {
			conn	= ConnectionPool.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<BeanDistrict>();
	}
}
