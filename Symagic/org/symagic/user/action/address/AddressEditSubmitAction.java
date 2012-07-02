package org.symagic.user.action.address;

import org.symagic.common.db.bean.BeanAddress;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoUserAddress;
import org.symagic.common.service.AddressService;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class AddressEditSubmitAction extends AddressBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8014737160381625059L;
	
	private DaoDistrict daoDistrict;
	
	private DaoUserAddress daoUserAddress;
	
	private AddressService addressService;
	
	private Integer addressID;
	
	private Boolean submitResult;
	
	private String resultInfo;

	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
	}

	public Boolean getEditResult() {
		return submitResult;
	}

	public void setEditResult(Boolean editResult) {
		this.submitResult = editResult;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

	public void setDaoDistrict(DaoDistrict daoDistrict) {
		this.daoDistrict = daoDistrict;
	}

	public DaoUserAddress getDaoUserAddress() {
		return daoUserAddress;
	}

	public void setDaoUserAddress(DaoUserAddress daoUserAddress) {
		this.daoUserAddress = daoUserAddress;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	
	public Integer getAddressID() {
		return addressID;
	}

	public void setAddressID(Integer addressID) {
		this.addressID = addressID;
	}

	public String execute() throws Exception{
		
		BeanAddress address = new BeanAddress();
		if(getDistrictLevel1ID() == null || getDistrictLevel2ID() == null
				|| getDistrictLevel3ID() == null || getAddressDetail() == null
				|| getReceiverName() == null 
				|| !(getMobileNum() != null || getPhoneNum() != null)){
			submitResult = false;
			resultInfo = "数据错误";
			return super.execute();
		}
		OrderService.Address addressDetail = new OrderService.Address();
		addressDetail.districtDetail = getAddressDetail();
		addressDetail.level1District = new DistrictBean();
		addressDetail.level1District.setID(getDistrictLevel1ID());
		addressDetail.level1District.setName(daoDistrict.getDistrictById(getDistrictLevel1ID()).getName());
		addressDetail.level2District.setID(getDistrictLevel2ID());
		addressDetail.level2District.setName(daoDistrict.getDistrictById(getDistrictLevel2ID()).getName());
		addressDetail.level3District.setID(getDistrictLevel3ID());
		addressDetail.level3District.setName(daoDistrict.getDistrictById(getDistrictLevel3ID()).getName());
		
		String strAddressDetail = OrderService.serializerAddress(addressDetail);
		address.setAddrdetail(strAddressDetail);
		address.setMobilenum(getMobileNum());
		address.setPhonenum(getPhoneNum());
		address.setReceivername(getReceiverName());
		address.setUsername(UserSessionUtilty.getUsername());
		address.setZipcode(getZipcode());
		address.setAddrid(addressID);
		submitResult = daoUserAddress.modifyAddress(address);
		if(submitResult == false)
			resultInfo = "服务器错误， 请重试";
		return super.execute();
	}
}
