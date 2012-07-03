package org.symagic.user.action.address;

import org.symagic.common.db.bean.BeanAddress;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoUserAddress;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class AddressSubmitAction extends AddressBase {

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

	public Boolean getSubmitResult() {
		return submitResult;
	}

	public void setSubmitResult(Boolean submitResult) {
		this.submitResult = submitResult;
	}

	public String execute() throws Exception {

		BeanAddress address = new BeanAddress();
		if (!isValidate) {
			submitResult = isValidate;
			return SUCCESS;
		}
		OrderService.Address addressDetail = new OrderService.Address();
		addressDetail.districtDetail = getAddressDetail();
		addressDetail.level1District = new DistrictBean();
		addressDetail.level1District.setID(getDistrictLevel1ID());
		addressDetail.level1District.setName(daoDistrict.getDistrictById(
				getDistrictLevel1ID()).getName());
		addressDetail.level2District = new DistrictBean();
		addressDetail.level2District.setID(getDistrictLevel2ID());
		addressDetail.level2District.setName(daoDistrict.getDistrictById(
				getDistrictLevel2ID()).getName());
		addressDetail.level3District = new DistrictBean();
		addressDetail.level3District.setID(getDistrictLevel3ID());
		addressDetail.level3District.setName(daoDistrict.getDistrictById(
				getDistrictLevel3ID()).getName());

		String strAddressDetail = OrderService.serializerAddress(addressDetail);
		address.setAddrdetail(strAddressDetail);
		address.setMobilenum(getMobileNum());
		address.setPhonenum(getPhoneNum());
		address.setReceivername(getReceiverName());
		address.setUsername(UserSessionUtilty.getUsername());
		address.setZipcode(getZipcode());

		submitResult = daoAddress.addAddress(address);
		if (submitResult) {
			resultInfo = "修改成功";
		} else {
			resultInfo = "修改失败";
		}
		return SUCCESS;
	}

	public void validate() {
		isValidate = true;
		clearErrorsAndMessages();
		if(getDistrictLevel1ID() == null || getDistrictLevel1ID() <= 0 ||
				getDistrictLevel2ID() == null || getDistrictLevel2ID() <= 0 ||
				getDistrictLevel3ID() == null || getDistrictLevel3ID() <= 0){
			isValidate = false;
			submitResult = false;
			resultInfo = "地址为空";
			return;
		}
		
		if(getMobileNum() == null || getMobileNum().isEmpty() ||
				!getMobileNum().matches("[1]{1}[3,5,8,6]{1}[0-9]{9}")){
			if(getPhoneNum() == null || getPhoneNum().isEmpty()
					|| !getPhoneNum().matches("^[0]\\d{2,3}\\d{7,8}")){
				submitResult = false;
				resultInfo = "请输入正确的手机号或电话号码";
				isValidate = false;
				return;
			}
		}
		
		if (!getZipcode().matches("^[1-9]\\d{5}")) {
			isValidate = false;
			submitResult = false;
			resultInfo = "邮编错误";
			return;
		}
		
		if(getAddressDetail() == null || getAddressDetail().isEmpty()){
			isValidate = false;
			submitResult = false;
			resultInfo = "填写地址详情";
			return;
		}
		
		if(getReceiverName() == null || getReceiverName().isEmpty()){
			isValidate = false;
			submitResult = false;
			resultInfo = "请填写收件人";
			return;
		}

	}
}
