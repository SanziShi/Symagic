package org.symagic.user.action.favority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.symagic.common.db.func.DaoFavorityFolder;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class FavorityDeleteAction extends ActionSupport {

/**
	 * 
	 */
private static final long serialVersionUID = -6979940644982084061L;
//配置
private DaoFavorityFolder daoFavorityFolder;//对收藏夹的管理
//传入
private List<Integer>itemID;//批量删除的id组合
//传出
private boolean deleteResult;//全部删除结果

@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
if(itemID==null||!UserSessionUtilty.isLogin()){
	deleteResult=false;
	return SUCCESS;
}	
//数据库中删除
deleteResult=daoFavorityFolder.delete(UserSessionUtilty.getUsername(),itemID);
return super.execute();
}



public List<Integer> getItemID() {
	return itemID;
}

public void setItemID(List<Integer> itemID) {
	this.itemID = itemID;
}

public boolean isDeleteResult() {
	return deleteResult;
}
public void setDeleteResult(boolean deleteResult) {
	this.deleteResult = deleteResult;
}

public DaoFavorityFolder getDaoFavorityFolder() {
	return daoFavorityFolder;
}
public void setDaoFavorityFolder(DaoFavorityFolder daoFavorityFolder) {
	this.daoFavorityFolder = daoFavorityFolder;
}



}
