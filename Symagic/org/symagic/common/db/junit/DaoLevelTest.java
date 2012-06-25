package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.bean.BeanLevel;
import org.symagic.common.db.func.DaoLevel;

public class DaoLevelTest extends TestCase{

//	@Test
//	public void testAdd() {
//		DaoLevel dl	= new DaoLevel();
//		BeanLevel	bl	= new BeanLevel();
//		bl.setName("普通会员");
//		bl.setLowLimit(0);
//		bl.setUpLimit(100);
//		bl.setRate(1.0f);
//		assertEquals(true, dl.add(bl));
//	}

//	@Test
//	public void testUpdate() {
//		DaoLevel dl	= new DaoLevel();
//		BeanLevel	bl	= new BeanLevel();
//		bl.setName("普通会员");
//		bl.setLowLimit(0);
//		bl.setUpLimit(200);
//		assertEquals(true, dl.update(bl));
//	}
	
	@Test
	public void testJudgeLevel()
	{
		DaoLevel dl	= new DaoLevel();
		assertEquals(1.0f, dl.judgeLevel(50).getRate());
	}

}
