package org.symagic.common.db.func;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.db.bean.BeanUserAddr;

/**
 * 封装用户预存地址相关操作的类
 * @author wanran
 *
 */
public class DaoUserAddress {

	/**
	 * 添加用户预存地址
	 * @param userAddress	封装用户地址信息的对象
	 * @return		true 添加成功	false 添加失败
	 */
	public boolean addAddress(BeanUserAddr userAddress)
	{
		return false;
	}
	
	/**
	 * 删除指定用户预存地址
	 * @param addrID	要删除的地址ID
	 * @return	true 删除成功	false 删除失败
	 */
	public boolean deleteAddress(int addrID)
	{
		return false;
	}
	
	/**
	 * 修改指定地址，地址ID封装在userAddress对象中
	 * @param userAddress	封装者用户地址信息的对象
	 * @return	true 修改成功	false 修改失败
	 */
	public boolean modifyAddress(BeanUserAddr userAddress)
	{
		return true;
	}
	
	/**
	 * 获取指定用户的预存地址数目
	 * @param username	指定用户名，可唯一标示一个用户
	 * @return	>=0 查找成功	<0 查找失败
	 */
	public int getAddressNumber(String username)
	{
		return 0;
	}
	
	/**
	 * 获取指定用户的所有预存地址详细信息
	 * @param username	指定用户名
	 * @return	List<BeanUserAddr> 存储BeanUserAddr(封装的用户详细信息的类)的列表
	 */
	public List<BeanUserAddr> listAddress(String username)
	{
		return new ArrayList<BeanUserAddr>();
	}
	
	/**
	 * 获取指定用户的制定地址详细信息
	 * @param username	指定用户名
	 * @param addressID	属于此用户的某个地址ID
	 * @return	BeanUserAddr对象，封装者地址详细信息
	 */
	public BeanUserAddr getAddressDetail(String username, int addressID)
	{
		return new BeanUserAddr();
	}
	
	
}






