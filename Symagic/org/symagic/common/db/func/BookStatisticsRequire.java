package org.symagic.common.db.func;

import java.util.List;

public class BookStatisticsRequire {
	/**
	 * 起始时间 yyyy-MM-dd
	 * 默认 1500-01-01
	 */
	private String startTime	= "1500-01-01";
	
	/**
	 * 结束时间yyyy-MM-dd
	 */
	private String endTime	= "2100-01-01";
	
	/**
	 * 销售量下线
	 */
	private Integer	lowlimit	= 0;
	
	/**
	 * 目录ID
	 */
	private List<Integer> catalogidList	= null;
	
	/**
	 * 指明第几页
	 */
	private int	page	= 0;
	
	
	/**
	 * 指明一页显示多少条
	 */
	private int lines	= 0;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getLowlimit() {
		return lowlimit;
	}
	public void setLowlimit(Integer lowlimit) {
		this.lowlimit = lowlimit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLines() {
		return lines;
	}
	public void setLines(int lines) {
		this.lines = lines;
	}
	public List<Integer> getCatalogidList() {
		return catalogidList;
	}
	public void setCatalogidList(List<Integer> catalogidList) {
		this.catalogidList = catalogidList;
	}
	
	
}
