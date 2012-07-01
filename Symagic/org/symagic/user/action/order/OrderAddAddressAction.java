package org.symagic.user.action.order;

import org.symagic.common.db.bean.BeanAddress;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoUserAddress;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class OrderAddAddressAction extends ActionSupport{
	
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

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

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public DaoUserAddress getDaoUserAddress() {
		return daoUserAddress;
	}

	public void setDaoUserAddress(DaoUserAddress daoUserAddress) {
		this.daoUserAddress = daoUserAddress;
	}

	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
	}

	public void setDaoDistrict(DaoDistrict daoDistrict) {
		this.daoDistrict = daoDistrict;
	}

	public Boolean getAddResult() {
		return addResult;
	}

	public void setAddResult(Boolean addResult) {
		this.addResult = addResult;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2501957677151982479L;
	
	private String receiverName;
	
	private Integer districtLevel1ID;
	
	private Integer districtLevel2ID;
	
	private Integer districtLevel3ID;
	
	private String addressDetail;
	
	private String zipcode;
	
	private String phoneNum;
	
	private String mobileNum;
	
	private OrderService orderService;
	
	private DaoUserAddress daoUserAddress;
	
	private DaoDistrict daoDistrict;
	
	private Boolean addResult;
	
	public String execute() throws Exception{
		OrderService.Address AddressSer = new OrderService.Address();
		AddressSer.level1District = new DistrictBean();
		AddressSer.level1District.setID(districtLevel1ID);
		AddressSer.level1District.setName(daoDistrict.getDistrictById(districtLevel1ID).getName());
		AddressSer.level2District.setID(districtLevel2ID);
		AddressSer.level2District.setName(daoDistrict.getDistrictById(districtLevel2ID).getName());
		AddressSer.level3District.setID(districtLevel3ID);
		AddressSer.level3District.setName(daoDistrict.getDistrictById(districtLevel3ID).getName());
		AddressSer.districtDetail = addressDetail; 
		String districtDetail = OrderService.serializerAddress(AddressSer);
		BeanAddress address = new BeanAddress();
		address.setAddrdetail(districtDetail);
		address.setMobilenum(mobileNum);
		address.setPhonenum(phoneNum);
		address.setReceivername(receiverName);
		address.setUsername(UserSessionUtilty.getUsername());
		address.setZipcode(zipcode);
		addResult = daoUserAddress.addAddress(address);
		return SUCCESS;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
