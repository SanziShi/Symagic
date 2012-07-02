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
import org.symagic.common.utilty.presentation.bean.AddressDetailBean;
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
	private List<AddressDetailBean> addressList;
	
	private AddressService addressService;
	
	private List<DistrictBean> level1Districts;

	public List<AddressDetailBean> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressDetailBean> addressList) {
		this.addressList = addressList;
	}
	
	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public String execute() throws Exception{
		addressList = addressService.getAddressDetail(UserSessionUtilty.getUsername());
		setLevel1Districts(addressService.getDistricts(0));
		return super.execute();
	}

	public List<DistrictBean> getLevel1Districts() {
		return level1Districts;
	}

	public void setLevel1Districts(List<DistrictBean> level1Districts) {
		this.level1Districts = level1Districts;
	}
}
