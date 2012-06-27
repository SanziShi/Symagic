package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.pool.ConnectionPool;

/**
 * 封装有关书籍表操作的类
 * 
 * @author wanran
 * 
 */
public class DaoBook {
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;
	private int count = 0;

	/**
	 * 获取给定书籍的库存
	 * 
	 * @param bookID	指定书籍
	 *            
	 * @return int 库存量
	 */
	public int getInventory(int bookID) {
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select inventory from book where bookid=?");
			ps.setInt(1, bookID);
			rs	= ps.executeQuery();
			// 查找成功，返回库存量
			if (rs.next()) {
				conn.close();
				return rs.getInt("inventory");
			}
			// 查找失败，返回-1
			conn.close();
			return -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	/**
	 * 添加书籍
	 * 
	 * @param book
	 *            封装者书籍详细信息的BeanBook实例
	 * @return true 添加成功 false 添加失败
	 */
	public boolean addBook(BeanBook book)
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("insert into book (" +
					"picture, bookname, author, " +
					"publisher, publishdate, version, " +
					"page, binding, folio, " +
					"marketprice, discount, inventory, " +
					"bookdesc, isbn, offline)" + 
					"values " + 
					"(?, ?, ?, " +
					"?, ?, ?, " +
					"?, ?, ?, " +
					"?, ?, ?, " +
					"?, ?, ?)");
			ps.setString(1, book.getPicture());
			ps.setString(2, book.getBookName());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getPublisher());
			ps.setString(5, book.getPublishDate());
			ps.setInt(6, book.getVersion());
			ps.setInt(7, book.getPage());
			ps.setString(8, book.getBinding());
			ps.setString(9, book.getFolio());
			ps.setFloat(10, book.getMarketPrice());
			ps.setFloat(11, book.getDiscount());
			ps.setInt(12, book.getInventory());
			ps.setString(13, book.getBookDesc());
			ps.setString(14, book.getIsbn());
			ps.setString(15,book.getOffline());
			
			ps.execute();
			
			// 插入成功
			if (ps.getUpdateCount() == 1) {
				conn.close();
				return true;
			}
			
