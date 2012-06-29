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
	private Float marketPrice;
	private Float discout;
	private Integer inventory;// 库存
	private String bookDesc;
	private Boolean offline;// 是否上架
	private String catalogClassify;
	private Float price;
	private Float savePrice;
	private Integer averageRating;// 用户评分
	private String picturePath;
	private TimeBean parseTime; //用于时间解析结果
	private CatalogBean parseCatalog; //
	

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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Float getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Float marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Float getDiscout() {
		return discout;
	}

	public void setDiscout(Float discout) {
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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getSavePrice() {
		return savePrice;
	}

	public void setSavePrice(Float savePrice) {
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

}
