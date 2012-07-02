package org.symagic.user.action.favority;

import org.symagic.common.db.func.DaoFavorityFolder;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class FavorityAddAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8679639043977363919L;
	//配置
	private DaoFavorityFolder daoFavorityFolder;//对收藏夹的管理
	//传入
	private Integer[]items;
	//传出
	private boolean addResult;//添加是否成功
	 private String resultInfo;
	@Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
		 //商品不能为空且用户已登录
			if(items==null||!UserSessionUtilty.isLogin()){
				addResult=false;
				return SUCCESS;
			}
			StringBuilder builder=new StringBuilder();
			addResult=true;
			boolean result;
			for(Integer index=0;index<items.length;index++){
			 result=daoFavorityFolder.add(UserSessionUtilty.getUsername(),items[index]);
			 if(!result){
				   addResult=false;
				   if(builder.length()==0){
					   builder.append("编号为"+items[index]);
				   }  
				   else{
					   builder.append(","+items[index]);
				   }
			   }
			}
			 if(!addResult){
				  builder.append("添加到收藏夹失败");
				  resultInfo=builder.toString();
			   }
			   else{
				   resultInfo="成功添加到收藏夹";
			   }
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
