package org.symagic.admin.action.item;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;

import com.opensymphony.xwork2.ActionSupport;

public class ItemUpAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2216563181086628014L;
	private Integer itemID;// 商品ID
	private Boolean upResult;
	private DaoBook daoBook;

	@Override
	public String execute() throws Exception {
		if (itemID != null) {
			BeanBook book = daoBook.getDetail(itemID);
			if( book == null ) return super.execute();
			if( book.getOffline().equals("在架") ){
				upResult = false;
				return SUCCESS;
			}
			book.setOffline("在架");
			if( !daoBook.modifyBook(book) ) return super.execute();
			upResult = true;
		} else {
			upResult = false;
		}
		return super.execute();
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public Boolean getUpResult() {
		return upResult;
	}

	public void setUpResult(Boolean upResult) {
		this.upResult = upResult;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}
	
}
