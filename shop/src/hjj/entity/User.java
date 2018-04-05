package hjj.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
	private int id;
	private String username;
	private String password;
	private String phone;
	private double money;
	private String address;
	private String date;
	private Set<ShopCarRecord> setRecord = new HashSet<>();
	private Set<PurchaseRecord> setPurchaseRecord = new HashSet<>();
	
	public Set<PurchaseRecord> getSetPurchaseRecord() {
		return setPurchaseRecord;
	}
	public void setSetPurchaseRecord(Set<PurchaseRecord> setPurchaseRecord) {
		this.setPurchaseRecord = setPurchaseRecord;
	}
	public Set<ShopCarRecord> getSetRecord() {
		return setRecord;
	}
	public void setSetRecord(Set<ShopCarRecord> setRecord) {
		this.setRecord = setRecord;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + " " + username + " " + password + " " + money + " " + address  + " "+ date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
