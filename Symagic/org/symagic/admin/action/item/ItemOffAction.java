package org.symagic.admin.action.item;

import java.util.List;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;

import com.opensymphony.xwork2.ActionSupport;

public class ItemOffAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7886694657160867924L;

	private List<Integer> itemID;// 商品ID
	private Boolean offResult;
	private DaoBook daoBook;

	@Override
	public String execute() throws Exception {
		offResult = false;
		if (itemID != null) {
			for (Integer id : itemID) {
				if( id == null ) return super.execute();
				BeanBook book = daoBook.getDetail(id);
				if (book == null)
					return super.execute();
				if (book.getOffline().equals("下架")) {
					return super.execute();
				}
				book.setOffline("下架");
				if (!daoBook.modifyBook(book))
					return super.execute();
			}
			offResult = true;
		}
		return super.execute();
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



	public List<Integer> getItemID() {
		return itemID;
	}



	public void setItemID(List<Integer> itemID) {
		this.itemID = itemID;
	}

}
