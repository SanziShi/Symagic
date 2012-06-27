package org.symagic.user.action.address;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanDistrict;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.service.AddressService;
import org.symagic.common.utilty.presentation.bean.DistrictBean;

import com.opensymphony.xwork2.ActionSupport;

public class GetNextLevelAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1435464181583164L;

	private Integer ID;
	
	private List<DistrictBean> nextLevelArray;
	
	private DaoDistrict daoDistrict;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public List<DistrictBean> getNextLevelArray() {
		return nextLevelArray;
	}

	public void setNextLevelArray(List<DistrictBean> nextLevelArray) {
		this.nextLevelArray = nextLevelArray;
	}
	
	public String execute() throws Exception{
		List<BeanDistrict> addresses = daoDistrict.getDistrict(ID);
		nextLevelArray = new ArrayList<DistrictBean>();
		if(addresses ==  null)
			return ERROR;
		
		for(int i = 0; i < addresses.size(); i ++){
			nextLevelArray.add(AddressService.convertBeanDistrictToDistrictBean(addresses.get(i)));
		}
		return SUCCESS;
	}
}
