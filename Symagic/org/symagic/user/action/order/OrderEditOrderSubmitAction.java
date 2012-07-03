package org.symagic.user.action.order;

import org.symagic.common.db.bean.BeanDistrict;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class OrderEditOrderSubmitAction extends OrderBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3290293998049828238L;

	private Integer districtLevel1ID;
	
	private Integer districtLevel2ID;
	
	private Integer districtLevel3ID;
	
	public Integer getDistrictLevel1ID() {
		return districtLevel1ID;
	}

	public void setDistrictLevel1ID(Integer districtLevel1ID) {
		this.districtLevel1ID = districtLevel1ID;
	}

	public Integer getDistrictLevel2ID() {
		return districtLevel2ID;
	}

	public void setDistrictLevel2ID(Integer districtLevel2ID) {
		this.districtLevel2ID = districtLevel2ID;
	}

	public Integer getDistrictLevel3ID() {
		return districtLevel3ID;
	}

	public void setDistrictLevel3ID(Integer districtLevel3ID) {
		this.districtLevel3ID = districtLevel3ID;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public DaoOrder getDaoOrder() {
		return daoOrder;
	}

	public void setDaoOrder(DaoOrder daoOrder) {
		this.daoOrder = daoOrder;
	}

	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
	}

	public void setDaoDistrict(DaoDistrict daoDistrict) {
		this.daoDistrict = daoDistrict;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	private String addressDetail;
	
	private DaoOrder daoOrder;
	
	private DaoDistrict daoDistrict;
	
	private boolean isValidate;
	
	private Integer orderID;
	
	private Boolean submitResult;
	
	private String resultInfo;
	
	public void validate(){
		isValidate = true;
		if(orderID == null || districtLevel1ID == null || districtLevel2ID == null || districtLevel3ID == null)
		{
			isValidate = false;
			return;
		}
	}
	
	public String execute() throws Exception{
		if(!isValidate){
			submitResult = false;
			resultInfo = "数据为空";
			return SUCCESS;
		}
		BeanOrder order = daoOrder.getOrderDetail(orderID);
		if(order == null){
			submitResult = false;
			resultInfo = "orderID错误";
			return SUCCESS;
		}
		//order地址处理
		OrderService.Address address = new OrderService.Address();
		address.districtDetail = this.addressDetail;
		address.level1District = new DistrictBean();
		address.level1District.setID(this.districtLevel1ID);
		BeanDistrict level1 = daoDistrict.getDistrictById(districtLevel1ID);
		if(level1 != null){
			address.level1District.setName(level1.getName());
			address.level1District.setID(level1.getId());
		}
		else {
			submitResult = false;
			resultInfo = "一级地址错误";
			return SUCCESS;
		}
		address.level2District = new DistrictBean();
		address.level2District.setID(this.districtLevel2ID);
		BeanDistrict level2 = daoDistrict.getDistrictById(districtLevel2ID);
		if(level2 != null)
			address.level2District.setName(level2.getName());
		else{
			submitResult = false;
			resultInfo = "二级地址错误";
			return SUCCESS;
		}
		address.level3District = new DistrictBean();
		address.level3District.setID(this.districtLevel3ID);
		BeanDistrict level3 = daoDistrict.getDistrictById(districtLevel3ID);
		if(level3 != null)
			address.level3District.setName(level3.getName());
		else{
			submitResult = false;
			resultInfo = "三级地址错误";
			return SUCCESS;
		}
		order.setAddrDetail(OrderService.serializerAddress(address));
		order.setMobilenum(getMobileNum());
		order.setPhonenum(getPhoneNum());
		if(getReceiverName() != null)
			order.setReceiverName(getReceiverName());
		else {
			submitResult = false;
			resultInfo = "收件人不能为空";
			return SUCCESS;
		}
		order.setZipcode(getZipcode());
		order.setDeliveryWay("0");
		order.setPayment("0");
		
		submitResult = daoOrder.updateOrder(order);
		
		return SUCCESS;
	}

	public Boolean getSubmitResult() {
		return submitResult;
	}

	public void setSubmitResult(Boolean submitResult) {
		this.submitResult = submitResult;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
}
