public class MatrixIndex {

	private int row;
	private int column;
	
	public MatrixIndex(int row, int column) {
		if(column<0 || row<0) {
			throw new IllegalArgumentException("column must not be smaller than 0");
		}
		this.row=row;
		this.column=column;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public boolean equals (Object o) {
		if(!(o instanceof MatrixIndex))
			throw new IllegalArgumentException();
		return ((MatrixIndex)o).getRow()==row && ((MatrixIndex)o).getColumn()==column;
	}
	
	public int hashCode () {
		final int primi = 11;
		return row * primi + column;
	}
}
