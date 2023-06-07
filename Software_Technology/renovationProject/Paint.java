package renovationProject;

public class Paint extends Material {

	private static double limit = 0.02;
	private int numberOfCoats;
	private double squareMetersPerLiter;
	
	public Paint (String name, double price, int numberOfCoats, double squareMetersPerLiter) {
		super(name, price);
		
		if (name==null) {
			throw new NullPointerException();
		}
		
		else if (name.isEmpty() || price < 0 || numberOfCoats <= 0 || squareMetersPerLiter <= 0) {
			throw new IllegalArgumentException();
		}
		
		this.numberOfCoats=numberOfCoats;
		this.squareMetersPerLiter=squareMetersPerLiter;
	}
	
	public int getNumberOfCoats() {
		return this.numberOfCoats;
	}
	
	public double getSquareMetersPerLiter() {
		return this.squareMetersPerLiter;
	}
	
	//Just buckets of half a liter exist
	public int getMaterialRequirements (Surface surface) {
		double litersNeeded = (surface.getArea()*this.numberOfCoats)/this.squareMetersPerLiter;
		return (int) Math.ceil((litersNeeded-limit+0.0001)*2);
	}

}
