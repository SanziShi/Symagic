package org.symagic.admin.action.item;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.utilty.presentation.bean.TimeBean;

import com.opensymphony.xwork2.ActionSupport;

public class ItemModifySubmitAction extends ActionSupport implements
		ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6403625327693017658L;

	/**
	 * 
	 */
	private Integer itemID;

	/**
	 * 
	 */
	private String ISBN;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private String author;

	/**
	 * 
	 */
	private String publisher;

	/**
	 * 
	 */
	private TimeBean publishTime;

	/**
	 * 
	 */
	private Integer edition;

	/**
	 * 
	 */
	private Integer page;

	/**
	 * 
	 */
	private String binding;

	/**
	 * 
	 */
	private Integer size;

	/**
	 * 
	 */
	private Float marketPrice;

	/**
	 * 
	 */
	private Float discount;

	/**
	 * 
	 */
	private Integer inventory;

	/**
	 * 
	 */
	private String description;

	/**
	 * 
	 */
	private Integer bookClassify;

	/**
	 * 
	 */
	private File picture;

	/**
	 * 
	 */
	private boolean formValidateResult;

	private DaoBook daoBook;

	/**
	 * 
	 */
	private ServletContext context;

	/**
	 * 用于存放上传的图片
	 */
	private String shopImageFileFolder;

	@Override
	public void validate() {

		// 处理商品类别以外其他都不能为空
		if (itemID == null || ISBN == null || name == null || author == null
				|| publisher == null || publishTime == null || edition == null
				|| page == null || binding == null || size == null
				|| marketPrice == null || discount == null || inventory == null
				|| description == null || picture == null) {
			formValidateResult = false;
		} else {
			formValidateResult = true;
		}

	}

	@Override
	public String execute() throws Exception {

		if (!formValidateResult)
			return ERROR;

		BeanBook book = daoBook.getDetail(itemID);
		
		if( book == null ) return ERROR;

		// 文件处理
		String fileFolder = context.getRealPath("/" + shopImageFileFolder);
		File destFile = new File(fileFolder, book.getPicture().substring(
				book.getPicture().lastIndexOf('/') + 1));

		FileUtils.moveFile(picture, destFile);

		// 生成时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar calender = new GregorianCalendar(
				publishTime.getYear(), publishTime.getMonth(),
				publishTime.getDay());

		book.setIsbn(ISBN);
		book.setBookName(name);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setPublishDate(dateFormat.format(calender.getTime()));
		book.setVersion(edition);
		book.setPage(page);
		book.setBinding(binding);
		book.setFolio(size.toString());
		book.setMarketPrice(marketPrice);
		book.setDiscount(discount);
		book.setInventory(inventory);
		book.setBookDesc(description);
		book.setCatalogID(bookClassify);

		daoBook.modifyBook(book);

		return SUCCESS;
	}
	
	@Override
	public void setServletContext(ServletContext arg0) {
		context = arg0;

	}

	public String getShopImageFileFolder() {
		return shopImageFileFolder;
	}

	public void setShopImageFileFolder(String shopImageFileFolder) {
		this.shopImageFileFolder = shopImageFileFolder;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public TimeBean getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(TimeBean publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
		this.edition = edition;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Float getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Float marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getBookClassify() {
		return bookClassify;
	}

	public void setBookClassify(Integer bookClassify) {
		this.bookClassify = bookClassify;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public boolean isFormValidateResult() {
		return formValidateResult;
	}

	public void setFormValidateResult(boolean formValidateResult) {
		this.formValidateResult = formValidateResult;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}



}
