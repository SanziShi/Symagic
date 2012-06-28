package org.symagic.user.action.favority;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.symagic.common.db.bean.BeanFavorityFolder;
import org.symagic.common.db.func.DaoFavorityFolder;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;
public class FavorityEnterAction extends ActionSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -517295759985703579L;
	private DaoFavorityFolder daoFavorityFolder;
	private Integer page;//第几页
	private Integer lines;//一页显示 的条数
	
	private List<ItemBean> items;//显示收藏夹中的东西
	private List<ItemTinyBean> recommend;
	private Integer totalPages;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		totalPages=(items.size()+lines-1)/lines;
		//得到显示记录 后进行装饰成Items
		List<BeanFavorityFolder> favorities=daoFavorityFolder.get(UserSessionUtilty.getUsername(), page, lines);
		items=new ArrayList<ItemBean>();
		BeanFavorityFolder favority; 
		ItemBean item;
		for(Iterator<BeanFavorityFolder>index=favorities.iterator();index.hasNext();){
			favority=index.next();
			item=new ItemBean();
			//
		}
		//得到推荐商品 recommned;
		
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
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	

	


}
