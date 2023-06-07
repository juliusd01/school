package pricingExam;

public class BestForStorePricing extends ComplexPricing {

	public BestForStorePricing(ISalePricing pricing) {
		super(pricing);
		if (pricing == null) {
			throw new NullPointerException("pricing must not be null");
		}
		// TODO Auto-generated constructor stub
	}

	public void add(ISalePricing pricing) {
		if (pricing == null) {
			throw new NullPointerException("pricing must not be null!");
		}
		getPricings().add(pricing);
	}
	
	//Ausgabe des Endpreises mit weniger Rabatt
	@Override
	public long getTotal(Sale sale) {
		if (sale == null) {
			throw new NullPointerException("sale must not be null");
		}
	long highest = 0;
	return highest;
}

}
