package org.symagic.common.db.func;

import junit.framework.TestCase;

import org.junit.Test;

public class UtilTest extends TestCase{

	@Test
	public void testGetMD5() {
		String md5 = Util.getMD5(new String("shanxiaoxi").getBytes());
		System.out.println(md5);
		assertEquals("4549e4f4b863d17b77c1c97f9ddb66e3"	, md5);
	}

}
