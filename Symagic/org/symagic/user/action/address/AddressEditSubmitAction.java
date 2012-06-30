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

	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
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
		super.execute();
		
		BeanAddress address = new BeanAddress();
		
		OrderService.Address addressDetail = new OrderService.Address();
		addressDetail.districtDetail = getAddressDetail();
		addressDetail.level1District = new DistrictBean();
		addressDetail.level1District.setID(getDistrictLevel1());
		addressDetail.level1District.setName(daoDistrict.getDistrictById(getDistrictLevel1()).getName());
		addressDetail.level2District.setID(getDistrictLevel2());
		addressDetail.level2District.setName(daoDistrict.getDistrictById(getDistrictLevel2()).getName());
		addressDetail.level3District.setID(getDistrictLevel3());
		addressDetail.level3District.setName(daoDistrict.getDistrictById(getDistrictLevel3()).getName());
		
		String strAddressDetail = OrderService.serializerAddress(addressDetail);
		address.setAddrdetail(strAddressDetail);
		address.setMobilenum(getMobileNum());
		address.setPhonenum(getPhoneNum());
		address.setReceivername(getRecieverName());
		address.setUsername(UserSessionUtilty.getUsername());
		address.setZipcode(getZipcode());
		address.setAddrid(addressID);
		daoUserAddress.modifyAddress(address);
		return SUCCESS;
	}
}
