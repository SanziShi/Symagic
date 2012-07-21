package org.symagic.user.action.cart;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoCart;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CartModifyItemAction extends ActionSupport {

/**
	 * 
	 */
	private static final long serialVersionUID = 3920616007203094591L;

//传入
	private Integer itemID;//商品id
	private Integer itemNumber;//更改后的数量
//配置
private DaoCart daoCart;//访问数据库中会员购物车信息
private DaoBook daoBook;
//传出

private boolean updateResult=true;//修改是否成功
private String resultInfo;
//内部
private boolean validateResult=true;
@Override
public void validate() {
	// TODO Auto-generated method stub
	if(itemID==null||itemNumber==null){
		validateResult=false;
	}
	
}
@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
	//数据不符合要求
	 if(validateResult==false){
		 updateResult=false;
		 resultInfo="请输入有效数据";
		 return SUCCESS;
	 }
	 //购物车中没有此商品
	 if(UserSessionUtilty.getCart().get(itemID)==null){
		 updateResult=false;
		 resultInfo="购物车中没有此商品";
		 return SUCCESS;
	 }
	 if(itemNumber<1||itemNumber>999){
		 updateResult=false;
		 resultInfo="请输入正确的数量";
		 return SUCCESS;
	 }
	 //库存检测
	 BeanBook book=daoBook.getDetail(itemID);
	 if(itemNumber>book.getInventory()){
		 updateResult=false;
		 resultInfo="库存不足";
	 }
	//会员登录更新到数据库中
	     if(UserSessionUtilty.isLogin()){
			updateResult=daoCart.modifyBook(UserSessionUtilty.getUsername(), itemID, itemNumber);
			}
			
		if(updateResult)
	         updateResult=UserSessionUtilty.modifyFromCart(itemID, itemNumber);
		if(!updateResult){
			
			resultInfo="修改失败";
			return "success";
		}
	
	  resultInfo="修改成功";
	 
	return super.execute();
}
public boolean isUpdateResult() {
	return updateResult;
}
public void setUpdateResult(boolean updateResult) {
	this.updateResult = updateResult;
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
public Integer getItemID() {
	return itemID;
}
public void setItemID(Integer itemID) {
	this.itemID = itemID;
}
public String getResultInfo() {
	return resultInfo;
}
public void setResultInfo(String resultInfo) {
	this.resultInfo = resultInfo;
}
public DaoBook getDaoBook() {
	return daoBook;
}
public void setDaoBook(DaoBook daoBook) {
	this.daoBook = daoBook;
}


}
