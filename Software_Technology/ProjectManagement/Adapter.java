package ProjectManagement;

import java.util.ArrayList;
import java.util.List;

public class Adapter implements IProject{

	private Project project;
	
	public Adapter(String name, String description, double rate) {
		if (name==null || description==null) {
			throw new NullPointerException("must not be null");
		}
		else if (name.isEmpty() || description.isEmpty() || rate<=0) {
			throw new IllegalArgumentException("arguments must not be empty");
		}
		project = new Project(name, description, rate);
	}
	
	public void setTask (Task newTask) {
		if (newTask==null) {
			throw new NullPointerException("newTask must not be null");
		}
		project.setTask(newTask);
	}
	
	public double getDuration() {
		return project.getDuration();
	}
	
	public long getTotalCost() {
		return project.getTotalCost();
	}
	
	public List<Deliverable> getDeliverables(){
		List<Deliverable> liste = new ArrayList<Deliverable>();
		for (List<Deliverable> deliverableList : project.allDeliverables().values()){
		    liste.addAll(deliverableList);
		}
		
		return liste;
	}
}
