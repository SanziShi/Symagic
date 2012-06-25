package org.symagic.common.service;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.common.utilty.presentation.bean.OrderBean;
import org.symagic.common.utilty.presentation.bean.TimeBean;

public class OrderService {

	public class Address {
		public DistrictBean level1District;
		public DistrictBean level2District;
		public DistrictBean level3District;
		public String districtDetail;
	}

	private DaoOrder daoOrder;

	/**
	 * 列出指定用户的order列表，当userName为空则列出所有。
	 * 
	 * @param startTime
	 * @param endTime
	 * @param orderState
	 * @param page
	 * @param userName
	 */
	public void orderList(TimeBean startTime, TimeBean endTime,
			Integer orderState, Integer page, String userName) {

		// OrderRequire require = new OrderRequire();

	}

	public BeanOrder orderDetail(Integer orderID) {

		BeanOrder bean = daoOrder.getOrderDetail(orderID);

		return bean;
	}

	public OrderBean convertBeanOrderToOrderBean(BeanOrder bean) {
		OrderBean result = new OrderBean();

		result.setOrderId(Integer.toString(bean.getOrderId()));
		switch (Integer.parseInt(bean.getOrderState())) {
		case 0:
			result.setOrderStatus("已下单");
			break;
		case 1:
			result.setOrderStatus("已审核");
			break;
		case 2:
			result.setOrderStatus("交易成功");
			break;
		case 3:
			result.setOrderStatus("交易失败");
			break;
		}
		result.setOrderTime(bean.getOrderDate());
		result.setReceiverName(bean.getReceiverName());
		// result.setScore();
		// result.setTotalPrice(bean.get)
		return result;
	}
	
	public Address deserializerAddress( String receiverAddress ){
			
		JSON json = JSONSerializer.toJSON(receiverAddress);
		if( !(json instanceof JSONObject ) )
			return null;
		JSONObject addressObject = (JSONObject) json;
		Address result = new Address();
		
		JSONObject level1 = addressObject.getJSONObject("level1");
		JSONObject level2 = addressObject.getJSONObject("level2");
		JSONObject level3 = addressObject.getJSONObject("level3");
		String detail = addressObject.getString("detail");
		
		if( detail != null ){
			result.districtDetail = detail;
		}
		else{
			return null;
		}
		
		if( level1 != null ){
			result.level1District = new DistrictBean();
			result.level1District.setId(level1.getInt("id"));
			result.level1District.setName(level1.getString("name"));
		}
		
		if( level2 != null ){
			result.level1District = new DistrictBean();
			result.level1District.setId(level2.getInt("id"));
			result.level1District.setName(level2.getString("name"));
		}
		
		if( level3 != null ){
			result.level1District = new DistrictBean();
			result.level1District.setId(level3.getInt("id"));
			result.level1District.setName(level3.getString("name"));
		}
			
		return result;
	}
	
	public String serializerAddress( Address address ){
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("detail", address.districtDetail);
		
		if( address.level1District != null ){
			JSONObject district = new JSONObject();
			district.put("id", address.level1District.getId() );
			district.put("name", address.level1District.getName());
		}
		
		if( address.level2District != null ){
			JSONObject district = new JSONObject();
			district.put("id", address.level2District.getId() );
			district.put("name", address.level2District.getName());
		}
		
		if( address.level3District != null ){
			JSONObject district = new JSONObject();
			district.put("id", address.level3District.getId() );
			district.put("name", address.level3District.getName());
		}
		
		return jsonObject.toString();
		
	}

	public DaoOrder getDaoOrder() {
		return daoOrder;
	}

	public void setDaoOrder(DaoOrder daoOrder) {
		this.daoOrder = daoOrder;
	}

}
