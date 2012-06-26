package org.symagic.admin.action.level;

import org.symagic.common.db.bean.BeanLevel;
import org.symagic.common.db.func.DaoLevel;

import com.opensymphony.xwork2.ActionSupport;

public class AdminScoreRateSubmitAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2799351158227669070L;
	
	private Integer levelID;
	
	private Integer low;
	
	private Integer hight;
	
	private Float scoreRate;
	
	private boolean validateResult;
	
	private DaoLevel daoLevel;
	
	private String levelName;

	@Override
	public String execute() throws Exception {
		
		if( !validateResult )
			return ERROR;
		BeanLevel level = new BeanLevel();
		level.setId(levelID);
		level.setLowLimit(low);
		level.setRate(scoreRate);
		level.setUpLimit(hight);
		level.setName(levelName);
		daoLevel.update(level);
		
		return super.execute();
	}

	@Override
	public void validate() {
		
		if( levelID == null || low == null || scoreRate == null )
			validateResult = false;
		else
			validateResult = true;
		
		super.validate();
	}

	public Integer getLevelID() {
		return levelID;
	}

	public void setLevelID(Integer levelID) {
		this.levelID = levelID;
	}

	public Integer getLow() {
		return low;
	}

	public void setLow(Integer low) {
		this.low = low;
	}

	public Integer getHight() {
		return hight;
	}

	public void setHight(Integer hight) {
		this.hight = hight;
	}

	public Float getScoreRate() {
		return scoreRate;
	}

	public void setScoreRate(Float scoreRate) {
		this.scoreRate = scoreRate;
	}

	public boolean isValidateResult() {
		return validateResult;
	}

	public void setValidateResult(boolean validateResult) {
		this.validateResult = validateResult;
	}

	public DaoLevel getDaoLevel() {
		return daoLevel;
	}

	public void setDaoLevel(DaoLevel daoLevel) {
		this.daoLevel = daoLevel;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	
	
}
