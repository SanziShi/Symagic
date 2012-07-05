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
import org.symagic.common.utilty.presentation.bean.CommentBean;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.common.utilty.presentation.bean.ItemDetailBean;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.common.utilty.presentation.bean.TimeBean;
import org.symagic.user.utilty.MathUtilty;
import org.symagic.user.utilty.UserSessionUtilty;

public class ItemService {

	// 配置项
	private DaoComment daoComment;// 访问comment
	private DaoBook daoBook;// 访问数据库中的书籍信息
	private DaoCatalog daoCatalog;
	private OrderService orderService;
	private RecommendService recommendService;

	/**
	 * 
	 * @param sign
	 *            0普通搜索 1为高级搜索
	 * @param require
	 * @return List<BeanBook>
	 */

	// 得到热销书
	public void getHotBook(Integer recommendNumber, List<ItemTinyBean> hotBook) {
		List<Integer> hotBookIDs = recommendService
				.mostBoughtItems(recommendNumber);
		if (hotBookIDs == null) {
			hotBookIDs = new ArrayList<Integer>();
		}
		// 随机
		List<Integer> random = daoBook.getRandBooks(hotBookIDs, recommendNumber
				- hotBookIDs.size());
		if (random != null) {
			hotBookIDs.addAll(random);
		}
		fillTinyItems(hotBookIDs, hotBook);

	}

	public void getViewFromItem(Integer recommendNumber, Integer itemID,
			List<ItemTinyBean> viewItem) {
		List<Integer> viewIDs = recommendService.otherUsersAlsoViewed(
				itemID.toString(), UserSessionUtilty.getUsername(),
				recommendNumber);
		if (viewIDs == null) {
			viewIDs = new ArrayList<Integer>();
		}
		// 随机
		List<Integer> random = daoBook.getRandBooks(viewIDs, recommendNumber
				- viewIDs.size());
		if (random != null) {
			viewIDs.addAll(random);
		}
		fillTinyItems(viewIDs, viewItem);

	}

	public void commentForRecomend(Integer rate, Integer itemID, String desc) {
		recommendService
				.rate(UserSessionUtilty.getSessionID(), String.valueOf(rate),
						String.valueOf(itemID), desc, "item_detail?itemID="
								+ itemID, UserSessionUtilty.getUsername());
	}

	public void viewForRecommend(Integer itemID, String desc) {
		recommendService.view(UserSessionUtilty.getSessionID(),
				String.valueOf(itemID), desc, "item_detail?itemID=" + itemID,
				UserSessionUtilty.getUsername());
	}

	public void getBoughtFromItem(Integer recommendNumber, Integer itemID,
			List<ItemTinyBean> boughtItem) {
		List<Integer> boughtIDs = recommendService.otherUsersAlsoBought(
				itemID.toString(), UserSessionUtilty.getUsername(),
				recommendNumber);
		if (boughtIDs == null) {
			boughtIDs = new ArrayList<Integer>();
		}
		List<Integer> random = daoBook.getRandBooks(boughtIDs, recommendNumber
				- boughtIDs.size());
		// 随机
		if (random != null) {
			boughtIDs.addAll(random);
		}
		fillTinyItems(boughtIDs, boughtItem);
	}

	// 得到推荐项,如果登录,根据用户推荐 ,数量不足,用浏览最多补足,再不足,随机补足
	public void getRecommendBook(Integer recommendNumber, Boolean login,
			List<ItemTinyBean> recommendItem) {
		List<Integer> recommend = new ArrayList<Integer>();
		List<Integer> mostView = null;
		List<Integer> random = null;
		if (login) {
			recommend = recommendService.recommendationsForUser(
					UserSessionUtilty.getUsername(), recommendNumber);
		}
		if (recommend == null) {
			recommend = new ArrayList<Integer>();
		}
		// 用浏览最多补足
		if (recommend.size() < recommendNumber) {
			mostView = recommendService.mostViewedItems(recommendNumber);
			if (mostView != null) {
				Iterator<Integer> index = mostView.iterator();
				while (index.hasNext()) {
					if (recommend.size() >= recommendNumber)
						break;
					Integer itemID = index.next();
					if (!recommend.contains(itemID)) {
						recommend.add(itemID);
					}
				}
			}
		}
		// 用随机组成
		random = daoBook.getRandBooks(recommend,
				recommendNumber - recommend.size());
		if (random != null) {
			recommend.addAll(random);
		}

		fillTinyItems(recommend, recommendItem);
	}

	public void getNewBook(List<ItemTinyBean> books) {
		List<BeanBook> newBooks = daoBook.getLatestBook();
		if (newBooks == null)
			return;
		ItemTinyBean item;
		for (Iterator<BeanBook> index = newBooks.iterator(); index.hasNext();) {
			item = new ItemTinyBean();
			BeanBook book = index.next();
			fillItemTinyBean(book, item);
			books.add(item);
		}
	}

	public List<BeanBook> search(Integer sign, BookRequire require) {
		return daoBook.search(sign, require);
	}

