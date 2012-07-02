package org.symagic.user.action.address;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.symagic.common.db.bean.BeanAddress;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoUserAddress;
import org.symagic.common.service.AddressService;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.user.utilty.UserSessionUtilty;

import com.sun.imageio.plugins.common.SubImageInputStream;

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

	public Boolean getSubmitResult() {
		return submitResult;
	}

	public void setSubmitResult(Boolean submitResult) {
		this.submitResult = submitResult;
	}

	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
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
		if(!submitResult)
			return super.execute();
		OrderService.Address addressDetail = new OrderService.Address();
		addressDetail.districtDetail = getAddressDetail();
		addressDetail.level1District = new DistrictBean();
		addressDetail.level1District.setID(getDistrictLevel1ID());
		addressDetail.level1District.setName(daoDistrict.getDistrictById(getDistrictLevel1ID()).getName());
		addressDetail.level2District = new DistrictBean();
		addressDetail.level3District = new DistrictBean();
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
		else {
			resultInfo = "修改成功";
		}
		return super.execute();
	}
	
	public void validate(){
		submitResult = true;
		if(getDistrictLevel1ID() == null || getDistrictLevel2ID() == null
				|| getDistrictLevel3ID() == null || getAddressDetail() == null
				|| getReceiverName() == null 
				|| !(getMobileNum() != null || getPhoneNum() != null)){
			submitResult = false;
			resultInfo = "数据错误";
		}
		if(!getMobileNum().matches("[1]{1}[3,5,8,6]{1}[0-9]{9}")){
			submitResult = false;
			resultInfo = "手机号码错误";
			return;
		}
		if(!getPhoneNum().matches("^[0]\\d{2,3}\\d{7,8}")){
			submitResult = false;
			resultInfo = "电话号码错误";
		}
		if(!getZipcode().matches("^[1-9]\\d{5}")){
			submitResult = false;
			resultInfo = "邮编错误";
		}
	}
	
	private  boolean startCheck(String reg,String string)  
    {  
		return Pattern.compile(reg).matcher(string).find();
    }
}
