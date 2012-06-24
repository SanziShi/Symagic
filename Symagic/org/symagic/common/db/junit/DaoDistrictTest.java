package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.func.DaoDistrict;

/**
 * 对DaoDistrict类接口的测试类
 * @author wanran
 *
 */
public class DaoDistrictTest extends TestCase{

	@Test
	public void testGetDistrict() {
		DaoDistrict	dd	= new DaoDistrict();
		assertEquals("北京市", dd.getDistrict(null).get(0).getName());
	}

}
