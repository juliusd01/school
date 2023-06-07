public abstract class Part {

	private String id;
	private String name;
	
	public Part (String id, String name) {
		if (id==null ||name==null) {
			throw new NullPointerException();
		}
		else if (id.isEmpty() || name.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
}