	public Integer getSearchNum(int sign, BookRequire require) {
		return daoBook.getSearchRowNumber(sign, require);
	}

	public List<Integer> getCatalogList(Integer catalogID) {
		List<Integer> catalogList = new ArrayList<Integer>();
		BeanCatalog requiredCatalog = daoCatalog.getCatalogByID(catalogID);
		
	       //搜索的目录
			catalogList.add(catalogID);
	
		// 对于一级目录，将其二级目录也加搜索范围
		if(requiredCatalog.getLevel().trim().equals("1")) {
			List<BeanCatalog> allCatalog = daoCatalog.getCatalog();
			BeanCatalog catalog;
			for (Iterator<BeanCatalog> index = allCatalog.iterator(); index
					.hasNext();) {
				catalog = index.next();
				if (catalog.getUpID() == catalogID) {
					catalogList.add(catalog.getCatalogID());
				}
			}
		}
		return catalogList;
	}

	// 为推荐项填充信息
	private void fillItemTinyBean(BeanBook book, ItemTinyBean item) {

		item.setItemID(book.getBookId());
		item.setName(book.getBookName());
		item.setPicturePath(book.getPicture());
		float marketPrice = book.getMarketPrice();
		float discount = book.getDiscount();
		item.setPrice(String.format("%.2f",
				(MathUtilty.roundWithdigits(marketPrice * discount))));
	}

	// 搜索显示出来的信息

	private void fillItemBean(BeanBook book, ItemBean item) {
		item.setItemID(String.valueOf(book.getBookId()));
		item.setName(book.getBookName());

		item.setDiscount(String.format("%.2f",
				MathUtilty.roundWithdigits(book.getDiscount())));
		item.setPicturePath(book.getPicture());
		item.setPublishTime(book.getPublishDate());
		item.setPublisher(book.getPublisher());
		item.setAuthor(book.getAuthor());
		float marketPrice = MathUtilty.roundWithdigits(book.getMarketPrice());
		float price = MathUtilty.roundWithdigits(marketPrice
				* book.getDiscount());
		item.setPrice(String.format("%.2f", price));
		item.setMarketPrice(String.format("%.2f", marketPrice));
		item.setSavePrice(String.format("%.2f",
				MathUtilty.roundWithdigits(marketPrice - price)));
		String status = book.getOffline();
		if (status.trim().equals("下架")) {
			item.setOffline(true);
		} else {
			item.setOffline(false);
		}
		item.setRating(getAverage(book.getBookId()));
		item.setInventory(book.getInventory());
	}

	public void fillItemBean(Integer itemID, ItemBean item) {
		BeanBook book = daoBook.getDetail(itemID);
		if (book != null)
			fillItemBean(book, item);
	}

	public void fillTinyItems(List<Integer> ids, List<ItemTinyBean> items) {
		ItemTinyBean item;
		BeanBook book;
		if (ids == null)
			return;
		for (Iterator<Integer> index = ids.iterator(); index.hasNext();) {
			int id = index.next();
			item = new ItemTinyBean();
			book = daoBook.getDetail(id);
			if (book != null)
				fillItemTinyBean(book, item);
			items.add(item);
		}
	}

	/**
	 * 将购物车的信息进行填充
	 * 
	 * @param items不能为null
	 * @return 商品总价格
	 */
	public float fillItemFromCart(ArrayList<ItemTinyBean> items) {
		if (items == null)
			return -1;
		// 获得购物车
		HashMap<Integer, Integer> cart = UserSessionUtilty.getCart();

		if (cart == null) {
			return 0;
		}
		// 遍历购物车中的商品信息
		Set<Integer> keySet = cart.keySet();

		BeanBook book;
		ItemTinyBean item;
		float totalPrice = 0;

		// 记录购物车每项商品的信息
		for (Iterator<Integer> ids = keySet.iterator(); ids.hasNext();) {
			int bookId = ids.next();
			int number = cart.get(bookId);
			book = daoBook.getDetail(bookId);
			item = new ItemTinyBean();
			item.setItemID(bookId);
			item.setItemNumber(number);
			float marketPrice = MathUtilty.roundWithdigits(book
					.getMarketPrice());
			float discount = MathUtilty.roundWithdigits(book.getDiscount());

			float bookprice = MathUtilty
					.roundWithdigits(marketPrice * discount);
			float itemTotalPrice = MathUtilty.roundWithdigits(bookprice
					* number);
			item.setItemTotalPrice(String.format("%.2f", itemTotalPrice));
			item.setMarketPrice(String.format("%.2f", marketPrice));

			item.setName(book.getBookName());

			item.setPrice(String.format("%.2f", bookprice));// 商城价
			item.setSavePrice(String.format("%.2f",
					MathUtilty.roundWithdigits(marketPrice - bookprice)));

			item.setPicturePath(book.getPicture());
			items.add(item);
			totalPrice += itemTotalPrice;
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
			fillItemBean(book, item);
			items.add(item);
		}
	}