			conn.close();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 设置给定书籍的库存量
	 * 
	 * @param bookID
	 *            指定特定书籍
	 * @param inventory
	 *            指定库存量
	 * @return true 设置成功 false 设置失败
	 */
	public boolean setInventory(int bookID, int inventory) {
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("update book set inventory=? where bookid=?");
			ps.setInt(1, inventory);
			ps.setInt(2, bookID);
			
			// 更新库存数据成功
			if (ps.executeUpdate() == 1) {
				conn.close();
				return true;
			}
			
			conn.close();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 获得指定书籍的书籍详细信息
	 * 
	 * @param bookID
	 *            指定书籍ID
	 * @return BeanBook实例，封装着书籍的详细信息
	 */
	public BeanBook getDetail(int bookID) {
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from book where bookid=?");
			ps.setInt(1, bookID);
			rs	= ps.executeQuery();
			BeanBook book;
			
			// 如果查询成功
			if (rs.next()) {
				book	= new BeanBook();
				book.setAuthor(rs.getString("author"));
				book.setBinding(rs.getString("binding"));
				book.setBookDesc(rs.getString("bookdesc"));
				book.setBookId(rs.getInt("bookid"));
				book.setBookName(rs.getString("bookname"));
				book.setDiscount(rs.getFloat("discount"));
				book.setFolio(rs.getString("folio"));
				book.setInventory(rs.getInt("inventory"));
				book.setIsbn(rs.getString("isbn"));
				book.setMarketPrice(rs.getFloat("marketprice"));
				book.setOffline(rs.getString("folio"));
				book.setPage(rs.getInt("page"));
				book.setPicture(rs.getString("picture"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublishDate(rs.getString("publishdate"));
				book.setVersion(rs.getInt("version"));
				
				conn.close();
				return book;
			}
			
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new BeanBook();
	}

	/**
	 * 按照给定条件搜索书籍
	 * 
	 * @param req
	 *            封装书籍搜索条件的对象
	 * @param sign
	 *        	  标志， 0:普通查询	 1:高级查询
	 * @return List<BeanBook> 存储着BeanBook对象的列表
	 */
	public List<BeanBook> search(int sign, BookRequire req) {
		List<BeanBook> list	= null;
		String r	= "";
		String sql	= "select * from book where ";
		// 普通查询
		if (sign == 0) {
			r	= " or ";
			// 年前
			if (req.getBefore() == true) {
				// 条件 年
				if (req.getYear() != null)
					sql += " year(publishdate) < " + req.getYear() + " ";
			}
			// 当前年
			else {
				// 条件 年
				if (req.getYear() != null)
					sql += " year(publishdate) = " + req.getYear() + " ";
			}
		}
		// 高阶查询
		else {
			r	= " and ";
		}
		
		try {
			
			conn	= ConnectionPool.getInstance().getConnection();
			st	= conn.createStatement();
			rs	= st.executeQuery(sql);
			list	= new ArrayList<BeanBook>();
			while (rs.next()) {
				BeanBook book	= new BeanBook();
				book.setAuthor(rs.getString("author"));
				book.setBinding(rs.getString("binding"));
				book.setBookDesc(rs.getString("bookdesc"));
				book.setBookId(rs.getInt("bookid"));
				book.setBookName(rs.getString("bookname"));
				//book.setCatalogID(rs.getInt("catalog"));
				book.setDiscount(rs.getFloat("discount"));
				book.setFolio(rs.getString("folio"));
				book.setInventory(rs.getInt("inventory"));
				book.setIsbn(rs.getString("isbn"));
				book.setMarketPrice(rs.getFloat("marketprice"));
				book.setOffline(rs.getString("offline"));
				book.setPage(rs.getInt("page"));
				book.setPicture(rs.getString("picture"));
				book.setPublishDate(rs.getString("publishdate"));
				book.setPublisher(rs.getString("publisher"));
				book.setVersion(rs.getInt("version"));
				list.add(book);
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
	 * 按照给定条件搜索书籍
	 * 
	 * @param req
	 *            封装书籍搜索条件的对象
	 * @param sign
	 *        	  标志， 0:普通查询	 1:高级查询
	 * @return int	返回符合条件的条数
	 */
	public int getSearchRowNumber(int sign, BookRequire req) {
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("");
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
	 * 修改商品信息
	 * @param book	封装着书籍信息的BeanBook对象
	 * @return	true 更新成功	false 更新失败
	 */
	public boolean modifyBook(BeanBook book) 
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("update book set picture=?, bookname=?," +
					"author=?, publisher=?," +
					"publishdate=?, version=?," +
					"page=?, binding=?," +
					"folio=?, marketprice=?," +
					"discount=?, inventory=?," +
					"bookdesc=?, isbn=?," +
					"offline=? where bookid=?");
			ps.setString(1, book.getPicture());
			ps.setString(2, book.getBookName());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getPublisher());
			ps.setString(5, book.getPublishDate());
			ps.setInt(6, book.getVersion());
			ps.setInt(7, book.getPage());
			ps.setString(8, book.getBinding());
			ps.setString(9, book.getFolio());
			ps.setFloat(10, book.getMarketPrice());
			ps.setFloat(11, book.getDiscount());
			ps.setInt(12, book.getInventory());
			ps.setString(13, book.getBookDesc());
			ps.setString(14, book.getIsbn());
			ps.setString(15, book.getOffline());
			ps.setInt(16, book.getBookId());
			
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
	 * 获取商品总量
	 * @return	int 商品总量
	 */
	public int getProductNum()
	{
		try {
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select count(*) from book");
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
	 * 获取最新添加的书籍10本
	 * @return	List<BeanBook>为null 获取失败	List<BeanBook>size为10	获取成功
	 */
	public List<BeanBook> getLatestBook()
	{
		List<BeanBook>	list	= null;
		try {
			
			conn	= ConnectionPool.getInstance().getConnection();
			ps	= conn.prepareStatement("select * from book order by bookid desc limit 10");
			rs	= ps.executeQuery();
			list	= new ArrayList<BeanBook>();
			while (rs.next()) {
				BeanBook book	= new BeanBook();
				book.setAuthor(rs.getString("author"));
				book.setBinding(rs.getString("binding"));
				book.setBookDesc(rs.getString("bookdesc"));
				book.setBookId(rs.getInt("bookid"));
				book.setBookName(rs.getString("bookname"));
				book.setDiscount(rs.getFloat("discount"));
				book.setFolio(rs.getString("folio"));
				book.setInventory(rs.getInt("inventory"));
				book.setIsbn(rs.getString("isbn"));
				book.setMarketPrice(rs.getFloat("marketPrice"));
				book.setOffline(rs.getString("offline"));
				book.setPage(rs.getInt("page"));
				book.setPicture(rs.getString("picture"));
				book.setPublishDate(rs.getString("publishdate"));
				book.setPublisher(rs.getString("publisher"));
				book.setVersion(rs.getInt("version"));
				
				list.add(book);
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
