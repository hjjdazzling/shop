package hjj.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSessionActivationListener;

public class Shop extends Person implements Serializable,HttpSessionActivationListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<Good> setGood = new HashSet<>();
	
	public Set<Good> getSetGood() {
		return setGood;
	}
	public void setSetGood(Set<Good> setGood) {
		this.setGood = setGood;
	}
}
