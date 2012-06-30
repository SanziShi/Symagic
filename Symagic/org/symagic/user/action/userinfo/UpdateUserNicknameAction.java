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
		updateResult = daoUser.updateNickname(UserSessionUtilty.getUsername(), nickname);
		//super.execute();
		UserSessionUtilty.setNickname(nickname);
		return SUCCESS;
	}
}
