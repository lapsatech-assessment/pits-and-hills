package fxpro.hiring_test.pits_and_hills;

public class LandscapeWaterCalculatorImplArrays implements LandscapeWaterCalculator {

  static final int MAX_LANDSCAPE_LENGTH = 32_000;
  static final int MAX_LANDSCAPE_HEIGHT = 32_000;

  public long calculateWaterAmount(final int[] landscape) {

    if (landscape.length > MAX_LANDSCAPE_LENGTH)
      throw new IllegalArgumentException("Wrong landscape length");

    if (landscape.length < 3)
      return 0;

    final LeftRightPair[] heights = new LeftRightPair[MAX_LANDSCAPE_HEIGHT + 1];

    final int peakColumn;
    {
      int _peak = Integer.MIN_VALUE;
      int _index = -1;
      for (int i = 0; i < landscape.length; i++) {

	if (landscape[i] < 0 || landscape[i] > MAX_LANDSCAPE_HEIGHT)
	  throw new IllegalArgumentException(String.format("Wrong landscape height value '%1$d'", landscape[i]));

	LeftRightPair pair = heights[landscape[i]];
	if (pair == null)
	  heights[landscape[i]] = pair = new LeftRightPair();
	pair.acceptValue(i);

	if (_peak < landscape[i]) {
	  _peak = landscape[i];
	  _index = i;
	}
      }
      peakColumn = _index;
    }

    long result = 0;
    final boolean[] taken = new boolean[landscape.length];
    for (int row = heights.length - 1; row >= 0; row--) {

      final LeftRightPair pair = heights[row];
      if (pair == null)
	continue;

      int from = pair.left;
      int to = pair.right;

      if (from == to)
	if (from > peakColumn)
	  from = peakColumn;
	else
	  to = peakColumn;

      for (int column = from; column <= to; column++)
	if (landscape[column] < row && !taken[column]) {
	  result += row - landscape[column];
	  taken[column] = true;
	}
    }
    return result;
  }

  private static class LeftRightPair {

    int left = Integer.MAX_VALUE;
    int right = Integer.MIN_VALUE;

    LeftRightPair acceptValue(final int value) {
      if (left > value)
	left = value;
      if (right < value)
	right = value;
      return this;
    }
  }

  void printLandscape(final int[] land) {
    if (land.length > MAX_LANDSCAPE_LENGTH)
      throw new IllegalArgumentException("Wrong positions count");

    int peak = 0;
    for (final int colHeight : land) {
      if (colHeight < 0 || colHeight > MAX_LANDSCAPE_HEIGHT)
	throw new IllegalArgumentException(String.format("Wrong landscape height value '%1$d'", colHeight));
      if (peak < colHeight)
	peak = colHeight;
    }

    for (int row = peak; row >= 0; row--) {
      for (final int height : land) {
	if (height == row)
	  System.out.print("O");
	else if (height > row)
	  System.out.print("|");
	else
	  System.out.print(" ");
	System.out.print(" ");
      }
      System.out.println();
    }
  }
}
