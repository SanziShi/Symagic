package org.symagic.user.action.cart;

import org.symagic.common.db.func.DaoCart;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class CartAddItemAction extends ActionSupport {
    
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -8688962449531199326L;
	private DaoCart daoCart;//对于会员来说，更新到数据库中
	private Integer itemID;//商品id号
	
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
		
		Integer  number=UserSessionUtilty.getCart().get(itemID);
	    if(number==null){
		//添加商品到session中的cart
		addResult=UserSessionUtilty.addToCart(itemID, itemNumber);
		}
	    else{
	    	//如果购物车中已有该商品，则数量增加
	    	addResult=UserSessionUtilty.addToCart(itemID, number+itemNumber);
	    }
	    //对于会员而言，后台数据库做同样的更新
	    if(UserSessionUtilty.isLogin()){
	    	if(number==null)
	    		addResult=daoCart.addBook(UserSessionUtilty.getUsername(), itemID, itemNumber);
	    	else
	    		daoCart.modifyBook(UserSessionUtilty.getUsername(), itemID, itemNumber+number);
	    
	    }
	    //增加session中的数量
	    UserSessionUtilty.addTotalNumber(number);
	    
	  
		return SUCCESS;
		
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(itemID==null||itemNumber==null||itemNumber<0){
			validateResult=false;
		}
		
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
