package org.symagic.common.utilty.presentation.bean;

import java.util.List;

public class AddressDetailBean {
	
	private List<DistrictBean> level1Districts;
	
	private List<DistrictBean> level2Districts;
	
	private List<DistrictBean> level3Districts;
	
	private Integer defaultLevel1District;
	
	private Integer defaultLevel2District;
	
	private Integer defaultLevel3District;
	
	private String AddressDetail;
	
	private String AddressSummary;
	
	private String phoneNum;
	
	private String MobileNum;
	
	private String zipcode;
	
	private String receiverName;

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

	public Integer getDefaultLevel1District() {
		return defaultLevel1District;
	}

	public void setDefaultLevel1District(Integer defaultLevel1District) {
		this.defaultLevel1District = defaultLevel1District;
	}

	public Integer getDefaultLevel2District() {
		return defaultLevel2District;
	}

	public void setDefaultLevel2District(Integer defaultLevel2District) {
		this.defaultLevel2District = defaultLevel2District;
	}

	public Integer getDefaultLevel3District() {
		return defaultLevel3District;
	}

	public void setDefaultLevel3District(Integer defaultLevel3District) {
		this.defaultLevel3District = defaultLevel3District;
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
