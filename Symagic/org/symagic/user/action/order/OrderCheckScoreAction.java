package org.symagic.user.action.order;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoUser;
import org.symagic.user.utilty.UserSessionUtilty;

public class OrderCheckScoreAction extends CatalogBase{

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
}
