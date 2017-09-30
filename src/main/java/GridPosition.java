public class GridPosition {
  public int row, col;

  public GridPosition(int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GridPosition that = (GridPosition) o;

    if (row != that.row) return false;
    return col == that.col;
  }

  @Override
  public int hashCode() {
    int result = row;
    result = 31 * result + col;
    return result;
  }
}
