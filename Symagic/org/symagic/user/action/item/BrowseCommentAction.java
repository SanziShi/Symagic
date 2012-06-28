package org.symagic.user.action.item;

import java.util.List;

import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.service.ItemService;

import com.opensymphony.xwork2.ActionSupport;

public class BrowseCommentAction extends ActionSupport{
/**
	 * 
	 */
	private static final long serialVersionUID = -7684113880187640570L;
private Integer page;
public Integer getPage() {
	return page;
}
public void setPage(Integer page) {
	this.page = page;
}
public Integer getItemID() {
	return itemID;
}
public void setItemID(Integer itemID) {
	this.itemID = itemID;
}
public List<BeanComment> getCommentList() {
	return commentList;
}
public void setCommentList(List<BeanComment> commentList) {
	this.commentList = commentList;
}
public ItemService getItemService() {
	return itemService;
}
public void setItemService(ItemService itemService) {
	this.itemService = itemService;
}
private Integer itemID;
private List<BeanComment>commentList;
private ItemService itemService;
private int lines=15;
@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
	commentList=itemService.getCommentWithPage(itemID, page, 15);
	return super.execute();
}
}
