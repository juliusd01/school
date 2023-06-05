import java.util.SortedSet;

public class Team extends AbstractEnterpriseUnit{

	private StaffMember teamLeader;
	private StaffMemberIterator staffIter;
	
	public Team (String name, StaffMember teamLeader) {
		super(name);
		if(name==null || teamLeader==null) {
			throw new NullPointerException();
		}
		else if (name.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.teamLeader=teamLeader;
		
	}
	
	public StaffMember getTeamLeader() {
		return this.teamLeader;
	}
	
	public SortedSet<StaffMember> getTeamMembers(){
		
	}
}
