package com.douzon.bookmall.vo;

public class CartVO {
	private long cartNo;
	private long memberNo;
	private long bookNo;
	private String bookName;
	private long count;
	private int price;
	
	
	public long getCartNo() {
		return cartNo;
	}
	public void setCartNo(long cartNo) {
		this.cartNo = cartNo;
	}
	public long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}
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
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
//	@Override
//	public String toString() {
//		return "CartVO [memberNo=" + memberNo + ", bookName=" + bookName + ", count=" + count + ", price=" + price
//				+ "]";
//	}	
}
