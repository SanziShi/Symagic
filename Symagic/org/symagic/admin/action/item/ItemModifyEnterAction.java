package org.symagic.admin.action.item;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.ItemDetailBean;


public class ItemModifyEnterAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1683701906910833628L;

	private Integer itemID;// 商品id
	private ItemDetailBean book;// 书籍详细信息
	private ItemService itemService;// 访问服务层
	
	private String errorHeader;
	private String errorSpecification;
	
	private Integer searchStartYear;
	private Integer searchEndYear;
	private Integer searchYearRange;
	
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
		
		GregorianCalendar calendar = new GregorianCalendar();
		searchEndYear = calendar.get(Calendar.YEAR);
		searchStartYear = calendar.get(Calendar.YEAR) - searchYearRange;
		
		book = new ItemDetailBean();
		if( !itemService.fillDetailBean(itemID, book) )
			return ERROR;

		
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

	public Integer getSearchStartYear() {
		return searchStartYear;
	}

	public void setSearchStartYear(Integer searchStartYear) {
		this.searchStartYear = searchStartYear;
	}

	public Integer getSearchEndYear() {
		return searchEndYear;
	}

	public void setSearchEndYear(Integer searchEndYear) {
		this.searchEndYear = searchEndYear;
	}

	public Integer getSearchYearRange() {
		return searchYearRange;
	}

	public void setSearchYearRange(Integer searchYearRange) {
		this.searchYearRange = searchYearRange;
	}


	
}
