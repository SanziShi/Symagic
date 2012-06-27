package org.symagic.user.action.address;

import org.symagic.common.action.catalog.CatalogBase;

public class AddressBase extends CatalogBase{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -654569210971834110L;

	private String name;
	
	private String addressDetail;
	
	private Integer districtLevel1;
	
	private Integer districtLevel2;
	
	private Integer districtLevel3;
	
	private String zipcode;
	
	private String phoneNum;
	
	private String mobileNum;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Integer getDistrictLevel1() {
		return districtLevel1;
	}

	public void setDistrictLevel1(Integer districtLevel1) {
		this.districtLevel1 = districtLevel1;
	}

	public Integer getDistrictLevel2() {
		return districtLevel2;
	}

	public void setDistrictLevel2(Integer districtLevel2) {
		this.districtLevel2 = districtLevel2;
	}

	public Integer getDistrictLevel3() {
		return districtLevel3;
	}

	public void setDistrictLevel3(Integer districtLevel3) {
		this.districtLevel3 = districtLevel3;
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
}
