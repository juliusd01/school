import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Stock {

	private Map<Part, Integer> parts = new HashMap<Part, Integer>();
	private List<StockObserver> observers = new ArrayList<StockObserver>();
	
	public int getCount(Part part) {
		if (part==null) {
			throw new NullPointerException();
		}
		if (!parts.containsKey(part)) {
			return -1;
		}
		return parts.get(part);
	}
	
	public boolean insert (Part part, int amount) {
		if (part==null) {
			throw new NullPointerException();
		}
		if (amount<=0) {
			throw new IllegalArgumentException();
		}
		if (parts.containsKey(part)) {
			parts.put(part, parts.get(part)+amount);
			return true;
		}
		parts.put(part, amount);
		return true;
	}
	
	public boolean remove (Part part, int amount) {
		if (part==null) {
			throw new NullPointerException();
		}
		if (amount<=0) {
			throw new IllegalArgumentException();
		}
		Integer amountOfParts = parts.get(part);
		if (amountOfParts==null || amountOfParts-amount<0)
			return false;
		parts.put(part, amountOfParts-amount);
		notifyPartCountChanged(part);
		return true;
	}
		
	public void addObserver(StockObserver observer) {
		if(observer==null) {
			throw new NullPointerException();
		}
		if(!observers.contains(observer)) {
			observers.add(observer);
		}	
	}
	//What does "must emit a signal" mean? Which methods am I supposed to call???	
	private void notifyPartCountChanged (Part part) {
		Objects.requireNonNull(part, "part shoul not be null");
		for (StockObserver so : observers) {
			so.onPartCountChanged(part, parts.get(part));
		}
	}
}

