package org.symagic.common.db.junit;

import junit.framework.TestCase;

import org.junit.Test;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.db.func.OrderRequire;
import org.symagic.common.db.pool.ConnectionPool;

public class DaoOrderTest extends TestCase{
		
	//初始化
	DaoOrder order	= new DaoOrder();
	
// 	@Test
//	public void testGetOrderDetail1(){
//		assertEquals("641567179@qq.com",order.getOrderDetail(1).getUsername());
//	}
//	
//	
//	@Test
//	public void testGetOrderDetail2(){
//		ConnectionPool.init();
//		assertEquals("123",order.getOrderDetail(16).getPhonenum());
//	}
//	
//	@Test
//	public void testAddOrder(){
//		BeanOrder bo= new BeanOrder();
//		BeanOrderDetail bod = new BeanOrderDetail();
//		
//		bo.setAddrDetail("guanghou");
//		bo.setOrderDate("2012-03-02 12:30:30");
//		bo.setReceiverName("shisanzi");
//		bo.setMobilenum("15018713530");
//		bo.setPhonenum("123");
//        bo.setZipcode("125000");
//        bo.setTotalprice(110f);
//        bo.setUsername("1037942090@qq.com");
//        bod.setBookId(1);
//        bod.setAmount(10);
//        bod.setBookName("曾国藩和他的湘军");
//        bod.setDiscount(0.69f);
//        bod.setIsbn("9787538296846");
//        bod.setMarketPrice(32.80f);
//        bod.setOrderId(11);
//        
//        bo.getList().add(bod);
//
//		assertEquals(false,order.addOrder(bo));
//	}
//	@Test
//	public void testAddOrder2(){		
//		BeanOrder bo= new BeanOrder();
//		
//		bo.setUsername("641567179@qq.com");
//		bo.setAddrDetail("guanghou");
//		bo.setOrderDate("2012-03-02");
//		bo.setReceiverName("shisanzi");
//		bo.setMobilenum("15018713530");
//        bo.setZipcode("125000");
//        bo.setTotalprice(110f);
//        
//		assertEquals(true,order.addOrder(bo));
//	}	
//	@Test
//	public void testAddOrder3(){
//		BeanOrder bo= new BeanOrder();
//		BeanOrderDetail bod = new BeanOrderDetail();
//		
//		bo.setUsername("641567179@qq.com");
//		bo.setAddrDetail("guanghou");
//		bo.setOrderDate("2011-12-02 01:39:13");
//		bo.setReceiverName("shisanzi");
//		bo.setMobilenum("15018713530");
//        bo.setZipcode("125000");
//        bo.setTotalprice(110f);
//        
//        bod.setBookId(1);
//        bod.setAmount(10);
//        bod.setBookName("曾国藩和他的湘军");
//        bod.setDiscount(0.69f);
//        bod.setIsbn("9787538296846");
//        bod.setMarketPrice(32.80f);
//        bod.setOrderId(26);
//        bod.setDiscount(0.69f);
//        
//        bo.getList().add(bod);
//        
//		assertEquals(true,order.addOrder(bo));
//	}
//	
//	@Test
//	public void testDeleteOrder1(){
//		assertEquals(true,order.deleteOrder(26));
//	}
//	@Test
//	public void testDeleteOrder2(){
//		assertEquals(false,order.deleteOrder(25));
//	}
//	
//	@Test
//	public void testGetTotalSaleAmount1(){
//		assertEquals(-1,order.getTotalSaleAmount());
//	}
//	@Test
//	public void testGetTotalSalesRevenue1(){
//		assertEquals(-1.0f,order.getTotalSalesRevenue());
//	}
//	@Test
//	public void testGetTotalOrderAmount1(){
//		assertEquals(-1,order.getTotalOrderAmount());
//	}
//	@Test
//	public void testGetUnauditedOrderAmount1(){
//		assertEquals(-1,order.getUnauditedOrderAmount());
//	}
//	@Test
//	public void testGetFinishOrderAmount1(){
//		assertEquals(-1,order.getFinishOrderAmount());
//	}
//	@Test
//	public void testGetLatestOrders1(){
//		assertEquals(0,order.getLatestOrders().size());
//	}
//	@Test
//	public void testGetTotalSaleAmount2(){
//		assertEquals(55,order.getTotalSaleAmount());
//	}
//	@Test
//	public void testGetTotalSalesRevenue2(){
//		assertEquals(3132.51f,order.getTotalSalesRevenue());
//	}
//	@Test
//	public void testGetTotalOrderAmount2(){
//		assertEquals(11,order.getTotalOrderAmount());
//	}
//	@Test
//	public void testGetUnauditedOrderAmount2(){
//		assertEquals(5,order.getUnauditedOrderAmount());
//	}
//	@Test
//	public void testGetFinishOrderAmount1(){
//		assertEquals(2,order.getFinishOrderAmount());
//	}
//	@Test
//	public void testGetLatestOrders1(){
//		assertEquals(10,order.getLatestOrders().size());
//	}
//	
//	@Test
//	public void testGetItemOrders1(){
//		assertEquals(0,order.getItemOrders(29, 1, 3).size());
//	}
//	@Test
//	public void testGetItemOrders2(){
//		assertEquals(2,order.getItemOrders(1, 1, 3).size());
//	}
//	
//	@Test
//	public void testSearch1(){
//		OrderRequire or = new OrderRequire();
//		or.setLines(3);
//		or.setPage(1);
//		or.setStartTime("2010-01-01");
//		or.setEndTime("2012-06-28");
//		or.setOrderState("2");
//		
//		assertEquals(2,order.search(or, "641567179@qq.com").size());
//	}
//	@Test
//	public void testSearch2(){
//		OrderRequire or = new OrderRequire();
//		or.setLines(3);
//		or.setPage(1);
//		or.setStartTime("2012-09-09");
//		
//		assertEquals(0,order.search(or, null).size());
//	}
//	@Test
//	public void testSearch3(){
//		OrderRequire or = new OrderRequire();
//		or.setLines(3);
//		or.setPage(1);
//		or.setStartTime("2010-01-01");
//		or.setEndTime("2012-06-28");
//		or.setOrderState("0");
//		
//		assertEquals(3,order.search(or, null).size());
//	}
//	
//	@Test
//	public void testGetRowNumber1(){
//		OrderRequire or = new OrderRequire();
//		or.setLines(3);
//		or.setPage(1);
//		or.setStartTime("2010-01-01");
//		or.setEndTime("2012-06-28");
//		or.setOrderState("2");
//		
//		assertEquals(2,order.getRowNumber(or, "641567179@qq.com"));
//	}
//	@Test
//	public void testGetRowNumber2(){
//		OrderRequire or = new OrderRequire();
//		or.setLines(3);
//		or.setPage(1);
//		or.setStartTime("2012-09-09");
//		
//		assertEquals(-1,order.getRowNumber(or, null));
//	}
//	@Test
//	public void testGetRowNumber3(){
//		OrderRequire or = new OrderRequire();
//		or.setLines(3);
//		or.setPage(1);
//		or.setStartTime("2010-01-01");
//		or.setEndTime("2012-06-28");
//		or.setOrderState("0");
//		
//		assertEquals(4,order.getRowNumber(or, null));
//	}
}
