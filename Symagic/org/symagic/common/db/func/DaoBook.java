package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanComment;
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
			conn	= ConnectionPool.getConnection();
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
			conn	= ConnectionPool.getConnection();
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
			ps.setString(5, book.getPublisherDate());
			ps.setInt(6, book.getVersion());
			ps.setInt(7, book.getPage());
			ps.setString(8, book.getBinding());
			ps.setString(9, book.getFolio());
			ps.setFloat(10, book.getMarketPrice());
			ps.setInt(11, book.getDiscount());
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
			conn	= ConnectionPool.getConnection();
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
	public BeanBook getDeatil(int bookID) {
		try {
			conn	= ConnectionPool.getConnection();
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
				book.setDiscount(rs.getInt("discount"));
				book.setFolio(rs.getString("folio"));
				book.setInventory(rs.getInt("inventory"));
				book.setIsbn(rs.getString("isbn"));
				book.setMarketPrice(rs.getFloat("marketprice"));
				book.setOffline(rs.getString("folio"));
				book.setPage(rs.getInt("page"));
				book.setPicture(rs.getString("picture"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublisherDate(rs.getString("publishdate"));
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
	 * @return List<BeanBook> 存储着BeanBook对象的列表
	 */
	public List<BeanBook> search(BookRequire req) {
		return new ArrayList<BeanBook>();
	}

	/**
	 * 获取符合书籍搜索条件的书籍条数
	 * 
	 * @param req
	 *            条件
	 * @return int 符合的条数
	 */
	public int getSearchRowNumber(BookRequire req) {
		return 0;
	}

	/**
	 * 获取给定书籍ID的评论条数
	 * 
	 * @param bookID
	 * @return >=0 成功成功，评论条数	-1 查询失败 
	 */
	public int getCommnetNumber(int bookID) {
		try {
			conn	= ConnectionPool.getConnection();
			ps	= conn.prepareStatement("select count(*) from comment where bookid=?");
			ps.setInt(1, bookID);
			rs	= ps.executeQuery();
			
			// 如果查询成功，则返回评论数目
			if (rs.next()) {
				conn.close();
				return rs.getInt(1);
			}
			
			// 若查询失败,则返回-1
			conn.close();
			return -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 根据给定条件获取给定书籍的部分评论（由于要分页，所以时部分）
	 * 
	 * @param bookID
	 *            给定书籍ID
	 * @param page
	 *            标示要第几页
	 * @param lines
	 *            标示一页要多少行（一页显示多少条记录）
	 * @return List<BeanComment>存储平路详细信息的BeanComment实例列表
	 */
	public List<BeanComment> getComment(int bookID, int page, int lines) {
		BeanComment	bc;
		try {
			conn	= ConnectionPool.getConnection();
			ps	= conn.prepareStatement("select * from comment where ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<BeanComment>();
	}
	
	/**
	 * 发表评论，向comment表中插入一条记录
	 * @param bookID	指定评论书籍ID
	 * @param comment	评论的详细信息
	 * @return	true 插入成功	false 插入失败
	 */
	public boolean publishComment(BeanComment comment)
	{
		try {
			conn	= ConnectionPool.getConnection();
			ps	= conn.prepareStatement("insert into comment " +
					"(username, bookid, " +
					" content, rating, commentdate)" +
					"values " +
					"(?, ?, ?, ?, ?)");
			ps.setString(1, comment.getUsername());
			ps.setInt(2, comment.getBookID());
			ps.setString(3, comment.getCotent());
			ps.setString(4, comment.getRating());
			ps.setString(5, comment.getCommentDate());
			
			ps.execute();
			
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
		return false;
	}

}
