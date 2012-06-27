package org.symagic.user.action.item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.func.BookRequire;

import org.symagic.common.service.ItemService;
import org.symagic.common.service.RecommandService;
import org.symagic.common.utilty.presentation.bean.ItemBean;
import org.symagic.common.utilty.presentation.bean.ItemDetailBean;



public class AdvanceSearchAction extends CatalogBase{
	private ItemService itemService;//访问书本的业务层
	private RecommandService recommendService;//推荐系统
	private List<ItemDetailBean>items;//用于显示的商品列表
	  private String name;//书本名字
	  private String publisher;//出版社
	  private int catalogId;//目录id
	  private  int publishTime;//出版时间
	  private int edition;//版次
	  private int searchPage;//书的页数范围
	  private int binding;//装帧
	  private int booksize;//书的大小
	  private int price;//书的价格
	  private int discount;//折扣
	  private String author;//作者
	  private int page;//分页显示
	  private List<ItemBean>recommend;//推荐商品
	  
	 


@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		items=new ArrayList<ItemDetailBean>();
		//设置搜索的条件
		BookRequire require=new BookRequire();
	    require.setItemName(name);
	    require.setPublisher(publisher);
	    require.setAtalogID(String.valueOf(catalogId));
	    setYear(require,publishTime);
	    require.setVersion(edition);
	    setPageNumber(require,searchPage);
	    setBinding(require,binding);
	    setPrice(require,price);
	    require.setDiscount(discount);
	      //搜索符合条件的商品
	    List<BeanBook> books=itemService.search(1, require);
	     //装饰成前台所需的信息
		itemService.decorate(books, items);
		//推荐商品
		/**
		 * 
		 */
		// List<Integer> bookIds=recommendService;
	  return super.execute();
	}
  
private void setBookSize(BookRequire require,int index){
	switch(index){
	case 1:require.setFolio("32");
	case 2:require.setFolio("16");
	case 3:require.setFolio("8");
	}
}
private void setPrice(BookRequire require,int index){
	switch(index){
	case 1:require.setUpPrice(10F);
			require.setLowPrice(0F);
			break;
	case 2:require.setUpPrice(30F);
			require.setLowPrice(10F);
			break;
	case 3:require.setUpPrice(50F);
			require.setLowPrice(30F);
			break;
	case 4: require.setUpPrice(100F);
			require.setLowPrice(50F);
			break;
	case 5:require.setUpPrice(Float.MAX_VALUE);
			require.setLowPrice(100F);
	default:break;
	}
}

private void setBinding(BookRequire require,int index){
	switch(index){
	case 1:require.setBinding("平装");
	       break;
	case 2: require.setBinding("精装");
			break;
   default:break;
	}
}
private void setYear(BookRequire require,int index){
	GregorianCalendar calender=new GregorianCalendar();
	int year=calender.get(Calendar.YEAR);
	switch(index){
	case 1:require.setYear(String.valueOf(year));break;
	case 2:require.setYear(String.valueOf(year-1));break;
	case 3:require.setYear(String.valueOf(year-2));break;
	case 4:require.setYear(String.valueOf(year-3));break;
	case 5:require.setYear(String.valueOf(year-4));break;
	default:break;
	}
}
private void setPageNumber(BookRequire require,int index){
	switch(index){
	case 1:require.setUpPage(200);
	 		require.setLowPage(0);
	 		break;
	case 2: require.setUpPage(400);
	  		require.setLowPage(200);
	  		break;
	case 3: require.setUpPage(600);
			require.setLowPage(400);
			break;
	case 4:require.setUpPage(Integer.MAX_VALUE);
		  require.setLowPage(600);
		  break;
	default:break;
	}
}

  
  
  public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public int getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(int publishTime) {
		this.publishTime = publishTime;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public int getSearchPage() {
		return searchPage;
	}

	public void setSearchPage(int searchPage) {
		this.searchPage = searchPage;
	}

	public int getBinding() {
		return binding;
	}

	public void setBinding(int binding) {
		this.binding = binding;
	}

	public int getBooksize() {
		return booksize;
	}

	public void setBooksize(int booksize) {
		this.booksize = booksize;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	

	
	
	 public int getDiscount() {
			return discount;
		}

		public void setDiscount(int discount) {
			this.discount = discount;
		}
		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

  
}
