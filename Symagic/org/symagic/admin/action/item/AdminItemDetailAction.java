package org.symagic.admin.action.item;

import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.ItemDetailBean;

public class AdminItemDetailAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2135206510537863273L;

	private Integer lines;
	private Integer totalPage;// 评论有多少页

	private Integer itemID;// 商品id
	private ItemDetailBean book;// 书籍详细信息
	private ItemService itemService;// 访问服务层
	private List<BeanComment> commentList;// 评论列表

	private String errorHeader;
	private String errorSpecification;

	private boolean validateResult;

	@Override
	public void validate() {

		validateResult = true;

		if (itemID == null)
			validateResult = false;

		super.validate();
	}

	@Override
	public String execute() throws Exception {

		if (!validateResult) {
			return INPUT;
		}

		book = new ItemDetailBean();
		if (!itemService.fillDetailBean(itemID, book)) {
			return ERROR;
		}
		book.setBookDesc(book.getBookDesc().replaceAll("\n", "<br>"));
		book.setSize(book.getSize()+"开");
		int commentNumber = itemService.getCommentNumber(itemID);
		if (commentNumber != -1)
			totalPage = (commentNumber + lines - 1) / lines;
		commentList = itemService.getCommentWithPage(itemID, 1, lines);

		return super.execute();

	}

	public Integer getLines() {
		return lines;
	}

	public void setLines(Integer lines) {
		this.lines = lines;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public ItemDetailBean getBook() {
		return book;
	}

	public void setBook(ItemDetailBean book) {
		this.book = book;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public List<BeanComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<BeanComment> commentList) {
		this.commentList = commentList;
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

}
