package org.symagic.user.action.userinfo;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.func.DaoUser;
import org.symagic.user.utilty.UserSessionUtilty;

public class UpdateUsernameAction extends CatalogBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5731738508921747195L;

	private String nickname;
	
	private Boolean updateResult;
	
	private DaoUser daoUser;
	
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

	public DaoUser getDaoUser() {
		return daoUser;
	}

	public void setDaoUser(DaoUser daoUser) {
		this.daoUser = daoUser;
	}

	public String execute() throws Exception{
		super.execute();
		daoUser.updateNickname(UserSessionUtilty.getUsername(), this.nickname);
		return SUCCESS;
		
	}
}
