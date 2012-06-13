package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.func.DaoUser;

public class DaoUserTest extends TestCase{

	@Test
	public void testValidateUser() {
		DaoUser du	= new DaoUser();
		assertEquals(true, du.validateUser("shanxiaoxi", "shanxiaoxi"));
	}
	
	@Test
	public void testValidateUserName() {
		DaoUser du	= new DaoUser();
		assertEquals(true, du.validateUserName("shanxiaoxi1"));
	}

}
