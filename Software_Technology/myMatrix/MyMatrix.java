import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;

public class MyMatrix<T> implements Matrix<T>{

	private Map<MatrixIndex, T> matrixEntries = new HashMap<MatrixIndex, T>();
	
	@Override
	  public int getRowCount() {
	    int maxRow = -1; // must be negative because index starts at 0, not 1.
	    for (Entry<MatrixIndex, T> matrixEntry : matrixEntries.entrySet())
	      if (matrixEntry.getValue() != null && maxRow < matrixEntry.getKey().getRow())
	        maxRow = matrixEntry.getKey().getRow();

	    return maxRow + 1;
	  }

	@Override
	  public int getColumnCount() {
	    int maxColumn = -1;
	    for (Entry<MatrixIndex, T> matrixEntry : matrixEntries.entrySet())
	      if (matrixEntry.getValue() != null && maxColumn < matrixEntry.getKey().getColumn())
	        maxColumn = matrixEntry.getKey().getColumn();

	    return maxColumn + 1;
	  }

	@Override
	public int getObjectCount() {
		return matrixEntries.size();
	}

	@Override
	public int getDistinctObjectCount() {
		List<T> store = new ArrayList<T>();
		for (T value : matrixEntries.values()) {
				if (store.contains(value)) {
						continue;
					}
				else{
						store.add(value);
					}
		}
		return store.size();
	}
	
	@Override
	public T get(int row, int column) {
		if(row<0 || column<0) {
			throw new IllegalArgumentException();
		}
		return matrixEntries.get(new MatrixIndex(row, column));
	}

	@Override
	public T put(int row, int column, T value) {
		return matrixEntries.put(new MatrixIndex(row, column), value);
	}

	@Override
	public boolean contains(T value) {
		if(value==null)
			throw new NullPointerException("value cannot be null");
		if (matrixEntries.containsValue(value))
			return true;
		return false;
	}
	
	public class DepthFirstIterator implements Iterator<T>{

		int rowX, columnX, returnedObjects;
		T next;
		
		public void DepthFirstIterator() {
			final int maxRowX = Math.max(getRowCount(), 0);
			final int maxColumnX = Math.max(getColumnCount(), 0);
			
			if (rowX < maxRowX) {
				rowX++;
			}
			else if (columnX < maxColumnX) {
				columnX++;
				rowX=0;
			}
			else 
				throw new NoSuchElementException("Jibbts nicht");
		}
		
		public void remove () {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean hasNext() {
			for (int i = 0; i<getColumnCount(); i++) {
				for (int j = 0; j<getRowCount(); j++) {
					if (i>=getColumnCount()) {
						return false;
					}
				}
			}
			return true;
		}

		@Override
		public T next() {
			T next;
			
			while ((next = matrixEntries.get(new MatrixIndex(rowX, columnX)))==null) {
				DepthFirstIterator();
			}
			DepthFirstIterator();
			returnedObjects++;
			return next;
		}
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}


}
