package org.symagic.user.action.item;

import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.common.utilty.presentation.bean.ItemDetailBean;
import org.symagic.common.utilty.presentation.bean.ItemTinyBean;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class SubmitCommentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5119384687733985464L;

	// 传入参数
	private Integer itemID;// 评论的商品
	private String content;// 评论内容
	private Integer rating = 5;// 评分
	// 配置项
	private ItemService itemService;// 访问商品项
	// 传出
	private Boolean submitResult = false;// 评论是否成功
	private String resultInfo;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if (itemID == null || content == null || rating == null
				|| !UserSessionUtilty.isLogin() || rating < 0 || rating > 5) {
			resultInfo = "请求非法";
			return SUCCESS;
		}
		if(!itemService.isCommentAble(itemID)){
			resultInfo="不能评论";
			return SUCCESS;
		}
		BeanComment comment = new BeanComment();
		comment.setBookID(itemID);
		comment.setContent(content);
		comment.setRating(rating + "");
		comment.setUsername(UserSessionUtilty.getUsername());
		submitResult = itemService.addItemComment(comment);
		if (!submitResult) {
			resultInfo = "评论未能提交";
			return SUCCESS;
		} else {
			resultInfo = "评论提交成功";
		}
		// 得到书箱描述信息，为推荐系统提供信息，得到后期的推荐
		ItemDetailBean book = new ItemDetailBean();
		itemService.fillDetailBean(itemID, book);
		itemService.commentForRecomend(rating, itemID, book.getBookDesc());
		return super.execute();
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public Boolean getSubmitResult() {
		return submitResult;
	}

	public void setSubmitResult(Boolean submitResult) {
		this.submitResult = submitResult;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

}
