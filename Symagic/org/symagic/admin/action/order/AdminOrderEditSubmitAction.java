package org.symagic.admin.action.order;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.service.OrderService;
import org.symagic.common.service.OrderService.Address;

import com.opensymphony.xwork2.ActionSupport;

public class AdminOrderEditSubmitAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3421413808732153648L;

	private Integer orderID;// :订单号；
	private String receiver;// :收货人；
	private Integer level1ID;// :一级地区ID；
	private Integer level2ID;// :二级地区名称;
	private Integer level3ID;// :三级地区名称；
	private String addressDetail;// :地区详情；
	private String zipcode;// :邮政编码；
	private String phoneNumber;// ：电话号码；
	private String mobileNumber;// :手机号码;
	private String items;// :json_array(id:商品ID;value:商品数量）)

	private boolean validateResult;
	
	private DaoOrder daoOrder;
	private DaoDistrict daoDistrict;
	
	@Override
	public String execute() throws Exception {
		
		if( !validateResult )
			return ERROR;
		
		BeanOrder order = daoOrder.getOrderDetail(orderID);
		if( order == null ) return ERROR;
		if( order.getOrderState().equals("2") || order.getOrderState().equals("3") ) return ERROR;
		OrderService.Address address = new Address();
		address.districtDetail = addressDetail;
		address.level1District.setId(level1ID);
		//address.level1District.setName();
		//order.setAddrDetail(OrderService.serializerAddress(address))

		return super.execute();
	}

	@Override
	public void validate() {

		if (orderID == null || receiver == null || level1ID == null
				|| addressDetail == null || zipcode == null
				|| (phoneNumber == null && mobileNumber == null)
				|| items == null)
			validateResult = false;

		super.validate();
	}

}
