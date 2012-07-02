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
private Integer[]items;//批量删除的id组合
//传出
private boolean deleteResult;//全部删除结果
private String resultInfo;
@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
if(items==null||!UserSessionUtilty.isLogin()){
	deleteResult=false;
	return SUCCESS;
}	
//数据库中删除
StringBuilder builder=new StringBuilder();
deleteResult=true;
boolean result;
for(Integer index=0;index<items.length;index++){
 result=daoFavorityFolder.delete(UserSessionUtilty.getUsername(),items[index]);
 if(!result){
	   deleteResult=false;
	   if(builder.length()==0){
		   builder.append("编号为"+items[index]);
	   }  
	   else{
		   builder.append(","+items[index]);
	   }
   }
}
 if(!deleteResult){
	  builder.append("删除失败");
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



public Integer[] getItems() {
	return items;
}



public void setItems(Integer[] items) {
	this.items = items;
}



public String getResultInfo() {
	return resultInfo;
}



public void setResultInfo(String resultInfo) {
	this.resultInfo = resultInfo;
}



}
