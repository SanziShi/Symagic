package org.symagic.user.utilty;

import java.util.HashMap;
import java.util.Map;

import org.symagic.common.utilty.session.SessionUtilty;

import com.opensymphony.xwork2.ActionContext;

public class UserSessionUtilty extends SessionUtilty {

	/**
	 * 记录用户登录失败次数
	 */
	public static void logLoginErrorTime(){
		Integer errorTimes=(Integer)ActionContext.getContext().getSession().get("loginErrorTimes");
		if(errorTimes==null){
			ActionContext.getContext().getSession().put("loginErrorTimes", 0);
		}
		else
		{
			errorTimes++;
			//更新错误次数
			ActionContext.getContext().getSession().put("loginErrorTimes", errorTimes);
		}
	}
	
	//维护session中的数量
	public static void addTotalNumber(int number){
		Integer totalNumber=(Integer)ActionContext.getContext().getSession().get("totalNumber");
		if(totalNumber==null){
			ActionContext.getContext().getSession().put("totalNumber", number);
		}
		else{
			ActionContext.getContext().getSession().put("totalNumber", number+totalNumber);
		}
	}
  /**
   * 取得用户登录失败次数
   */
	public static Integer getLoginErrorTime(){
	 Map<String,Object> session=ActionContext.getContext().getSession();
	 Integer errorTimes=(Integer)session.get("loginErrorTimes");
	 if(errorTimes==null){
		 session.put("loginErrorTimes", 0);
		 return 0;
	 }
	 else
		 return errorTimes;
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

		session.put("userName", userName);
		session.put("nickname", nickname);
	    session.put("loginErrorTimes", 0);
	}


	/**
	 * 判断当前用户是否已经登陆
	 * 
	 * @return 当前用户是否已经登陆
	 */
	public static boolean isLogin() {
		// 获得session
		Map<String, Object> session = ActionContext.getContext().getSession();

		return session.containsKey("userName")
				&& session.containsKey("nickname");

	}
	
	/**
	 * 当一个用户（包括游客和会员）有添加商品到购物车时，保存一个session状态下的购物车
	 * @param id
	 * @param number
	 */
	
	public static boolean addToCart(Integer id,Integer number){
		//商品id是否存在
		
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
		{   //如果购物车已有相应 的商品
			cart.put(id, value+number);
		}
		//商品总数量增加
		UserSessionUtilty.addTotalNumber(number);
		
		return true;
	}
	/**
	 * 当用户将从购物车中删除商品时
	 */
	public static boolean deleteFromCart(int id){
		        HashMap<Integer,Integer> cart=getCart();
				Integer number=cart.get(id);
				if(number==null)return false;
				cart.remove(id);
				//商品总数量减少
				UserSessionUtilty.addTotalNumber(0-number);
				return true;
	}
	/**
	 * 用户修改购物车中商品的数量
	 */
	public static boolean modifyFromCart(int id,int number){
		
		
		//得到购物车
		HashMap<Integer,Integer> cart=getCart();
		Integer value=cart.get(id);
		if(value==null)return false;
		if(value!=0)
		cart.put(id, number);
		if(value==0)
			cart.remove(id);
		//改变session中的总数量
		UserSessionUtilty.addTotalNumber(number-value);
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
	
	public static Integer getTotalNumber(){
		return (Integer)ActionContext.getContext().getSession().get("totalNumber");
	}
	
	public static HashMap<Integer, Integer> getOrder(){
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		
		HashMap<Integer, Integer> order = (HashMap<Integer, Integer>)sessionMap.get("order");
		if(order == null){
			order = new HashMap<Integer, Integer>();
			sessionMap.put("order", order);
		}
		return order;
	}
	
	public static void setOrder(HashMap<Integer, Integer> order){
		Map<String, Object> session = ActionContext.getContext().getSession();
		
		if(session.get("order") != null){
			session.remove("order");			
		}
		session.put("order", order);
	}
	
	public static void removeOrder(){
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		
		if(sessionMap.get("order") != null)
			sessionMap.remove("order");
	}
	
	
	
	public static String getNickname(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		return (String)session.get("nickname");
	}
	public static void setNickname(String nickname){
		ActionContext.getContext().getSession().put("nickname", nickname);
	}
	public static String getUsername(){
		return (String)ActionContext.getContext().getSession().get("userName");
	}
	public static void cleanSession(){
		ActionContext.getContext().getSession().clear();
		ActionContext.getContext().getSession().put("totalNumber", 0);
	}
	
	public static void clearCart(){
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		
		if(sessionMap.get("cart") != null)
			sessionMap.remove("cart");
	}

}
