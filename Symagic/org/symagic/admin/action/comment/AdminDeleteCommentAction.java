package org.symagic.admin.action.comment;

import org.symagic.common.db.func.DaoComment;

import com.opensymphony.xwork2.ActionSupport;

public class AdminDeleteCommentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8918344867773606664L;
	
	private String userName;//:用户名，
	private Integer itemID;//：商品ID
	private Boolean deleteResult;
	
	private DaoComment daoComment;
	

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Integer getItemID() {
		return itemID;
	}


	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}


	public Boolean getDeleteResult() {
		return deleteResult;
	}


	public void setDeleteResult(Boolean deleteResult) {
		this.deleteResult = deleteResult;
	}


	public DaoComment getDaoComment() {
		return daoComment;
	}


	public void setDaoComment(DaoComment daoComment) {
		this.daoComment = daoComment;
	}


	@Override
	public String execute() throws Exception {
		
		deleteResult = false;
		
		if( userName == null || itemID == null ){
			return super.execute();
		}
		
		if( !daoComment.delete(itemID, userName) ) return super.execute();
		
		deleteResult = true;
		
		return super.execute();
	}
	

}
