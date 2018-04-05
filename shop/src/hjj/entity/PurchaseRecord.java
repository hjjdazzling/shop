package hjj.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PurchaseRecord implements Serializable {
	private int id;
	private User user;
	private Good good;
	private int number;
	private String situation;
	private Set<DeliverRecord> setDeliverRecord = new HashSet<>();
	
	
	public Set<DeliverRecord> getSetDeliverRecord() {
		return setDeliverRecord;
	}
	public void setSetDeliverRecord(Set<DeliverRecord> setDeliverRecord) {
		this.setDeliverRecord = setDeliverRecord;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	@Override
	public String toString() {
		return user + " " + good + " " + number + "" + situation;
	}

	
	
}
