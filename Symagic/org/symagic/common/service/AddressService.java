package org.symagic.common.service;

import org.symagic.common.db.bean.BeanAddress;
import org.symagic.common.db.bean.BeanDistrict;
import org.symagic.common.utilty.presentation.bean.AddressBean;
import org.symagic.common.utilty.presentation.bean.DistrictBean;

public class AddressService {
	public static DistrictBean convertBeanDistrictToDistrictBean(BeanDistrict beanDistrict){
		DistrictBean districtBean = new DistrictBean();
		districtBean.setID(beanDistrict.getId());
		districtBean.setName(beanDistrict.getName());
		return districtBean;
	}
	
	public static AddressBean convertBeanAddressToAddressBean(BeanAddress beanAddress){
		AddressBean addressBean = new AddressBean();
		OrderService.Address address = OrderService.deserializerAddress(beanAddress.getAddrdetail());
		addressBean.setAddress(address.level1District.getName() + address.level2District.getName() +
				address.level3District.getName() + address.districtDetail);
		addressBean.setID(beanAddress.getAddrid());
		return addressBean;
	}
}
