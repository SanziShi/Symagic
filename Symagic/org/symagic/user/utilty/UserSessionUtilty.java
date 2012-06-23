package org.symagic.user.utilty;

import java.util.*;


import org.symagic.common.utilty.session.SessionUtilty;

import com.opensymphony.xwork2.ActionContext;

public class UserSessionUtilty extends SessionUtilty {

	/**
	 * 记录当前用户的登陆信息
	 * 
	 * @param userName
	 * @param nickname
	 */
	public static void logLogin(String userName, String nickname) {
		// 获得session
		Map<String, Object> session = ActionContext.getContext().getSession();

		session.put("username", userName);
		session.put("nickname", nickname);
	}

	/**
	 * 判断当前用户是否已经登陆
	 * 
	 * @return 当前用户是否已经登陆
	 */
	public static boolean isLogin() {
		// 获得session
		Map<String, Object> session = ActionContext.getContext().getSession();

		return session.containsKey("username")
				&& session.containsKey("nickname");

	}
	/**
	 * 当一个用户（包括游客和会员）有添加商品到购物车时，保存一个session状态下的购物车
	 */
	
	public static void addCart(int id,int number){
		//得到session
		Map<String ,Object> session=ActionContext.getContext().getSession();
		//得到购物车
		HashMap<Integer,Integer> cart=(HashMap<Integer,Integer>)session.get("cart");
		if(cart==null){
			cart=new HashMap<Integer,Integer>();
		}
		//添加物品和数量
		cart.put(id, number);
	}
	public static HashMap<Integer,Integer> getCart(){
		//购物车中没有东西时，返回null
		return (HashMap<Integer,Integer>)ActionContext.getContext().getSession().get("cart");
	}

}
