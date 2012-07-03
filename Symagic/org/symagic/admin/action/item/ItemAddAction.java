package org.symagic.admin.action.item;

import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.utilty.presentation.bean.TimeBean;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author hao
 * 
 */
public class ItemAddAction extends ActionSupport implements ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6680323098777982598L;
	
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
	private String pictureFileName;

	/**
	 * 
	 */
	private boolean formValidateResult;

	/**
	 * 
	 */
	private ServletContext context;

	private DaoBook daoBook;

	/**
	 * 用于存放上传的图片
	 */
	private String shopImageFileFolder;

	@Override
	public String execute() throws Exception {

		if (!formValidateResult)
			return ERROR;

		// 文件处理
		BeanBook book = new BeanBook();
		MessageDigest md5Encoder = MessageDigest.getInstance("MD5");

		// 生成文件名
		if (picture != null) {
			String fileName = new String(Hex.encodeHex(md5Encoder.digest(ISBN
					.getBytes("UTF-8"))))
					+ pictureFileName.substring(pictureFileName.indexOf('.'));

			String fileFolder = context.getRealPath("/" + shopImageFileFolder);

			book.setPicture("/" + shopImageFileFolder + "/" + fileName);

			File destFile = new File(fileFolder, fileName);

			FileUtils.copyFile(picture, destFile);
		}

		// 生成时间
		if (publishTime != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			GregorianCalendar calender = new GregorianCalendar(
					publishTime.getYear(), publishTime.getMonth() - 1,
					publishTime.getDay());
			book.setPublishDate(dateFormat.format(calender.getTime()));
		}

		// 生成数据库所需信息

		book.setIsbn(ISBN);
		book.setBookName(name);
		book.setAuthor(author);
		book.setPublisher(publisher);
		if (edition != null)
			book.setVersion(edition);
		if (page != null)
			book.setPage(page);
		book.setBinding(binding);
		if (size != null)
			book.setFolio(size.toString());
		book.setMarketPrice(marketPrice);
		book.setDiscount(discount);
		book.setInventory(inventory);
		book.setBookDesc(description);
		if (bookClassify != null && bookClassify != 0 )
			book.setCatalogID(bookClassify);
		book.setOffline("在架");

		itemID = daoBook.addBook(book);
		if( itemID == -1 ) return ERROR;

		return SUCCESS;
	}

	@Override
	public void validate() {

		// 处理商品类别以外其他都不能为空
		if (ISBN == null || ISBN.length() == 0 || name == null || name.length() == 0 || author == null || author.length() == 0 || publisher == null || publisher.length() == 0 ||
				binding == null || binding.length() == 0 || marketPrice == null || discount == null
				|| inventory == null || description == null || description.length() == 0 ) {
			formValidateResult = false;
		} else {
			formValidateResult = true;
		}

	}

	@Override
	public void setServletContext(ServletContext arg0) {
		context = arg0;
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

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public boolean isFormValidateResult() {
		return formValidateResult;
	}

	public void setFormValidateResult(boolean formValidateResult) {
		this.formValidateResult = formValidateResult;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

	public String getShopImageFileFolder() {
		return shopImageFileFolder;
	}

	public void setShopImageFileFolder(String shopImageFileFolder) {
		this.shopImageFileFolder = shopImageFileFolder;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

}
