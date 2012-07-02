package org.symagic.admin.action.order;

import org.symagic.common.db.func.DaoAdmin;

public class OrderSuccessInterface extends AdminSuccessOrderAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6556123802319695117L;
	
	private String adminName;
	private String password;
	private DaoAdmin daoAdmin;
	@Override
	public String execute() throws Exception {
		
		if(!daoAdmin.validateAdmin(adminName, password)) return SUCCESS;
		
		return super.execute();
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DaoAdmin getDaoAdmin() {
		return daoAdmin;
	}
	public void setDaoAdmin(DaoAdmin daoAdmin) {
		this.daoAdmin = daoAdmin;
	}
	
	

}
