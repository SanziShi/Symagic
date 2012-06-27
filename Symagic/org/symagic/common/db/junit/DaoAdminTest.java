package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.func.DaoAdmin;


public class DaoAdminTest extends TestCase{

	//初始化
	DaoAdmin da	= new DaoAdmin();
	
	@Test
	public void testValidateAdmin(){
		assertEquals(true,da.validateAdmin("sanzi", "123456"));
	}
	@Test
	public void testValidateAdmin2(){
		assertEquals(false,da.validateAdmin("shi", "234556"));
	}

}
