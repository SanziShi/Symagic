package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.db.func.DaoUser;

public class DaoUserTest extends TestCase{

//	@Test
//	public void testValidateUser() {
//		DaoUser du	= new DaoUser();
//		assertEquals(true, du.validateUser("单小熙", "12"));
//	}
	
//	@Test
//	public void testValidateUserName() {
//		DaoUser du	= new DaoUser();
//		assertEquals(false, du.validateUserName("单小熙"));
//	}
	
	@Test
	public void testAddUser() {
		BeanUser user	= new BeanUser();
		DaoUser du	= new DaoUser();
		user.setUsername("单小熙1");
		user.setNickname("小单车");
		user.setPassword("12");
		user.setQuestion("我的姓氏");
		user.setAnswer("单小熙");
		assertEquals(true, du.addUser(user));
	}

}
