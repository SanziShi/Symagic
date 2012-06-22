package org.symagic.common.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;



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


}
