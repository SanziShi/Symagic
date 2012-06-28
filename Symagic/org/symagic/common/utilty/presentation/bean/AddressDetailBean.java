package org.symagic.common.utilty.presentation.bean;

import java.util.List;

public class AddressDetailBean {
	
	private List<DistrictBean> level1Districts;
	
	private List<DistrictBean> level2Districts;
	
	private List<DistrictBean> level3Districts;
	
	private String addressDetail;
	
	private String receiverName;
	
	private String zipcode;
	
	private String phoneNum;
	
	private String mobileNum;
	
	private Integer addressID;

	public List<DistrictBean> getLevel1Districts() {
		return level1Districts;
	}

	public void setLevel1Districts(List<DistrictBean> level1Districts) {
		this.level1Districts = level1Districts;
	}

	public List<DistrictBean> getLevel2Districts() {
		return level2Districts;
	}

	public void setLevel2Districts(List<DistrictBean> level2Districts) {
		this.level2Districts = level2Districts;
	}

	public List<DistrictBean> getLevel3Districts() {
		return level3Districts;
	}

	public void setLevel3Districts(List<DistrictBean> level3Districts) {
		this.level3Districts = level3Districts;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
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

	public Integer getAddressID() {
		return addressID;
	}

	public void setAddressID(Integer addressID) {
		this.addressID = addressID;
	}
}
