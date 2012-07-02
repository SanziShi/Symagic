package org.symagic.user.action.order;

import java.util.List;

import org.symagic.common.service.AddressService;
import org.symagic.common.utilty.presentation.bean.AddressDetailBean;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class OrderAddressDetail extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6014180875947468269L;

	private AddressDetailBean addressList;
	
	private AddressService addressService;
	
	private Integer addressID;
	
	public AddressDetailBean getAddressList() {
		return addressList;
	}

	public void setAddressList(AddressDetailBean addressList) {
		this.addressList = addressList;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public String execute() throws Exception{
		List<AddressDetailBean> addresses = addressService.getAddressDetail(UserSessionUtilty.getUsername());
		if (addresses != null) {
			for (int i = 0; i < addresses.size(); i++) {
				if (addresses.get(i).getID() == addressID) {
					addressList = addresses.get(i);
					break;
				}
			}
		}
		return SUCCESS;
	}

	public Integer getAddressID() {
		return addressID;
	}

	public void setAddressID(Integer addressID) {
		this.addressID = addressID;
	}
}
