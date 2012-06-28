package org.symagic.user.action.order;

import java.util.List;
import java.util.Map;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.common.utilty.presentation.bean.ItemBean;

public class OrderSubmitAction extends OrderBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4501362220836715613L;

	/**
	 * 地址编号
	 */
	private String addressId;
	
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
	
	private List<ItemBean> items;
	
	private DaoOrder daoOrder;
	
	private DaoDistrict daoDistrict;
	

	private Map<String, String> parameters;



	


	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
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
	
	public List<ItemBean> getItems(){
		return items;
	}
	
	public void setItems(List<ItemBean> items){
		this.items = items;
	}
	
	@Override
	public String execute() throws Exception{
		BeanOrder order = new BeanOrder();
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
		order.setDeliveryWay("0");
		order.setPayment("0");
		
		for(int i = 0; i < items.size(); i ++){
			
		}
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
	
}
