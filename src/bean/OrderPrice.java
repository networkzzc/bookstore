package bean;

import java.util.Date;

public class OrderPrice {
	private String orderCode;
	private float sumPrice;
	private String phone;
	private String address;
	private Date addTime;
	
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public float getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(float sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
	
	
}
