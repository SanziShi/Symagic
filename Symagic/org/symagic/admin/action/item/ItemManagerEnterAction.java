package org.symagic.admin.action.item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.BookRequire;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.ItemBean;

/**
 * 控制进入商品管理页面的Action
 * 
 * @author hao
 * 
 */
public class ItemManagerEnterAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7734758591630202798L;

	private ItemService itemService;// 访问书本的业务层
	private int sign;// 搜索标志，1为高级搜索

	private Integer page = 1;// 分页显示
	private String author;// 作者
	private String name;// 书本名字
	private String publisher;// 出版社
	private Integer catalogID;// 目录id
	private Integer publishTime;// 出版时间
	private Integer edition;// 版次
	private Integer searchPage;// 书的页数范围
	private Integer binding;// 装帧
	private Integer booksize;// 书的大小
	private Integer price;// 书的价格
	private Integer discount;// 折扣

	// 配置项
	private Integer lines;
	private String errorHeader;
	private String errorSpecification;

	private Integer totalPage;
	private List<ItemBean> items;// 用于显示的商品列表

	public String getErrorHeader() {
		return errorHeader;
	}

	public void setErrorHeader(String errorHeader) {
		this.errorHeader = errorHeader;
	}

	public String getErrorSpecification() {
		return errorSpecification;
	}

	public void setErrorSpecification(String errorSpecification) {
		this.errorSpecification = errorSpecification;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		items = new ArrayList<ItemBean>();
		// 设置搜索的条件
		BookRequire require = new BookRequire();
		if (name != null)
			require.setItemName(name);
		if (publisher != null)
			require.setPublisher(publisher);
		require.setCatalogID(catalogID);
		setYear(require, publishTime);
		require.setVersion(edition);
		setPageNumber(require, searchPage);
		setBinding(require, binding);
		setBookSize(require, booksize);
		setPrice(require, price); 
		require.setDiscount(discount);
		if (author != null)
			require.setAuthor(author);
		require.setLines(lines);
		require.setPage(page);

		// 搜索符合条件的商品
		List<BeanBook> books = itemService.search(sign, require);
		if (books == null)
			return "error";
		int searchNumber = itemService.getSearchNum(sign, require);
		if (searchNumber == -1)
			return "error";
		totalPage = (searchNumber + lines - 1) / lines;
		// 装饰成前台所需的信息
		itemService.decorateForItem(books, items);
		return super.execute();
	}

	private void setBookSize(BookRequire require, Integer index) {
		if (index == null)
			return;
		switch (index) {
		case 1:
			require.setFolio("32");
		case 2:
			require.setFolio("16");
		case 3:
			require.setFolio("8");
		}
	}

	private void setPrice(BookRequire require, Integer index) {
		if (index == null)
			return;
		switch (index) {
		case 1:
			require.setUpPrice(10F);
			require.setLowPrice(0F);
			break;
		case 2:
			require.setUpPrice(30F);
			require.setLowPrice(10F);
			break;
		case 3:
			require.setUpPrice(50F);
			require.setLowPrice(30F);
			break;
		case 4:
			require.setUpPrice(100F);
			require.setLowPrice(50F);
			break;
		case 5:
			require.setUpPrice(Float.MAX_VALUE);
			require.setLowPrice(100F);
		default:
			break;
		}
	}

	private void setBinding(BookRequire require, Integer index) {
		if (index == null)
			return;
		switch (index) {
		case 1:
			require.setBinding("平装");
			break;
		case 2:
			require.setBinding("精装");
			break;
		default:
			break;
		}
	}

	private void setYear(BookRequire require, Integer index) {
		if (index == null)
			return;
		GregorianCalendar calender = new GregorianCalendar();
		int year = calender.get(Calendar.YEAR);
		switch (index) {
		case 1:
			require.setYear(String.valueOf(year));
			break;
		case 2:
			require.setYear(String.valueOf(year - 1));
			break;
		case 3:
			require.setYear(String.valueOf(year - 2));
			break;
		case 4:
			require.setYear(String.valueOf(year - 3));
			break;
		case 5:
			require.setYear(String.valueOf(year - 4));
			require.setBefore(true);
			break;
		default:
			break;
		}
	}

	private void setPageNumber(BookRequire require, Integer index) {
		if (index == null)
			return;
		switch (index) {
		case 1:
			require.setUpPage(200);
			require.setLowPage(0);
			break;
		case 2:
			require.setUpPage(400);
			require.setLowPage(200);
			break;
		case 3:
			require.setUpPage(600);
			require.setLowPage(400);
			break;
		case 4:
			require.setUpPage(Integer.MAX_VALUE);
			require.setLowPage(600);
			break;
		default:
			break;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(int publishTime) {
		this.publishTime = publishTime;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public int getSearchPage() {
		return searchPage;
	}

	public void setSearchPage(int searchPage) {
		this.searchPage = searchPage;
	}

	public int getBinding() {
		return binding;
	}

	public void setBinding(int binding) {
		this.binding = binding;
	}

	public int getBooksize() {
		return booksize;
	}

	public void setBooksize(int booksize) {
		this.booksize = booksize;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public List<ItemBean> getItems() {
		return items;
	}

	public void setItems(List<ItemBean> items) {
		this.items = items;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getLines() {
		return lines;
	}

	public void setLines(Integer lines) {
		this.lines = lines;
	}

	public Integer getCatalogID() {
		return catalogID;
	}

	public void setCatalogID(Integer catalogID) {
		this.catalogID = catalogID;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPublishTime(Integer publishTime) {
		this.publishTime = publishTime;
	}

	public void setEdition(Integer edition) {
		this.edition = edition;
	}

	public void setSearchPage(Integer searchPage) {
		this.searchPage = searchPage;
	}

	public void setBinding(Integer binding) {
		this.binding = binding;
	}

	public void setBooksize(Integer booksize) {
		this.booksize = booksize;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

}