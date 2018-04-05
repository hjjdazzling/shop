package hjj.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Good implements Serializable {
	private int id;
	private String name;
	private String function;
	private double price;
	private Shop shop;
	private Set<ShopCarRecord> setRecord = new HashSet<>();
	private Set<PurchaseRecord> setPurchaseRecord = new HashSet<>();
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name, function, price);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;  
        if (!(obj instanceof Good)) {  
            return false;  
        }  
        Good good = (Good) obj;
        if(id == good.getId() && name.equals(good.getName()) && function.equals(good.getFunction()) && Math.abs(price-good.getPrice()) < 1e-6) {
        	return true;
        } else {
        	return false;
        }
	}
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
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return id + " " + name + " " +  function + " " + price;
	}
	
}
