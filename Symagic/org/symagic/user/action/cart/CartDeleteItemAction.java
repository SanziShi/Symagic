package org.symagic.user.action.cart;

import java.util.HashMap;
import java.util.Map;

import org.symagic.common.db.func.DaoCart;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CartDeleteItemAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 531344796388813069L;
	//传入
	private Integer itemID;
	//配置项
	private DaoCart daoCart;
	//传出
	
private boolean deleteResult=false;;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(itemID==null){
			deleteResult=false;
			return SUCCESS;
		}
		//购物车没有商品
		
		if(UserSessionUtilty.getCart().get(itemID)==null){
			deleteResult=false;
			return "success";
		}
		//在session中将对应商品从cart中删除
		deleteResult=UserSessionUtilty.deleteFromCart(itemID);
		//对于会员，保持数据库中cart的数据与session一致性
		if(UserSessionUtilty.isLogin()){
		deleteResult=daoCart.deleteBook(UserSessionUtilty.getUsername(), itemID);
		}
		
		return super.execute();
	}


public boolean isDeleteResult() {
	return deleteResult;
}
public void setDeleteResult(boolean deleteResult) {
	this.deleteResult = deleteResult;
}

public DaoCart getDaoCart() {
	return daoCart;
}

public void setDaoCart(DaoCart daoCart) {
	this.daoCart = daoCart;
}


public Integer getItemID() {
	return itemID;
}


public void setItemID(Integer itemID) {
	this.itemID = itemID;
}


}
