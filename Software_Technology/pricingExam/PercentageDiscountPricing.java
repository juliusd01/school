package pricingExam;

public class PercentageDiscountPricing implements ISalePricing {

	private double percentage;
	
	public PercentageDiscountPricing (double percentage) {
		if (percentage>100 || percentage<0) {
			throw new IllegalArgumentException("Discount must contain value between 0 and 100");
		}
		this.percentage=percentage;
	}
	
	//Hier muss Rabatt berechnet werden
	@Override
	public long getTotal(Sale sale) {
		long rabatt = (long) (sale.getPreDiscountTotal()*percentage*0.01);
		return sale.getPreDiscountTotal()-rabatt;
	}
}
