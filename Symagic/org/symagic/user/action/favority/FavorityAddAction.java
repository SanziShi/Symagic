package org.symagic.user.action.favority;

import org.symagic.common.db.func.DaoFavorityFolder;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class FavorityAddAction extends ActionSupport {
	
	private DaoFavorityFolder daoFavorityFolder;//对收藏夹的管理
	private Integer itemId; //添加商品的id
	 private boolean addResult;//添加是否成功
	 
	 @Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
			if(itemId==null||!UserSessionUtilty.isLogin()){
				addResult=false;
				return SUCCESS;
			}
			addResult=daoFavorityFolder.add(UserSessionUtilty.getUsername(), itemId);
			return super.execute();
		}
	public DaoFavorityFolder getDaoFavorityFolder() {
		return daoFavorityFolder;
	}
	public void setDaoFavorityFolder(DaoFavorityFolder daoFavorityFolder) {
		this.daoFavorityFolder = daoFavorityFolder;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public boolean isAddResult() {
		return addResult;
	}
	public void setAddResult(boolean addResult) {
		this.addResult = addResult;
	}

 
}
