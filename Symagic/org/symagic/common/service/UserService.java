package org.symagic.common.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



import org.symagic.common.db.bean.BeanCart;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.db.func.DaoCart;
import org.symagic.common.db.func.DaoUser;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionContext;

public class UserService {
	//配置项
	private DaoUser daoUser; //用于访问用户数据
	private BeanUser beanUser; //记录用户信息
	private DaoCart daoCart;//用于访问数据库中的购物车
    //用户名是否存在
	public boolean isUsernameUnique(String username){
	return daoUser.validateUserName(username);
    }
	
public boolean register(String name,String nickname,String password,String question,String answer){
	beanUser.setUsername(name);
	beanUser.setAnswer(answer);
	beanUser.setNickname(nickname);
	beanUser.setPassword(password);
	beanUser.setQuestion(question);
	beanUser.setScore(0);
	boolean result= daoUser.addUser(beanUser);
	//注册成功
	if(result){
		//保存用户的信息
		 UserSessionUtilty.logLogin(name, nickname);
		//将session购物车的东西保存到数据库中
		 HashMap<Integer,Integer> cart=UserSessionUtilty.getCart();
		 if(cart!=null){
			//将cart的每项记录保存到数据库中
			 Set<Integer> itemIdSet=cart.keySet();
			 for(Iterator key =itemIdSet.iterator();key.hasNext();){
				 int id=(Integer)key.next();
				 int number=cart.get(id);
				 daoCart.addBook(name, id, number);
			 }
		 }
	}
	return result;
}


/**
 * 
 * @param username
 * @param password
 * @return
 */
public boolean login(String username,String password){
	boolean loginResult=daoUser.validateUser(username,password);
	//登录成功,更新购物车的信息,保存用户信息到session
	if(loginResult){
	BeanUser user=daoUser.getUser(username);
	UserSessionUtilty.logLogin(username,user.getNickname());
	
	//同步数据库和session
		accordCart();
	}
	//登录失败，增加失败次数
	else{
		UserSessionUtilty.logLoginErrorTime();
	}
	return loginResult;
}
//数据库与session中关于购物车的信息保持 一致
private void accordCart(){
	 //会员数据库中已有关于购物车的信息
	   List<BeanCart> historyItems=daoCart.getBooks(UserSessionUtilty.getUsername());
	   Set<Integer> historyKey=new HashSet<Integer>();
	    //会员未登录前加商品到购物车中
	   HashMap<Integer,Integer> lastingItems=UserSessionUtilty.getCart();
	  //将session和数据库的信息进行整合
	   HashMap<Integer,Integer> allItems=new HashMap<Integer,Integer>();
	    //首先将session中加入 
	   allItems.putAll(lastingItems);
	   BeanCart item;
	   //将数据库中的信息进行整合
	   for(Iterator<BeanCart> index=historyItems.iterator();index.hasNext();){
		   item=index.next();
		   Integer number=allItems.get(item.getBookID());
		   //如果allItems没有此商品，则将数据库中的信息进入。如果有，则更改数量 
		   if(number==null){
			   allItems.put(item.getBookID(), item.getAmount());
			  }
		   else{
			   allItems.put(item.getBookID(), item.getAmount()+number);
		   }
		   historyKey.add(item.getBookID());
	   }
   //同步session和数据库中购物的信息
	  
	    
	   ActionContext.getContext().getSession().put("totalNumber", 0);
	     Set<Integer> allKeys=allItems.keySet(); //当前session中的数据
	     
	     boolean result;//数据库操作的结果
		 for(Iterator<Integer> key =allKeys.iterator();key.hasNext();){
			 int id=key.next();
			 int number=allItems.get(id);
			 result=true;
			 //如果历史中当前包含这个，则修改数量,不包含则加新记录
			 if(historyKey.contains(id)){
				 result=daoCart.modifyBook(UserSessionUtilty.getUsername(), id, number);
			 }
			 else{
				 result=daoCart.addBook(UserSessionUtilty.getUsername(), id, number);
			 }
			 
			 if(result){

				 lastingItems.put(id, number);
				 UserSessionUtilty.addTotalNumber(number);
			 }
		 }
}

public DaoUser getDaoUser() {
	return daoUser;
}
public void setDaoUser(DaoUser daoUser) {
	this.daoUser = daoUser;
}
public BeanUser getBeanUser() {
	return beanUser;
}
public void setBeanUser(BeanUser beanUser) {
	this.beanUser = beanUser;
}
public DaoCart getDaoCart() {
	return daoCart;
}
public void setDaoCart(DaoCart daoCart) {
	this.daoCart = daoCart;
}

}
