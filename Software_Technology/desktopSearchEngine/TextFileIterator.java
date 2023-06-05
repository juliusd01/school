public class TextFileIterator implements java.util.Iterator<String> {

	public TextFileIterator (Resource res) {
		if (res==null) {
			throw new NullPointerException();
		}
	}
	
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException("remove is not supported");
	}
	
	public String getAsString(Resource res) {
		return "We wish you good luck in this exam!\nWe hope you are well pre-\npared.";
	}
}