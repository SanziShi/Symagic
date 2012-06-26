package org.symagic.admin.action.comment;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoComment;
import org.symagic.common.utilty.presentation.bean.CommentBean;

public class AdminEnterCommentManagerAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6278899703556693654L;

	private List<CommentBean> commentList;// (一维数组包含评论编号（commentID），用户名(username),打分(rating),内容(content),时间(date))；page（页数);catalog:一维数组（一个含有名字(name)和简介(desc)id,目录的ID，二级目录的一维数组（含有名字(name)和简介(desc)id,目录的ID，一个空的一维数组））；
	private Integer currentPage;// (当前页面)；
	private Integer totalPage;// （总页数）；

	private DaoBook daoBook;
	private DaoComment daoComment;

	private Integer page;

	private Integer lines;

	@Override
	public String execute() throws Exception {
		if (page == null)
			page = 1;
		// totalPage = daoBook.get
		List<BeanComment> list = daoComment.getAllComment(page, lines);
		commentList = new ArrayList<CommentBean>();

		if (list != null) {
			for (BeanComment comment : list) {
				CommentBean bean = new CommentBean();
				bean.setUsername(comment.getUsername());
				bean.setContent(comment.getContent());
				bean.setDate(comment.getCommentDate());
				bean.setRating(Integer.parseInt(comment.getRating()));
				commentList.add(bean);
			}
		}

		return super.execute();
	}

}
