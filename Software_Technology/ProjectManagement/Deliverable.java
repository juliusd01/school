package ProjectManagement;

import java.time.LocalDate;

public class Deliverable extends ProjectItem {

	private long materialCost;
	private double productionTime;
	private LocalDate date;
	
	public Deliverable (String name, String details, double rate, long materialCost, double productionTime, LocalDate date) {
		super(name, details, rate);
		
		if (name==null || details==null || date==null) {
			throw new NullPointerException("name must not be null");
		}
		
		if (rate<0 || materialCost<0 || productionTime<=0 || name.isEmpty() || details.isEmpty()) {
			throw new IllegalArgumentException("name and details must not be empty");
		}
		this.materialCost=materialCost;
		this.productionTime=productionTime;
		this.date=date;
		
	}
	
	public double getTimeRequired() {
		return productionTime;
	}
	
	public long getMaterialCost() {
		return materialCost;
	}
	
	public LocalDate getDate() {
		return date;
	}
}
