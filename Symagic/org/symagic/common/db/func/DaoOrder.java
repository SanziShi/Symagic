package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
import org.symagic.common.db.pool.ConnectionPool;
import org.w3c.dom.ls.LSException;

/**
 * 封装与用户订单相关操作
 * @author wanran
 *
 */
public class DaoOrder {
	
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;
	private int count = 0;
	
	/**
	 * 根据给定条件搜索用户订单
	 * @param req	封装着所搜相关的条件
	 * @param username	指定用户名
	 * @return	List<BeanOrder>存储BeanOrder(封装订单详细信息类)的链表
	 */
	public List<BeanOrder> search(OrderRequire req, String username)
	{
		List<BeanOrder> list	= null;
		String sql	= "select * from book_order where ";
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			sql += "orderdate > " + " '" + req.getStartTime() + "' ";
			if (username != null)
				sql += " and username like " + " '" + username + "' ";
			if (req.getEndTime() != null)
				sql += " and orderdate < " + " '" + req.getEndTime() + "' ";
			if (req.getOrderState() != null)
				sql += " and orderstate=" + " '" + req.getOrderState() + "' ";
			
			sql += " order by orderdate asc limit " 
				+ ((req.getPage() - 1) * req.getLines())
				+ ", " + req.getLines();
	
			st	= conn.createStatement();
			rs	= st.executeQuery(sql);
			
			list	= new ArrayList<BeanOrder>();
			while (rs.next()) {
				BeanOrder order	= new BeanOrder();
				order.setAddrDetail(rs.getString("addrdetail"));
				order.setDeliveryWay(rs.getString("deliveryway"));
				order.setMobilenum(rs.getString("mobilenum"));
				order.setOrderDate(rs.getString("orderdate"));
				order.setOrderId(rs.getInt("orderid"));
				order.setOrderState(rs.getString("orderstate"));
				order.setPayment(rs.getString("payment"));
				order.setPhonenum(rs.getString("phonenum"));
				order.setReceiverName(rs.getString("receivername"));
				order.setScore(rs.getInt("score"));
				order.setTotalprice(rs.getFloat("totalprice"));
				order.setUsername(rs.getString("username"));
				order.setZipcode(rs.getString("zipcode"));
				list.add(order);
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
	 * 获取给定订单ID对应订单的详细信息
	 * @param orderID	订单ID
	 * @return	BeanOrder对象，封装着订单的详细信息
	 */
	public BeanOrder getOrderDetail(int orderID)
	{
		BeanOrder order	= null;
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from order_detail where orderid=?");
			ps.setInt(1, orderID);
			rs	= ps.executeQuery();
			order	= new BeanOrder();
			while (rs.next()) {
				BeanOrderDetail	orderDetail	= new BeanOrderDetail();
				orderDetail.setAmount(rs.getInt("amount"));
				orderDetail.setBookId(rs.getInt("bookid"));
				orderDetail.setBookName(rs.getString("bookname"));
				orderDetail.setDiscount(rs.getFloat("discount"));
				orderDetail.setIsbn(rs.getString("isbn"));
				orderDetail.setMarketPrice(rs.getFloat("marketprice"));
				orderDetail.setOrderId(rs.getInt("orderid"));
				order.getList().add(orderDetail);
			}
			return order;
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
		return order;
	}
	
	/**
	 * 获取给定条件下的订单条数
	 * @param req	搜索订单条件
	 * @param username	指定订单用户
	 * @return	int	用户订单数目
	 */
	public int getRowNumber(OrderRequire req, String username)
	{
		
		String sql	= "select count(*) from book_order where ";
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			sql += "orderdate > " + " '" + req.getStartTime() + "' ";
			if (username != null)
				sql += " and username like " + " '" + username + "' ";
			if (req.getEndTime() != null)
				sql += " and orderdate < " + " '" + req.getEndTime() + "' ";
			if (req.getOrderState() != null)
				sql += " and orderstate=" + " '" + req.getOrderState() + "' ";
			st	= conn.createStatement();
			rs	= st.executeQuery(sql);
			if (rs.next())
				return rs.getInt(1);
			return -1;
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
		return -1;
	}
	
	/**
	 * 对给定用户添加订单
	 * @param username	指定添加订单用户
	 * @param order	存储订单详细信息的BeanOrder对象
	 * @return	true 添加成功	false 添加失败
	 */
	public boolean addOrder(BeanOrder order)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("insert into book_order (orderdate, payment," +
					"deliveryway, username," +
					"receivername, addrdetail," +
					"zipcode, phonenum," +
					"mobilenum, orderstate, totalprice, score) values (" +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, order.getOrderDate());
			ps.setString(2, "0");
			ps.setString(3, "0");
			ps.setString(4, order.getUsername());
			ps.setString(5, order.getReceiverName());
			ps.setString(6, order.getAddrDetail());
			ps.setString(7, order.getZipcode());
			ps.setString(8, order.getPhonenum());
			ps.setString(9, order.getMobilenum());
			ps.setString(10, "0");
			ps.setFloat(11, order.getTotalprice());
			ps.setInt(12, order.getScore());
			ps.execute();
			
			
			if (ps.getUpdateCount() == 1) {
				int orderid = 0;
				// 获取订单ID
				ps	= conn.prepareStatement("select orderid from book_order " +
						"where username=? and orderdate=?");
				ps.setString(1, order.getUsername());
				ps.setString(2, order.getOrderDate());
				rs	= ps.executeQuery();	
				while (rs.next())
					orderid	= rs.getInt("orderid");
				
				List<BeanOrderDetail> list	= order.getList();
				// 插入所有订单项
				ps	= conn.prepareStatement("insert into order_detail " +
						"(bookid, isbn, " +
						" bookname, marketprice," +
						" amount, orderid) values" +
						"(?, ?, ?, ?, ?, ?)");
				for (int i=0; i < list.size(); i++) {
					ps.setInt(1, list.get(i).getBookId());
					ps.setString(2, list.get(i).getIsbn());
					ps.setString(3, list.get(i).getBookName());
					ps.setFloat(4, list.get(i).getMarketPrice());
					ps.setInt(5, list.get(i).getAmount());
					ps.setInt(6, orderid);
					ps.execute();
				}
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
	
	/**
	 * 更新给定用户的制定订单(有问题)
	 * @param order	保存更新订单中所有信息的BeanOrder对象
	 * @return	true 更新成功	false 更新失败
	 */
	public boolean updateOrder(BeanOrder order)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("update book_order set orderstate=?," +
					"receivername=?, addrdetail=?, " +
					"zipcode=?, phonenum=?," +
					"mobilenum=? where orderid=?");
			ps.setString(1, order.getOrderState());
			ps.setString(2, order.getReceiverName());
			ps.setString(3, order.getAddrDetail());
			ps.setString(4, order.getZipcode());
			ps.setString(5, order.getPhonenum());
			ps.setString(6, order.getMobilenum());
			ps.setInt(7, order.getOrderId());
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
	 * 删除给定用户的指定订单
	 * @param username	指定要删除订单的用户
	 * @param orderID	指定要删除的订单
	 * @return	true 删除成功	false 删除失败
	 */
	public boolean deleteOrder(int orderID)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("delete from order_detail where orderid=?");
			ps.setInt(1, orderID);
			ps.execute();
			if (ps.getUpdateCount() == 0)
				return false;
			ps	= conn.prepareStatement("delete from book_order where orderid=?");
			ps.setInt(1, orderID);
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
	 * 获取销售总量(所有)
	 * @return int -1 执行失败	>=0 执行成功
	 */
	public int getTotalSaleAmount()
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select sum(amount) from order_detail");
			rs	= ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
			return -1;
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
		return -1;
	}
	
	/**
	 * 获取销售总额
	 * @return float 销售总额 -1.0 查询出错	>=0.0f	查询成功
	 */
	public float getTotalSalesRevenue()
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select sum(totalprice) from book_order");
			rs	= ps.executeQuery();
			if (rs.next())
				return rs.getFloat(1);
			return -1.0f;
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
		return -1.0f;
	}
	
	/**
	 * 获取订单总量
	 * @return	int 订单总量
	 */
	public int getTotalOrderAmount()
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select count(*) from book_order");
			rs	= ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
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
		return 10;
	}
	
