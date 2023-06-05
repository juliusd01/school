import java.util.SortedSet;
import java.util.TreeSet;

public class StaffMember implements EnterpriseNode, java.lang.Comparable<StaffMember>{

	private String name;
	private String job;
	private SortedSet<StaffMember> directSubordinates;
	
	public StaffMember (String name, String job) {
		if(name==null || job==null) {
			throw new NullPointerException();
		}
		else if (name.isEmpty() || job.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.name=name;
		this.job=job;
		directSubordinates = new TreeSet<StaffMember>();
	}
	
	public String getJob() {
		return this.job;
	}
	
	public boolean addDirectSubordinate (StaffMember subordinate) {
		if(subordinate==null) {
			throw new NullPointerException();
		}
		return directSubordinates.add(subordinate);
	}
	
	public boolean removeDirectSubordinate (StaffMember subordinate) {
		if(subordinate==null) {
			throw new NullPointerException();
		}
		return directSubordinates.remove(subordinate);
	}
	
	public SortedSet<StaffMember> getDirectSubordinates(){
		return directSubordinates;
	}

	public String toString() {
		return this.name;
	}
	
	@Override
	public int compareTo(StaffMember staffi) {
		if(staffi==null) {
			throw new NullPointerException();
		}
		// TODO Auto-generated method stub
		return this.name.compareTo(staffi.toString());
	}

	@Override
	public String getName() {
		return this.name;
	}
	
}
