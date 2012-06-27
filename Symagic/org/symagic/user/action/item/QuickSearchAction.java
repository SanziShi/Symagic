package org.symagic.user.action.item;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.BookRequire;
import org.symagic.common.service.ItemService;
import org.symagic.common.service.RecommandService;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.common.utilty.presentation.bean.ItemDetailBean;
import org.symagic.user.utilty.UserSessionUtilty;
import com.opensymphony.xwork2.ActionSupport;

public class QuickSearchAction extends CatalogBase {



/**
	 * 
	 */
private static final long serialVersionUID = 8991605145652333401L;
private ItemService itemService;//访问书本的业务层
private RecommandService recommendService;//推荐系统

 private String keyword; //关键字
 private int catalogId;    //目录

	
 private Integer page;//分页显示
 private  Integer lines=10;
 private Integer totalPage;
 
 private int sign=0;//搜索标志，0为快速搜索

    private List<ItemBean>recommend;//推荐商品
	private List<ItemDetailBean>items;//用于显示的商品列表
	private  Integer recommendNumber=15;
	
	 @Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
			
		 items=new ArrayList<ItemDetailBean>();
			//设置搜索的条件
			BookRequire require=new BookRequire();
		   require.setCatalogID(String.valueOf(catalogId));
		   require.setAuthor(keyword);
		   require.setItemName(keyword);
		   require.setPublisher(keyword);
		   require.setLines(lines);
		   require.setPage(page);
		      //搜索符合条件的商品
		    List<BeanBook> books=itemService.search(sign, require);
		    totalPage=(itemService.getSearchNum(sign, require)+lines-1)/lines;
		     //装饰成前台所需的信息
			itemService.decorate(books, items);
			
			List<Integer> bookIds;
			//推荐商品
			if(UserSessionUtilty.isLogin()){
			bookIds=recommendService.recommendationsForUser(UserSessionUtilty.getUsername(), recommendNumber);
			}
			else{
				bookIds=recommendService.mostViewedItems(recommendNumber);
			}
			itemService.fillItem(bookIds, recommend);
		
		 return super.execute();
		}
	 
	 public ItemService getItemService() {
			return itemService;
		}

		public void setItemService(ItemService itemService) {
			this.itemService = itemService;
		}

		public RecommandService getRecommendService() {
			return recommendService;
		}

		public void setRecommendService(RecommandService recommendService) {
			this.recommendService = recommendService;
		}

		public String getKeyword() {
			return keyword;
		}

		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

		public int getCatalogId() {
			return catalogId;
		}

		public void setCatalogId(int catalogId) {
			this.catalogId = catalogId;
		}

		public Integer getPage() {
			return page;
		}

		public void setPage(Integer page) {
			this.page = page;
		}

		public Integer getTotalPage() {
			return totalPage;
		}

		public void setTotalPage(Integer totalPage) {
			this.totalPage = totalPage;
		}

		public List<ItemBean> getRecommend() {
			return recommend;
		}

		public void setRecommend(List<ItemBean> recommend) {
			this.recommend = recommend;
		}

		public List<ItemDetailBean> getItems() {
			return items;
		}

		public void setItems(List<ItemDetailBean> items) {
			this.items = items;
		}
	
}
