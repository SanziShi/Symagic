package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.func.DaoUser;
import org.symagic.common.db.func.UserRequire;

public class DaoUserTest extends TestCase{


	DaoUser du = new DaoUser();
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
	
/*	@Test
	public void testGetUser1(){
		assertEquals("sanzi",du.getUser("641567179@qq.com").getNickname());
	}
	@Test
	public void testGetUser2(){
		assertEquals(null,du.getUser("12345@qq.com"));
	}*/
	
/*	@Test
	public void testSearch()
	{
		DaoUser	du	= new DaoUser();
		UserRequire	req	= new UserRequire();
		req.setStartTime("2009-01-01");
		req.setPage(1);
		req.setLines(10);
		req.setEndTime("2012-12-01");
		req.setUsername("641567");
		assertEquals("641567179@qq.com", du.search(req).get(0).getUsername());
	}*/
	@Test
	public void testGetUserNum(){
		
		assertEquals(10,du.getUserNum());			
	}
	
	@Test
	public void testSearch1(){
		UserRequire	req	= new UserRequire();
		req.setUsername("641567179@qq.com");
		req.setUserLevel(1);
		req.setStartTime("2012-01-01");
		req.setEndTime("2013-01-01");
		
		req.setLines(3);
		req.setPage(1);
		
		assertEquals("641567179@qq.com",du.search(req).get(0).getUsername());
	}
	@Test
	public void testSearch2(){
		UserRequire	req	= new UserRequire();
		req.setUsername("12345@qq.com");
		req.setUserLevel(1);
		req.setStartTime("2012-01-01");
		req.setEndTime("2013-01-01");
		
		req.setLines(3);
		req.setPage(1);
		
		assertEquals(0,du.search(req).size());
	}
	@Test
	public void testSearch3(){
		UserRequire	req	= new UserRequire();
		
		req.setLines(3);
		req.setPage(1);
		
		assertEquals(3,du.search(req).size());
	}
	
	@Test
	public void testGetSearchNum1(){
		UserRequire	req	= new UserRequire();
		req.setUsername("641567179@qq.com");
		req.setUserLevel(1);
		req.setStartTime("2012-01-01");
		req.setEndTime("2013-01-01");
		
		assertEquals(1,du.getSearchNum(req));
	}
	@Test
	public void testGetSearchNum2(){
		UserRequire	req	= new UserRequire();
		req.setUsername("123456@qq.com");
		req.setUserLevel(1);
		req.setStartTime("2012-01-01");
		req.setEndTime("2013-01-01");
		
		assertEquals(0,du.getSearchNum(req));
	}
	@Test
	public void testGetSearchNum3(){
		UserRequire	req	= new UserRequire();
		
		assertEquals(10,du.getSearchNum(req));
	}
	
	@Test
	public void testUpdateScore(){
		assertEquals(true,du.updateScore(10000, "544921965@qq.com"));
	}
	@Test
	public void testUpdateScore2(){
		assertEquals(false,du.updateScore(10000, "123456@qq.com"));
	}
	
	@Test
	public void testUpdateQA1(){
		assertEquals(true,du.updateQA("544921965@qq.com", "我是谁", "刘光宗"));
	}
	@Test
	public void testUpdateQA2(){
		assertEquals(false,du.updateQA("123456@qq.com", "我是谁", "刘光宗"));
	}
}
