package org.symagic.admin.action.item;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.func.DaoBook;
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

	private Integer totalPage;

	private boolean validateResult;

	@Override
	public String execute() throws Exception {
		
		if( !validateResult ) return ERROR;
		
		//daoBook.getBookStatistics(bookID)

		return super.execute();
	}

	@Override
	public void validate() {
		validateResult = true;
		if (startTime == null || endTime == null || catalogID == null
				|| limit == null)
			validateResult = false;

		if (page == null)
			page = 1;

		super.validate();
	}

}
