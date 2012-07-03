package org.symagic.user.action.userinfo;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.func.DaoUser;
import org.symagic.user.utilty.UserSessionUtilty;

public class UpdateUserNicknameAction extends CatalogBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6228449826308630527L;

	private String nickname;
	
	private Boolean updateResult;
	
	private String resultInfo;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Boolean getUpdateResult() {
		return updateResult;
	}

	public void setUpdateResult(Boolean updateResult) {
		this.updateResult = updateResult;
	}
	
	private DaoUser daoUser;

	public DaoUser getDaoUser() {
		return daoUser;
	}

	public void setDaoUser(DaoUser daoUser) {
		this.daoUser = daoUser;
	}
	
	public String execute() throws Exception{
		if(!nickname.matches("^[a-zA-Z0-9_\u4e00-\u9fa5]+$")){
			resultInfo = "非法字符";
			updateResult = false;
			return SUCCESS;
		}
		if(nickname.getBytes().length >20){
			resultInfo = "昵称过长，应小于20位";
			updateResult = false;
			return SUCCESS;
		}
		if(nickname.getBytes().length < 4){
			resultInfo = "昵称过短";
			updateResult = false;
			return SUCCESS;
		}
		updateResult = daoUser.updateNickname(UserSessionUtilty.getUsername(), nickname);
		//super.execute();
		UserSessionUtilty.setNickname(nickname);
		return SUCCESS;
	}
}
