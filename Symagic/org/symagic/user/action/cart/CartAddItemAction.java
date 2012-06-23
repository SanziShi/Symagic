package org.symagic.user.action.cart;

import org.symagic.common.db.func.DaoCart;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class CartAddItemAction extends ActionSupport {
    
    private DaoCart daoCart;//对于会员来说，更新到数据库中
	public DaoCart getDaoCart() {
		return daoCart;
	}
	public void setDaoCart(DaoCart daoCart) {
		this.daoCart = daoCart;
	}
	private Integer itemId;//商品id号
	private Integer itemNumber;//商品数量
	private boolean addResult;//添加结果
	private boolean validateResult=true;//对参数有效性的验证结果
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(!validateResult){
			addResult=false;
			return SUCCESS;
		}
		//添加商品到session中的cart
		addResult=UserSessionUtilty.addToCart(itemId, itemNumber);
		//对于会员,添加商品到数据库中的cart,对于已存在的商品，数量加1
		if(UserSessionUtilty.isLogin()){
		addResult=daoCart.addBook(UserSessionUtilty.getUsername(), itemId, itemNumber);
		}
		return SUCCESS;
		
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(itemId==null|itemNumber==null||itemNumber<0){
			validateResult=false;
		}
		
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
	public boolean isAddResult() {
		return addResult;
	}
	public void setAddResult(boolean addResult) {
		this.addResult = addResult;
	}

}
