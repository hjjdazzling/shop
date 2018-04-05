package hjj.entity;

import java.io.Serializable;

public class ShopCarRecord implements Serializable {
	private int id;
	private int number;
	private Good good;
	private User user;
	
	
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return id + " " + number + "商品:" + good + "用户:" + user;
	}
	
	
}
