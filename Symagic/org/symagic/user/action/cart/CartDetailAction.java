package org.symagic.user.action.cart;

import org.symagic.common.utilty.presentation.bean.ItemBean;

import com.opensymphony.xwork2.ActionSupport;

public class CartDetailAction extends ActionSupport {
 private DaoBook daoCart
private ItemBean[] items;//购物车中每项商品
 private float totalPrice;//购物车中商品的总价
 
 @Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
 public ItemBean[] getItems() {
	return items;
}
public void setItems(ItemBean[] items) {
	this.items = items;
}
public float getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(float totalPrice) {
	this.totalPrice = totalPrice;
}

 
}
