package org.symagic.admin.action.comment;

import org.symagic.common.db.func.DaoComment;

import com.opensymphony.xwork2.ActionSupport;

public class AdminDeleteCommentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8918344867773606664L;
	
	private Integer commentID;
	private Boolean deleteResult;
	
	private DaoComment daoComment;
	

	@Override
	public String execute() throws Exception {
		
		deleteResult = false;
		
		if( commentID != null ){
			//daoComment.
		}
		
		return super.execute();
	}
	

}
