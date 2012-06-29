package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.db.func.BookRequire;
import org.symagic.common.db.func.DaoBook;

public class DaoBookTest extends TestCase{

	//初始化
	DaoBook db  = new DaoBook();



	
/*
	@Test
	public void testAddBook1(){
		BeanBook book1 = new BeanBook();
		//设置book1的参数
		book1.setAuthor("yusen");
		book1.setBinding("精装");
		book1.setBookDesc("good");
		book1.setBookName("what is that");
		book1.setDiscount(0.5f);
		book1.setFolio("16");
		book1.setInventory(20);
		book1.setIsbn("12kjkjdf24");
		book1.setMarketPrice(49.21f);
		book1.setOffline("在架");
		book1.setPage(400);
		book1.setPicture("picture3.jpg");
		book1.setPublisher("public department");
		book1.setPublishDate("2009-03-27");
		book1.setVersion(5);
		
		//断言判断
		assertEquals(true,db.addBook(book1));
		}
	
	@Test
	public void testAddBook2(){
		BeanBook book2 = new BeanBook();  //book2采用默认值
		book2.setOffline("在架吗");
		//判断
		assertEquals(false,db.addBook(book2));	
	}
	
	@Test
	public void test1SetInventory1(){
		assertEquals(true,db.setInventory(9, 30));
	}
	
	@Test
	public void test1SetInventory2(){
		assertEquals(false,db.setInventory(15275, 30));
	}
	

	}
	
	@Test
	public void testGetInventory1(){
		assertEquals(30,db.getInventory(9));
	}
	
	@Test
	public void testGetInventory2(){
		assertEquals(-1,db.getInventory(1234));
	}
	
	@Test
	public void testPublishComment1(){
		BeanComment bc1 = new BeanComment();
		//设置bc的参数
		bc1.setCommentDate("2011-09-27");
		bc1.setContent("good,good");
		bc1.setRating("5");
		bc1.setUsername("641567179@qq.com");
		bc1.setBookID(9);
		
		assertEquals(true,db.publishComment(bc1));
	}
	
	@Test
	public void testPublishComment2(){
		BeanComment bc2 = new BeanComment();//bc2采用默认的值
		bc2.setUsername("12345@qq.com");
		assertEquals(false,db.publishComment(bc2));
	}
	
	@Test
	public void testGetCommentNumber1(){
		assertEquals(1,db.getCommnetNumber(9));
	}
	
	@Test
	public void testGetCommentNumber2(){
		assertEquals(-1,db.getCommnetNumber(1234));
	}*/
	
	/*@Test
	public void testModifyBook()
	{
		BeanBook book = new BeanBook();
		DaoBook db	= new DaoBook();
		book.setBookId(1);
		book.setAuthor("单小熙1");
		book.setBinding("精装");
		book.setBookDesc("第三本好书");
		book.setBookName("计算机导论");
		book.setDiscount(0.5f);
		book.setFolio("16");
		book.setInventory(30);
		book.setIsbn("12kjkjdf23");
		book.setMarketPrice(49.21f);
		book.setOffline("在架");
		book.setPage(400);
		book.setPicture("picture3.jpg");
		book.setPublisher("电子工业出版社");
		book.setPublishDate("2009-03-27");
		book.setVersion(5);
		
		assertEquals(true, db.modifyBook(book));
		
	}
	
	@Test
	public void testGetProductNum()
	{
		DaoBook	db	= new DaoBook();
		assertEquals(1, db.getProductNum());
	}
	
	@Test
	public void testGetLatestBook()
	{
		DaoBook	db	= new DaoBook();
		assertEquals("计算机组成", db.getLatestBook().get(0).getBookName());
	}
	*/
	
	public void testSearch()
	{
		DaoBook	db	= new DaoBook();
		BookRequire req	= new BookRequire();
		req.setPage(1);
		req.setLines(10);
//		req.setYear("2012");
		req.setItemName("");
		assertEquals(1, db.search(1, req).get(0).getBookId());
	}
	
/*	@Test
	public void testGetProductNum1(){
		assertEquals(0,db.getProductNum());
	}
	
	@Test
	public void testGetLatestBook1(){
		assertEquals(0,db.getLatestBook().size());
	}
	
	@Test 
	public void testGetLatestBook2(){
		assertEquals(1,db.getLatestBook().size());
	}*/
	
//	@Test
//	public void testModifyBook1(){
//		BeanBook book = new BeanBook();
//		book.setBookId(29);
//		book.setAuthor("单小熙1");
//		book.setBinding("精装");
//		book.setBookDesc("第三本好书");
//		book.setBookName("计算机导论");
//		book.setDiscount(0.5f);
//		book.setFolio("16");
//		book.setInventory(30);
//		book.setIsbn("12kjkjdf23");
//		book.setMarketPrice(49.21f);
//		book.setOffline("在架");
//		book.setPage(400);
//		book.setPicture("picture3.jpg");
//		book.setPublisher("电子工业出版社");
//		book.setPublishDate("2009-03-27");
//		book.setVersion(5);
//		
//		assertEquals(true,db.modifyBook(book));
//	}
//	@Test
//	public void testModifyBook2(){
//		BeanBook book = new BeanBook();
//		book.setBookId(29);
//	
//		assertEquals(false,db.modifyBook(book));
//	}
//	
//	@Test
//	public void testGetProductNum2(){
//		assertEquals(30,db.getProductNum());
//	}
//	
//	@Test
//	public void testGetLatestBook3(){
//		assertEquals(10,db.getLatestBook().size());
//	}
//	
//	@Test
//	public void testDeleteBook1(){
//		assertEquals(true,db.deleteBook(29));
//	}
//	@Test
//	public void testDeleteBook2(){
//		assertEquals(false,db.deleteBook(50));
//	}
}
