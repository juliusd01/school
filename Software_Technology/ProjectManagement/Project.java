package ProjectManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {
	
	private String name;
	private String description;
	private Task maintask;
	
	public Project (String name, String description, double rate) {
	
		if (name==null || description==null) {
			throw new NullPointerException("must not be null");
		}
		 
		else if (name.isEmpty() || description.isEmpty() || rate<=0) {
			throw new IllegalArgumentException("name must not be null");
		}
		this.name=name;
		this.description=description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setTask (Task newTask) {
		if (newTask==null) {
			throw new NullPointerException("newTask must not be null");
		}
		this.maintask = newTask;
	}
	
	public double getDuration() {
		return maintask.getTimeRequired();
	}
	
	public long getTotalCost() {
		return maintask.getCostEstimate();
	}
	
	public Map<LocalDate, List<Deliverable>> allDeliverables(){
		Map<LocalDate, List<Deliverable>> map = new HashMap<LocalDate, List<Deliverable>>();
		for (Deliverable deliverable : maintask.allDeliverables()){
			if(map.containsKey(deliverable.getDate())) {
				map.get(deliverable.getDate()).add(deliverable);
			}
			else {
				ArrayList<Deliverable> deli = new ArrayList<Deliverable>();
				deli.add(deliverable);
				map.put(deliverable.getDate(), deli);
			}
	
		}
		
		return map;
	}

}