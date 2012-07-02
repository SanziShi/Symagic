package org.symagic.common.utilty.presentation.bean;

import java.util.List;

public class AddressDetailBean {
	
	private List<DistrictBean> level1Districts;
	
	private List<DistrictBean> level2Districts;
	
	private List<DistrictBean> level3Districts;
	
	private Integer level1DistrictDefaultID;
	
	private Integer level2DistrictDefaultID;
	
	private Integer level3DistrictDefaultID;
	
	private String AddressDetail;
	
	private String AddressSummary;
	
	private String phoneNum;
	
	private String MobileNum;
	
	private String zipcode;
	
	private String receiverName;
	
	private Integer ID;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

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

	
	public Integer getLevel1DistrictDefaultID() {
		return level1DistrictDefaultID;
	}

	public void setLevel1DistrictDefaultID(Integer level1DistrictDefaultID) {
		this.level1DistrictDefaultID = level1DistrictDefaultID;
	}

	public Integer getLevel2DistrictDefaultID() {
		return level2DistrictDefaultID;
	}

	public void setLevel2DistrictDefaultID(Integer level2DistrictDefaultID) {
		this.level2DistrictDefaultID = level2DistrictDefaultID;
	}

	public Integer getLevel3DistrictDefaultID() {
		return level3DistrictDefaultID;
	}

	public void setLevel3DistrictDefaultID(Integer level3DistrictDefaultID) {
		this.level3DistrictDefaultID = level3DistrictDefaultID;
	}

	public String getAddressDetail() {
		return AddressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		AddressDetail = addressDetail;
	}

	public String getAddressSummary() {
		return AddressSummary;
	}

	public void setAddressSummary(String addressSummary) {
		AddressSummary = addressSummary;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getMobileNum() {
		return MobileNum;
	}

	public void setMobileNum(String mobileNum) {
		MobileNum = mobileNum;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
}
