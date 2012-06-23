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
	
	private Integer scoreRate;
	
	private boolean validateResult;
	
	private DaoLevel daoLevel;

	@Override
	public String execute() throws Exception {
		
		if( !validateResult )
			return ERROR;
		BeanLevel level = new BeanLevel();
		level.setId(levelID);
		level.setLowLimit(low);
		//积分比率？？！！！
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
	
	
	
}
