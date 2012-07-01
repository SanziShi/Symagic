package org.symagic.user.action.order;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.db.func.DaoUser;
import org.symagic.user.utilty.UserSessionUtilty;

public class OrderCheckScoreAction extends CatalogBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6603324986655777287L;

	private Integer score;
	
	private Boolean checkResult;
	
	private DaoUser daoUser;
	
	public String execute() throws Exception{
		BeanUser user = daoUser.getUser(UserSessionUtilty.getUsername());
		if(user == null)
			checkResult = false;
		else{
			score = user.getScore();
			checkResult = true;
		}
		return SUCCESS;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(Boolean checkResult) {
		this.checkResult = checkResult;
	}

	public DaoUser getDaoUser() {
		return daoUser;
	}

	public void setDaoUser(DaoUser daoUser) {
		this.daoUser = daoUser;
	}
}
