package org.symagic.user.utilty;

import java.util.*;


import org.symagic.common.utilty.session.SessionUtilty;

import com.opensymphony.xwork2.ActionContext;

public class UserSessionUtilty extends SessionUtilty {

	/**
	 * 记录用户登录失败次数
	 */
	public static void logLoginErrorTime(){
		Integer errorTimes=(Integer)ActionContext.getContext().getSession().get("errorTimes");
		if(errorTimes==null){
			ActionContext.getContext().getSession().put("errorTimes", 1);
		}
		else
		{
			errorTimes++;
			//更新错误次数
			ActionContext.getContext().getSession().put("errorTimes", errorTimes);
		}
	}
  /**
   * 取得用户登录失败次数
   */
	public static Integer getLoginErrorTime(){
	 return (Integer)ActionContext.getContext().getSession().get("errorTimes");	
	}
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
	 * @param id
	 * @param number
	 */
	
	public static boolean addToCart(int id,int number){
		//得到session
		Map<String ,Object> session=ActionContext.getContext().getSession();
		//得到购物车
		HashMap<Integer,Integer> cart=(HashMap<Integer,Integer>)session.get("cart");
		if(cart==null){
			cart=new HashMap<Integer,Integer>();
			session.put("cart", cart);
		}
		
		//添加物品和数量
		Integer value=cart.get(id);
		if(value==null)
		cart.put(id, number);
		else
		{
			cart.put(id, value+number);
		}
		return true;
	}
	/**
	 * 当用户将从购物车中删除商品时
	 */
	public static boolean deleteFromCart(int id){
		            //得到session
				 Map<String ,Object> session=ActionContext.getContext().getSession();
				//得到购物车
				HashMap<Integer,Integer> cart=(HashMap<Integer,Integer>)session.get("cart");
				cart.remove(id);
				return true;
	}
	
	/**
	 * 得到session中的购物车
	 * @return session中的购物车，
	 */
	public static HashMap<Integer,Integer> getCart(){
		//购物车中没有东西时，返回null
		//得到session
				Map<String ,Object> session=ActionContext.getContext().getSession();
				//得到购物车
				HashMap<Integer,Integer> cart=(HashMap<Integer,Integer>)session.get("cart");
				if(cart==null){
					cart=new HashMap<Integer,Integer>();
					session.put("cart", cart);
				}
				
		return cart;
	}
	
	public static int getCartNumber(){
		HashMap<Integer,Integer> cart=getCart();
		Collection<Integer> numbers=cart.values();
		int totalNumber=0;
		for(Iterator<Integer> number=numbers.iterator();number.hasNext();){
			totalNumber+=number.next();
		}
		return totalNumber;
	}
	
	
	public static String getNickname(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		return (String)session.get("nickname");
	}
	public static void setNickname(String nickname){
		ActionContext.getContext().getSession().put("nickname", nickname);
	}
	public static String getUsername(){
		return (String)ActionContext.getContext().getSession().get("username");
	}

}
