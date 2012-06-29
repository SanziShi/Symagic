package org.symagic.admin.action.item;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.ItemDetailBean;
import org.symagic.common.utilty.presentation.bean.TimeBean;


public class ItemModifyEnterAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1683701906910833628L;

	private Integer itemID;// 商品id
	private ItemDetailBean book;// 书籍详细信息
	private ItemService itemService;// 访问服务层
	
	private TimeBean parseTime; //用于时间解析
	
	private String errorHeader;
	private String errorSpecification;
	
	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public ItemDetailBean getBook() {
		return book;
	}

	public void setBook(ItemDetailBean book) {
		this.book = book;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public boolean isValidateResult() {
		return validateResult;
	}

	public void setValidateResult(boolean validateResult) {
		this.validateResult = validateResult;
	}

	private boolean validateResult;

	@Override
	public void validate() {
		
		validateResult = true;
		
		if( itemID == null )
			validateResult = false;
		
		super.validate();
	}

	@Override
	public String execute() throws Exception {
		
		if( !validateResult ) return ERROR;
		
		book = new ItemDetailBean();
		if( !itemService.fillDetailBean(itemID, book) )
			return ERROR;
		
		//时间解析
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(book.getPublishDate());
		GregorianCalendar calender = new GregorianCalendar();
		calender.setTime(date);
		parseTime = new TimeBean();
		parseTime.setYear(calender.get(Calendar.YEAR));
		parseTime.setYear(calender.get(Calendar.MONTH));
		parseTime.setYear(calender.get(Calendar.DAY_OF_MONTH));
		
		return super.execute();
		
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

	public TimeBean getParseTime() {
		return parseTime;
	}

	public void setParseTime(TimeBean parseTime) {
		this.parseTime = parseTime;
	}

	
}
