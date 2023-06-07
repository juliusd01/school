public class ReceivingStock extends Stock{
	
	private int minStockItems;
	private int maxStockItems;
	
	public ReceivingStock (int minStockItems, int maxStockItems) {
		
		if (minStockItems<0 || minStockItems>=maxStockItems) {
			throw new IllegalArgumentException();
		}
		
		this.minStockItems=minStockItems;
		this.maxStockItems=maxStockItems;
	}
	
	public int getMinStockItems() {
		return this.minStockItems;
	}
	
	public int getMaxStockItems() {
		return this.maxStockItems;
	}
	
	public boolean insert (Part part, int amount) {
		if (part==null) {
			throw new NullPointerException();
		}
		if (amount<=0) {
			throw new IllegalArgumentException();
		}
		if((getCount(part)+amount)>maxStockItems) {
			return false;
		}
		else
			return super.insert(part, amount);
		
	}		
}
