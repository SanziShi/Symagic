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
private DaoFavorityFolder daoFavorityFolder;//对收藏夹的管理

private int[]itemID;//批量删除的id组合

private boolean deleteResult;//全部删除结果

@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
if(itemID==null){
	deleteResult=false;
	return SUCCESS;
}	
//将id数组封装成一个list
List<Integer> list=new ArrayList<Integer>();
for(int index=0;index<itemID.length;index++){
	list.add(itemID[index]);
}
//数据库中删除
deleteResult=daoFavorityFolder.delete(UserSessionUtilty.getUsername(),list);
return super.execute();
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

public int[] getItemID() {
	return itemID;
}

public void setItemID(int[] itemID) {
	this.itemID = itemID;
}

}
