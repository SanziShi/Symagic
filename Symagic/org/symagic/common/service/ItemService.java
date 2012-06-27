package org.symagic.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.db.func.BookRequire;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoComment;
import org.symagic.common.utilty.presentation.bean.CommentBean;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.common.utilty.presentation.bean.ItemDetailBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class ItemService {
    private DaoComment daoComment;//访问comment
	private DaoBook daoBook;//访问数据库中的书籍信息
	/**
	 * 
	 * @param sign 0普通搜索 1为高级搜索
	 * @param require
	 * @return List<BeanBook>
	 */
	public List<BeanBook> search(int sign,BookRequire require){
		return daoBook.search(sign,require);
	}
	
	//将查询 得到的书本信息装饰成前台所需的信息
	public void decorate(List<BeanBook>books,List<ItemDetailBean>items){
		
		BeanBook book;
		ItemDetailBean bean;
		for(Iterator<BeanBook>index=books.iterator();index.hasNext();){
			bean=new ItemDetailBean();
			book=index.next();
			bean.setItemId(String.valueOf(book.getBookId()));
			bean.setName(book.getBookName());
			bean.setMarketPrice(book.getMarketPrice());
			bean.setDiscount(book.getDiscount());
			bean.setAuthor(book.getAuthor());
			bean.setPublisher(book.getPublisher());
			bean.setPublishTime(book.getPublishDate());
			bean.setPicturePath(book.getPicture());
			bean.setPrice(book.getMarketPrice()*book.getDiscount());
			String status=book.getOffline();
			if(status.trim().equals("下架")){
				bean.setOffline(0);
			}
			else
			{
				bean.setOffline(1);
			}
			bean.setRating(getAverage(book.getBookId()));
			items.add(bean);
		}
	}
	
    //得到商品详情
	public BeanBook getDetail(int itemId){
		return daoBook.getDetail(itemId);
	}
    //商品评论总数
	public int getCommentNumber(int itemId){
		return daoComment.getCommnetNumber(itemId);
	}
	//得到商品评论
	public List<BeanComment> getCommentWithPage(int itemId,int page,int lines){
		return daoComment.getComment(itemId,page,lines);
	}
	//得到商品平均得分
	/**
	 * 
	 * @param bookId
	 * @return
	 */
	public int getAverage(int bookId){
		//daoComment.getAverageRating();
		return 0;
	}

	//增加用户对商品的评论
	public boolean addItemComment(BeanComment comment){
	 return  daoComment.publishComment(comment);
  }
	
	
	//根据id的集合填充相应的信息到items中
	public void fillItem(List<ItemBean>items,List<Integer>ids){
			 ItemBean item;
		     BeanBook book;
			for(Iterator<Integer> index=ids.iterator();index.hasNext();){
				int id=index.next();
				item=new ItemBean();
				item.setItemId(id);
				
				book=daoBook.getDetail(id);
				item.setName(book.getBookName());
				item.setPicturePath(book.getPicture());
				float marketPrice=book.getMarketPrice();
				float discount=book.getDiscount();
				item.setPrice(marketPrice*discount);
				item.setSavePrice(marketPrice*(1-discount));
				
				items.add(item);
			}
		}
	
	public float fillItemWithNumber(ArrayList<ItemBean>items){
		//获得购物车
		 HashMap<Integer,Integer> cart=UserSessionUtilty.getCart();
		 //遍历购物车中的商品信息
		 Set<Integer>keySet=cart.keySet();
		
		 BeanBook book;
		 ItemBean item;
		float totalPrice=0;
		 
		 //记录购物车每项商品的信息
		 for(Iterator<Integer> ids=keySet.iterator();ids.hasNext();){
			  int bookId=ids.next();
			  int number=cart.get(bookId);
			  book=daoBook.getDetail(bookId);
			  item=new ItemBean();
			  item.setItemId(bookId);
			  item.setItemNumber(number);
			  float marketPrice=book.getMarketPrice();//书本市场价
			  float discount=book.getDiscount();//书本折扣价
			  float itemTotalPrice=marketPrice*number*discount;
			  item.setItemTotalPrice(itemTotalPrice);
			  item.setName(book.getBookName());
			  item.setPrice(marketPrice*discount);//商城价
			  item.setSavePrice(number*marketPrice*(1-discount));
			  item.setPicturePath(book.getPicture());
			  items.add(item);
			  totalPrice+=itemTotalPrice;
		 }
		 return totalPrice;
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

	
}
