package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.bean.BeanCatalog;
import org.symagic.common.db.func.DaoCatalog;

public class DaoCatalogTest extends TestCase{

    //初始化
	DaoCatalog dc = new DaoCatalog();
	
//	@Test
//	public void testGetCatalog1(){
//		assertEquals(0,dc.getCatalog().size());
//	}
//	
//	@Test
//	public void testAddCatalog1(){
//		BeanCatalog bc = new BeanCatalog();
//		bc.setCatalogName("传记");
//		bc.setCatalogDesc("传记");
//		bc.setLevel("1");
//		bc.setUpID(0);
//		
//		assertEquals(true,dc.addCatalog(bc));
//	}
//	@Test
//	public void testAddCatalog2(){
//		BeanCatalog bc = new BeanCatalog();
//		bc.setCatalogName(null);
//
//		assertEquals(false,dc.addCatalog(bc));
//	}
//	@Test
//	public void testAddCatalog3(){
//		BeanCatalog bc = new BeanCatalog();
//		bc.setCatalogName("文学");
//		bc.setCatalogDesc("文学");
//		bc.setLevel("2");
//		bc.setUpID(5);
//		
//		assertEquals(true,dc.addCatalog(bc));
//	}
//
//	@Test
//	public void testGetCatalog2(){
//		assertEquals(2,dc.getCatalog().size());
//	}
//	
	@Test
	public void testModifyCatalog1(){
		BeanCatalog bc = new BeanCatalog();
		bc.setCatalogName("人文社会");
		bc.setCatalogDesc("wuyi");
		bc.setLevel("2");
		bc.setUpID(0);
		bc.setCatalogID(3);
		
		assertEquals(true,dc.modifyCatalog(bc));
	}
//	@Test
//	public void testModifyCatalog2(){
//		BeanCatalog bc = new BeanCatalog();
//		bc.setCatalogName(null);
//
//		assertEquals(false,dc.modifyCatalog(bc));
//	}
//	
//	@Test
//	public void testGetCatalogByID1(){
//		assertEquals("1",dc.getCatalogByID(1).getLevel());
//	}
//	@Test
//	public void testGetCatalogByID2(){
//		assertEquals(null,dc.getCatalogByID(123456));
//	}
//	
//	@Test
//	public void testDelete()
//	{
//		DaoCatalog	dc	= new DaoCatalog();
//		assertEquals(true, dc.deleteCatalog(2));
//	}
}
