package org.symagic.admin.action.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanLevel;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.db.func.DaoLevel;
import org.symagic.common.db.func.DaoUser;
import org.symagic.common.db.func.UserRequire;
import org.symagic.common.utilty.presentation.bean.LevelBean;
import org.symagic.common.utilty.presentation.bean.TimeBean;

public class UserManagerAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -522419410711132164L;

	private String userName;//
	private Integer userLevel;// userLevel ID
	private TimeBean startTime;// :开始索引时间(year:年，month:月;day:日）;
	private TimeBean endTime;// :结束索引时间（year：年，month:月，day:日);
	private Integer page;
	private List<LevelBean> levelList;
	private Integer totalPage;
	
	private Integer lines;
	
	private DaoUser daoUser;
	private DaoLevel daoLevel;

	private boolean validateResult;

	@Override
	public String execute() throws Exception {

		if (!validateResult)
			return ERROR;
		
		List<BeanLevel> levels = daoLevel.getAll();
		
		levelList = new ArrayList<LevelBean>();
		
		for( BeanLevel bean : levels ){
			LevelBean level = new LevelBean();
			level.setHight(bean.getUpLimit());
			level.setLevelID(bean.getId());
			level.setLevelName(bean.getName());
			level.setLow(bean.getLowLimit());
			level.setScoreRate(bean.getRate());
			levelList.add(level);
		}

		UserRequire userRequire = new UserRequire();
		userRequire.setUsername(userName);
		userRequire.setUserLevel(userLevel);
		if (startTime != null) {
			GregorianCalendar calendar = new GregorianCalendar(
					startTime.getYear(), startTime.getMonth(),
					startTime.getDay());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			userRequire.setStartTime(formater.format(calendar.getTime()));
		}
		
		if (endTime != null) {
			GregorianCalendar calendar = new GregorianCalendar(
					endTime.getYear(), endTime.getMonth(),
					endTime.getDay());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			userRequire.setStartTime(formater.format(calendar.getTime()));
		}
		
		userRequire.setPage(page);
		
		//分页用的Number
		float number = daoUser.getSearchNum(userRequire);
		
		totalPage = (int) Math.ceil(number / lines);
		
		List<BeanUser> orderList = daoUser.search(userRequire);
		

		return super.execute();
	}

	@Override
	public void validate() {
		validateResult = true;

		if (startTime != null && endTime != null) {
			GregorianCalendar startTimeCalendar = new GregorianCalendar(
					startTime.getYear(), startTime.getMonth(),
					startTime.getDay());
			GregorianCalendar endTimeCalendar = new GregorianCalendar(
					endTime.getYear(), endTime.getMonth(), endTime.getDay());
			if (startTimeCalendar.getTime().after(endTimeCalendar.getTime()))
				validateResult = false;
		}

		super.validate();
	}

}
