package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.func.DaoCart;

public class DaoCartTest extends TestCase{

	//初始化
	DaoCart dc	= new DaoCart();
	@Test
	public void testAddBook1(){
		assertEquals(true,dc.addBook("641567179@qq.com", 1, 12));
	}
	@Test
	public void testAddBook2(){
		assertEquals(false,dc.addBook("1234@qq.com", 1, 12));
	}
	@Test
	public void testAddBook3(){
		assertEquals(false,dc.addBook("641567179@qq.com", 2355, 12));
	}
	@Test
	public void testAddBook4(){
		assertEquals(false,dc.addBook("641567179@qq.com", 1, 0));
	}
	@Test
	public void testAddBook5(){
		assertEquals(true,dc.addBook("641567179@qq.com", 2, 6));
	}
	@Test
	public void testAddBook6(){
		assertEquals(true,dc.addBook("641567179@qq.com", 4, 16));
	}
	@Test
	public void testAddBook7(){
		assertEquals(true,dc.addBook("641567179@qq.com", 5, 10));
	}
	
	@Test
	public void testGetBooks1(){
		assertEquals(5,dc.getBooks("641567179@qq.com").size());
	}
	@Test
	public void testGetBooks2(){
		assertEquals(0,dc.getBooks("1234@qq.com").size());
	}
	
	@Test
	public void DeleteBook1(){
		assertEquals(true,dc.deleteBook("641567179@qq.com", 4));
	}
	@Test
	public void DeleteBook2(){
		assertEquals(false,dc.deleteBook("641567179@qq.com", 1234));
	}
	@Test
	public void DeleteBook3(){
		assertEquals(false,dc.deleteBook("1234@qq.com", 1));
	}
	
	@Test
	public void testModifyBook1(){
		assertEquals(true,dc.modifyBook("641567179@qq.com", 2, 0));
	}
	@Test
	public void testModifyBook2(){
		assertEquals(false,dc.modifyBook("641567179@qq.com", 3, 0));
	}
	@Test
	public void testModifyBook3(){
		assertEquals(true,dc.modifyBook("641567179@qq.com", 5, 3));
	}
	@Test
	public void testModifyBook4(){
		assertEquals(false,dc.modifyBook("1234@qq.com", 1, 3));
	}
	
	@Test
	public void testClean1(){
		assertEquals(true,dc.clean("641567179@qq.com"));
	}
	@Test
	public void testClean2(){
		assertEquals(false,dc.clean("12345@qq.com"));
	}
}
