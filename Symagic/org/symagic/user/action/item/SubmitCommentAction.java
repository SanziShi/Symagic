package org.symagic.user.action.item;

import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.service.ItemService;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class SubmitCommentAction extends ActionSupport {
 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5119384687733985464L;
	private ItemService itemService;//访问商品项
	private int itemID;//评论的商品
	
	private String content;//评论内容
	 private int rating;//评分
	 private boolean submitResult;//评论是否成功
	
@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		

		BeanComment comment=new BeanComment();
		comment.setBookID(itemID);
		comment.setContent(content);
		comment.setRating(rating+"");//?string
		comment.setUsername(UserSessionUtilty.getUsername());
		submitResult=itemService.addItemComment(comment);

	 return super.execute();
	}
 
public int getItemID() {
	return itemID;
}
public void setItemID(int itemID) {
	this.itemID = itemID;
}
public boolean isSubmitResult() {
	return submitResult;
}
public void setSubmitResult(boolean submitResult) {
	this.submitResult = submitResult;
}
 
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getRating() {
	return rating;
}
public void setRating(int rating) {
	this.rating = rating;
}
public ItemService getItemService() {
	return itemService;
}
public void setItemService(ItemService itemService) {
	this.itemService = itemService;
}

 
}
