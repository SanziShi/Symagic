package org.symagic.common.db.func;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanOrder;

/**
 * 封装与用户订单相关操作
 * @author wanran
 *
 */
public class DaoOrder {
	
	/**
	 * 根据给定条件搜索用户订单
	 * @param req	封装着所搜相关的条件
	 * @param username	指定用户名
	 * @return	List<BeanOrder>存储BeanOrder(封装订单详细信息类)的链表
	 */
	public List<BeanOrder> search(OrderRequire req, String username)
	{
		return new ArrayList<BeanOrder>();
	}
	
	/**
	 * 获取给定订单ID对应订单的详细信息
	 * @param orderID	订单ID
	 * @return	BeanOrder对象，封装着订单的详细信息
	 */
	public BeanOrder getOrderDetail(int orderID)
	{
		return new BeanOrder();
	}
	
	/**
	 * 获取给定条件下的订单条数
	 * @param req	搜索订单条件
	 * @param username	指定订单用户
	 * @return	int	用户订单数目
	 */
	public int getRowNumber(OrderRequire req, String username)
	{
		return 0;
	}
	
	/**
	 * 对给定用户添加订单
	 * @param username	指定添加订单用户
	 * @param order	存储订单详细信息的BeanOrder对象
	 * @return	true 添加成功	false 添加失败
	 */
	public boolean addOrder(String username, BeanOrder order)
	{
		return true;
	}
	
	/**
	 * 更新给定用户的制定订单
	 * @param username	指定要更新订单的用户
	 * @param order	保存更新订单中所有信息的BeanOrder对象
	 * @return	true 更新成功	false 更新失败
	 */
	public boolean updateOrder(String username, BeanOrder order)
	{
		return true;
	}
	
	/**
	 * 删除给定用户的指定订单
	 * @param username	指定要删除订单的用户
	 * @param orderID	指定要删除的订单
	 * @return	true 删除成功	false 删除失败
	 */
	public boolean deleteOrder(String username, int orderID)
	{
		return true;
	}
	
	/**
	 * 获取销售总量(所有)
	 * @return int 销售量
	 */
	public int getTotalSaleAmount()
	{
		return 10;
	}
	
	/**
	 * 获取销售总额
	 * @return float 销售总额
	 */
	public float getTotalSalesRevenue()
	{
		return 10.0f;
	}
	
	/**
	 * 获取订单总量
	 * @return	int 订单总量
	 */
	public int getTotalOrderAmount()
	{
		return 10;
	}
	
	/**
	 * 获取待审核订单数
	 * @return int 待审核订单数
	 */
	public int getUnauditedOrderAmount()
	{
		return 5;
	}
	
	/**
	 * 获取完成的订单数
	 * @return 完成订单数
	 */
	public int getFinishOrderAmount()
	{
		return 10;
	}
	
	/**
	 * 获取最近的10次订单详情
	 * @return	List<BeanOrder> 最近10次订单详细信息列表
	 */
	public List<BeanOrder> getLatestOrders()
	{
		List<BeanOrder> list = null;
		return list;
	}
	
	
}









