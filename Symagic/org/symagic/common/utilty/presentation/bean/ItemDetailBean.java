package org.symagic.common.utilty.presentation.bean;

public class ItemDetailBean {

	private String isbn;
	private String bookName;
	private String author;
	private String publisher;
	private String publishDate;
	private Integer version;
	private Integer page;// 书的页数
	private String binding;
	private String size;// 书的大小
	private String marketPrice;
	private String discout;
	private Integer inventory;// 库存
	private String bookDesc;
	private Boolean offline;// 是否上架
	private String catalogClassify;
	private String price;
	private String savePrice;
	private Integer averageRating;// 用户评分
	private String picturePath;
	private TimeBean parseTime; //用于时间解析结果
	private CatalogBean parseCatalog; //
	private Boolean commentAble;//能否评论

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public Boolean getCommentAble() {
		return commentAble;
	}

	public void setCommentAble(Boolean commentAble) {
		this.commentAble = commentAble;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getDiscout() {
		return discout;
	}

	public void setDiscout(String discout) {
		this.discout = discout;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public Boolean getOffline() {
		return offline;
	}

	public void setOffline(Boolean offline) {
		this.offline = offline;
	}

	public String getCatalogClassify() {
		return catalogClassify;
	}

	public void setCatalogClassify(String catalogClassify) {
		this.catalogClassify = catalogClassify;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSavePrice() {
		return savePrice;
	}

	public void setSavePrice(String savePrice) {
		this.savePrice = savePrice;
	}

	public Integer getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Integer averageRating) {
		this.averageRating = averageRating;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public TimeBean getParseTime() {
		return parseTime;
	}

	public void setParseTime(TimeBean parseTime) {
		this.parseTime = parseTime;
	}

	public CatalogBean getParseCatalog() {
		return parseCatalog;
	}

	public void setParseCatalog(CatalogBean parseCatalog) {
		this.parseCatalog = parseCatalog;
	}

}
