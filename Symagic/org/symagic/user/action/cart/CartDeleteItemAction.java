package org.symagic.user.action.cart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.symagic.common.db.func.DaoCart;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CartDeleteItemAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 531344796388813069L;
	//传入
	private List<Integer>items;
	

	public void setItems(List<Integer> items) {
		this.items = items;
	}

	//配置项
	private DaoCart daoCart;
	//传出
	 private boolean deleteResult=false;
	 private String resultInfo;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(items==null){
			deleteResult=false;
			return SUCCESS;
		}
		
		boolean result;
		boolean login=UserSessionUtilty.isLogin();
		StringBuilder builder=new StringBuilder();
		Iterator<Integer> it=items.iterator();
		while(it.hasNext()){
			   result=deleteOneFromCart(it.next(),login);
			   if(!result){
				   deleteResult=false;
				   if(builder.length()==0){
					   builder.append("编号为"+it.next());
				   }  
				   else{
					   builder.append(","+it.next());
				   }
			   }
		   }
	   if(!deleteResult){
			  builder.append("删除失败");
			  resultInfo=builder.toString();
		   }
		   else{
			   resultInfo="删除成功";
	   }
	
		
		return super.execute();
	}

private boolean deleteOneFromCart(Integer itemID,boolean login){
	//购物车没有此商品
	boolean deleteResult;
			if(UserSessionUtilty.getCart().get(itemID)==null){
				deleteResult=false;
				return deleteResult;
			}
			//在session中将对应商品从cart中删除
			deleteResult=UserSessionUtilty.deleteFromCart(itemID);
			//对于会员，保持数据库中cart的数据与session一致性
			if(login){
			deleteResult=daoCart.deleteBook(UserSessionUtilty.getUsername(), itemID);
			}
		return deleteResult;
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





public List<Integer> getItems() {
	return items;
}

public String getResultInfo() {
	return resultInfo;
}

public void setResultInfo(String resultInfo) {
	this.resultInfo = resultInfo;
}





}
