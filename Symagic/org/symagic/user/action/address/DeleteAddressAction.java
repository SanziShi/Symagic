package org.symagic.user.action.address;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.func.DaoUserAddress;

public class DeleteAddressAction extends CatalogBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4894601951077580773L;

	private Boolean deleteResult;
	
	private Integer addressID;
	
	private DaoUserAddress daoUserAddress;

	public DaoUserAddress getDaoUserAddress() {
		return daoUserAddress;
	}

	public void setDaoUserAddress(DaoUserAddress daoUserAddress) {
		this.daoUserAddress = daoUserAddress;
	}

	public Boolean getDeleteResult() {
		return deleteResult;
	}

	public void setDeleteResult(Boolean deleteResult) {
		this.deleteResult = deleteResult;
	}

	public Integer getAddressID() {
		return addressID;
	}

	public void setAddressID(Integer addressID) {
		this.addressID = addressID;
	}
	
	public String execute() throws Exception{
		super.execute();
		
		deleteResult = daoUserAddress.deleteAddress(addressID);
		return SUCCESS;
	}
	
}