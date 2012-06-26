package org.symagic.user.action.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class CartDetailAction extends ActionSupport {
 private DaoBook daoBook;//访问书籍信息

private List<ItemBean>items;//购物车中每项商品

private float totalPrice=0;//购物车中商品的总价
 
 @Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	//获得购物车
	 HashMap<Integer,Integer> cart=UserSessionUtilty.getCart();
	 //遍历购物车中的商品信息
	 Set<Integer>keySet=cart.keySet();
	 BeanBook book;
	 ItemBean item;
	 items=new ArrayList<ItemBean>();
	
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
		  item.setPrice(marketPrice*discount);
		  item.setSavePrice(number*marketPrice*(1-discount));
		  items.add(item);
		  totalPrice+=itemTotalPrice;
	 }
	 
	 return super.execute();
	}
 

public List<ItemBean> getItems() {
	return items;
}


public void setItems(List<ItemBean> items) {
	this.items = items;
}

 public DaoBook getDaoBook() {
		return daoBook;
	}
	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

public float getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(float totalPrice) {
	this.totalPrice = totalPrice;
}

 
}
