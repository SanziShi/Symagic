package org.symagic.common.utilty.presentation.bean;

public class LevelBean {
	private Integer levelID;
	private Integer low;
	private String scoreRate;
	private Integer hight;
	private String levelName;
	public Integer getLevelID() {
		return levelID;
	}
	public void setLevelID(Integer levelID) {
		this.levelID = levelID;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Integer getLow() {
		return low;
	}
	public void setLow(Integer low) {
		this.low = low;
	}
	public String getScoreRate() {
		return scoreRate;
	}
	public void setScoreRate(String scoreRate) {
		this.scoreRate = scoreRate;
	}
	public Integer getHight() {
		return hight;
	}
	public void setHight(Integer hight) {
		this.hight = hight;
	}
	public String getLevelName() {
		return levelName;
	}
}
