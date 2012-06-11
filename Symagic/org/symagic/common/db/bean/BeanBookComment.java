package org.symagic.common.db.bean;

public class BeanBookComment {
	private int	userID	= 0;	// 用户ID
	private int bookID	= 0;	// 注释对应的Bookid
	private String	cotent	= "";	// 评论内容
	private String rating	= "";	// 书籍被评论等级,共五个等级可选,1、2、3、4、5
	private String commentDate	= "";	// 评论日期
	
}
