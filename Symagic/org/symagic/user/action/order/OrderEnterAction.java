package org.symagic.user.action.order;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.service.AddressService;
import org.symagic.common.utilty.presentation.bean.AddressDetailBean;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.user.utilty.MathUtilty;
import org.symagic.user.utilty.UserSessionUtilty;

public class OrderEnterAction extends CatalogBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4781084758132850598L;

	private List<AddressDetailBean> addressList;
	
	private String userName;
	
	private Float price;
	
	private String payment;
	
	private String deliverWay;
	
	private boolean isValidate;
	
	private String buyItems;
	
	private AddressService addressService;
	
	private DaoBook daoBook;
	
	private List<ItemTinyBean> items;

	public List<AddressDetailBean> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressDetailBean> addressList) {
		this.addressList = addressList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getDeliverWay() {
		return deliverWay;
	}

	public void setDeliverWay(String deliverWay) {
		this.deliverWay = deliverWay;
	}

	@Override
	public String execute() throws Exception {
		if(!isValidate)
			return ERROR;
		JSON json = JSONSerializer.toJSON(items);
		items = new ArrayList<ItemTinyBean>();
		price = 0.0f;
		if(json.isArray()){
			JSONArray jsonArray = (JSONArray)json;
			for(int i = 0; i < jsonArray.size(); i ++){
				Integer itemId = jsonArray.getInt(i);
				BeanBook beanBook = daoBook.getDetail(itemId);
				if(beanBook != null){
					ItemTinyBean itemTinyBean = new ItemTinyBean();
					itemTinyBean.setItemID(beanBook.getBookId());
					itemTinyBean.setItemNumber(jsonArray.getInt(i));
					itemTinyBean.setPrice(MathUtilty.roundWithdigits
							(beanBook.getMarketPrice() * beanBook.getMarketPrice()));
					itemTinyBean.setItemTotalPrice(MathUtilty.roundWithdigits
							(itemTinyBean.getPrice() * itemTinyBean.getItemNumber()));
					price += itemTinyBean.getItemTotalPrice();
					items.add(itemTinyBean);
				}
			}
		}
		userName = UserSessionUtilty.getUsername();
		addressList = addressService.getAddressDetail(userName);
		payment = "货到付款";
		deliverWay = "快递";
		return super.execute();
	}

	@Override
	public void validate() {
		if(items == null){
			isValidate = false;
			return;
		}
		isValidate = true;
		super.validate();
	}

	public String getBuyItems() {
		return buyItems;
	}

	public void setBuyItems(String buyItems) {
		this.buyItems = buyItems;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

	public List<ItemTinyBean> getItems() {
		return items;
	}

	public void setItems(List<ItemTinyBean> items) {
		this.items = items;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	
	
}
