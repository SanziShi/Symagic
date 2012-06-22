package org.symagic.common.db.func;

/**
 * 封装所有书籍搜索条件的类
 * @author wanran
 *
 */
public class BookRequire {
	// 内容待定
	
// 
	private String itemName = null;
	private String publisher = null;
	private String atalogID = null;
//	year(年);
	private String year	= null;
//	version;
	private Integer version = null;
//	up_searchPage(页数);
	private Integer upPage	= null;
//	low_searchPage;
	private Integer lowPage	= null;
//	binding;
	/**
	 * 书籍装帧 
	 * 选择（"精装"或"平装"）
	 */
	private String binding	= null;
//	booksize;
	private String folio	= null;
//	up_price;
	private Float upPrice	= null;
//	low_price;
	private Float lowPrice	= null;
//	discount(discount(0:0`10,1:10`30,2:30`50,3:50`100,4:>100））;
	private Integer discount	= null;
//	page（第几页)
	private Integer page	= null;
	
	private Integer lines	= null;
}
