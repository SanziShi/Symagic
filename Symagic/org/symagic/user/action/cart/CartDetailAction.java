package org.symagic.user.action.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class CartDetailAction extends ActionSupport {
 
	public ItemService getItemService() {
		return itemService;
	}



	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}



	public ArrayList<ItemBean> getItems() {
		return items;
	}



	public void setItems(ArrayList<ItemBean> items) {
		this.items = items;
	}
private  ItemService itemService;//访问商品信息

private ArrayList<ItemBean>items;//购物车中每项商品

private float totalPrice;//购物车中商品的总价
 
 @Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	 items=new ArrayList<ItemBean>();
	 totalPrice=itemService.fillItemWithNumber(items);
	 return super.execute();
	}
 


public float getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(float totalPrice) {
	this.totalPrice = totalPrice;
}

 
}
