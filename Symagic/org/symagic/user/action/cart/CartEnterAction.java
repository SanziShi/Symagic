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
	private float totalPrice;
	private ItemService itemService;
	private ArrayList<ItemTinyBean>items;
	private ArrayList<ItemTinyBean>recommendItems;
	private Integer totalNumber;//购物车商品数量

	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	 items=new ArrayList<ItemTinyBean>();
	 totalPrice=itemService.fillItemWithNumber(items);
	 totalNumber=UserSessionUtilty.getTotalNumber();
	 //推荐商品
	 /*
	  *test 
	  */
	  recommendItems=new ArrayList<ItemTinyBean>();
	  itemService.getNewBook(recommendItems);
	 return super.execute();
	}

public float getTotalPrice() {
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
public ArrayList<ItemTinyBean> getRecommendItems() {
	return recommendItems;
}

public void setRecommendItems(ArrayList<ItemTinyBean> recommendItems) {
	this.recommendItems = recommendItems;
}
public Integer getTotalNumber() {
	return totalNumber;
}

public void setTotalNumber(Integer totalNumber) {
	this.totalNumber = totalNumber;
}
}