	/**
	 * 获取待审核订单数
	 * @return -1 查询失败	>=0 查询成功,返回待审核订单数
	 */
	public int getUnauditedOrderAmount()
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select count(*) from book_order where orderstate='0'");
			rs	= ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
			
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
		return -1;
	}
	
	/**
	 * 获取完成的订单数
	 * @return 完成订单数
	 */
	public int getFinishOrderAmount()
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select count(*) from book_order where orderstate='2'");
			rs	= ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
			return 0;
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
		return 0;
	}
	
	/**
	 * 获取最近的10次订单详情
	 * @return	List<BeanOrder> 最近10次订单详细信息列表
	 */
	public List<BeanOrder> getLatestOrders()
	{
		List<BeanOrder> list = null;
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from book_order order by orderdate desc limit 10");
			rs	= ps.executeQuery();
			list	= new ArrayList<BeanOrder>();
			while (rs.next()) {
				BeanOrder order	= new BeanOrder();
				order.setAddrDetail(rs.getString("addrdetail"));
				order.setDeliveryWay(rs.getString("deliveryway"));
				order.setMobilenum(rs.getString("mobilenum"));
				order.setOrderDate(rs.getString("orderdate"));
				order.setOrderId(rs.getInt("orderid"));
				order.setOrderState(rs.getString("orderstate"));
				order.setPayment(rs.getString("payment"));
				order.setPhonenum(rs.getString("phonenum"));
				order.setReceiverName(rs.getString("receivername"));
				order.setScore(rs.getInt("score"));
				order.setTotalprice(rs.getFloat("totalprice"));
				order.setUsername(rs.getString("username"));
				order.setZipcode(rs.getString("zipcode"));
				list.add(order);
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
	 * 获取指定书籍的所有订单，要分页
	 * @param bookID	指定书籍ID
	 * @param page	指定第几页
	 * @param lines	指定每页显示多少行
	 * @return	List<BeanOrder>	封装着订单信息的BeanOrder链表
	 */
	public List<BeanOrder> getItemOrders(int bookID, int page, int lines)
	{
		List<BeanOrder> list	 = null;
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from book_order " +
					"where orderid in " +
					"(select distinct orderid from order_detail where bookid=? ) " +
					"order by orderdate asc limit ?,?");
			ps.setInt(1, bookID);
			ps.setInt(2, (page - 1) * lines);
			ps.setInt(3, lines);
			rs	= ps.executeQuery();
			list	= new ArrayList<BeanOrder>();
			
			while (rs.next()) {
				BeanOrder order	= new BeanOrder();
				order.setAddrDetail(rs.getString("addrdetail"));
				order.setDeliveryWay(rs.getString("deliveryway"));
				order.setMobilenum(rs.getString("mobilenum"));
				order.setOrderDate(rs.getString("orderdate"));
				order.setOrderId(rs.getInt("orderid"));
				order.setOrderState(rs.getString("orderstate"));
				order.setPayment(rs.getString("payment"));
				order.setPhonenum(rs.getString("phonenum"));
				order.setReceiverName(rs.getString("receivername"));
				order.setScore(rs.getInt("score"));
				order.setTotalprice(rs.getFloat("totalprice"));
				order.setUsername(rs.getString("username"));
				order.setZipcode(rs.getString("zipcode"));
				list.add(order);
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









