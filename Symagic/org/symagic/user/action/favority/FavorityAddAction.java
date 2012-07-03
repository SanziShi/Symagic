package org.symagic.user.action.favority;

import java.util.Iterator;
import java.util.List;

import org.symagic.common.db.func.DaoBook;
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
	private DaoBook daoBook;
	//传入
	private List<Integer>items;
	
	public void setItems(List<Integer> items) {
		this.items = items;
	}
	public List<Integer> getItems() {
		return items;
	}
	//传出
	private boolean addResult;//添加是否成功
	private String resultInfo;
	@Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
		 //商品不能为空且用户已登录
			if(items==null||!UserSessionUtilty.isLogin()){
				addResult=false;
				resultInfo="未登录 或商品不存在";
				return SUCCESS;
			}
			StringBuilder builder=new StringBuilder();
			addResult=true;
			boolean result;
			Iterator<Integer> it=items.iterator();
			while(it.hasNext()){
			Integer itemID=it.next();
			if(itemID==null){
				addResult=false;
				continue;
			}
			if(daoBook.getDetail(itemID)==null){
				result=false;
			}
			else{
			 result=daoFavorityFolder.add(UserSessionUtilty.getUsername(),itemID);
			 }
			 if(!result){
				   addResult=false;
				   if(builder.length()==0){
					   builder.append("编号为"+itemID);
				   }  
				   else{
					   builder.append(","+itemID);
				   }
			   }
			}
			 if(!addResult){
				  builder.append("未能添加到收藏夹");
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
	
	
	public String getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
	public DaoBook getDaoBook() {
		return daoBook;
	}
	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}
	
	
	
 
}
