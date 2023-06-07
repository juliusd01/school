import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Map.Entry;

public class MyTetris<T> implements Matrix<T> {
  private Map<MatrixIndex, T> matrixEntries = new HashMap<MatrixIndex, T>();

  public class DepthFirstIterator implements Iterator<T> {
    private int rowIndex, columnIndex, returnedObjectsCount;

    @Override
    public boolean hasNext() {
      return returnedObjectsCount < getObjectCount();
    }

    private void traverseOne() {
      final int maxRowIndex = Math.max(getRowCount() - 1, 0);
      final int maxColumnIndex = Math.max(getColumnCount() - 1, 0);

      if (rowIndex < maxRowIndex)
        rowIndex++;
      else if (columnIndex < maxColumnIndex) {
        columnIndex++;
        rowIndex = 0;// reset row index for each column to start at the top again.
      } else
        throw new NoSuchElementException("no more elements in collection");
    }

    @Override
    public T next() {
      T nextMatrixValue;

      while ((nextMatrixValue = matrixEntries.get(new MatrixIndex(rowIndex, columnIndex))) == null)
        traverseOne();
      traverseOne();
      returnedObjectsCount++;
      return nextMatrixValue;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("remove is not supported by this iterator");
    }

  }

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
    // NOTE returning matrixEntries.size() only succeeds if there are no
    // null-values!
    return matrixEntries.values().stream().filter(value -> value != null).toArray().length;
  }

  @Override
  public int getDistinctObjectCount() {
    return matrixEntries.values().stream().distinct().filter(value -> value != null).toArray().length;
  }

  @Override
  public Iterator<T> iterator() {
    return new DepthFirstIterator();
  }

  @Override
  public T get(int row, int column) {
    final MatrixIndex matrixIndex = new MatrixIndex(row, column);
    if (row > getRowCount() - 1 || column > getColumnCount() - 1)
      throw new IllegalArgumentException("specified row and column are out of bounds");
    return matrixEntries.get(matrixIndex);
  }

  @Override
  public T put(int row, int column, T value) {
    return matrixEntries.put(new MatrixIndex(row, column), value);
  }

  @Override
  public boolean contains(T value) {
    Objects.requireNonNull(value, "value must be non-null");
    return matrixEntries.containsValue(value);
  }

}