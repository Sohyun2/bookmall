package com.douzon.bookmall.vo;

public class RequestVO {
	private long memeberNo;
	private String memberName;
	private String memberEmail;
	private long requstPrice;
	private String odAddress;
	
	public long getRequstPrice() {
		return requstPrice;
	}
	public void setRequstPrice(long requstPrice) {
		this.requstPrice = requstPrice;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getOdAddress() {
		return odAddress;
	}
	public void setOdAddress(String odAddress) {
		this.odAddress = odAddress;
	}
	public long getMemeberNo() {
		return memeberNo;
	}
	public void setMemeberNo(long memeberNo) {
		this.memeberNo = memeberNo;
	}	
}
