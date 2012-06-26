package org.symagic.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoComment;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class ItemService {

	private DaoBook daoBook;//访问数据库中的书籍信息
	private DaoComment daoComment;
	public DaoBook getDaoBook() {
		return daoBook;
	}


	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
		
	}


	//增加用户对商品的评论
	public boolean addItemComment(BeanComment comment){
	 return daoComment.publishComment(comment);
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


	public DaoComment getDaoComment() {
		return daoComment;
	}


	public void setDaoComment(DaoComment daoComment) {
		this.daoComment = daoComment;
	}


	
}
