package com.revature.beans;

public class Request {
	
	public Request() {
		super();
	
	}
	public Request(int rId, String type, double amount, String status) {
		super();
		this.rId = rId;
		this.type = type;
		this.amount = amount;
		this.status = status;
	}
	public Request(int rId, String type, double amount, String img, String status) {
		super();
		this.rId = rId;
		this.type = type;
		this.amount = amount;
		this.img = img;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Request [rId=" + rId + ", type=" + type + ", amount=" + amount + ", img=" + img + ", status=" + status
				+ "]";
	}
	private int rId;
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String type;
	private double amount;
	private String img;
	private String status;

}
