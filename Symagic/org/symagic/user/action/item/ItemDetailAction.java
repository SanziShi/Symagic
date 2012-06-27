package org.symagic.user.action.item;

import java.util.ArrayList;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.service.ItemService;
import org.symagic.common.utilty.presentation.bean.CommentBean;
import org.symagic.common.utilty.presentation.bean.ItemBean;

public class ItemDetailAction extends CatalogBase {
/**
	 * 
	 */
	private static final long serialVersionUID = 9054557857853861076L;
@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	book=itemService.getDetail(itemId);
	//商品是否下架
	if(book.getOffline().equals("下架")){
		offline=0;
	}
	else{
		offline=1;
	}
	price=book.getMarketPrice()*book.getDiscount();
	
	return super.execute();
	}
private int itemId;//商品id
private BeanBook book;//书籍详细信息
private ItemService itemService;//访问服务层
private float price;//商城价
private int offline;//是否下架

private String catalogClassify;//目录分类


private int averageRating;//平均得分
private ArrayList<CommentBean>commentList;//评论列表

private  ArrayList<ItemBean>recommendView;
private ArrayList<ItemBean>recommendBought;

}
