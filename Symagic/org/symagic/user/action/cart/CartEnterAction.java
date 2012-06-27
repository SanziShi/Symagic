package org.symagic.user.action.cart;

import java.util.ArrayList;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.ItemBean;

public class CartEnterAction extends CatalogBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2966695836419045173L;
	private float totalPrice;
	private ItemService itemService;
	private ArrayList<ItemBean>items;
	private ArrayList<ItemBean>recommendItems;
	private Integer totalNumber;

	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	 items=new ArrayList<ItemBean>();
	 totalPrice=itemService.fillItemWithNumber(items);
	 //推荐商品
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
public ArrayList<ItemBean> getItems() {
	return items;
}
public void setItems(ArrayList<ItemBean> items) {
	this.items = items;
}
public ArrayList<ItemBean> getRecommendItems() {
	return recommendItems;
}

public void setRecommendItems(ArrayList<ItemBean> recommendItems) {
	this.recommendItems = recommendItems;
}
public Integer getTotalNumber() {
	return totalNumber;
}

public void setTotalNumber(Integer totalNumber) {
	this.totalNumber = totalNumber;
}
}
