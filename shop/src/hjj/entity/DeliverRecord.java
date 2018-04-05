package hjj.entity;

public class DeliverRecord {
	private int id;
	private String type;
	private String address;
	private String targetAddress;
	private PurchaseRecord purchaseRecord;
	private Deliver deliver;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PurchaseRecord getPurchaseRecord() {
		return purchaseRecord;
	}
	public void setPurchaseRecord(PurchaseRecord purchaseRecord) {
		this.purchaseRecord = purchaseRecord;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Deliver getDeliver() {
		return deliver;
	}
	public void setDeliver(Deliver deliver) {
		this.deliver = deliver;
	}
	public String getTargetAddress() {
		return targetAddress;
	}
	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}
	
	
}