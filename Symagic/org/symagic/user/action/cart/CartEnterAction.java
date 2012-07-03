package org.symagic.user.action.cart;

import java.util.ArrayList;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class CartEnterAction extends CatalogBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2966695836419045173L;
	//配置项
	private ItemService itemService;
	//传出
	private Float totalPrice;
	
	private ArrayList<ItemTinyBean>items;

	private Integer totalNumber;//购物车商品数量

	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	 items=new ArrayList<ItemTinyBean>();
	 totalPrice=itemService.fillItemFromCart(items);
	 totalNumber=UserSessionUtilty.getTotalNumber();
	
	 return super.execute();
	}

public Float getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(float totalPrice) {
	this.totalPrice = totalPrice;
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

public Integer getTotalNumber() {
	return totalNumber;
}

public void setTotalNumber(Integer totalNumber) {
	this.totalNumber = totalNumber;
}
}
