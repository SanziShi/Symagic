package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.symagic.common.db.func.DaoAdmin;

/**
 * 对Admin表操作接口的单元测试
 * @author wanran
 *
 */
public class DaoAdminTest extends TestCase{
	public void testValidateAdmin()
	{
		DaoAdmin da	= new DaoAdmin();
		assertEquals(true, da.validateAdmin("管理员", "12"));
	}
}