	// 得到商品详情
	public boolean fillDetailBean(Integer itemId, ItemDetailBean detail) {
		BeanBook book = daoBook.getDetail(itemId);
		if (book == null)
			return false;
		BeanCatalog currentCatalog = null;
		if (book.getCatalogID() != null && book.getCatalogID() != 0 ) {
			currentCatalog = daoCatalog.getCatalogByID(book.getCatalogID());
			detail.setCatalogClassify(getCatalogName(currentCatalog));
		}
		detail.setAuthor(book.getAuthor());
		int rating = daoComment.getAverageRating(itemId);
		detail.setAverageRating(rating);
		detail.setBinding(book.getBinding());
		detail.setBookDesc(book.getBookDesc());
		detail.setBookName(book.getBookName());
		detail.setDiscout(String.format("%.2f", book.getDiscount()));
		detail.setSize(book.getFolio());
		detail.setInventory(book.getInventory());
		detail.setIsbn(book.getIsbn());
		float marketPrice = MathUtilty.roundWithdigits(book.getMarketPrice());
		float discount = MathUtilty.roundWithdigits(book.getDiscount());
		detail.setMarketPrice(String.format("%.2f", marketPrice));
		if (book.getOffline().equals("下架")) {
			detail.setOffline(true);
		} else {
			detail.setOffline(false);
		}
		detail.setPage(book.getPage());
		float price = MathUtilty.roundWithdigits(discount * marketPrice);
		detail.setPrice(String.format("%.2f", price));
		detail.setSavePrice(String.format("%.2f",
				MathUtilty.roundWithdigits(marketPrice - price)));
		detail.setPublishDate(book.getPublishDate());
		detail.setPublisher(book.getPublisher());
		detail.setVersion(book.getVersion());
		detail.setPicturePath(book.getPicture());

		// 时间解析
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
		parseTime.setMonth(calender.get(Calendar.MONTH) + 1);
		parseTime.setDay(calender.get(Calendar.DAY_OF_MONTH));
		detail.setParseTime(parseTime);

		// 设置当前选中的ID

if (currentCatalog != null) {
		CatalogBean catalog = new CatalogBean();

		catalog.setID(currentCatalog.getCatalogID());
		catalog.setDesc(currentCatalog.getCatalogDesc());
		catalog.setName(currentCatalog.getCatalogName());
		catalog.setChildCatalog(null);
		detail.setParseCatalog(catalog);
}
		// 是否可以评论，默认不能评论
		detail.setCommentAble(isCommentAble(book.getBookId()));

		return true;
	}

	// 是否能够评论
	public boolean isCommentAble(Integer itemID) {
		// 是否已登录
		if (UserSessionUtilty.isLogin()) {
			// 购买记录
			int purchaseRecord = orderService.orderNumber(
					UserSessionUtilty.getUsername(), itemID);
			// 已评论数量
			int commentNumber = daoComment.getNumByID(
					UserSessionUtilty.getUsername(), itemID);
			if (purchaseRecord > commentNumber) {
				return true;
			}
		}
		return false;
	}

	// 得到目录的字符串表示
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
	public Integer getCommentNumber(Integer itemId) {
		return daoComment.getCommnetNumber(itemId);
	}

	// 得到商品评论
	public List<BeanComment>getCommentWithPage(Integer itemId, Integer page, Integer lines){
		return daoComment.getComment(itemId, page, lines);
	}
	public List<CommentBean> getComments(Integer itemId, Integer page, Integer lines) {
		List<BeanComment> getResult=daoComment.getComment(itemId, page, lines);
		List<CommentBean> result=new ArrayList<CommentBean>();
		BeanComment comment;
		CommentBean item;
		String username;
		for(Iterator<BeanComment>index=getResult.iterator();index.hasNext();){
			comment=index.next();
			item=new CommentBean();
			item.setContent(comment.getContent());
			item.setDate(comment.getCommentDate());
			item.setRating(comment.getRating());
			item.setUserName(replace(3,4,comment.getUsername()));
			result.add(item);
			
		}
		return result;
	}
	
	private   String replace(Integer from ,Integer length,String source){
		if(source==null||source.trim().length()==0)
			return "";
		if(from>source.length()){
			from=0;
		}
		if(from+length>source.length()){
			length=source.length()-from;
		}
		
		
		String reg=source.substring(from, from+length);
		StringBuilder replace=new StringBuilder();
		for(int index=0;index<reg.length();index++){
			replace.append("*");
		}
	  return source.replaceAll(reg, replace.toString());
		
	}

	// 得到商品平均得分
	/**
	 * 
	 * @param bookId
	 * @return
	 */
	public int getAverage(Integer bookId) {
		// daoComment.getAverageRating();
		return daoComment.getAverageRating(bookId);
	}

	// 增加用户对商品的评论
	public boolean addItemComment(BeanComment comment) {
		// 是否能评论
		if (isCommentAble(comment.getBookID()))
			return daoComment.publishComment(comment);
		else
			return false;
	}

	

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

	public OrderService getOrderService() {
		return orderService;
	}

	public RecommendService getRecommendService() {
		return recommendService;
	}

	public void setRecommendService(RecommendService recommendService) {
		this.recommendService = recommendService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

}
