package org.symagic.user.action.cart;

import org.symagic.common.db.func.DaoCart;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class CartDeleteItemAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 531344796388813069L;
	private DaoCart daoCart;
	public DaoCart getDaoCart() {
		return daoCart;
	}

	public void setDaoCart(DaoCart daoCart) {
		this.daoCart = daoCart;
	}
	private Integer itemID;
	private boolean deleteResult;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(itemID==null){
			deleteResult=false;
			return SUCCESS;
		}
		//在session中将对应商品从cart中删除
		deleteResult=UserSessionUtilty.deleteFromCart(itemID);
		//对于会员，保持数据库中cart的数据与session一致性
		if(UserSessionUtilty.isLogin()){
		deleteResult=daoCart.deleteBook(UserSessionUtilty.getUsername(), itemID);
		}
		
		return super.execute();
	}

public Integer getItemId() {
	return itemID;
}
public void setItemId(Integer itemId) {
	this.itemID= itemId;
}
public boolean isDeleteResult() {
	return deleteResult;
}
public void setDeleteResult(boolean deleteResult) {
	this.deleteResult = deleteResult;
}


}
