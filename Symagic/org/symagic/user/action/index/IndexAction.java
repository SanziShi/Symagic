package org.symagic.user.action.index;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.service.ItemService;
import org.symagic.common.service.RecommandService;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.user.utilty.UserSessionUtilty;

public class IndexAction extends CatalogBase{



/**
	 * 
	 */
private static final long serialVersionUID = 5685501467658534869L;

private RecommandService recommandService;
private ItemService itemService;



private String nickname;
private int cartItemNumber;
private List<ItemBean> recommendItem;

private List<ItemBean> newBook;
private List<ItemBean> hotBook;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		nickname=UserSessionUtilty.getNickname();
		cartItemNumber=UserSessionUtilty.getCart().size();
		
		//推荐商品的id
	     List<Integer> recommendIds=recommandService.recommendationsForUser(UserSessionUtilty.getUsername(), 10);
	     itemService.fillItem(recommendIds,recommendItem);
	     
	     //新书和热销书
	    newBook=new ArrayList<ItemBean>();
	   
	    
		hotBook=new ArrayList<ItemBean>();
		return super.execute();
	}
	
	
	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public int getCartItemNumber() {
		return cartItemNumber;
	}


	public void setCartItemNumber(int cartItemNumber) {
		this.cartItemNumber = cartItemNumber;
	}
	
  
}
