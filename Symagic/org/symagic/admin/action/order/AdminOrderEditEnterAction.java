package org.symagic.admin.action.order;

import java.util.List;

import org.apache.struts2.views.velocity.components.BeanDirective;
import org.symagic.common.action.order.OrderDetailAction;
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

	@Override
	public String execute() throws Exception {
		
		String result = super.execute();
		
		if( order == null || order.getOrderState().equals("2") || order.getOrderState().equals("3") )
			return ERROR;
		
		  daoDistrict.getDistrict(null);
		
		return result;
		
	}
	

}
