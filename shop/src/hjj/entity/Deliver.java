package hjj.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component(value="deliver")
public class Deliver {
	private int id;
	private String username;
	private String password;
	private String phone;
	private double money;
	private String date;
	private Set<DeliverRecord> setDeliverRecord = new HashSet<>();
	
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
	
	public Set<DeliverRecord> getSetDeliverRecord() {
		return setDeliverRecord;
	}
	public void setSetDeliverRecord(Set<DeliverRecord> setDeliverRecord) {
		this.setDeliverRecord = setDeliverRecord;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, phone, money);
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Deliver)) {
			return false;
		}
		Deliver deliver = (Deliver)obj; 
		
		if(id == deliver.getId() && username.equals(deliver.getUsername()) && password.equals(deliver.getPassword()) 
				&& phone.equals(deliver.getPhone()) && (Math.abs(money - deliver.getMoney()) < 1e-6)) {
			return true;
		} else {
			return false;
		}
		
	}
	@Override
	public String toString() {
		return username + " " + password + " " + money + " " + phone + " " + date;
	}
	
	
}
