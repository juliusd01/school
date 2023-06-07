package pricingExam;

import java.util.List;

public class BestForCustomerPricing extends ComplexPricing{
	
	
	public BestForCustomerPricing (ISalePricing pricing) {
		super(pricing);
		if (pricing == null) {
			throw new NullPointerException("pricing must not be null");
		}
		
	}
	
	public void add(ISalePricing pricing) {
		if (pricing == null) {
			throw new NullPointerException("pricing must not be null!");
		}
		getPricings().add(pricing);
	}
	
	//Ausgabe des Endpreises mit dem meisten Rabatt -> Objekte, die mit createPricing erstellt wurden, vergleichen und kleinstes ausgeben
	@Override
	public long getTotal(Sale sale) {
		if (sale == null) {
			throw new NullPointerException("sale must not be null");
		}
		long smallest = 0;
		return smallest;
		
}
}
