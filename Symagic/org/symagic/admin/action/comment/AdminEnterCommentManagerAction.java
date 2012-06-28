package org.symagic.admin.action.comment;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.db.func.DaoComment;
import org.symagic.common.utilty.presentation.bean.CommentBean;

public class AdminEnterCommentManagerAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6278899703556693654L;
	
	private List<CommentBean> commentList;//(一维数组包含评论编号（commentID），用户名(username),打分(rating),内容(content),时间(date))；page（页数);catalog:一维数组（一个含有名字(name)和简介(desc)id,目录的ID，二级目录的一维数组（含有名字(name)和简介(desc)id,目录的ID，一个空的一维数组））；
	private Integer totalPage;//（总页数）；
	private Integer page; //请求的页数
	private Integer lines; //每页的条数
	
	private DaoComment daoComment;
	
	@Override
	public String execute() throws Exception {
		
		float rowNumber = daoComment.getAllCommmentRowNum();
		
		totalPage = (int)Math.floor(rowNumber / lines);
		
		List<BeanComment> comments = daoComment.getAllComment(page, lines);
		commentList = new ArrayList<CommentBean>();
		
		for( BeanComment comment : comments ){
			CommentBean bean = new CommentBean();
			bean.setUsername(comment.getUsername());
			bean.setContent(comment.getContent());
			bean.setDate(comment.getCommentDate());
			bean.setRating(comment.getRating());
			commentList.add(bean);
		}
		
		return super.execute();
	}

	public List<CommentBean> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentBean> commentList) {
		this.commentList = commentList;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLines() {
		return lines;
	}

	public void setLines(Integer lines) {
		this.lines = lines;
	}

	public DaoComment getDaoComment() {
		return daoComment;
	}

	public void setDaoComment(DaoComment daoComment) {
		this.daoComment = daoComment;
	}
	

}
