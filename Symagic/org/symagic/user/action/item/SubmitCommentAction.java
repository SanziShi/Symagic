package org.symagic.user.action.item;

import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.service.ItemService;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class SubmitCommentAction extends ActionSupport {
     private ItemService itemService;
	
	private int itemId;//评论的商品
	 private String content;//评论内容
	 private int rating;//评分
	 private boolean submitResult;//评论是否成功
	 
	
@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		BeanComment comment=new BeanComment();
		comment.setBookID(itemId);
		comment.setContent(content);
		comment.setRating(rating+"");//?string
		comment.setUsername(UserSessionUtilty.getUsername());
		submitResult=itemService.addItemComment(comment);
	 return super.execute();
	}
 
 
public ItemService getItemService() {
	return itemService;
}


public void setItemService(ItemService itemService) {
	this.itemService = itemService;
}
 public int getItemId() {
	return itemId;
}
public void setItemId(int itemId) {
	this.itemId = itemId;
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
public boolean isSubmitResult() {
	return submitResult;
}
public void setSubmitResult(boolean submitResult) {
	this.submitResult = submitResult;
}

 
}
