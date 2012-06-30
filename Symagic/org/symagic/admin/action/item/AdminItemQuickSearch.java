package org.symagic.admin.action.item;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.BookRequire;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.ItemBean;

public class AdminItemQuickSearch extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7196520080355742015L;
	// 传入参数
	private String keyword; // 关键字
	private Integer catalogID; // 目录
	private Integer page = 1;// 分页显示

	private String errorHeader;
	private String errorSpecification;
	private ItemService itemService;// 访问书本的业务层
	private Integer sign;// 搜索标志，0为快速搜索
	private Integer lines;

	// 传出
	private Integer totalPage;
	private List<ItemBean> items;// 用于显示的商品列表

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		items = new ArrayList<ItemBean>();
		// 设置搜索的条件,两个条件 都为空时，返回所有商品
		BookRequire require = new BookRequire();

		setCatalog(require, catalogID);
		if (keyword != null && !keyword.trim().equals("")) {
			require.setAuthor(keyword);
			require.setItemName(keyword);
			require.setPublisher(keyword);
		}
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

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}

	private void setCatalog(BookRequire require, Integer catalogID) {
		if (catalogID == null || catalogID == 0)
			return;
		require.setCatalogIDList(itemService.getCatalogList(catalogID));
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<ItemBean> getItems() {
		return items;
	}

	public void setItems(List<ItemBean> items) {
		this.items = items;
	}

	public Integer getCatalogID() {
		return catalogID;
	}

	public void setCatalogID(Integer catalogID) {
		this.catalogID = catalogID;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public Integer getLines() {
		return lines;
	}

	public void setLines(Integer lines) {
		this.lines = lines;
	}

}
