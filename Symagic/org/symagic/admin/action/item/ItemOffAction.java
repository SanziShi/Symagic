package org.symagic.admin.action.item;

import org.symagic.common.db.func.DaoBook;

import com.opensymphony.xwork2.ActionSupport;

public class ItemOffAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7886694657160867924L;

	private Integer itemID;// 商品ID
	private Boolean offResult;
	private DaoBook daoBook;

	@Override
	public String execute() throws Exception {
		if (itemID != null) {

		} else {
			offResult = false;
		}
		return SUCCESS;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public Boolean getOffResult() {
		return offResult;
	}

	public void setOffResult(Boolean offResult) {
		this.offResult = offResult;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

}
