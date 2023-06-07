package pricingExam;
import java.util.*;

public abstract class ComplexPricing implements ISalePricing{
	
	private List<ISalePricing> pricings;
	
	public ComplexPricing (ISalePricing pricing) {
		if (pricing == null) {
			throw new NullPointerException("pricing must not be null");
		}
		pricings  = new ArrayList<ISalePricing>();
	}
	//wahrscheinlich soll neues pricing hinzugef�gt werden
	public void add (ISalePricing pricing) {
		if (pricing == null) {
			throw new NullPointerException("pricing must not be null");
		}
		pricings.add(pricing);
	}
	
	//Diese Liste enth�lt alle Objekte, die durch createPricing erzeugt wurden
	public List<ISalePricing> getPricings () {
	return pricings;
	}
}
