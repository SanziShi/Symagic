package org.symagic.admin.action.level;

import java.util.List;

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

	private Float scoreRate;

	private boolean validateResult;

	private DaoLevel daoLevel;

	private String levelName;

	@Override
	public String execute() throws Exception {

		if (!validateResult)
			return ERROR;

		List<BeanLevel> levels = daoLevel.getAll();

		int modifyLevelIndex = -1;

		for (int i = 0; i < levels.size(); i++) {
			if (levels.get(i).getId() == levelID) {
				modifyLevelIndex = i;
				break;
			}
		}

		if (modifyLevelIndex == -1)
			return ERROR;

		BeanLevel level = levels.get(modifyLevelIndex);

		if (modifyLevelIndex == 0) {
			if (level.getUpLimit() <= low) {
				return ERROR;
			}
			level.setLowLimit(low);
			daoLevel.update(level);
		} else if (modifyLevelIndex == levels.size() - 1) {
			if (levels.get(modifyLevelIndex - 1).getLowLimit() >= low)
				return ERROR;
			level.setLowLimit(low);
			levels.get(modifyLevelIndex - 1).setUpLimit(low);
			daoLevel.update(level);
			daoLevel.update(levels.get(modifyLevelIndex - 1));
		} else {

			if (levels.get(modifyLevelIndex - 1).getLowLimit() >= low
					|| level.getUpLimit() <= low) {
				return ERROR;
			}
			level.setLowLimit(low);
			levels.get(modifyLevelIndex - 1).setUpLimit(low);
			daoLevel.update(level);
			daoLevel.update(levels.get(modifyLevelIndex - 1));
		}

		return super.execute();
	}

	@Override
	public void validate() {

		if (levelID == null || low == null || scoreRate == null)
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
