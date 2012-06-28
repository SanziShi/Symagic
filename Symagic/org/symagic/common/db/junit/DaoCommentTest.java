package org.symagic.common.db.junit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.symagic.common.db.func.DaoComment;

public class DaoCommentTest {
	
	//初始化
	DaoComment dc = new DaoComment();
	
	@Test
	public void testGetAllComment1() {
        assertEquals(3,dc.getAllComment(1, 3).size());		
	}
	@Test
	public void testGetAllComment2() {
        assertEquals(0,dc.getAllComment(1, 0).size());		
	}

	@Test
	public void testGetAverageRating1() {
        assertEquals(-1,dc.getAverageRating(1000));
	}
	@Test
	public void testGetAverageRating2() {
        assertEquals(1,dc.getAverageRating(11));
	}

}
