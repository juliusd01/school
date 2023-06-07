package renovationProject;

public class Flooring extends Material{
	
	private static double limit = 0.02;
	private double widthOfFlooring;
	
	public Flooring (String name, double price, double width) {
		super(name, price);
		this.widthOfFlooring=width;
	
		if (name==null) {
			throw new NullPointerException();
	}
	
		else if (name.isEmpty() || price < 0 || width <= 0) {
			throw new IllegalArgumentException();
	}

	}
	
	public double getWidth() {
		return this.widthOfFlooring;
	}
	
	public int getMaterialRequirements (Surface surface) {
		int panelsNeeded = (int) Math.ceil((surface.getArea()/this.widthOfFlooring)-limit+0.0001);
		return panelsNeeded;
	}
	
}
