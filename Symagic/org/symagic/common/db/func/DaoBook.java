package org.symagic.common.db.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanBookStatistics;
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
	 * @param bookID
	 *            指定书籍
	 * 
	 * @return int 库存量
	 */
	public int getInventory(int bookID) {
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn
					.prepareStatement("select inventory from book where bookid=?");
			ps.setInt(1, bookID);
			rs = ps.executeQuery();
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
	public int addBook(BeanBook book) {
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn.prepareStatement("insert into book ("
					+ "picture, bookname, author, "
					+ "publisher, publishdate, version, "
					+ "page, binding, folio, "
					+ "marketprice, discount, inventory, "
					+ "bookdesc, isbn, offline)" + "values " + "(?, ?, ?, "
					+ "?, ?, ?, " + "?, ?, ?, " + "?, ?, ?, " + "?, ?, ?)");
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

			ps.execute();

			if (ps.getUpdateCount() != 1)
				return -1;

			// 插入成功
			ps = conn
					.prepareStatement("select bookid from book where isbn = ?");
			ps.setString(1, book.getIsbn());
			rs = ps.executeQuery();
			if (!rs.next())
				return -1;

			

			if (book.getCatalogID() != null) {

				ps = conn.prepareStatement("insert into book_catalog_detail "
						+ "(bookid, catalogid) " + "values (?, ?)");
				ps.setInt(1, rs.getInt("bookid"));
				ps.setInt(2, book.getCatalogID());

				ps.execute();

				if (ps.getUpdateCount() == 1)
					return rs.getInt("bookid");
				return -1;
			} else
				return rs.getInt("bookid");

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
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn
					.prepareStatement("update book set inventory=? where bookid=?");
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
		BeanBook book = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn
					.prepareStatement("select * from book, book_catalog_detail "
							+ "where  book.bookid = ? and "
							+ "book.bookid=book_catalog_detail.bookid");

			ps.setInt(1, bookID);
			rs = ps.executeQuery();

			// 如果查询成功
			if (rs.next()) {
				book = new BeanBook();
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
				book.setCatalogID(rs.getInt("catalogid"));

				return book;
			} else {
				ps = conn.prepareStatement("select * from book where bookid=?");
				ps.setInt(1, bookID);
				rs = ps.executeQuery();
				if (rs.next()) {
					book = new BeanBook();
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

					return book;
				}
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
		return book;
	}

	/**
	 * 按照给定条件搜索书籍
	 * 
	 * @param req
	 *            封装书籍搜索条件的对象
	 * @param sign
	 *            标志， 0:普通查询 1:高级查询
	 * @return List<BeanBook> 存储着BeanBook对象的列表
	 */
	public List<BeanBook> search(int sign, BookRequire req) {
		List<BeanBook> list = null;
		String sql = "select t1.*, t2.catalogid "
				+ " from book as t1 left join book_catalog_detail as t2 "
				+ " on t1.bookid=t2.bookid where ";
		// 普通查询
		if (sign == 0) {
			sql += " t1.author like " + " '%" + req.getAuthor() + "%' "
					+ " and " + " t1.bookname like " + " '%"
					+ req.getItemName() + "%' " + " and "
					+ " t1.publisher like " + " '%" + req.getPublisher()
					+ "%' ";

			sql += " order by t1.bookid asc limit " + (req.getPage() - 1)
					* req.getLines() + " , " + req.getLines();

		}
		// 高阶查询
		else {

			sql += " t1.author like " + " '%" + req.getAuthor() + "%' "
					+ " and " + " t1.bookname like " + " '%"
					+ req.getItemName() + "%' " + " and "
					+ " t1.publisher like " + " '%" + req.getPublisher()
					+ "%' ";

			// 年前
			if (req.getBefore() == true) {
				// 条件 年
				if (req.getYear() != null)
					sql += " and " + " year(t1.publishdate) < " + req.getYear()
							+ " ";
			}
			// 当前年
			else {
				// 条件 年
				if (req.getYear() != null)
					sql += " and " + " year(t1.publishdate) = " + req.getYear()
							+ " ";
			}

			if (req.getVersion() != null)
				sql += " and " + " t1.version = " + req.getVersion() + " ";

			if (req.getBinding() != null)
				sql += " and " + " t1.binding like " + " '%" + req.getBinding()
						+ "%' ";

			if (req.getFolio() != null)
				sql += " and " + " t1.folio like " + " '%" + req.getFolio()
						+ "%' ";

			if (req.getLowPrice() != null)
				sql += " and " + " t1.marketprice > " + req.getLowPrice() + " ";

			if (req.getUpPrice() != null)
				sql += " and " + " marketprice < " + req.getUpPrice() + " ";

			if (req.getLowPage() != null)
				sql += " and " + " t1.page > " + req.getLowPage() + " ";

			if (req.getUpPage() != null)
				sql += " and " + " t1.page < " + req.getUpPage() + " ";

			if (req.getUpDiscount() != null) {
				sql += " and " + " t1.discount > " + req.getLowDiscount() + " "
						+ " and " + " t1.discount < " + req.getUpDiscount()
						+ " ";
			}

			if (req.getCatalogID() != null) {
				sql += " and " + " t2.catalogid = " + req.getCatalogID() + " ";
			}

			sql += " order by t1.bookid asc limit " + (req.getPage() - 1)
					* req.getLines() + " , " + req.getLines();

		}

		try {

			conn = ConnectionPool.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			list = new ArrayList<BeanBook>();
			while (rs.next()) {
				BeanBook book = new BeanBook();
				book.setAuthor(rs.getString("author"));
				book.setBinding(rs.getString("binding"));
				book.setBookDesc(rs.getString("bookdesc"));
				book.setBookId(rs.getInt("bookid"));
				book.setBookName(rs.getString("bookname"));
				if (rs.getInt("catalogid") != 0)
					book.setCatalogID(rs.getInt("catalogid"));
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
	 *            标志， 0:普通查询 1:高级查询
	 * @return int 返回符合条件的条数
	 */
	public int getSearchRowNumber(int sign, BookRequire req) {
		String sql = "select * from book where ";
		// 普通查询
		if (sign == 0) {
			sql += " author like " + " '%" + req.getAuthor() + "%' " + " and "
					+ " bookname like " + " '%" + req.getItemName() + "%' "
					+ " and " + " publisher like " + " '%" + req.getPublisher()
					+ "%' ";

			sql += " order by bookid asc  ";

		}
		// 高阶查询
		else {
			sql += " author like " + " '%" + req.getAuthor() + "%' " + " and "
					+ " bookname like " + " '%" + req.getItemName() + "%' "
					+ " and " + " publisher like " + " '%" + req.getPublisher()
					+ "%' ";

			// 年前
			if (req.getBefore() == true) {
				// 条件 年
				if (req.getYear() != null)
					sql += " and " + " year(publishdate) < " + req.getYear()
							+ " ";
			}
			// 当前年
			else {
				// 条件 年
				if (req.getYear() != null)
					sql += " and " + " year(publishdate) = " + req.getYear()
							+ " ";
			}

			if (req.getVersion() != null)
				sql += " and " + " version = " + req.getVersion() + " ";

			if (req.getBinding() != null)
				sql += " and " + " binding like " + " '%" + req.getBinding()
						+ "%' ";

			if (req.getFolio() != null)
				sql += " and " + " folio like " + " '%" + req.getFolio()
						+ "%' ";

			if (req.getLowPrice() != null)
				sql += " and " + " marketprice > " + req.getLowPrice() + " ";

			if (req.getUpPrice() != null)
				sql += " and " + " marketprice < " + req.getUpPrice() + " ";

			if (req.getLowPage() != null)
				sql += " and " + " page > " + req.getLowPage() + " ";

			if (req.getUpPage() != null)
				sql += " and " + " page < " + req.getUpPage() + " ";

			if (req.getUpDiscount() != null) {
				sql += " and " + " discount > " + req.getLowDiscount() + " "
						+ " and " + " discount < " + req.getUpDiscount() + " ";
			}

			sql += " order by bookid asc";

		}
		try {
			count = -1;
			conn = ConnectionPool.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next())
				count++;
			return ++count;
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
	 * 修改商品信息
	 * 
	 * @param book
	 *            封装着书籍信息的BeanBook对象
	 * @return true 更新成功 false 更新失败
	 */
	public boolean modifyBook(BeanBook book) {
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn.prepareStatement("update book set picture=?, bookname=?,"
					+ "author=?, publisher=?," + "publishdate=?, version=?,"
					+ "page=?, binding=?," + "folio=?, marketprice=?,"
					+ "discount=?, inventory=?," + "bookdesc=?, isbn=?,"
					+ "offline=? where bookid=?");
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

			if (ps.executeUpdate() == 1) {
				if (book.getCatalogID() != null) {
					ps = conn
							.prepareStatement("update book_catalog_detail set "
									+ " catalodid=? " + " where bookid=? ");
					ps.setInt(1, book.getCatalogID());
					ps.setInt(2, book.getBookId());
					if (ps.executeUpdate() == 1)
						return true;
					return false;
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
	 * 获取商品总量
	 * 
	 * @return int 商品总量
	 */
	public int getProductNum() {
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn.prepareStatement("select count(*) from book");
			rs = ps.executeQuery();
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
	 * 
	 * @return List<BeanBook>为null 获取失败 List<BeanBook>size为10 获取成功
	 */
	public List<BeanBook> getLatestBook() {
		List<BeanBook> list = null;
		try {

			conn = ConnectionPool.getInstance().getConnection();
			ps = conn
					.prepareStatement("select * from book order by bookid desc limit 10");
			rs = ps.executeQuery();
			list = new ArrayList<BeanBook>();
			while (rs.next()) {
				BeanBook book = new BeanBook();
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

	/**
	 * 删除指定ID的书籍
	 * 
	 * @param bookid
	 *            指定书籍ID
	 * @return true 删除成功 false 删除失败
	 */
	public boolean deleteBook(int bookid) {
		try {
			conn = ConnectionPool.getInstance().getConnection();
			// 删除书籍目录
			ps = conn
					.prepareStatement("delete from book_catalog_detail where bookid=?");
			ps.setInt(1, bookid);
			ps.execute();

			// 删除包含此商品的购物车
			ps = conn.prepareStatement("delete from cart where bookid=?");
			ps.setInt(1, bookid);
			ps.execute();

			// 删除book表中指定书籍记录
			ps = conn.prepareStatement("delete from book where bookid=?");
			ps.setInt(1, bookid);
			ps.execute();

			// 删除收藏夹中指定书籍记录

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

	public List<BeanBookStatistics> getBookStatistics(BookStatisticsRequire req) {
		boolean haveCatalogID = true;
		if (req.getCatalogid() == null)
			haveCatalogID = false;

		List<BeanBookStatistics> list = null;
		String sql = "";
		if (haveCatalogID == false)
			sql = "select bookid, bookname, sum(amount) as sum_amount, sum(discount * marketprice*amount) as sum_price "
					+ "from book_order, order_detail "
					+ "where book_order.orderid=order_detail.orderid and orderdate >  "
					+ " '"
					+ req.getStartTime()
					+ "' "
					+ " and orderdate < "
					+ " '"
					+ req.getEndTime()
					+ "' "
					+ "group by bookid "
					+ "having sum(amount) > ? "
					+ "order by bookid asc  limit ?, ?";
		else
			sql = "select t1.bookid, bookname, sum_amount, sum_price, t2.catalogid "
					+ "from ("
					+ " select bookid, bookname, sum(amount) as sum_amount, sum(discount * marketprice*amount) as sum_price "
					+ " from book_order, order_detail "
					+ " where book_order.orderid=order_detail.orderid and orderdate >  "
					+ " '"
					+ req.getStartTime()
					+ "' "
					+ " and orderdate < "
					+ " '"
					+ req.getEndTime()
					+ "' "
					+ " group by bookid "
					+ " having sum(amount) > ? "
					+ "order by bookid asc limit ?, ?) "
					+ " as t1, book_catalog_detail as t2 "
					+ " where t1.bookid=t2.bookid and t2.catalogid=? "
					+ " order by bookid asc ";
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, req.getLowlimit());
			ps.setInt(2, (req.getPage() - 1) * req.getLines());
			ps.setInt(3, req.getLines());

			if (haveCatalogID)
				ps.setInt(4, req.getCatalogid());

			rs = ps.executeQuery();
			list = new ArrayList<BeanBookStatistics>();
			while (rs.next()) {
				BeanBookStatistics bbs = new BeanBookStatistics();
				bbs.setBookid(rs.getInt("bookid"));
				bbs.setBookname(rs.getString("bookname"));
				bbs.setTotalSaleAmount(rs.getInt("sum_amount"));
				bbs.setTotalSaleRevenue(rs.getFloat("sum_price"));
				list.add(bbs);
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
	 * 获取统计数据条数
	 * 
	 * @param req
	 *            封装搜索条件
	 * @return -1 搜索失败 >=0 搜索成功
	 */
	public int getStatisticsNum(BookStatisticsRequire req) {
		count = -1;
		boolean haveCatalogID = true;
		if (req.getCatalogid() == null)
			haveCatalogID = false;

		String sql = "";
		if (haveCatalogID == false)
			sql = "select bookid, bookname, sum(amount) as sum_amount, sum(discount * marketprice*amount) as sum_price "
					+ "from book_order, order_detail "
					+ "where book_order.orderid=order_detail.orderid and orderdate >  "
					+ " '"
					+ req.getStartTime()
					+ "' "
					+ " and orderdate < "
					+ " '"
					+ req.getEndTime()
					+ "' "
					+ "group by bookid "
					+ "having sum(amount) > ? "
					+ "order by bookid asc  limit ?, ?";
		else
			sql = "select t1.bookid, bookname, sum_amount, sum_price, t2.catalogid "
					+ "from ("
					+ " select bookid, bookname, sum(amount) as sum_amount, sum(discount * marketprice*amount) as sum_price "
					+ " from book_order, order_detail "
					+ " where book_order.orderid=order_detail.orderid and orderdate >  "
					+ " '"
					+ req.getStartTime()
					+ "' "
					+ " and orderdate < "
					+ " '"
					+ req.getEndTime()
					+ "' "
					+ " group by bookid "
					+ " having sum(amount) > ? "
					+ "order by bookid asc limit ?, ?) "
					+ " as t1, book_catalog_detail as t2 "
					+ " where t1.bookid=t2.bookid and t2.catalogid=? "
					+ " order by bookid asc ";
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, req.getLowlimit());
			ps.setInt(2, (req.getPage() - 1) * req.getLines());
			ps.setInt(3, req.getLines());

			if (haveCatalogID)
				ps.setInt(4, req.getCatalogid());

			rs = ps.executeQuery();
			while (rs.next()) {
				count++;
			}
			return count;
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
		return count;
	}

}
