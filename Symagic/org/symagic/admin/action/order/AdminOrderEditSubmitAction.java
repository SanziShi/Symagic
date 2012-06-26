package org.symagic.admin.action.order;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanDistrict;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
import org.symagic.common.db.func.DaoBook;
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
	private DaoBook daoBook;

	@Override
	public String execute() throws Exception {

		if (!validateResult)
			return ERROR;

		BeanOrder order = daoOrder.getOrderDetail(orderID);
		if (order == null)
			return ERROR;
		// 排除不合适修改的订单
		if (order.getOrderState().equals("2")
				|| order.getOrderState().equals("3"))
			return ERROR;
		// 编码地址
		OrderService.Address address = new Address();
		address.districtDetail = addressDetail;
		address.level1District.setID(level1ID);
		BeanDistrict district = daoDistrict.getDistrictById(level1ID);
		address.level1District.setName(district.getName());
		address.level2District.setID(level2ID);
		if (level2ID != null) {
			district = daoDistrict.getDistrictById(level2ID);
			address.level2District.setName(district.getName());
		}
		address.level3District.setID(level3ID);
		if (level3ID != null) {
			district = daoDistrict.getDistrictById(level3ID);
			address.level3District.setName(district.getName());
		}
		order.setAddrDetail(OrderService.serializerAddress(address));

		order.setReceiverName(receiver);
		order.setZipcode(zipcode);
		order.setPhonenum(phoneNumber);
		order.setMobilenum(mobileNumber);

		List<BeanOrderDetail> itemList = new ArrayList<BeanOrderDetail>();

		// 解析JSON数组
		JSON json = JSONSerializer.toJSON(items);
		if (json.isArray()) {
			JSONArray array = (JSONArray) json;
			for (int i = 0; i < array.size(); i++) {
				JSONObject item = array.getJSONObject(i);
				BeanBook book = daoBook.getDetail(item.getInt("id"));
				if (book != null) {
					BeanOrderDetail orderDetail = new BeanOrderDetail();
					orderDetail.setAmount(item.getInt("number"));
					orderDetail.setBookId(book.getBookId());
					orderDetail.setBookName(book.getBookName());
					orderDetail.setDiscount(book.getDiscount());
					orderDetail.setIsbn(book.getIsbn());
					orderDetail.setMarketPrice(book.getMarketPrice());
					orderDetail.setOrderId(orderID);
					itemList.add(orderDetail);
				}

			}
			order.setList(itemList);
		}
		
		daoOrder.updateOrder(order);

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

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Integer getLevel1ID() {
		return level1ID;
	}

	public void setLevel1ID(Integer level1id) {
		level1ID = level1id;
	}

	public Integer getLevel2ID() {
		return level2ID;
	}

	public void setLevel2ID(Integer level2id) {
		level2ID = level2id;
	}

	public Integer getLevel3ID() {
		return level3ID;
	}

	public void setLevel3ID(Integer level3id) {
		level3ID = level3id;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public boolean isValidateResult() {
		return validateResult;
	}

	public void setValidateResult(boolean validateResult) {
		this.validateResult = validateResult;
	}

	public DaoOrder getDaoOrder() {
		return daoOrder;
	}

	public void setDaoOrder(DaoOrder daoOrder) {
		this.daoOrder = daoOrder;
	}

	public DaoDistrict getDaoDistrict() {
		return daoDistrict;
	}

	public void setDaoDistrict(DaoDistrict daoDistrict) {
		this.daoDistrict = daoDistrict;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

}
