package org.symagic.user.action.address;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanAddress;
import org.symagic.common.db.bean.BeanDistrict;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoUserAddress;
import org.symagic.common.service.AddressService;
import org.symagic.common.utilty.presentation.bean.AddressBean;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class AddressEnterAction extends CatalogBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3896009125464396233L;

	/**
	 * 地址列表
	 */
	private List<AddressBean> addressList;
	
	/*
	 * 一级地区列表
	 */
	private List<DistrictBean> level1Districts;
	
	private DaoDistrict daoDistrict;
	
	private DaoUserAddress daoUserAddress;

	public List<AddressBean> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressBean> addressList) {
		this.addressList = addressList;
	}

	public List<DistrictBean> getLevel1Districts() {
		return level1Districts;
	}

	public void setLevel1Districts(List<DistrictBean> level1Districts) {
		this.level1Districts = level1Districts;
	}
	
	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
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

	public String execute() throws Exception{
		super.execute();
		
		addressList = new ArrayList<AddressBean>();
		level1Districts = new ArrayList<DistrictBean>();
		List<BeanDistrict> districts = daoDistrict.getDistrict(0);
		for(int i = 0; i < districts.size() ; i ++){
			level1Districts.add(AddressService.convertBeanDistrictToDistrictBean(districts.get(i)));
		}
		
		List<BeanAddress> addresses = daoUserAddress.listAddress(UserSessionUtilty.getUsername());
		if(addresses != null){
			for(int i = 0; i < addresses.size(); i ++){
				if(addresses.get(i) != null)
					addressList.add(AddressService.convertBeanAddressToAddressBean(addresses.get(i)));
			}
		}
		return SUCCESS;
	}
}
