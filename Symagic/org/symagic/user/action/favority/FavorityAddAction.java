package org.symagic.user.action.favority;

import org.symagic.common.db.func.DaoFavorityFolder;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class FavorityAddAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8679639043977363919L;
	private DaoFavorityFolder daoFavorityFolder;//对收藏夹的管理
	private Integer itemID; //添加商品的id
	
	private boolean addResult;//添加是否成功
	 
	 @Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
			if(itemID==null||!UserSessionUtilty.isLogin()){
				addResult=false;
				return SUCCESS;
			}
			addResult=daoFavorityFolder.add(UserSessionUtilty.getUsername(), itemID);
			return super.execute();
		}
	public DaoFavorityFolder getDaoFavorityFolder() {
		return daoFavorityFolder;
	}
	public void setDaoFavorityFolder(DaoFavorityFolder daoFavorityFolder) {
		this.daoFavorityFolder = daoFavorityFolder;
	}
	
	public boolean isAddResult() {
		return addResult;
	}
	public void setAddResult(boolean addResult) {
		this.addResult = addResult;
	}
	 public Integer getItemID() {
			return itemID;
		}
		public void setItemID(Integer itemID) {
			this.itemID = itemID;
		}
 
}
