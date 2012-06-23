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

public class UserService {
	private DaoUser daoUser; //用于访问用户数据
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
	private BeanUser beanUser; //记录用户信息
	private DaoCart daoCart;//用于访问数据库中的购物车
public boolean isUsernameUnique(String username){
	return daoUser.validateUserName(username);
}
public boolean register(String name,String nickname,String password,String question,String answer){
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

public boolean login(String username,String password){
	boolean loginResult=daoUser.validateUser(username,password);
	//登录成功,更新购物车的信息,保存用户信息到session
	if(loginResult){
		
		//UserSessionUtilty.logLogin(username,daoUser.getNickName());//
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
	      //会员未登录前加商品到购物车中
	   HashMap<Integer,Integer> lastingItems=UserSessionUtilty.getCart();
	   BeanCart item;
	   //更新session中购物车的信息,将历史记录中当前session的购物车没有的项加入到session购物车中
	   for(Iterator<BeanCart> index=historyItems.iterator();index.hasNext();){
		   item=index.next();
		   if(!lastingItems.containsKey(item.getBookID())){
			   lastingItems.put(item.getBookID(), item.getAmount());
		   }
	   }
	   
	   //更新数据库中购物车中的信息，遍历session中的购物车（此时已是合并后的结果），加入新项，对于已有id的项，更新数量
	   Set<Integer> historyId=new HashSet();
	   BeanCart historyItem;
	   for(int index=0;index<historyItems.size();index++){
		  historyItem=historyItems.get(index);
		  historyId.add(historyItem.getBookID());
	   }
	   Set<Integer> itemIdSet=lastingItems.keySet();
		 for(Iterator key =itemIdSet.iterator();key.hasNext();){
			 int id=(Integer)key.next();
			 int number=lastingItems.get(id);
			 if(historyId.contains(id)){
				 daoCart.modifyBook(UserSessionUtilty.getSessionID(), id, number);
			 }
			 else{
				 daoCart.addBook(UserSessionUtilty.getSessionID(), id, number);
			 }
		 }
}

}
