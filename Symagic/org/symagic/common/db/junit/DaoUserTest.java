package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.db.func.DaoUser;

public class DaoUserTest extends TestCase{

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
	
	@Test
	public void testGetUser()
	{
		DaoUser du	= new DaoUser();
		assertEquals(0, du.getUser("单小熙1").getScore());
	}

}
