package ProjectManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task extends ProjectItem{

	private List<ProjectItem> projectItems;
	
	public Task (String name, String details, double rate) {
		super(name, details, rate);
		
		if (name == null || details==null) {
			throw new NullPointerException("name and details must not be null");
		}
		else if (name.isEmpty() || details.isEmpty() || rate<=0) {
			throw new IllegalArgumentException("must not be empty");
		}
		
		projectItems = new ArrayList<ProjectItem>();
	}

	@Override
	public double getTimeRequired() {
		double time = 0;
		for (int i = 0; i<projectItems.size(); i++) {
			time+=projectItems.get(i).getTimeRequired();
		}
		return time;
	}

	@Override
	public long getMaterialCost() {
		long sum=0;
		for (int i = 0; i<projectItems.size(); i++) {
			sum+=projectItems.get(i).getMaterialCost();
		}
		return sum;
	}
	
	public void addProjectItem (ProjectItem pi) {
		if (pi==null) {
			throw new NullPointerException("ProjectItem must not be null");
		}
		projectItems.add(pi);
	}
	
	public void removeProjectItem (ProjectItem pi) {
		if (pi==null) {
			throw new NullPointerException("ProjectItem must not be null");
		}
		projectItems.remove(pi);
	}
	
	public List<Deliverable> allDeliverables(){
		List<Deliverable> myListe = new ArrayList<Deliverable>();
		for (ProjectItem pi : projectItems) {
			if (pi instanceof Deliverable) {
				myListe.add((Deliverable) pi);
			}
			else myListe.addAll(((Task) pi).allDeliverables());
		}
		return myListe;
	}

	public LocalDate getDate() {
		// TODO Auto-generated method stub
		return getDate();
	}
}