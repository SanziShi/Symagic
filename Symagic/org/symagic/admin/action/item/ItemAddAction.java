package org.symagic.admin.action.item;

import java.io.File;
import java.security.MessageDigest;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;
import org.symagic.common.db.bean.BeanBook;

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

	private String ISBN;

	private String itemName;

	private String itemAuthor;

	private String itemPublisher;

	private String itemPublishingTime;

	private Integer itemEdition;

	private Integer itemPage;

	private String itemBinding;

	private Integer itemFormat;

	private Double itemPrice;

	private Double itmePublishingPrice;

	private Double itemDiscount;

	private Integer itemStock;

	private String itemBookDescribe;

	private Integer itemBookClassID;

	private File picture;

	private String pictureFileName;

	private boolean formValidateResult;

	private ServletContext context;

	private String shopImageFileFolder;

	@Override
	public String execute() throws Exception {

		if (!formValidateResult)
			return ERROR;

		// 文件处理
		MessageDigest md5Encoder = MessageDigest.getInstance("MD5");

		// 生成文件名
		String fileName = new String(md5Encoder.digest(pictureFileName
				.getBytes("UTF-8")))
				+ pictureFileName.substring(pictureFileName.indexOf('.'));

		String fileFolder = context.getRealPath("/" + shopImageFileFolder);

		File destFile = new File(fileFolder, fileName);

		FileUtils.moveFile(picture, destFile);
		
		//生成数据库所需信息
		BeanBook book = new BeanBook();
		
		book.setIsbn(ISBN);
		book.setBookName(itemName);
		book.setAuthor(itemAuthor);
		book.setPublisher(itemPublisher);

		return SUCCESS;
	}

	@Override
	public void validate() {

		if (ISBN == null || itemName == null || itemAuthor == null
				|| itemPublisher == null || itemPublishingTime == null
				|| itemEdition == null || itemPage == null
				|| itemBinding == null || itemFormat == null
				|| itemPrice == null || itmePublishingPrice == null
				|| itemDiscount == null || itemStock == null
				|| itemBookDescribe == null || itemBookClassID == null
				|| picture == null) {
			formValidateResult = false;
		} else {
			formValidateResult = true;
		}

	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemAuthor() {
		return itemAuthor;
	}

	public void setItemAuthor(String itemAuthor) {
		this.itemAuthor = itemAuthor;
	}

	public String getItemPublisher() {
		return itemPublisher;
	}

	public void setItemPublisher(String itemPublisher) {
		this.itemPublisher = itemPublisher;
	}

	public String getItemPublishingTime() {
		return itemPublishingTime;
	}

	public void setItemPublishingTime(String itemPublishingTime) {
		this.itemPublishingTime = itemPublishingTime;
	}

	public Integer getItemEdition() {
		return itemEdition;
	}

	public void setItemEdition(Integer itemEdition) {
		this.itemEdition = itemEdition;
	}

	public Integer getItemPage() {
		return itemPage;
	}

	public void setItemPage(Integer itemPage) {
		this.itemPage = itemPage;
	}

	public String getItemBinding() {
		return itemBinding;
	}

	public void setItemBinding(String itemBinding) {
		this.itemBinding = itemBinding;
	}

	public Integer getItemFormat() {
		return itemFormat;
	}

	public void setItemFormat(Integer itemFormat) {
		this.itemFormat = itemFormat;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getItmePublishingPrice() {
		return itmePublishingPrice;
	}

	public void setItmePublishingPrice(Double itmePublishingPrice) {
		this.itmePublishingPrice = itmePublishingPrice;
	}

	public Double getItemDiscount() {
		return itemDiscount;
	}

	public void setItemDiscount(Double itemDiscount) {
		this.itemDiscount = itemDiscount;
	}

	public Integer getItemStock() {
		return itemStock;
	}

	public void setItemStock(Integer itemStock) {
		this.itemStock = itemStock;
	}

	public String getItemBookDescribe() {
		return itemBookDescribe;
	}

	public void setItemBookDescribe(String itemBookDescribe) {
		this.itemBookDescribe = itemBookDescribe;
	}

	public Integer getItemBookClassID() {
		return itemBookClassID;
	}

	public void setItemBookClassID(Integer itemBookClassID) {
		this.itemBookClassID = itemBookClassID;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		context = arg0;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public String getShopImageFileFolder() {
		return shopImageFileFolder;
	}

	public void setShopImageFileFolder(String shopImageFileFolder) {
		this.shopImageFileFolder = shopImageFileFolder;
	}

}
