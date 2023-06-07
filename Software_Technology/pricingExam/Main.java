package pricingExam;

public class Main {

	public static void main(String[] args) {
		Sale sale1;
		ISalePricing p1, p2;
		p1 = Sale.createPricing(DiscountType.ABSOLUTEDISCOUNT, 0, 5, 50);
		p2 = Sale.createPricing(DiscountType.PERCENTAGEDISCOUNT, 10, 0, 0);
		BestForStorePricing p = new BestForStorePricing(p1);
		p.add(p2);
		sale1 = new Sale(100, p);
		System.out.println("BestForStorePricing = " + sale1.getTotal());

	}

}
