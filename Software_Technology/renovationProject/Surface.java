package renovationProject;

import java.util.HashMap;
import java.util.Map;

public class Surface extends RenovationObject{
	
	private double length;
	private double width;
	private Material selectedMaterial;
	
	public Surface (double length, double width) {
		
		if (length<=0 || width<=0) {
			throw new IllegalArgumentException("length and width must not be smaller or equal to zero");
		}
		
		this.length=length;
		this.width=width;
	}
	
	public void setMaterial(Material material) {
		
		this.selectedMaterial=material;
		
		if (selectedMaterial==null) {
			throw new NullPointerException();
		}		
	}
	
	public double getArea() {
		return this.width*this.length;
	}
	
	public double getLength() {
		return this.length;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public double getPrice() {
		return selectedMaterial.getPricePerUnit()*selectedMaterial.getMaterialRequirements(this);
	}
	
	//The integer specifies how many units of that Material are needed for the RenovationObject
	@Override
	public Map<String, Integer> addMaterialRequirements (Map<String, Integer> materials){
		
		if (materials.containsKey(null) || materials.containsValue(null)) {
			throw new NullPointerException("keys and values should not be null");
		}
		Map<String, Integer> requirements = new HashMap<String, Integer>();
		requirements.putAll(materials);
		return requirements;
	}
}
