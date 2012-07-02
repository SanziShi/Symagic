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
	
	private boolean isValidate;
	
	private String resultInfo;

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

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
		if(!isValidate){
			submitResult = isValidate;
			return SUCCESS;
		}
		OrderService.Address addressDetail = new OrderService.Address();
		addressDetail.districtDetail = getAddressDetail();
		addressDetail.level1District = new DistrictBean();
		addressDetail.level1District.setID(getDistrictLevel1ID());
		addressDetail.level1District.setName(daoDistrict.getDistrictById(getDistrictLevel1ID()).getName());
		addressDetail.level2District = new DistrictBean();
		addressDetail.level2District.setID(getDistrictLevel2ID());
		addressDetail.level2District.setName(daoDistrict.getDistrictById(getDistrictLevel2ID()).getName());
		addressDetail.level3District = new DistrictBean();
		addressDetail.level3District.setID(getDistrictLevel3ID());
		addressDetail.level3District.setName(daoDistrict.getDistrictById(getDistrictLevel3ID()).getName());
		
		String strAddressDetail = OrderService.serializerAddress(addressDetail);
		address.setAddrdetail(strAddressDetail);
		address.setMobilenum(getMobileNum());
		address.setPhonenum(getPhoneNum());
		address.setReceivername(getReceiverName());
		address.setUsername(UserSessionUtilty.getUsername());
		address.setZipcode(getZipcode());
		
		submitResult = daoAddress.addAddress(address);
		return SUCCESS;
	}
	
	public void validate(){
		isValidate = true;
		if( getDistrictLevel1ID() == null || getDistrictLevel2ID() == null || getDistrictLevel3ID() == null ||
				getAddressDetail() == null || getReceiverName() == null 
				|| getMobileNum() == null || getPhoneNum() == null){
			isValidate = false;
			resultInfo = "用户名为空";
		}
		
	}

	public Boolean getSubmitResult() {
		return submitResult;
	}

	public void setSubmitResult(Boolean submitResult) {
		this.submitResult = submitResult;
	}
}
