package org.symagic.user.action.address;

import org.symagic.common.db.bean.BeanAddress;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoUserAddress;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class AddressSubmitAction extends AddressBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2504916134181840602L;

	private DaoUserAddress daoAddress;
	
	private DaoDistrict daoDistrict;
	
	private Boolean submitResult;

	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
	}

	public void setDaoDistrict(DaoDistrict daoDistrict) {
		this.daoDistrict = daoDistrict;
	}

	public DaoUserAddress getDaoAddress() {
		return daoAddress;
	}

	public void setDaoAddress(DaoUserAddress daoAddress) {
		this.daoAddress = daoAddress;
	}

	public String execute() throws Exception{
		
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
		
		daoAddress.addAddress(address);
		return SUCCESS;
	}

	public Boolean getSubmitResult() {
		return submitResult;
	}

	public void setSubmitResult(Boolean submitResult) {
		this.submitResult = submitResult;
	}
}
