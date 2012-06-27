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

private RecommandService recommendService; //访问推荐系统
private ItemService itemService;//访问商品项


//传出参数
private String nickname;//昵称
private Integer totalNumber;//购物车数量
private List<ItemBean> recommendItem;//浏览量的商品
private List<ItemBean> newBook;//新书
private List<ItemBean> hotBook;//热销书

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		nickname=UserSessionUtilty.getNickname();
		totalNumber=UserSessionUtilty.getCartNumber();
		
		//推荐商品的id
	   // List<Integer> recommendIds=recommendService.recommendationsForUser(UserSessionUtilty.getUsername(), 10);
	    //itemService.fillItem(recommendIds,recommendItem);
	     
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


	

	public RecommandService getRecommendService() {
		return recommendService;
	}


	public void setRecommendService(RecommandService recommendService) {
		this.recommendService = recommendService;
	}


	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}


	public ItemService getItemService() {
		return itemService;
	}


	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}


	public int getTotalNumber() {
		return totalNumber;
	}


	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}


	public List<ItemBean> getRecommendItem() {
		return recommendItem;
	}


	public void setRecommendItem(List<ItemBean> recommendItem) {
		this.recommendItem = recommendItem;
	}


	public List<ItemBean> getNewBook() {
		return newBook;
	}


	public void setNewBook(List<ItemBean> newBook) {
		this.newBook = newBook;
	}


	public List<ItemBean> getHotBook() {
		return hotBook;
	}


	public void setHotBook(List<ItemBean> hotBook) {
		this.hotBook = hotBook;
	}
	
  
}
