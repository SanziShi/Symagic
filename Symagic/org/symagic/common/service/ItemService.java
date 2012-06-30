package org.symagic.common.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanCatalog;
import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.db.func.BookRequire;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoCatalog;
import org.symagic.common.db.func.DaoComment;
import org.symagic.common.utilty.presentation.bean.CatalogBean;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.common.utilty.presentation.bean.ItemDetailBean;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.common.utilty.presentation.bean.TimeBean;
import org.symagic.user.utilty.MathUtilty;
import org.symagic.user.utilty.UserSessionUtilty;

public class ItemService {
	private DaoComment daoComment;// 访问comment
	private DaoBook daoBook;// 访问数据库中的书籍信息
	private DaoCatalog daoCatalog;
	/**
	 * 
	 * @param sign
	 *            0普通搜索 1为高级搜索
	 * @param require
	 * @return List<BeanBook>
	 */
	public List<BeanBook> search(int sign, BookRequire require) {
		return daoBook.search(sign, require);
	}

	public int getSearchNum(int sign, BookRequire require) {
		return daoBook.getSearchRowNumber(sign, require);
	}

	//为推荐项填充信息
	private void fillItemTinyBean(BeanBook book,ItemTinyBean item){
		
		item.setItemID(book.getBookId());
		item.setName(book.getBookName());
		item.setPicturePath(book.getPicture());
		float marketPrice =book.getMarketPrice();
		float discount =book.getDiscount();
		item.setPrice(MathUtilty.roundWithdigits(marketPrice * discount));
	}
	//搜索显示出来的信息

	private void fillItemBean(BeanBook book,ItemBean item){
		item.setItemID(String.valueOf(book.getBookId()));
		item.setName(book.getBookName());
		item.setPrice(book.getMarketPrice() * book.getDiscount());
		item.setDiscount(book.getDiscount());
		item.setPicturePath(book.getPicture());
		item.setPublishTime(book.getPublishDate());
		item.setPublisher(book.getPublisher());
		item.setAuthor(book.getAuthor());
		item.setMarketPrice(book.getMarketPrice());
		String status = book.getOffline();
		if (status.trim().equals("下架")) {
			item.setOffline(true);
		} else {
			item.setOffline(false);
		}
		item.setRating(getAverage(book.getBookId()));
		item.setInventory(book.getInventory());
	}

	public void  fillItemBean(Integer itemID,ItemBean item){
		BeanBook book=daoBook.getDetail(itemID);
		fillItemBean(book, item);
	}
	
