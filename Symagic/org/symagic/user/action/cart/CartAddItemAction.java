package org.symagic.user.action.cart;

import java.util.List;

import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoCart;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class CartAddItemAction extends ActionSupport {
    
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -8688962449531199326L;
	//传入
   
   private ItemTinyBean[]items ;

	
	//配置项
	private DaoCart daoCart;//对于会员来说，更新到数据库中
    private DaoBook daoBook;
	//传出	
	private Boolean addResult=false;//添加结果
	private String resultInfo;//操作的结果信息
	//内部
	private Boolean validateResult=true;//对参数有效性的验证结果

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(!validateResult){
			addResult=false;
			return SUCCESS;
		}
		boolean login=UserSessionUtilty.isLogin();
		boolean result;
		addResult=true;
		StringBuilder builder=new StringBuilder();
	   for(int index=0;index<items.length;index++){
		   result=addOneToCart(items[index].getItemID(),items[index].getItemNumber(),login);
		   if(!result){
			   addResult=false;
			   if(builder.length()==0){
				   builder.append("编号为"+items[index].getItemID());
			   }  
			   else{
				   builder.append(","+items[index]);
			   }
		   }
	   }
	   if(!addResult){
		  builder.append("添加到购物车失败");
		  resultInfo=builder.toString();
	   }
	   else{
		   resultInfo="成功添加到购物车";
	   }
		
	   
	   return SUCCESS;
		
	}
	private boolean addOneToCart(Integer itemID,Integer itemNumber,boolean login){
		//itemID不存在于数据库中
		if(daoBook.getDetail(itemID)==null){
			   return false;
			 }
		boolean addResult;
		 addResult=UserSessionUtilty.addToCart(itemID, itemNumber);
		//对于会员而言，后台数据库做同样的更新
		    Integer  number=UserSessionUtilty.getCart().get(itemID);
		    if(login){
		    	if(number==null)
		    		addResult=daoCart.addBook(UserSessionUtilty.getUsername(), itemID, itemNumber);
		    	else
		    		addResult=daoCart.modifyBook(UserSessionUtilty.getUsername(), itemID, itemNumber+number);
		    	}
		    return addResult;
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(items==null){
			validateResult=false;
		}
		
	}
	public DaoCart getDaoCart() {
		return daoCart;
	}
	public void setDaoCart(DaoCart daoCart) {
		this.daoCart = daoCart;
	}
	public DaoBook getDaoBook() {
		return daoBook;
	}
	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}
	
	public ItemTinyBean[] getItems() {
		return items;
	}
	public void setItems(ItemTinyBean[] items) {
		this.items = items;
	}
	
	public Boolean getAddResult() {
		return addResult;
	}
	public void setAddResult(Boolean addResult) {
		this.addResult = addResult;
	}
	public Boolean getValidateResult() {
		return validateResult;
	}
	public void setValidateResult(Boolean validateResult) {
		this.validateResult = validateResult;
	}

	

}
