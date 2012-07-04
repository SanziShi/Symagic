package org.symagic.user.action.favority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
private List<Integer>items;//批量删除的id组合
//传出
private boolean deleteResult;//全部删除结果
private String resultInfo;
@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
if(!UserSessionUtilty.isLogin()){
	deleteResult=false;
	resultInfo="请登录后再对收藏夹进行操作";
	return SUCCESS;
}
if(items==null){
	deleteResult=false;
	resultInfo="输入数据非法";
	return SUCCESS;
}
//数据库中删除
StringBuilder builder=new StringBuilder();
deleteResult=true;
boolean result;
Iterator<Integer>index=items.iterator();
while(index.hasNext()){
	Integer itemID=index.next();
	
 result=daoFavorityFolder.delete(UserSessionUtilty.getUsername(),itemID);
 if(!result){
	   deleteResult=false;
	   if(builder.length()==0){
		   builder.append("编号为"+itemID);
	   }  
	   else{
		   builder.append(","+itemID);
	   }
   }
}
 if(!deleteResult){
	  builder.append("删除失败,可能没有在收藏夹中");
	  resultInfo=builder.toString();
   }
   else{
	   resultInfo="成功删除";
   }
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







public List<Integer> getItems() {
	return items;
}





public void setItems(List<Integer> items) {
	this.items = items;
}





public String getResultInfo() {
	return resultInfo;
}



public void setResultInfo(String resultInfo) {
	this.resultInfo = resultInfo;
}



}
