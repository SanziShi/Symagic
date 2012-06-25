package org.symagic.user.action.item;

import com.opensymphony.xwork2.ActionSupport;

public class SubmitCommentAction extends ActionSupport {
 
	private int itemId;//评论的商品
	 private String content;//评论内容
	 private int rating;//评分
	 private boolean submitResult;//评论是否成功
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}
@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
	 return super.execute();
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

 
}
