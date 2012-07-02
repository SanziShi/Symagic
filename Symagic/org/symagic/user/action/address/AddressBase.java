package org.symagic.user.action.address;

import org.symagic.common.action.catalog.CatalogBase;

public class AddressBase extends CatalogBase{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -654569210971834110L;


	private String recieverName;

	
	private String addressDetail;
	
	private Integer districtLevel1ID;
	
	public Integer getDistrictLevel1ID() {
		return districtLevel1ID;
	}

	public void setDistrictLevel1ID(Integer districtLevel1ID) {
		this.districtLevel1ID = districtLevel1ID;
	}

	public Integer getDistrictLevel2ID() {
		return districtLevel2ID;
	}

	public void setDistrictLevel2ID(Integer districtLevel2ID) {
		this.districtLevel2ID = districtLevel2ID;
	}

	public Integer getDistrictLevel3ID() {
		return districtLevel3ID;
	}

	public void setDistrictLevel3ID(Integer districtLevel3ID) {
		this.districtLevel3ID = districtLevel3ID;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	private Integer districtLevel2ID;
	
	private Integer districtLevel3ID;
	
	private String zipcode;
	
	private String phoneNum;
	
	private String mobileNum;


	public String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}

	public String getAddressDetail() {
		return addressDetail;
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
