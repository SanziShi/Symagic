package org.symagic.admin.action.item;

import org.symagic.common.db.func.DaoBook;

import com.opensymphony.xwork2.ActionSupport;

public class ItemDeleteAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4066697997147063158L;
	
	private Integer itemID;//商品ID
	private Boolean deleteResult;
	
	private DaoBook daoBook;

	@Override
	public String execute() throws Exception {
		
		if( itemID != null ){
			
		}
		else{
			deleteResult = false;
		}
		
		return SUCCESS;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public Boolean getDeleteResult() {
		return deleteResult;
	}

	public void setDeleteResult(Boolean deleteResult) {
		this.deleteResult = deleteResult;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}
	
	
	
}
