package org.symagic.admin.action.item;

import java.util.List;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;

import com.opensymphony.xwork2.ActionSupport;

public class ItemUpAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2216563181086628014L;
	private List<Integer> itemID;// 商品ID
	private Boolean upResult;
	private DaoBook daoBook;

	@Override
	public String execute() throws Exception {
		upResult = false;
		if (itemID != null) {
			for (Integer id : itemID) {
				if (id == null)
					return super.execute();
				BeanBook book = daoBook.getDetail(id);
				if (book == null)
					return super.execute();
				if (book.getOffline().equals("在架")) {
					upResult = false;
					return SUCCESS;
				}
				book.setOffline("在架");
				if (!daoBook.modifyBook(book))
					return super.execute();
			}
			upResult = true;
		}
		return super.execute();
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



	public List<Integer> getItemID() {
		return itemID;
	}



	public void setItemID(List<Integer> itemID) {
		this.itemID = itemID;
	}

}
