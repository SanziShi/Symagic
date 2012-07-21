package org.symagic.common.service;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanAddress;
import org.symagic.common.db.bean.BeanDistrict;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoUserAddress;
import org.symagic.common.utilty.presentation.bean.AddressBean;
import org.symagic.common.utilty.presentation.bean.AddressDetailBean;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.user.utilty.UserSessionUtilty;

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
	
	private DaoDistrict daoDistrict;
	
	private DaoUserAddress daoUserAddress;
	
	public List<AddressDetailBean> getAddressDetail(String userName){
		List<AddressDetailBean> addresses = new ArrayList<AddressDetailBean>();
		List<BeanAddress> beanAddressList = daoUserAddress.listAddress(UserSessionUtilty.getUsername());
		if(beanAddressList == null){
			return addresses;//如果为空的话直接返回一个空的列表
		}
		for(int i = 0; i < beanAddressList.size(); i ++){
			BeanAddress beanAddress = beanAddressList.get(i);
			if(beanAddress == null)
				continue;
			AddressDetailBean address = new AddressDetailBean();
			OrderService.Address orderAddress = OrderService.deserializerAddress(beanAddress.getAddrdetail());
			address.setLevel1DistrictDefaultID(orderAddress.level1District.getID());
			address.setLevel2DistrictDefaultID(orderAddress.level2District.getID());
			if(orderAddress.level3District != null)
				address.setLevel3DistrictDefaultID(orderAddress.level3District.getID());
			else
				address.setLevel3DistrictDefaultID(0);
			List<BeanDistrict> districtLevel1List = daoDistrict.getDistrict(0);
			List<BeanDistrict> districtLevel2List = daoDistrict.getDistrict(orderAddress.level1District.getID());
			List<BeanDistrict> districtLevel3List = daoDistrict.getDistrict(orderAddress.level2District.getID());
			address.setLevel1Districts(new ArrayList<DistrictBean>());
			address.setLevel2Districts(new ArrayList<DistrictBean>());
			address.setLevel3Districts(new ArrayList<DistrictBean>());
			
			if(districtLevel1List != null){
				for(int j = 0; j < districtLevel1List.size(); j ++){
					DistrictBean db1 = new DistrictBean();
					db1.setID(districtLevel1List.get(j).getId());
					db1.setName(districtLevel1List.get(j).getName());
					address.getLevel1Districts().add(db1);
				}
			}
			
			if(districtLevel2List != null){
				for(int j = 0; j < districtLevel2List.size(); j ++){
					DistrictBean db2 = new DistrictBean();
					db2.setID(districtLevel2List.get(j).getId());
					db2.setName(districtLevel2List.get(j).getName());
					address.getLevel2Districts().add(db2);
				}
			}
			
			if(districtLevel3List != null){
				for(int j = 0; j < districtLevel3List.size(); j ++){
					DistrictBean db3 = new DistrictBean();
					db3.setID(districtLevel3List.get(j).getId());
					db3.setName(districtLevel3List.get(j).getName());
					address.getLevel3Districts().add(db3);
				}
			}
			
			address.setAddressDetail(orderAddress.districtDetail);
			address.setAddressSummary(orderAddress.level1District.getName()
					+ orderAddress.level2District.getName());
			if(orderAddress.level3District != null){
				String string = address.getAddressSummary();
				string += orderAddress.level3District.getName();
			}
			address.setReceiverName(beanAddress.getReceivername());
			address.setMobileNum(beanAddress.getMobilenum());
			address.setPhoneNum(beanAddress.getPhonenum());
			address.setZipcode(beanAddress.getZipcode());
			address.setID(beanAddress.getAddrid());
			addresses.add(address);
		}
		return addresses;
	}
	
	public List<DistrictBean> getDistricts(int upID){
		List<DistrictBean> districts = new ArrayList<DistrictBean>();
		List<BeanDistrict> beanDistricts = daoDistrict.getDistrict(upID);
		for(int i = 0; i < beanDistricts.size(); i ++){
			DistrictBean dBean = new DistrictBean();
			dBean.setID(beanDistricts.get(i).getId());
			dBean.setName(beanDistricts.get(i).getName());
			districts.add(dBean);
		}
		return districts;
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
}
