package org.symagic.user.action.cart;

import org.symagic.common.db.func.DaoCart;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class CartModifyItemAction extends ActionSupport {

private DaoCart daoCart;//访问数据库中会员购物车信息

private Integer itemId;//商品id
private Integer itemNumber;//更改后的数量
private boolean validateResult=true;
private boolean updateResult;//修改是否成功

@Override
public void validate() {
	// TODO Auto-generated method stub
	if(itemId==null||itemNumber==null||itemNumber<0){
		validateResult=false;
	}
	
}
@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
	 if(validateResult==false){
		 updateResult=false;
		 return "SUCCESS";
	 }
	 
	 updateResult=UserSessionUtilty.addToCart(itemId, itemNumber);
		//会员登录更新到数据库中
		if(UserSessionUtilty.isLogin()){
			updateResult=daoCart.modifyBook(UserSessionUtilty.getUsername(), itemId, itemNumber);
		}
	 
	return super.execute();
}
public boolean isUpdateResult() {
	return updateResult;
}
public void setUpdateResult(boolean updateResult) {
	this.updateResult = updateResult;
}
public Integer getItemId() {
	return itemId;
}
public void setItemId(Integer itemId) {
	this.itemId = itemId;
}
public Integer getItemNumber() {
	return itemNumber;
}
public void setItemNumber(Integer itemNumber) {
	this.itemNumber = itemNumber;
}
public DaoCart getDaoCart() {
	return daoCart;
}
public void setDaoCart(DaoCart daoCart) {
	this.daoCart = daoCart;
}
}
