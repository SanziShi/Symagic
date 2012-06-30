package org.symagic.user.action.order;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;

public class ItemCheckInventoryAction extends CatalogBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -702720467489852437L;
	
	private Integer itemID;
	
	private Integer itemNum;
	
	private Boolean checkResult;
	
	private DaoBook daoBook;
	
	public String execute() throws Exception{
		BeanBook beanBook = daoBook.getDetail(itemID);
		if(beanBook == null)
			checkResult = false;
		else{
			if(beanBook.getInventory() > itemNum)
				checkResult = true;
		}
		return SUCCESS;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public Boolean getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(Boolean checkResult) {
		this.checkResult = checkResult;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

}
