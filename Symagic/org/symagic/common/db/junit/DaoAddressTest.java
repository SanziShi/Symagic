package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.bean.BeanAddress;
import org.symagic.common.db.func.DaoUserAddress;


public class DaoAddressTest extends TestCase{
	
	//初始化
	DaoUserAddress da	= new DaoUserAddress();
	
	/*@Test
	public void testAddAddress1(){
		BeanAddress	ba	= new BeanAddress();
		ba.setUsername("641567179@qq.com");
		ba.setMobilenum("15018713530");
		ba.setReceivername("yusen");
		ba.setAddrdetail("广州大学城");
		ba.setZipcode("213500");
		
		assertEquals(true,da.addAddress(ba));
	}
	@Test
	public void testAddAddress2(){
		BeanAddress	ba	= new BeanAddress();
		ba.setUsername(null);
		assertEquals(false,da.addAddress(ba));
	}
	
	@Test
	public void testModifyAddress1(){
		BeanAddress	ba	= new BeanAddress();
		ba.setUsername("641567179@qq.com");
		ba.setMobilenum("15018713530");
		ba.setReceivername("sanzi");
		ba.setAddrdetail("广州大学城");
		ba.setZipcode("213500");
		
		assertEquals(true,da.modifyAddress(ba));
	}
	@Test
	public void testModifyAddress2(){
		BeanAddress	ba	= new BeanAddress();
		ba.setReceivername(null);
		
		assertEquals(false,da.modifyAddress(ba));
	}
	
	@Test
	public void testGetAddressNumber1(){
		assertEquals(new Integer(1),da.getAddressNumber("641567179@qq.com"));
	}
	@Test
	public void testGetAddressNumber2(){
		assertEquals(new Integer(0),da.getAddressNumber("1234@qq.com"));
	}
	
	@Test
	public void testListAddress1(){
		assertEquals("广州大学城",da.listAddress("641567179@qq.com").get(0).getAddrdetail());
	}
	@Test
	public void testListAddress2(){
		assertEquals(0,da.listAddress("12345@qq.com").size());
	}
	
	@Test
	public void testGetAddressDetail(){
		assertEquals("广州大学城",da.getAddressDetail("641567179@qq.com", 5).getAddrdetail());
	}
	@Test
	public void testGetAddressDetail2(){
		assertEquals(null,da.getAddressDetail("641567179@qq.com", 123));
	}
	@Test
	public void testGetAddressDetail3(){
		assertEquals(null,da.getAddressDetail("12345@qq.com", 5));
	}
	
	@Test
	public void testDeleteAddress1(){
		assertEquals(true,da.deleteAddress(5));
	}
	@Test
	public void testDeleteAddress2(){
		assertEquals(false,da.deleteAddress(0));
	}*/
	

}
