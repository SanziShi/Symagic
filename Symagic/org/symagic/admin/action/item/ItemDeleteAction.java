package org.symagic.admin.action.item;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanOrderDetail;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoOrder;
import org.symagic.common.db.func.OrderRequire;

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
	private DaoOrder daoOrder;

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

			// 处理已下单状态的订单
			OrderRequire require = new OrderRequire();

			require.setOrderState("0");

			List<BeanOrder> orders = daoOrder.search(require, null);

			if (orders != null) {
				for (BeanOrder order : orders) {
					List<BeanOrderDetail> lists = order.getList();
					for (BeanOrderDetail detail : lists) {
						if (detail.getBookId() == itemID)
							return ERROR;
					}
				}
			}

			require.setOrderState("1");

			orders = daoOrder.search(require, null);

			if (orders != null) {
				for (BeanOrder order : orders) {
					List<BeanOrderDetail> lists = order.getList();
					for (BeanOrderDetail detail : lists) {
						if (detail.getBookId() == itemID)
							return ERROR;
					}
				}
			}

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
