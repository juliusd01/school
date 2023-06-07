package ProjectManagement;

public abstract class ProjectItem {
	
	private String name;
	private String details;
	private double rate;
	
	public ProjectItem (String name, String details, double rate) {
		if (name==null || details==null) {
			throw new NullPointerException("must not be null");
		}
		else if (name.isEmpty() || details.isEmpty() || rate<=0) {
			throw new IllegalArgumentException("name must not be null");
		}
		this.name=name;
		this.details=details;
		this.rate=rate;
	}
	
	public void setDetails (String newDetails) {
		this.details=newDetails;
	}
	//evtl Rundungsdifferenzen?
	public long getCostEstimate() {
		return Math.round(rate*getTimeRequired()) + this.getMaterialCost();
	}
	
	public abstract double getTimeRequired();
	
	public abstract long getMaterialCost();
}
