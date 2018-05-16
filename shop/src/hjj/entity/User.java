package hjj.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSessionActivationListener;

public class User extends Person implements Serializable,HttpSessionActivationListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
}
