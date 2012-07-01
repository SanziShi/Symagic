package org.symagic.admin.action.item;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;

import com.opensymphony.xwork2.ActionSupport;

public class ItemDeleteAction extends ActionSupport implements
		ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4066697997147063158L;

	private Integer itemID;// 商品ID
	private Boolean deleteResult;

	private DaoBook daoBook;

	/**
	 * 用于存放上传的图片
	 */
	private String shopImageFileFolder;

	/**
	 * 
	 */
	private ServletContext context;

	@Override
	public String execute() throws Exception {

		deleteResult = false;

		if (itemID != null) {
			BeanBook book = daoBook.getDetail(itemID);
			if (book == null)
				return super.execute();

			String fileFolder = context.getRealPath("/" + shopImageFileFolder);
			File destFile = new File(fileFolder, book.getPicture().substring(
					book.getPicture().lastIndexOf('/') + 1));

			if (destFile.exists())
				FileUtils.forceDelete(destFile);

			if (!daoBook.deleteBook(itemID))
				return super.execute();
			deleteResult = true;

		}

		return super.execute();
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public Boolean getDeleteResult() {
		return deleteResult;
	}

	public void setDeleteResult(Boolean deleteResult) {
		this.deleteResult = deleteResult;
	}

	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		context = arg0;
	}

	public String getShopImageFileFolder() {
		return shopImageFileFolder;
	}

	public void setShopImageFileFolder(String shopImageFileFolder) {
		this.shopImageFileFolder = shopImageFileFolder;
	}

}
