package org.symagic.common.db.bean;

/**
 * 书籍信息表
 * @author wanran
 *
 */
public class BeanBookInfo {
	private int bookId	= 0;	// 书籍id
	private String picture	= "";	// 书籍封面图片存储路径
	private String bookName	= "";	// 书籍名称
	private String author	= "";	// 书籍作者
	private String publisher = "";	// 书籍出版社
	private String publisherDate	= "";	// 书籍出版日期
	private int version	= 1;	// 书籍出版版次
	private int page	= 1;	// 书籍页数
	private String binding	= "";	// 书籍的装帧，有两个选择（精装，平装）默认为NULL
	//private String f
}
