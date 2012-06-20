package org.symagic.admin.action.item;

import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.utility.presentation.bean.TimeBean;

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

	/**
	 * 
	 */
	private String ISBN;

	/**
	 * 
	 */
	private String itemName;

	/**
	 * 
	 */
	private String itemAuthor;

	/**
	 * 
	 */
	private String itemPublisher;

	/**
	 * 
	 */
	private TimeBean itemPublishingTime;

	/**
	 * 
	 */
	private Integer itemEdition;

	/**
	 * 
	 */
	private Integer itemPage;

	/**
	 * 
	 */
	private String itemBinding;

	/**
	 * 
	 */
	private Integer itemFormat;

	/**
	 * 
	 */
	private Float itmePublishingPrice;

	/**
	 * 
	 */
	private Float itemDiscount;

	/**
	 * 
	 */
	private Integer itemInventory;

	/**
	 * 
	 */
	private String itemBookDescribe;

	/**
	 * 
	 */
	private Integer itemBookClassID;

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

	/**
	 * 用于存放上传的图片
	 */
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
		
		//生成时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar calender = new GregorianCalendar(itemPublishingTime.getYear(), itemPublishingTime.getMonth(), itemPublishingTime.getDay());
		

		// 生成数据库所需信息
		BeanBook book = new BeanBook();

		book.setIsbn(ISBN);
		book.setBookName(itemName);
		book.setAuthor(itemAuthor);
		book.setPublisher(itemPublisher);
		book.setPublisherDate(dateFormat.format(calender.getTime()));
		book.setVersion(itemEdition);
		book.setPage(itemPage);
		book.setBinding(itemBinding);
		book.setFolio(itemFormat.toString());
		book.setMarketPrice(itmePublishingPrice);
//		book.setDiscount(itemDiscount);
//		
		

		return SUCCESS;
	}

	@Override
	public void validate() {

		//处理商品类别以外其他都不能为空
		if (ISBN == null || itemName == null || itemAuthor == null
				|| itemPublisher == null || itemPublishingTime == null
				|| itemEdition == null || itemPage == null
				|| itemBinding == null || itemFormat == null || itmePublishingPrice == null
				|| itemDiscount == null || itemInventory == null
				|| itemBookDescribe == null || picture == null) {
			formValidateResult = false;
		} else {
			formValidateResult = true;
		}

	}

	@Override
	public void setServletContext(ServletContext arg0) {
		context = arg0;
	}

}
