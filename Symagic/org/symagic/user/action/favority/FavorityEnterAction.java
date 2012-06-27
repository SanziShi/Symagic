package org.symagic.user.action.favority;

import java.util.List;

import org.symagic.common.db.func.DaoFavorityFolder;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.common.utilty.presentation.bean.ItemDetailBean;

import com.opensymphony.xwork2.ActionSupport;
public class FavorityEnterAction extends ActionSupport {
	
	private DaoFavorityFolder daoFavorityFolder;
	private int page;//第几页
	private List<ItemDetailBean> items;//显示收藏夹中的东西
	private int offlines=10;//一页显示 的条数
	private List<ItemBean> recommend;
	private int totalPages;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		totalPages=(items.size()+offlines-1)/offlines;
		
		//得到显示记录 后进行装饰成Items
		//得到推荐商品 recommned;
		return super.execute();
	}
	
	public DaoFavorityFolder getDaoFavorityFolder() {
		return daoFavorityFolder;
	}

	public void setDaoFavorityFolder(DaoFavorityFolder daoFavorityFolder) {
		this.daoFavorityFolder = daoFavorityFolder;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<ItemDetailBean> getItems() {
		return items;
	}

	public void setItems(List<ItemDetailBean> items) {
		this.items = items;
	}

	public int getOfflines() {
		return offlines;
	}

	public void setOfflines(int offlines) {
		this.offlines = offlines;
	}



}
