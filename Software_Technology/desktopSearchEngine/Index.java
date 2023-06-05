import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index {

	private Map<String, List<Resource>> index;
	private List<Resource> liste;
	
	public Index() {
		index = new HashMap<String, List<Resource>>();
		liste = new ArrayList<Resource>();
	}
	
	public void add (Resource res) {
		liste.add(res);
		index.put(res.getName(), liste);
	}
	
	public List<Resource> getResources (String keyword){
		if (keyword==null) {
			throw new NullPointerException();
		}
		return index.getOrDefault(keyword, Collections.emptyList());
		
	}
}
