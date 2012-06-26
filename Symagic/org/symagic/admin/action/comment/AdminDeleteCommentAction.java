package org.symagic.admin.action.comment;

import com.opensymphony.xwork2.ActionSupport;

public class AdminDeleteCommentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8918344867773606664L;
	
	private Integer commentID;
	private Boolean deleteResult;
	

	@Override
	public String execute() throws Exception {
		
		
		if( commentID != null ){
			
		}
		else{
			deleteResult = false;
		}
		
		
		return super.execute();
	}
	

}
