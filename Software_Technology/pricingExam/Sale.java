package pricingExam;
//stellt ein Produkt dar
public class Sale {

	//catalog price of a product
	private long preDiscountTotal;
	
	private ISalePricing pricing;
	
	public Sale (long preDiscountTotal, ISalePricing pricing) {
		if (pricing == null) {
			throw new NullPointerException("pricing must not be null.");
		}
		else if (preDiscountTotal<0){
			throw new IllegalArgumentException("PreDiscountTotal must not be null");
		}
		this.preDiscountTotal=preDiscountTotal;
		this.pricing = pricing;
	}
	
	public long getPreDiscountTotal() {
		return preDiscountTotal;
	}
	
	//pricing logic is selected (BestForCustomerPricing oder BestForStorePricing)
	public void setPricing (ISalePricing pricing) {
		if (pricing == null) {
			throw new NullPointerException("pricing must not be null");
		}
		if (pricing == new BestForCustomerPricing(pricing)) {
			this.pricing = pricing;
		}
		else {
			this.pricing = new BestForStorePricing(pricing);
		}
		
	}
	//final price of the product 
	public long getTotal() {
		return 0;
	}
	
	public static ISalePricing createPricing (DiscountType discountType, double percentage, long discount, long threshold) {
		if (discountType==null) {
			throw new NullPointerException("DiscountType must be either ABSOLUTEDISCOUNT or PERCENTAGEDISCOUNT");
		}
		else if ((discountType==DiscountType.PERCENTAGEDISCOUNT && (discount!=0 || threshold !=0)) || (discountType==DiscountType.ABSOLUTEDISCOUNT && percentage!=0)) {
			throw new IllegalArgumentException("Cannot apply discount. Use suitable arguemnts.");
		}
		
		else if (discountType == DiscountType.PERCENTAGEDISCOUNT) {
			return new PercentageDiscountPricing(percentage);
		}
		else return new AbsoluteDiscountPricing(discount, threshold);
	}
}
