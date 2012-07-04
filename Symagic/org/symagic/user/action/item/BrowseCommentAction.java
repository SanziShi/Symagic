package org.symagic.user.action.item;

import java.util.List;

import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.CommentBean;

import com.opensymphony.xwork2.ActionSupport;

public class BrowseCommentAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7684113880187640570L;
	//传入参数
	private Integer page; 
    private Integer itemID;
    //配置项
    private int lines ;
	private ItemService itemService;
	private String errorHeader;
	private String errorSpecification;
	//传出
	private List<CommentBean> commentList;

	

	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(page==null||itemID==null)return "error";
		commentList=itemService.getComments(itemID, page, lines);
		if(commentList==null)return "error";
		return super.execute();
	}
	
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

	

	public List<CommentBean> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentBean> commentList) {
		this.commentList = commentList;
	}

	public String getErrorHeader() {
		return errorHeader;
	}

	public void setErrorHeader(String errorHeader) {
		this.errorHeader = errorHeader;
	}

	public String getErrorSpecification() {
		return errorSpecification;
	}

	public void setErrorSpecification(String errorSpecification) {
		this.errorSpecification = errorSpecification;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}
}
