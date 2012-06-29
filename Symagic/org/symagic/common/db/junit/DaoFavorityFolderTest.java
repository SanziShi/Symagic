package org.symagic.common.db.junit;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.func.DaoFavorityFolder;

public class DaoFavorityFolderTest extends TestCase{
	
	//初始化
	DaoFavorityFolder dff	= new DaoFavorityFolder();
	List<Integer> bookIDList = null;
		
	@Test
	public void testadd1(){
		assertEquals(true,dff.add("641567179@qq.com", 3));
	}
	@Test
	public void testadd2(){
		assertEquals(false,dff.add("641567179@qq.com", 0));
	}
	@Test
	public void testadd3(){
		assertEquals(false,dff.add("12345@qq.com", 3));
	}
	
	@Test
	public void testGet(){
		assertEquals(3,dff.get("641567179@qq.com", 1, 3).size());
	}
	@Test
	public void testGet2(){
		assertEquals(0,dff.get("12345@qq.com", 1, 3).size());
	}
	@Test
	public void testGet3(){
		assertEquals(0,dff.get("641567179@qq.com", 1, 0).size());
	}
	
	
	@Test
	public void testDelete(){
		bookIDList = new ArrayList<Integer>();
		assertEquals(false,dff.delete("641567179@qq.com", bookIDList));
	}
	@Test
	public void testDelete2(){
		bookIDList = new ArrayList<Integer>();
		bookIDList.add(1);
		bookIDList.add(3);
		assertEquals(true,dff.delete("641567179@qq.com", bookIDList));
	}
	@Test
	public void testDelete3(){
		bookIDList = new ArrayList<Integer>();
		bookIDList.add(123);
		bookIDList.add(234);
		assertEquals(false,dff.delete("641567179@qq.com", bookIDList));
	}
	@Test
	public void testDelete4(){
		bookIDList = new ArrayList<Integer>();
		bookIDList.add(1);
		bookIDList.add(3);
		assertEquals(false,dff.delete("12345@qq.com", bookIDList));
	}

}
