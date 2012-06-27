package org.symagic.admin.action.comment;

import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.func.DaoComment;
import org.symagic.common.utilty.presentation.bean.CommentBean;

public class AdminEnterCommentManagerAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6278899703556693654L;
	
	private List<CommentBean> commentList;//(一维数组包含评论编号（commentID），用户名(username),打分(rating),内容(content),时间(date))；page（页数);catalog:一维数组（一个含有名字(name)和简介(desc)id,目录的ID，二级目录的一维数组（含有名字(name)和简介(desc)id,目录的ID，一个空的一维数组））；
	private Integer currentPage;//(当前页面)；
	private Integer totalPage;//（总页数）；
	private Integer page; //请求的页数
	private Integer lines; //每页的条数
	
	private DaoComment daoComment;
	
	@Override
	public String execute() throws Exception {
		
		//daoComment.get（获得评论总条数）
		
		daoComment.getAllComment(page, lines);
		
		return super.execute();
	}
	

}
