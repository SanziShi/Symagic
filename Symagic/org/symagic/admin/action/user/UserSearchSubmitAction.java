package org.symagic.admin.action.user;

import java.util.GregorianCalendar;

import org.symagic.common.utilty.presentation.bean.TimeBean;

import com.opensymphony.xwork2.ActionSupport;

public class UserSearchSubmitAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -522419410711132164L;
	
	private String userName;//
	private String userLevel;//:(0:普通会员,1:银卡会员,2:金卡会员,3:白金会员；);
	private TimeBean startTime;//:开始索引时间(year:年，month:月;day:日）;
	private TimeBean endTime;//:结束索引时间（year：年，month:月，day:日); 
	
	private boolean validateResult;
	
	@Override
	public String execute() throws Exception {
		
		if( !validateResult )
			return ERROR;
		
//		UserRequire userRequire = new UserRequire();

		
		return super.execute();
	}
	@Override
	public void validate() {
		validateResult = true;
		
		if( userName == null || userLevel == null || startTime == null || endTime == null){
			validateResult = false;
		}
		
		GregorianCalendar startTimeCalendar = new GregorianCalendar(startTime.getYear(), startTime.getMonth(), startTime.getDay());
		GregorianCalendar endTimeCalendar = new GregorianCalendar( endTime.getYear(), endTime.getMonth(), endTime.getDay());
		if( startTimeCalendar.getTime().after(endTimeCalendar.getTime()) )
			validateResult = false;
		
		super.validate();
	}
	
}
