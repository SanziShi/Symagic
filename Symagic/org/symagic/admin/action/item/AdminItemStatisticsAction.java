package org.symagic.admin.action.item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBookStatistics;
import org.symagic.common.db.func.BookStatisticsRequire;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.utilty.presentation.bean.StatisticBean;
import org.symagic.common.utilty.presentation.bean.TimeBean;


public class AdminItemStatisticsAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7238512637976603546L;

	private DaoBook daoBook;

	private TimeBean startTime;// :开始索引时间(year:年，month:月;day:日）;
	private TimeBean endTime;// :结束索引时间（year：年，month:月，day:日);
	private Integer catalogID;// :目录ID,
	private Integer limit;// (销售量下限）,
	private Integer page;// (请求的页面）
	
	private List<StatisticBean> items;

	private Integer totalPage;
	private Integer lines;

	@Override
	public String execute() throws Exception {
		
		if (page == null)
			page = 1;
		
		BookStatisticsRequire require = new BookStatisticsRequire();
		
		//建立require
		require.setCatalogid(catalogID);
		require.setLines(lines);
		require.setLowlimit(limit);
		require.setPage(page);
		//编码时间
		GregorianCalendar calendar = new GregorianCalendar(
				startTime.getYear(), startTime.getMonth(),
				startTime.getDay());
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		require.setStartTime(formater.format(calendar.getTime()));
		calendar =  new GregorianCalendar(
				endTime.getYear(), endTime.getMonth(),
				endTime.getDay());
		require.setEndTime(formater.format(calendar.getTime()));
		
		List<BeanBookStatistics> statistics = daoBook.getBookStatistics(require);
		
		items = new ArrayList<StatisticBean>();
		
		for( BeanBookStatistics statistic : statistics ){
			StatisticBean bean = new StatisticBean();
			bean.setItemID(statistic.getBookid());
			bean.setItemName(statistic.getBookname());
			bean.setSales(statistic.getTotalSaleAmount());
			bean.setTotalPrice(statistic.getTotalSaleRevenue());
			items.add(bean);
		}
		
		//分页？？？？！！！！！

		return super.execute();
	}


}
