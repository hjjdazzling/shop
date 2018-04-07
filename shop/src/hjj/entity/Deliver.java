package hjj.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component(value="deliver")
public class Deliver extends Person {
	private Set<DeliverRecord> setDeliverRecord = new HashSet<>();
	
	
	public Set<DeliverRecord> getSetDeliverRecord() {
		return setDeliverRecord;
	}
	public void setSetDeliverRecord(Set<DeliverRecord> setDeliverRecord) {
		this.setDeliverRecord = setDeliverRecord;
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
