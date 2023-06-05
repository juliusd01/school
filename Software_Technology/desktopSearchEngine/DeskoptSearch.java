import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesktopSearch {

	private Map<String, ResourceType> types;
	
	public DesktopSearch () {
		types = new HashMap<String, ResourceType>();
	}
	
	public void registerType (String extension, ResourceType type) {
		if (extension==null || type==null) {
			throw new NullPointerException();
		}
		if (extension.isEmpty()) {
			throw new IllegalArgumentException();
		}
		types.put(extension, type);
	}
	
	public ResourceType getType (String extension) {
		return types.get(extension);
	}
	
	public void unregisterType (String extension) {
		if (extension==null) {
			throw new NullPointerException();
		}
		if (extension.isEmpty()) {
			throw new IllegalArgumentException();
		}
		types.remove(extension);
	}
	
	public void registerResource (Resource res) {
		types.put(res.getName(), res.getType());
	}
	
	public List<Resource> processRequest (String request){
		List<Resource> liste = new ArrayList<Resource>();
		
		return liste;
	}
}
