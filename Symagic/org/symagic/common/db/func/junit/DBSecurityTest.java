package org.symagic.common.db.func.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.db.func.DBSecurity;

public class DBSecurityTest extends TestCase{

	@Test
	public void testVerify() {
		BeanUser user = new BeanUser("shanxiaoxi", "");
		DBSecurity security = new DBSecurity();
		assertEquals(true, security.verify(user));
	}

}
