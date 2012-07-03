package org.symagic.admin.action.order;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.action.order.OrderDetailAction;
import org.symagic.common.db.bean.BeanDistrict;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.utilty.presentation.bean.DistrictBean;

public class AdminOrderEditEnterAction extends OrderDetailAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -47361816799225643L;

	private DaoDistrict daoDistrict;
	private List<DistrictBean> level1District;
	private List<DistrictBean> level2District;
	private List<DistrictBean> level3District;
	private Integer selectedLevel1DistrictID;
	private Integer selectedLevel2DistrictID;
	private Integer selectedLevel3DistrictID;
	private String addressDetail;

	@Override
	public String execute() throws Exception {

		String result = super.execute();

		if (order == null || order.getOrderState().equals("2")
				|| order.getOrderState().equals("3"))
			return ERROR;

		// level1 district
		List<BeanDistrict> list = daoDistrict.getDistrict(null);
		level1District = convertBeanDistrictList(list);

		list = daoDistrict.getDistrict(this.address.level1District.getID());
		if (list != null)
			level2District = convertBeanDistrictList(list);
		else
			level2District = null;
		selectedLevel1DistrictID = address.level1District.getID();

		if (this.address.level2District != null) {
			list = daoDistrict.getDistrict(this.address.level2District.getID());
			if (list != null)
				level3District = convertBeanDistrictList(list);
			else
				level3District = null;
			selectedLevel2DistrictID = address.level2District.getID();
		}

		if (this.address.level3District != null)
			selectedLevel3DistrictID = address.level3District.getID();
		
		addressDetail = this.address.districtDetail;

		return result;

	}

	private List<DistrictBean> convertBeanDistrictList(List<BeanDistrict> list) {
		List<DistrictBean> districts = new ArrayList<DistrictBean>();
		for (BeanDistrict beanDistrict : list) {
			DistrictBean bean = new DistrictBean();
			bean.setID(beanDistrict.getId());
			bean.setName(beanDistrict.getName());
			districts.add(bean);
		}
		return districts;
	}

	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
	}

	public void setDaoDistrict(DaoDistrict daoDistrict) {
		this.daoDistrict = daoDistrict;
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

	public Integer getSelectedLevel1DistrictID() {
		return selectedLevel1DistrictID;
	}

	public void setSelectedLevel1DistrictID(Integer selectedLevel1DistrictID) {
		this.selectedLevel1DistrictID = selectedLevel1DistrictID;
	}

	public Integer getSelectedLevel2DistrictID() {
		return selectedLevel2DistrictID;
	}

	public void setSelectedLevel2DistrictID(Integer selectedLevel2DistrictID) {
		this.selectedLevel2DistrictID = selectedLevel2DistrictID;
	}

	public Integer getSelectedLevel3DistrictID() {
		return selectedLevel3DistrictID;
	}

	public void setSelectedLevel3DistrictID(Integer selectedLevel3DistrictID) {
		this.selectedLevel3DistrictID = selectedLevel3DistrictID;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

}
