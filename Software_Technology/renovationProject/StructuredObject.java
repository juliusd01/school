package renovationProject;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class StructuredObject extends RenovationObject {
	
	Set<RenovationObject> parts;
	
	public StructuredObject() {
		parts = new HashSet<RenovationObject>();
	}
	
	public void add(RenovationObject renovationObject) {
		
		if (renovationObject==null) {
			throw new NullPointerException();
		}
		if(!parts.contains(renovationObject))
		parts.add(renovationObject);
	}
	
	public double getPrice() {
		double price = 0;
		for (RenovationObject object : parts) {
			price+=object.getPrice();
		}
		return price;
	}
	
	//The integer specifies how many units of that Material are needed for the RenovationObject
	@Override
	public Map<String, Integer> addMaterialRequirements (Map<String, Integer> materials){
		
		if (materials.containsKey(null) || materials.containsValue(null)) {
			throw new NullPointerException();
		}
		Map<String, Integer> requirements = new TreeMap<String, Integer>();
		requirements.putAll(materials);
		return requirements;
	}
}
