package org.symagic.user.action.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.user.utilty.MathUtilty;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class CartDetailAction extends ActionSupport {
 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9126493268779714120L;

	
private  ItemService itemService;//访问商品信息

private ArrayList<ItemTinyBean>items;//购物车中每项商品
private Integer totalNumber;//购物车中的商品数量
private Float totalPrice;//购物车中商品的总价
 
 @Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	 items=new ArrayList<ItemTinyBean>();

	 totalPrice=itemService.fillItemFromCart(items);
     totalNumber=UserSessionUtilty.getTotalNumber();
	
	 return super.execute();
	}
 
 public ItemService getItemService() {
		return itemService;
	}



	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}



	public ArrayList<ItemTinyBean> getItems() {
		return items;
	}



	public void setItems(ArrayList<ItemTinyBean> items) {
		this.items = items;
	}


public Float getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(float totalPrice) {
	this.totalPrice = totalPrice;
}
public Integer getTotalNumber() {
	return totalNumber;
}



public void setTotalNumber(Integer totalNumber) {
	this.totalNumber = totalNumber;
}
 
}
