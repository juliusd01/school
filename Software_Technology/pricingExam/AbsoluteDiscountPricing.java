package pricingExam;

public class AbsoluteDiscountPricing implements ISalePricing{
	
	private long discount;
	private long threshold;

	public AbsoluteDiscountPricing (long discount, long threshold){
		if (discount < 0 || threshold < 0) {
			throw new IllegalArgumentException("Must not contain values smaller than 0");
		}
		this.discount=discount;
		this.threshold=threshold;
	}
	
	//Rabatt berechnen
	@Override
	public long getTotal(Sale sale) {
		if (threshold > sale.getPreDiscountTotal()) {
			discount = 0;
		}
		else if (threshold > sale.getPreDiscountTotal()-discount) {
			System.out.println("We are not able to give a discount, since the price of the product does not exceed the threshold.");
		discount = sale.getPreDiscountTotal()-threshold;
		}
		return sale.getPreDiscountTotal()-discount;
	}
}
