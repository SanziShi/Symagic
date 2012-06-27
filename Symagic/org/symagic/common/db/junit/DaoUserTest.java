package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.db.func.DaoUser;

public class DaoUserTest extends TestCase{


	//设置环境参数

//	@Ignore
//	@Test
//	public void testValidateUser() {
//		DaoUser du	= new DaoUser();
//		assertEquals(true, du.validateUser("单小熙1", "12"));
//	}
//	
//	@Ignore
//	@Test
//	public void testValidateUserName() {
//		DaoUser du	= new DaoUser();
//		assertEquals(false, du.validateUserName("单小熙"));
//	}
	
//	@Ignore
//	@Test
//	public void testAddUser() {
//		BeanUser user	= new BeanUser();
//		DaoUser du	= new DaoUser();
//		user.setUsername("单小熙1");
//		user.setNickname("小单车");
//		user.setPassword("12");
//		user.setQuestion("我的姓氏");
//		user.setAnswer("单小熙");
//		assertEquals(true, du.addUser(user));
//	}
//	
//	@Test
//	public void testUpdateNickname()
//	{
//		DaoUser du	= new DaoUser();
//		assertEquals(true, du.updateNickname("单小熙1", "单小车11"));
//	}
	
//	@Test
//	public void testGetScore()
//	{
//		DaoUser du = new DaoUser();
//		assertEquals(0, du.getScore("单小熙1"));
//	}
	
//	@Test
//	public void testUpdatePassword()
//	{
//		DaoUser du = new DaoUser();
//		assertEquals(true, du.updatePassword("单小熙1", "21", "12"));
//	}


//	DaoUser du = new DaoUser();
//	
//	@Test
//	public void testAddUser1(){
//		BeanUser user = new BeanUser();
//		//设置user的参数
//		user.setUsername("641567179@qq.com");
//		user.setNickname("Sanzi");
//		user.setPassword("123456");
//		user.setQuestion("who am I");
//		user.setAnswer("yusen");
//		user.setScore(100);
//		
//		assertEquals(true,du.addUser(user));
//	}
//	
//	@Test
//	public void testAddUser2(){	
//		BeanUser user = new BeanUser();
//		user.setUsername("641567179@qq.com");
//		assertEquals(false,du.addUser(user));
//	}
//	
//	@Test
//	public void testValidateUser1(){
//		assertEquals(true,du.validateUser("641567179@qq.com", "123456"));
//	}
//	
//	@Test
//	public void testValidateUser2(){
//		assertEquals(false,du.validateUser("123456@qq.com", "112233"));
//	}
//	
//	@Test
//	public void testValidateUser3(){
//		assertEquals(false,du.validateUser("641567179@qq.com", "112233"));
//	}
//	
//	@Test
//	public void testValidateUserName1(){
//		assertEquals(true,du.validateUserName("12345@qq.com"));
//	}
//	
//	@Test
//	public void testValidateUserName2(){
//		assertEquals(false,du.validateUserName("641567179@qq.com"));
//	}
//	
//	@Test
//	public void testUpdateNickname1(){
//		assertEquals(true,du.updateNickname("641567179@qq.com", "yusen"));
//	}
//	
//	@Test
//	public void testUpdateNickname2(){
//		assertEquals(false,du.updateNickname("12345@qq.com", "ssade"));
//	}
//	
//	@Test
//	public void testGetScore1(){
//		assertEquals(100,du.getScore("641567179@qq.com"));
//	}
//	
//	@Test
//	public void testGetScore2(){
//		assertEquals(-1,du.getScore("12345@qq.com"));
//	}
//	
//	@Test
//	public void testUpdatePassword1(){
//		assertEquals(false,du.updatePassword("12345qq.com", "135454", "54768768"));
//	}
//	
//	@Test
//	public void testUpdatePassword2(){
//		assertEquals(false,du.updatePassword("641567179@qq.com", "1234567", "23456789"));
//	}
//	
//	
//	@Test
//	public void testUpdatePassword3(){
//		assertEquals(true,du.updatePassword("641567179@qq.com", "1234567", "123456"));
//	}
}
