import java.util.Set;

public class StaffMemberIterator implements EnterpriseNodeIterator<EnterpriseNode> {

	private Set<StaffMember> allMembers;
	public StaffMemberIterator (Set<StaffMember> directSubordinates) {
		if(directSubordinates==null) {
			throw new NullPointerException();
		}
		this.allMembers=directSubordinates;
	}
	
	public void findSubordinatesRecursively (StaffMember m) {
		
	}

	public boolean hasNext() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
