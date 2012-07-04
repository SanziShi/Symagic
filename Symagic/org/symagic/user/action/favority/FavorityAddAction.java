package org.symagic.user.action.favority;

import java.util.Iterator;
import java.util.List;

import org.symagic.common.db.bean.BeanBook;
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
			if(!UserSessionUtilty.isLogin()){
				addResult=false;
				resultInfo="请登录后再对收藏夹进行操作 ";
				return SUCCESS;
			}
			if(items==null){
				addResult=false;
				resultInfo="数据输入有误";
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
				builder.append("编号为"+itemID+"不存在\n");
				continue;
			}
			//商品是否存在
			BeanBook book=daoBook.getDetail(itemID);
			if(book==null){
				addResult=false;
				builder.append("编号为"+itemID+"不存在\n");
				continue;
			}
			else{
			 result=daoFavorityFolder.add(UserSessionUtilty.getUsername(),itemID);
			 }
			 if(!result){
				   addResult=false;
				   builder.append("书籍《"+book.getBookName()+"》已存在于收藏夹中\n");
			 }
		   }
			
			 if(!addResult){
				  
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
