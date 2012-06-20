package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;

public class DaoBookTest extends TestCase{
//	@Test
//	public void testGetInventory() {
//		DaoBook	db	= new DaoBook();
//		assertEquals(30, db.getInventory(6));
//	}

	@Test
	public void testAddBook() {
		BeanBook book = new BeanBook();
		DaoBook db	= new DaoBook();
		
		book.setAuthor("单小熙");
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
		book.setPublisherDate("2009-03-27");
		book.setVersion(5);
		
		assertEquals(true, db.addBook(book));
	}

//	@Test
//	public void testSetInventory() {
//		DaoBook	db	= new DaoBook();
//		assertEquals(true, db.setInventory(6, 10));
//	}

//	@Test
//	public void testGetDeatil() {
//		DaoBook	db	= new DaoBook();
//		assertEquals("2343234324", db.getDeatil(2).getIsbn());
//	}

//	@Test
//	public void testSearch() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetSearchRowNumber() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCommnetNumber() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetComment() {
//		fail("Not yet implemented");
//	}
	
//	@Test
//	public void testPublishDate() {
//		DaoBook	db	= new DaoBook();
//		BeanComment comment	= new BeanComment();
//		comment.setBookID(1);
//		comment.setUsername("单小熙1");
//		comment.setCotent("一本好书");
//		comment.setRating("5");
//		comment.setCommentDate("2012-06-19 16:57:30");
//		assertEquals(true, db.publishComment(comment));
//	}

}
