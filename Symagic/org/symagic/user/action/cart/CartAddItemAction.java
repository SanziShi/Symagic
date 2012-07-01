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
   private Integer itemID;//商品id号
   private Integer itemNumber;//商品数量
   private Integer[] i;
	public Integer[] getI() {
	return i;
}
public void setI(Integer[] i) {
	this.i = i;
}
	//配置项
	private DaoCart daoCart;//对于会员来说，更新到数据库中
    private DaoBook daoBook;
	//传出	
	private Boolean addResult=false;//添加结果
	//内部
	private Boolean validateResult=true;//对参数有效性的验证结果
	private List<ItemTinyBean>items;
	public List<ItemTinyBean> getItems() {
		return items;
	}
	public void setItems(List<ItemTinyBean> items) {
		this.items = items;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(!validateResult){
			addResult=false;
			return SUCCESS;
		}
		//itemID不存在于数据库中
	   if(daoBook.getDetail(itemID)==null){
		   addResult=false;
		   return SUCCESS;
	   }
	   
		
	    addResult=UserSessionUtilty.addToCart(itemID, itemNumber);
		Integer[]array=i;
	   
	    //对于会员而言，后台数据库做同样的更新
	    Integer  number=UserSessionUtilty.getCart().get(itemID);
	    if(UserSessionUtilty.isLogin()){
	    	if(number==null)
	    		addResult=daoCart.addBook(UserSessionUtilty.getUsername(), itemID, itemNumber);
	    	else
	    		addResult=daoCart.modifyBook(UserSessionUtilty.getUsername(), itemID, itemNumber+number);
	    	}
	   return SUCCESS;
		
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(itemID==null||itemNumber==null||itemNumber<0){
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
	public Integer getItemID() {
		return itemID;
	}
	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}
	public Integer getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
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
