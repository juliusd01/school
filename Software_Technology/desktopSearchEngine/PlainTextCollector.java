import java.util.HashSet;
import java.util.Set;

public class PlainTextCollector implements KeywordCollector{

	private TextFileIterator tfi;
	@Override
	public Set<String> getKeywords(Resource res) {
		if(res==null) {
			throw new NullPointerException("res must not be null");
		}
		Set<String> liste = new HashSet<String>();
		tfi.getAsString(res);
		//hat sicherlich was mit dem Next zu tun
		return liste;
	}

}
