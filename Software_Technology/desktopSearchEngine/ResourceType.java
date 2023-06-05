public class ResourceType {

	private String description;
	private KeywordCollector collector;
	
	public ResourceType(String desc, KeywordCollector collector) {
		if(desc==null || collector==null) {
			throw new NullPointerException("must not be null");
		}
		if (desc.isEmpty()) {
			throw new IllegalArgumentException("description must not be empty");
		}
		this.description=desc;
		this.collector=collector;
	}
	
	public String getDescription () {
		return this.description;
	}
	
	public KeywordCollector getCollector() {
		return this.collector;
	}
}
