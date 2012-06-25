package org.symagic.common.service;

import org.symagic.common.db.bean.BeanComment;
import org.symagic.common.db.func.DaoBook;

public class ItemService {

	private DaoBook daoBook;//访问数据库中的书籍信息
	//增加用户对商品的评论
	public boolean addItemComment(BeanComment comment){
	 return daoBook.publishComment(comment);
}
}
