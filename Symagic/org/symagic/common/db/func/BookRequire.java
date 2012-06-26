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
	private String catalogID = null;
//	year(年);1:当前年 2:-1 3:-2 4:-3 5:-4以上
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getUpPage() {
		return upPage;
	}

	public void setUpPage(Integer upPage) {
		this.upPage = upPage;
	}

	public Integer getLowPage() {
		return lowPage;
	}

	public void setLowPage(Integer lowPage) {
		this.lowPage = lowPage;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Float getUpPrice() {
		return upPrice;
	}

	public void setUpPrice(Float upPrice) {
		this.upPrice = upPrice;
	}

	public Float getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Float lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLines() {
		return lines;
	}

	public void setLines(Integer lines) {
		this.lines = lines;
	}

	public String getCatalogID() {
		return catalogID;
	}

	public void setCatalogID(String catalogID) {
		this.catalogID = catalogID;
	}
}
