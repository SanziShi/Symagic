package org.symagic.admin.action.item;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;

import com.opensymphony.xwork2.ActionSupport;

public class ItemUpAction extends ActionSupport {
	
	private Integer itemID;// 商品ID
	private Boolean upResult;
	private DaoBook daoBook;

	@Override
	public String execute() throws Exception {
		if (itemID != null) {
			BeanBook book = daoBook.getDetail(itemID);
			if( book.getOffline().equals("上架") ){
				upResult = false;
				return SUCCESS;
			}
			book.setOffline("上架");
			daoBook.modifyBook(book);
			upResult = true;
		} else {
			upResult = false;
		}
		return SUCCESS;
	}
	
}
