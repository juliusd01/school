import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class AbstractUnit extends AbstractEnterpriseUnit{

	private SortedSet<AbstractEnterpriseUnit> childNodes;
	
	public AbstractUnit(String name) {
		super(name);
		if(name==null) {
			throw new NullPointerException();
		}
		else if (name.isEmpty()) {
			throw new IllegalArgumentException();
		}
		childNodes=new TreeSet<AbstractEnterpriseUnit>();
	}
	
	public boolean add(AbstractEnterpriseUnit childNode) {
		if ((childNode instanceof Team && (childNodes instanceof Holding || childNodes instanceof Company || childNodes instanceof Team)) || 
				(childNode instanceof Division && (childNodes instanceof Team || childNodes instanceof Division || childNodes instanceof Holding)) ||
				(childNode instanceof Company && !(childNodes instanceof Holding)) || childNode instanceof Holding) {
			throw new IllegalArgumentException();
		}
		return childNodes.add(childNode);
	}
	
	public boolean remove (AbstractEnterpriseUnit childNode) {
		return childNodes.remove(childNode);
	}
	
	public Set<AbstractEnterpriseUnit> getChildNodes(){
		return this.childNodes;
	}
}
