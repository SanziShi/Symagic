package org.symagic.user.action.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.symagic.common.db.bean.BeanAddress;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.db.func.DaoUserAddress;
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

	/**
	 * 地址编号
	 */
	private String addressID;
	
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
	
	private List<ItemTinyBean> items;
	
	private DaoOrder daoOrder;
	
	private DaoDistrict daoDistrict;
	
	private DaoBook daoBook;
	
	private RecommandService recommandService;
	
	private DaoUserAddress daoUserAddress;

	public DaoBook getBook() {
		return daoBook;
	}

	public void setBook(DaoBook book) {
		this.daoBook = book;
	}

	private Map<String, String> parameters;



	


	public String getAddressId() {
		return addressID;
	}

	public void setAddressId(String addressId) {
		this.addressID = addressId;
	}

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
	
	public List<ItemTinyBean> getItems(){
		return items;
	}
	
	public void setItems(List<ItemTinyBean> items){
		this.items = items;
	}
	
	@Override
	public String execute() throws Exception{
		BeanOrder order = new BeanOrder();
		if(addressID == null){
			OrderService.Address address = new OrderService.Address();
			address.districtDetail = this.addressDetail;
			address.level1District = new DistrictBean();
			address.level1District.setID(this.level1Id);
			address.level1District.setName(daoDistrict.getDistrictById(level1Id).getName());
			address.level2District = new DistrictBean();
			address.level2District.setID(this.level2Id);
			address.level2District.setName(daoDistrict.getDistrictById(level2Id).getName());
			address.level3District = new DistrictBean();
			address.level3District.setID(this.level3Id);
			address.level3District.setName(daoDistrict.getDistrictById(level3Id).getName());
			order.setAddrDetail(OrderService.serializerAddress(address));
			order.setMobilenum(getMobileNum());
			order.setPhonenum(getPhoneNum());
			order.setZipcode(getZipcode());
			order.setReceiverName(getReceiverName());
		}
		else{
			BeanAddress beanAddress = daoUserAddress.getAddressDetail(UserSessionUtilty.getUsername(), Integer.parseInt(addressID));
			order.setAddrDetail(beanAddress.getAddrdetail());
			order.setMobilenum(beanAddress.getMobilenum());
			order.setPhonenum(beanAddress.getPhonenum());
			order.setReceiverName(beanAddress.getReceivername());
			order.setZipcode(beanAddress.getZipcode());
			order.setUsername(UserSessionUtilty.getUsername());
		}
		
		order.setDeliveryWay("0");
		order.setPayment("0");
		
		//查库存
		for(int i = 0; i < items.size(); i ++){
			int inventory = daoBook.getInventory(items.get(i).getItemID());
			if(inventory < items.get(i).getItemNumber())
				return ERROR;
		}
		
		//减库存, 夹详情
		List<BeanOrderDetail> orderItems = new ArrayList<BeanOrderDetail>();
		for(int i = 0; i < items.size(); i ++){
			daoBook.setInventory(items.get(i).getItemID(),
					daoBook.getInventory(items.get(i).getItemID())
					- items.get(i).getItemNumber());
			BeanBook book = daoBook.getDetail(items.get(i).getItemID());
			BeanOrderDetail orderItem = new BeanOrderDetail();
			orderItem.setAmount(items.get(i).getItemNumber());
			orderItem.setBookId(items.get(i).getItemID());
			orderItem.setBookName(book.getBookName());
			orderItem.setDiscount(book.getDiscount());
			orderItem.setIsbn(book.getIsbn());
			orderItem.setMarketPrice(book.getMarketPrice());
			orderItems.add(orderItem);
		}
		order.setList(orderItems);
		return SUCCESS;
	}

	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
	}

	public void setDaoDistrict(DaoDistrict daoDistrict) {
		this.daoDistrict = daoDistrict;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public RecommandService getRecommandService() {
		return recommandService;
	}

	public void setRecommandService(RecommandService recommandService) {
		this.recommandService = recommandService;
	}

	public DaoUserAddress getDaoUserAddress() {
		return daoUserAddress;
	}

	public void setDaoUserAddress(DaoUserAddress daoUserAddress) {
		this.daoUserAddress = daoUserAddress;
	}
	
}
