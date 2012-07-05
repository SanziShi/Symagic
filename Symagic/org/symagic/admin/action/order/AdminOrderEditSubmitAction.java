package org.symagic.admin.action.order;

import java.util.ArrayList;
import java.util.List;

import org.symagic.admin.utilty.AdminUtility;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanDistrict;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoDistrict;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.service.MailService;
import org.symagic.common.service.OrderService;
import org.symagic.common.service.OrderService.Address;
import org.symagic.common.utilty.presentation.bean.DistrictBean;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;

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
	private List<ItemTinyBean> items;

	private boolean validateResult;

	private DaoOrder daoOrder;
	private DaoDistrict daoDistrict;
	private DaoBook daoBook;

	private String errorHeader;
	private String errorSpecification;

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
		address.level1District = new DistrictBean();

		address.districtDetail = addressDetail;
		address.level1District.setID(level1ID);
		BeanDistrict district = daoDistrict.getDistrictById(level1ID);
		address.level1District.setName(district.getName());
		if (level2ID != null) {
			address.level2District = new DistrictBean();
			address.level2District.setID(level2ID);
			district = daoDistrict.getDistrictById(level2ID);
			address.level2District.setName(district.getName());
		}
		if (level3ID != null) {
			address.level3District = new DistrictBean();
			address.level3District.setID(level3ID);
			district = daoDistrict.getDistrictById(level3ID);
			address.level3District.setName(district.getName());
		}
		order.setAddrDetail(OrderService.serializerAddress(address));

		order.setReceiverName(receiver);
		order.setZipcode(zipcode);
		order.setPhonenum(phoneNumber);
		order.setMobilenum(mobileNumber);

		List<BeanOrderDetail> itemList = new ArrayList<BeanOrderDetail>();

		List<BeanOrderDetail> oldItems = order.getList();

		float totalprice = 0;

		// 解析JSON数组
		for (int i = 0; i < items.size(); i++) {
			BeanBook book = daoBook.getDetail(items.get(i).getItemID());
			if (book != null && items.get(i).getItemNumber() != 0) {
				BeanOrderDetail orderDetail = new BeanOrderDetail();
				orderDetail.setAmount(items.get(i).getItemNumber());
				for (BeanOrderDetail detail : oldItems) {
					if (detail.getBookId() == book.getBookId()) {
						book.setInventory(book.getInventory()
								+ detail.getAmount() - orderDetail.getAmount());
						if (!daoBook.modifyBook(book))
							return ERROR;
					}
				}
				orderDetail.setBookId(book.getBookId());
				orderDetail.setBookName(book.getBookName());
				orderDetail.setDiscount(book.getDiscount());
				orderDetail.setIsbn(book.getIsbn());
				orderDetail.setMarketPrice(book.getMarketPrice());
				orderDetail.setOrderId(orderID);
				totalprice += book.getMarketPrice() * book.getDiscount()
						* items.get(i).getItemNumber();
				itemList.add(orderDetail);
			}

		}
		order.setList(itemList);
		order.setTotalprice(totalprice);

		if (!daoOrder.updateOrder(order))
			return ERROR;

		MailService.sendOrder(order);

		return super.execute();
	}

	@Override
	public void validate() {

		if (orderID == null || AdminUtility.isEmpty(receiver)
				|| level1ID == null || AdminUtility.isEmpty(addressDetail)
				|| items == null) {
			errorHeader = "信息不全";
			errorSpecification = "您填写的信息不全";
			validateResult = false;
			return;
		}

		if (level1ID == null || level1ID <= 0
				|| (level2ID != null && level2ID <= 0)
				|| (level3ID != null && level3ID <= 0)) {
			errorHeader = "信息错误";
			errorSpecification = "地区选择错误";
			validateResult = false;
			return;
		}

		if (AdminUtility.isEmpty(mobileNumber)
				|| !mobileNumber.matches("[1]{1}[3,5,8,6]{1}[0-9]{9}")) {
			if (AdminUtility.isEmpty(phoneNumber)
					|| !phoneNumber.matches("^[0]\\d{2,3}\\d{7,8}")) {
				errorHeader = "信息错误";
				errorSpecification = "电话号码不全，或错误";
				validateResult = false;
				return;
			}
		}

		if (AdminUtility.isEmpty(zipcode) && !zipcode.matches("^[1-9]\\d{5}")) {
			errorHeader = "信息错误";
			errorSpecification = "邮编错误";
			validateResult = false;
			return;
		}

		validateResult = true;

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

	public List<ItemTinyBean> getItems() {
		return items;
	}

	public void setItems(List<ItemTinyBean> items) {
		this.items = items;
	}

	public String getErrorHeader() {
		return errorHeader;
	}

	public void setErrorHeader(String errorHeader) {
		this.errorHeader = errorHeader;
	}

	public String getErrorSpecification() {
		return errorSpecification;
	}

	public void setErrorSpecification(String errorSpecification) {
		this.errorSpecification = errorSpecification;
	}

}
