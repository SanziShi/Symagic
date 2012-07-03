package org.symagic.user.action.cart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.symagic.common.db.bean.BeanBook;
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
   
   private List<ItemTinyBean>items ;
  

	//配置项
	private DaoCart daoCart;//对于会员来说，更新到数据库中
    private DaoBook daoBook;//
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
		boolean result=true;
		addResult=true;
		StringBuilder builder=new StringBuilder();
		Iterator<ItemTinyBean> index=items.iterator();
		ItemTinyBean item;
	  while(index.hasNext()){
		  item=index.next();
		  //数据不符合规则，忽略掉
		  if(item==null||item.getItemID()==null||item.getItemNumber()==null){
			 addResult=false;
			  continue;
		  }
		  if(daoBook.getDetail(item.getItemID())==null){
			  result=false;
		  }
		  else{
		   result=addOneToCart(item.getItemID(),item.getItemNumber(),login);
		   }
		   if(!result){
			   addResult=false;
			   if(builder.length()==0){
				   builder.append("编号为"+item.getItemID());
			   }  
			   else{
				   builder.append(","+item.getItemID());
			   }
		   }
	   }
	   if(!addResult){
		  builder.append("未能添加到购物车");
		  resultInfo=builder.toString();
	   }
	   else{
		   resultInfo="成功添加到购物车";
	   }
		
	   
	   return SUCCESS;
		
	}
	private boolean addOneToCart(Integer itemID,Integer itemNumber,boolean login){
		//首先判断商品是否下架
		BeanBook book=daoBook.getDetail(itemID);
		if(book.getOffline().trim().equals("下架"))
		{
			return false;
		}
			//通过其数量判断是否有这商品
		 Integer  number=UserSessionUtilty.getCart().get(itemID);
		//itemID不存在于数据库中或者是库存不足
		
		int compare=(number==null?0:number);
		if(book==null||book.getInventory()<(itemNumber+compare)||(itemNumber+compare)>1000){
			   return false;
			 }
		boolean addResult;
		
		
		 addResult=UserSessionUtilty.addToCart(itemID, itemNumber);
		  if(addResult&&login){
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

	public List<ItemTinyBean> getItems() {
		return items;
	}
	public void setItems(List<ItemTinyBean> items) {
		this.items = items;
	}
	public String getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

}
