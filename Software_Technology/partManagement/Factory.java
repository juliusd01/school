public class Factory {

	private Purchasing purchasing;
	private ReceivingStock receivingStock;
	
	public Factory (Purchasing purchasing, ReceivingStock receivingStock) {
		
		if (purchasing==null || receivingStock==null) {
			throw new NullPointerException();
		}
		
		this.purchasing=purchasing;
		this.receivingStock=receivingStock;
	}
	
	public Purchasing getPurchasing() {
		return this.purchasing;
	}
	
	public ReceivingStock getReceivingStock() {
		return this.receivingStock;
	}
	
	public static Part createPart(PartType partType, String id, String name) {
		if (partType==null || id==null || name==null) {
			throw new NullPointerException();
		}
		if (id.isEmpty() || name.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (partType==PartType.COMPONENTS) {
			return new Components(id, name);
		}
		else if (partType==PartType.RESOURCE) {
			return new Resource(id, name);
		}
		else {
			return new SingleComponent(id, name);
		}
	}
}