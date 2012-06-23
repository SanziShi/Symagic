package org.symagic.user.action.cart;

import org.symagic.common.db.func.DaoCart;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class CartDeleteItemAction extends ActionSupport {
	private DaoCart daoCart;
	public DaoCart getDaoCart() {
		return daoCart;
	}

	public void setDaoCart(DaoCart daoCart) {
		this.daoCart = daoCart;
	}
	private Integer itemId;
	private boolean deleteResult;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(itemId==null){
			deleteResult=false;
			return SUCCESS;
		}
		//在session中将对应商品从cart中删除
		deleteResult=UserSessionUtilty.deleteFromCart(itemId);
		//对于会员，保持数据库中cart的数据与session一致性
		if(UserSessionUtilty.isLogin()){
		deleteResult=daoCart.deleteBook(UserSessionUtilty.getUsername(), itemId);
		}
		
		return super.execute();
	}

public Integer getItemId() {
	return itemId;
}
public void setItemId(Integer itemId) {
	this.itemId = itemId;
}
public boolean isDeleteResult() {
	return deleteResult;
}
public void setDeleteResult(boolean deleteResult) {
	this.deleteResult = deleteResult;
}


}
