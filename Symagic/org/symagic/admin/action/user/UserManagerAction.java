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
import org.symagic.common.utilty.presentation.bean.UserBean;

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
	private List<UserBean> userList;
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
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		if (startTime != null) {
			GregorianCalendar calendar = new GregorianCalendar(
					startTime.getYear(), startTime.getMonth(),
					startTime.getDay());
			userRequire.setStartTime(formater.format(calendar.getTime()));
		}
		
		if (endTime != null) {
			GregorianCalendar calendar = new GregorianCalendar(
					endTime.getYear(), endTime.getMonth(),
					endTime.getDay());
			userRequire.setStartTime(formater.format(calendar.getTime()));
		}
		
		userRequire.setPage(page);
		
		//分页用的Number
		float number = daoUser.getSearchNum(userRequire);
		
		totalPage = (int) Math.ceil(number / lines);
		
		List<BeanUser> users = daoUser.search(userRequire);
		List<UserBean> userList = new ArrayList<UserBean>();
		
		for( BeanUser user : users ){
			UserBean bean = new UserBean();
			bean.setUserName(user.getUsername());
			//bean.setRegisterDate(user.get)(注册时间）
			BeanLevel level = daoLevel.judgeLevel(user.getScore());
			bean.setLevelName(level.getName());
			userList.add(bean);
		}
		

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public TimeBean getStartTime() {
		return startTime;
	}

	public void setStartTime(TimeBean startTime) {
		this.startTime = startTime;
	}

	public TimeBean getEndTime() {
		return endTime;
	}

	public void setEndTime(TimeBean endTime) {
		this.endTime = endTime;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public List<LevelBean> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<LevelBean> levelList) {
		this.levelList = levelList;
	}

	public List<UserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<UserBean> userList) {
		this.userList = userList;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getLines() {
		return lines;
	}

	public void setLines(Integer lines) {
		this.lines = lines;
	}

	public DaoUser getDaoUser() {
		return daoUser;
	}

	public void setDaoUser(DaoUser daoUser) {
		this.daoUser = daoUser;
	}

	public DaoLevel getDaoLevel() {
		return daoLevel;
	}

	public void setDaoLevel(DaoLevel daoLevel) {
		this.daoLevel = daoLevel;
	}

	public boolean isValidateResult() {
		return validateResult;
	}

	public void setValidateResult(boolean validateResult) {
		this.validateResult = validateResult;
	}

}
