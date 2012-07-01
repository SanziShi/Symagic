package org.symagic.user.action.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanDistrict;
import org.symagic.common.db.bean.BeanLevel;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoLevel;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.db.func.DaoUser;
import org.symagic.common.service.OrderService;
import org.symagic.common.service.RecommandService;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class OrderSubmitAction extends OrderBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4501362220836715613L;
	
	/*
	 * 订单获取的积分
	 */
	private Integer score;
	
	/*
	 * 地址代码
	 */
	private Integer level1Id;
	
	private Integer level2Id;
	
	private Integer level3Id;
	
	private String addressDetail;
	
	private DaoOrder daoOrder;
	
	private DaoDistrict daoDistrict;
	
	private RecommandService recommandService;

	private String items;
	
	private DaoBook daoBook;
	
	private DaoUser daoUser;
	
	private DaoLevel daoLevel;
	
	public DaoLevel getDaoLevel() {
		return daoLevel;
	}

	public void setDaoLevel(DaoLevel daoLevel) {
		this.daoLevel = daoLevel;
	}

	private boolean isValidate;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getLevel1Id() {
		return level1Id;
	}

	public void setLevel1Id(Integer level1Id) {
		this.level1Id = level1Id;
	}

	public Integer getLevel2Id() {
		return level2Id;
	}

	public void setLevel2Id(Integer level2Id) {
		this.level2Id = level2Id;
	}

	public Integer getLevel3Id() {
		return level3Id;
	}

	public void setLevel3Id(Integer level3Id) {
		this.level3Id = level3Id;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	
	public DaoOrder getDaoOrder(){
		return this.daoOrder;
	}
	
	public void setDaoOrder(DaoOrder order){
		this.daoOrder = order;
	}
	
	public String getItems(){
		return items;
	}
	
	public void setItems(String items){
		this.items = items;
	}
	
	@Override
	public String execute() throws Exception{ 
		String username = UserSessionUtilty.getUsername();
		if(!isValidate)
			return ERROR;
		BeanOrder order = new BeanOrder();
		//order地址处理
		OrderService.Address address = new OrderService.Address();
		address.districtDetail = this.addressDetail;
		address.level1District = new DistrictBean();
		address.level1District.setID(this.level1Id);
		BeanDistrict level1 = daoDistrict.getDistrictById(level1Id);
		if(level1 != null){
			address.level1District.setName(level1.getName());
			address.level1District.setID(level1.getId());
		}
		else {
			return ERROR;
		}
		address.level2District = new DistrictBean();
		address.level2District.setID(this.level2Id);
		BeanDistrict level2 = daoDistrict.getDistrictById(level2Id);
		if(level2 != null)
			address.level2District.setName(level2.getName());
		else{
			return ERROR;
		}
		address.level3District = new DistrictBean();
		address.level3District.setID(this.level3Id);
		BeanDistrict level3 = daoDistrict.getDistrictById(level3Id);
		if(level3 != null)
			address.level3District.setName(level3.getName());
		else
			return ERROR;
		order.setAddrDetail(OrderService.serializerAddress(address));
		order.setMobilenum(getMobileNum());
		order.setPhonenum(getPhoneNum());
		order.setReceiverName(getReceiverName());
		order.setZipcode(getZipcode());
		order.setDeliveryWay("0");
		order.setPayment("0");
		
		order.setList(new ArrayList<BeanOrderDetail>());
		
		
		
		String baseURL = ServletActionContext.getServletContext().getContextPath();
		JSON json = JSONSerializer.toJSON(items);
		List<ItemTinyBean> items = new ArrayList<ItemTinyBean>();
		if(json.isArray()){
			JSONArray jsonArray = (JSONArray)json;
			for(int i = 0; i < jsonArray.size(); i ++){
				JSONObject object = (JSONObject) jsonArray.get(i);
				ItemTinyBean item = (ItemTinyBean) JSONObject.toBean(object, ItemTinyBean.class);
				items.add(item);
				if(daoBook.getInventory(item.getItemID()) < item.getItemNumber())
					return ERROR;
			
			}
		}
		float totalPrice = 0;
		
		for(int i = 0; i < items.size(); i ++){
			if(daoBook.getInventory(items.get(i).getItemID()) < items.get(i).getItemNumber())
				daoBook.setInventory(items.get(i).getItemID(),
						daoBook.getInventory(items.get(i).getItemID())
						- items.get(i).getItemNumber());
			
			BeanBook book = daoBook.getDetail(items.get(i).getItemID());
			if(book == null)
				continue;
			BeanOrderDetail orderDetail = new BeanOrderDetail();
			orderDetail.setAmount(items.get(i).getItemNumber());
			orderDetail.setBookId(items.get(i).getItemID());
			orderDetail.setBookName(book.getBookName());
			orderDetail.setDiscount(book.getDiscount());
			orderDetail.setIsbn(book.getIsbn());
			orderDetail.setMarketPrice(book.getMarketPrice());
			order.getList().add(orderDetail);
			totalPrice += book.getMarketPrice() - book.getDiscount();
			String URL = baseURL + "itemDetail?itemID=" + book.getBookId();
			//通知推荐系统用户购买
			for(int j = 0; j < items.get(i).getItemNumber(); j ++){
				recommandService.buy(UserSessionUtilty.getSessionID(),
						Integer.toString(items.get(j).getItemID()),
						null, URL, UserSessionUtilty.getUsername());
			}
			
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
		order.setOrderDate(sdf.format(date));
		
		order.setTotalprice(totalPrice);
		BeanLevel level = daoLevel.judgeLevel(daoUser.getScore(username));
		order.setScore((int)(totalPrice * level.getRate()));
		order.setUsername(username);
		order.setZipcode(getZipcode());
		if(daoOrder.addOrder(order))
			return SUCCESS;
		else {
			return ERROR;
		}
	}
	
	public void validate(){
		if(daoUser.getScore(UserSessionUtilty.getUsername()) < score){
			isValidate = false;
			return;
		}
		if(getZipcode() == null || getAddressDetail() == null || getItems() == null || getLevel1Id() == null||
				getLevel2Id() == null || getLevel3Id() == null || getMobileNum() == null||
				getPhoneNum() == null){
			isValidate = false;
			return;
		}
			
		isValidate = true;
	}

	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
	}

	public void setDaoDistrict(DaoDistrict daoDistrict) {
		this.daoDistrict = daoDistrict;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

	public RecommandService getRecommandService() {
		return recommandService;
	}

	public void setRecommandService(RecommandService recommandService) {
		this.recommandService = recommandService;
	}

	public DaoUser getDaoUser() {
		return daoUser;
	}

	public void setDaoUser(DaoUser daoUser) {
		this.daoUser = daoUser;
	}
	
}