	//得到商品详情
	public boolean fillDetailBean(int itemId, ItemDetailBean detail) {
			BeanBook book = daoBook.getDetail(itemId);
			if (book == null)
				return false;
			BeanCatalog currentCatalog = daoCatalog.getCatalogByID(book.getCatalogID());
			detail.setAuthor(book.getAuthor());
			detail.setAverageRating(daoComment.getAverageRating(itemId));
			detail.setBinding(book.getBinding());
			detail.setBookDesc(book.getBookDesc());
			detail.setBookName(book.getBookName());
			detail.setCatalogClassify(getCatalogName(currentCatalog));
			detail.setDiscout(book.getDiscount());
			detail.setSize(book.getFolio()+"开");
			detail.setInventory(book.getInventory());
			detail.setIsbn(book.getIsbn());
			float marketPrice = MathUtilty.roundWithdigits(book.getMarketPrice());
			float discount = MathUtilty.roundWithdigits(book.getDiscount());
			detail.setMarketPrice(marketPrice);
			if(book.getOffline().equals("下架")){
			detail.setOffline(true);
			}
			else{
				detail.setOffline(false);
			}
			detail.setPage(book.getPage());
			float price=MathUtilty.roundWithdigits(discount * marketPrice);
			detail.setPrice(price);
		    detail.setSavePrice(MathUtilty.roundWithdigits(marketPrice-price));
			detail.setPublishDate(book.getPublishDate());
			detail.setPublisher(book.getPublisher());
			detail.setVersion(book.getVersion());
			detail.setPicturePath(book.getPicture());
			
			//时间解析
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = dateFormat.parse(book.getPublishDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			GregorianCalendar calender = new GregorianCalendar();
			calender.setTime(date);
			TimeBean parseTime = new TimeBean();
			parseTime.setYear(calender.get(Calendar.YEAR));
			parseTime.setYear(calender.get(Calendar.MONTH));
			parseTime.setYear(calender.get(Calendar.DAY_OF_MONTH));
			detail.setParseTime(parseTime);
			
			//设置当前选中的ID
			CatalogBean catalog = new CatalogBean();
			
			catalog.setID(currentCatalog.getCatalogID());
			catalog.setDescription(currentCatalog.getCatalogDesc());
			catalog.setName(currentCatalog.getCatalogName());
			catalog.setChildCatalog(null);
			detail.setParseCatalog(catalog);
			
			return true;
	}
	
	/**
	 * @param books为外部引用
	 *            ，不能为null
	 */
public void getNewBook(List<ItemTinyBean> books) {
		List<BeanBook> newBooks = daoBook.getLatestBook();
		if (newBooks == null)
			return;
		ItemTinyBean item;
		for (Iterator<BeanBook> index = newBooks.iterator(); index.hasNext();) {
			item = new ItemTinyBean();
			BeanBook book = index.next();
			fillItemTinyBean(book,item);
			books.add(item);
		}
	}
	
	
	public void fillTinyItems(List<Integer> ids, List<ItemTinyBean> items) {
		ItemTinyBean item;
		BeanBook book;
		for (Iterator<Integer> index = ids.iterator(); index.hasNext();) {
			int id = index.next();
			item = new ItemTinyBean();
			book = daoBook.getDetail(id);
			if(book!=null)
			fillItemTinyBean(book,item);
			items.add(item);
		}
	}
	
	/**
	 * 将购物车的信息进行填充
	 * @param items不能为null
	 * @return 商品总数量
	 */
	public float fillItemFromCart(ArrayList<ItemTinyBean>items){
		if(items==null)return -1;
		//获得购物车
		HashMap<Integer,Integer> cart=UserSessionUtilty.getCart();
		
		 if(cart==null){return 0;}
		 //遍历购物车中的商品信息
		 Set<Integer>keySet=cart.keySet();
		
		 BeanBook book;
		 ItemTinyBean item;
		float totalPrice=0;
		 
		 //记录购物车每项商品的信息
		 for(Iterator<Integer> ids=keySet.iterator();ids.hasNext();){
			  int bookId=ids.next();
			  int number=cart.get(bookId);
			  book=daoBook.getDetail(bookId);
			  item=new ItemTinyBean();
			  item.setItemID(bookId);
			  item.setItemNumber(number);
			  float marketPrice=MathUtilty.roundWithdigits(book.getMarketPrice());
			  float discount=MathUtilty.roundWithdigits(book.getDiscount());
			  float bookprice=MathUtilty.roundWithdigits(marketPrice*discount);
			  float itemTotalPrice=MathUtilty.roundWithdigits(bookprice*number);
			  item.setItemTotalPrice(itemTotalPrice);
			  item.setMarketPrice(marketPrice);
			  item.setName(book.getBookName());
			  item.setPrice(bookprice);//商城价
			  item.setSavePrice(MathUtilty.roundWithdigits(number*marketPrice*(1-discount)));
			  item.setPicturePath(book.getPicture());
			  items.add(item);
			  totalPrice+=itemTotalPrice;
		 }
		 return MathUtilty.roundWithdigits(totalPrice);
	}


// 将查询得到的书本信息装饰成前台所需的信息
	public void decorateForItem(List<BeanBook> books, List<ItemBean> items) {
		BeanBook book;
		ItemBean item;
		for (Iterator<BeanBook> index = books.iterator(); index.hasNext();) {
			item = new ItemBean();
			book = index.next();
			fillItemBean(book,item);
			items.add(item);
		}
	}
	

	
   

	
	
	
	

	
	
	
	// 得到目录的描述
	private String getCatalogName(BeanCatalog catalog) {
		if (catalog == null)
			return "";
		StringBuilder content = new StringBuilder();
		BeanCatalog upLevel = daoCatalog.getCatalogByID(catalog.getUpID());
		content.append(upLevel.getCatalogName());
		content.append("->");
		content.append(catalog.getCatalogName());

		return content.toString();
	}

	// 商品评论总数
	public int getCommentNumber(int itemId) {
		return daoComment.getCommnetNumber(itemId);
	}

	// 得到商品评论
	public List<BeanComment> getCommentWithPage(int itemId, int page, int lines) {
		return daoComment.getComment(itemId, page, lines);
	}

	// 得到商品平均得分
	/**
	 * 
	 * @param bookId
	 * @return
	 */
	public int getAverage(int bookId) {
		// daoComment.getAverageRating();
		return daoComment.getAverageRating(bookId);
	}

	// 增加用户对商品的评论
	public boolean addItemComment(BeanComment comment) {
		return daoComment.publishComment(comment);
	}

	// 根据id的集合填充相应的信息到tinyitems中
	
	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;

	}

	public DaoComment getDaoComment() {
		return daoComment;
	}

	public void setDaoComment(DaoComment daoComment) {
		this.daoComment = daoComment;
	}

	public DaoCatalog getDaoCatalog() {
		return daoCatalog;
	}

	public void setDaoCatalog(DaoCatalog daoCatalog) {
		this.daoCatalog = daoCatalog;
	}

	
}
