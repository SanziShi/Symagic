package org.symagic.user.action.item;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.service.ItemService;

import org.symagic.common.utilty.presentation.bean.ItemDetailBean;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.common.utilty.presentation.bean.ItemBean;

public class ItemDetailAction extends CatalogBase {

/**
	 * 
	 */
	private static final long serialVersionUID = 9054557857853861076L;
	
	private int lines=15;
	private int totalPage;//评论有多少页

	private int itemID;//商品id
	private ItemDetailBean book;//书籍详细信息
	private ItemService itemService;//访问服务层
	private List<BeanComment>commentList;//评论列表
	private  ArrayList<ItemTinyBean>recommendView;
	private ArrayList<ItemTinyBean>recommendBought;
@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	book=new ItemDetailBean();
	itemService.fillDetailBean(itemID, book);
	int commentNumber=itemService.getCommentNumber(itemID);
	totalPage=(commentNumber+lines-1)/lines;
	commentList=itemService.getCommentWithPage(itemID, 1, lines);
	/*
	 *test 
	 */
	recommendView=new ArrayList<ItemTinyBean>();
	recommendBought=new ArrayList<ItemTinyBean>();
	itemService.getNewBook(recommendView);
	itemService.getNewBook(recommendBought);
	return super.execute();
	}



public int getTotalPage() {
	return totalPage;
}
public void setTotalPage(int totalPage) {
	this.totalPage = totalPage;
}
public int getItemID() {
	return itemID;
}
public void setItemID(int itemID) {
	this.itemID = itemID;
}
public ItemDetailBean getBook() {
	return book;
}
public void setBook(ItemDetailBean book) {
	this.book = book;
}
public ItemService getItemService() {
	return itemService;
}
public void setItemService(ItemService itemService) {
	this.itemService = itemService;
}

public List<BeanComment> getCommentList() {
	return commentList;
}
public void setCommentList(List<BeanComment> commentList) {
	this.commentList = commentList;
}
public ArrayList<ItemTinyBean> getRecommendView() {
	return recommendView;
}
public void setRecommendView(ArrayList<ItemTinyBean> recommendView) {
	this.recommendView = recommendView;
}
public ArrayList<ItemTinyBean> getRecommendBought() {
	return recommendBought;
}
public void setRecommendBought(ArrayList<ItemTinyBean> recommendBought) {
	this.recommendBought = recommendBought;
}

}
