package com.douzon.bookmall.vo;

public class BookVO {
	private long bookNo;
	private String bookName;
	private long bookPrice;
	private long categoryNo;
	private String categoryName;
	
	public long getBookNo() {
		return bookNo;
	}
	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public long getPrice() {
		return bookPrice;
	}
	public void setPrice(long price) {
		this.bookPrice = price;
	}
	public long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return bookNo + ", " + bookName + ", " + categoryName  + ", " + bookPrice;
	}	
	
}
