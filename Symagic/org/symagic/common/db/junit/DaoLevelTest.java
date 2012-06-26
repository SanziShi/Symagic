package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.bean.BeanLevel;
import org.symagic.common.db.func.DaoLevel;

public class DaoLevelTest extends TestCase{
	//初始化
	DaoLevel dl	= new DaoLevel();
	@Test
	public void testAdd1(){
		BeanLevel	bl	= new BeanLevel();
		bl.setLowLimit(0);
		bl.setUpLimit(1000);
		bl.setName("一级");
		bl.setRate(0.8f);
		
		assertEquals(true,dl.add(bl));
	}
	@Test
	public void testAdd2(){
		BeanLevel	bl	= new BeanLevel();
		bl.setName(null);
		
		assertEquals(false,dl.add(bl));
	}
	
	@Test
	public void testUpdate1(){
		BeanLevel	bl	= new BeanLevel();
		bl.setLowLimit(0);
		bl.setUpLimit(1000);
		bl.setName("二级");
		bl.setRate(0.8f);
		bl.setId(2);
		
		assertEquals(true,dl.update(bl));
	}
	@Test
	public void testUpdate2(){
		BeanLevel	bl	= new BeanLevel();
		bl.setName(null);
		bl.setId(2);
		
		assertEquals(false,dl.update(bl));
	}
	
	@Test
	public void testJudgeLevel1(){
		assertEquals(new Integer(1000),dl.judgeLevel(100).getUpLimit());
	}
	@Test
	public void testJudgeLevel2(){
		assertEquals(null,dl.judgeLevel(-100));
	}
}
