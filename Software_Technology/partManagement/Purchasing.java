public class Purchasing implements StockObserver {

	private ReceivingStock receivingStock;
	
	public Purchasing (ReceivingStock receivingStock) {
	    if(receivingStock==null) {
			throw new NullPointerException();
		}
		this.receivingStock=receivingStock;
	}
	
	public void buy(Part part, int count) {
		if (part==null) {
			throw new NullPointerException();
		}
		if (count<=0) {
			throw new IllegalArgumentException();
		}
			receivingStock.insert(part, count);
	}
	
	public ReceivingStock getStock() {
		return this.receivingStock;
	}
	
	@Override
	public void onPartCountChanged(Part part, int count) {
		if (part==null) {
			throw new NullPointerException("part must not be null");
		}
		if (count<=0) {
			throw new IllegalArgumentException();
		}
		if (count < receivingStock.getMinStockItems()) {
			buy(part, receivingStock.getMaxStockItems()-count);
		}
	}

}

