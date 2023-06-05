import java.util.HashSet;
import java.util.Set;

public class DefaultCollector implements KeywordCollector {

	@Override
	public Set<String> getKeywords(Resource res) {
		Set<String> liste = new HashSet<String>();
		liste.add(res.getName());
		return liste;
	}

}
