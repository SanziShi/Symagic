package org.symagic.user.action.address;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanAddress;
import org.symagic.common.db.bean.BeanDistrict;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoUserAddress;
import org.symagic.common.service.AddressService;
import org.symagic.common.service.OrderService;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class AddressEditEnter extends CatalogBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2678887360540938691L;

	private String addressDetail;
	
	private String  mobileNum;
	
	private String phoneNum;
	
	private String zipcode;
	
	private String addressID;
	
	private String receiverName;
	
	private List<DistrictBean> level1District;
	
	private List<DistrictBean> level2District;
	
	private List<DistrictBean> level3District;
	
	private DaoUserAddress daoAddress;
	
	private DaoDistrict daoDistrict;
	
	private AddressService addressService;
	
	public DaoUserAddress getDaoAddress() {
		return daoAddress;
	}

	public void setDaoAddress(DaoUserAddress daoAddress) {
		this.daoAddress = daoAddress;
	}

	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
	}

	public void setDaoDistrict(DaoDistrict daoDistrict) {
		this.daoDistrict = daoDistrict;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddressID() {
		return addressID;
	}

	public void setAddressID(String addressID) {
		this.addressID = addressID;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public List<DistrictBean> getLevel1District() {
		return level1District;
	}

	public void setLevel1District(List<DistrictBean> level1District) {
		this.level1District = level1District;
	}

	public List<DistrictBean> getLevel2District() {
		return level2District;
	}

	public void setLevel2District(List<DistrictBean> level2District) {
		this.level2District = level2District;
	}

	public List<DistrictBean> getLevel3District() {
		return level3District;
	}

	public void setLevel3District(List<DistrictBean> level3District) {
		this.level3District = level3District;
		
	}
	
	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	
	public String execute() throws Exception{
		
		BeanAddress addressDetail = daoAddress.getAddressDetail(UserSessionUtilty.getUsername(), Integer.parseInt(addressID));
		OrderService.Address address = OrderService.deserializerAddress(addressDetail.getAddrdetail());
		
		this.addressDetail = address.districtDetail;
		level1District = new ArrayList<DistrictBean>();
		level2District = new ArrayList<DistrictBean>();
		level3District = new ArrayList<DistrictBean>();
		//三级地区列表
		List<BeanDistrict> beanDaoDistricts1 = daoDistrict.getDistrict(0);
		DistrictBean provinceBean = addressService.convertBeanDistrictToDistrictBean(daoDistrict.getDistrictById(address.level1District.getID()));
		level1District.add(provinceBean);
		for(int i = 0; i < beanDaoDistricts1.size(); i ++){
			if(beanDaoDistricts1.get(i).getId() != provinceBean.getID())
				level1District.add(addressService.convertBeanDistrictToDistrictBean(beanDaoDistricts1.get(i)));
		}
		
		List<BeanDistrict> beanDaoDistricts2 = daoDistrict.getDistrict(address.level1District.getID());
		DistrictBean cityBean =addressService.convertBeanDistrictToDistrictBean(daoDistrict.getDistrictById(address.level2District.getID()));
		level2District.add(cityBean);
		for(int i = 0; i < beanDaoDistricts2.size(); i ++){
			if(beanDaoDistricts2.get(i).getId() != cityBean.getID())
				level2District.add(addressService.convertBeanDistrictToDistrictBean(beanDaoDistricts2.get(i)));
		}
		
		List<BeanDistrict> beanDaoDistricts3 = daoDistrict.getDistrict(address.level2District.getID());
		DistrictBean areaBean = addressService.convertBeanDistrictToDistrictBean(daoDistrict.getDistrictById(address.level3District.getID()));
		for(int i = 0; i < beanDaoDistricts3.size(); i ++){
			if(beanDaoDistricts3.get(i).getId() != areaBean.getID())
				level3District.add(addressService.convertBeanDistrictToDistrictBean(beanDaoDistricts3.get(i)));
		}
		
		receiverName = addressDetail.getReceivername();
		this.setAddressID(address.districtDetail);
		this.setAddressID(addressDetail.getAddrid().toString());
		this.setMobileNum(addressDetail.getMobilenum());
		this.setPhoneNum(addressDetail.getPhonenum());
		this.setZipcode(addressDetail.getZipcode());
		return SUCCESS;
	}

}
