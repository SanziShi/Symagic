package org.symagic.user.action.favority;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanFavorityFolder;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoFavorityFolder;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.user.utilty.MathUtilty;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;
public class FavorityEnterAction extends ActionSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -517295759985703579L;
	
	//传入
	private Integer page;//第几页
	//配置项
	private DaoFavorityFolder daoFavorityFolder;
	private ItemService itemService;
	private Integer lines;//一页显示 的条数
	//传出
	private List<ItemBean> items;//显示收藏夹中的东西
	private List<ItemTinyBean> recommend;
	private Integer totalPage;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		//得到显示记录 后进行装饰成Items
		List<BeanFavorityFolder> favorities=daoFavorityFolder.get(UserSessionUtilty.getUsername(), page, lines);
		//
		items=new ArrayList<ItemBean>();
		BeanFavorityFolder favority; 
		ItemBean item;
		for(Iterator<BeanFavorityFolder>index=favorities.iterator();index.hasNext();){
			favority=index.next();
			item=new ItemBean();
			itemService.fillItemBean(favority.getBookID(), item);
			items.add(item);
		}
		totalPage=(items.size()+lines-1)/lines;
		
		//得到推荐商品 recommned;
		/**
		 * test,待修改为真正的推荐系统
		 */
		recommend=new ArrayList<ItemTinyBean>();
		itemService.getNewBook(recommend);
		 
		return super.execute();
	}
	
	
	public DaoFavorityFolder getDaoFavorityFolder() {
		return daoFavorityFolder;
	}
	public void setDaoFavorityFolder(DaoFavorityFolder daoFavorityFolder) {
		this.daoFavorityFolder = daoFavorityFolder;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public List<ItemBean> getItems() {
		return items;
	}
	public void setItems(List<ItemBean> items) {
		this.items = items;
	}
	public Integer getLines() {
		return lines;
	}
	public void setLines(Integer lines) {
		this.lines = lines;
	}
	public List<ItemTinyBean> getRecommend() {
		return recommend;
	}
	public void setRecommend(List<ItemTinyBean> recommend) {
		this.recommend = recommend;
	}


	public ItemService getItemService() {
		return itemService;
	}


	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}


	public Integer getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	

	


}
