package org.symagic.admin.action.order;

import com.opensymphony.xwork2.ActionSupport;

public class AdminOrderEditSubmitAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3421413808732153648L;
	
	private Integer orderID;//:订单号；
	private String receiver;//:收货人；
	private Integer level1ID;//:一级地区ID；
	private Integer level2ID;//:二级地区名称;
	private Integer level3ID;//:三级地区名称；
	private String addressDetail;//:地区详情；
	private String zipcode;//:邮政编码；
	private String phoneNumber;//：电话号码；
	private String mobileNumber;//:手机号码;
	private String items;//:json_array(id:商品ID;value:商品数量）)
	
	
	
	@Override
	public String execute() throws Exception {
		
		
		
		return super.execute();
	}
	
	

}
