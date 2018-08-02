package fxpro.hiring_test.pits_and_hills;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class LandscapeWaterCalculatorTest {

  @Parameters
  public static Collection<LandscapeWaterCalculator> data() {
    return Arrays.asList(new LandscapeWaterCalculatorImplArrays(), new LandscapeWaterCalculatorImplSet());
  }

  @Parameter
  public LandscapeWaterCalculator calculator;

  @Test
  public void givenBeerMugLandscape_whenCalculate_thenReturnCorrectValue() {

    // given
    final int[] landscape = new int[32_000];
    landscape[0] = 32_000;
    landscape[32_000 - 1] = 32_000;

    // when
    final long amount = calculator.calculateWaterAmount(landscape);

    // then
    assertThat(amount)
	.isEqualTo(32_000L * (32_000 - 2));

  }

  @Test
  public void givenDoubleValueLandscape_whenCalculate_thenReturnZeroValue() {

    // given
    final int[] landscape = { 2, 3 };

    // then
    final long amount = calculator.calculateWaterAmount(landscape);

    // when
    assertThat(amount)
	.isZero();
  }

  @Test
  public void givenEmptyLandscape_whenCalculate_thenReturnZeroValue() {

    // given
    final int[] landscape = {};

    // when
    final long amount = calculator.calculateWaterAmount(landscape);

    // then
    assertThat(amount)
	.isZero();
  }

  /**
   * The result should be '7'
   *
   * <pre>
      7 |                 O
      6 |           O O O |
      5 |   O ~ ~ O | | | | ~ ~ O
      4 | O | ~ O | | | | | ~ ~ |
      3 | | | O | | | | | | O O |
      2 | | | | | | | | | | | | |
      1 | | | | | | | | | | | | |
      0 | | | | | | | | | | | | |
        +------------------------
          0 1 2 3 4 5 6 7 8 9 0 1
                              1
   * </pre>
   */
  @Test
  public void givenLandscapeVariant1_whenCalculate_thenReturnCorrectValue() {

    // given
    final int[] landscape = { 4, 5, 3, 4, 5, 6, 6, 6, 7, 3, 3, 5 };

    // when
    final long amount = calculator.calculateWaterAmount(landscape);

    // then
    assertThat(amount)
	.isEqualTo(7);
  }

  /**
   * The result should be '12'
   *
   * <pre>
       7 |                 O
       6 | O ~ ~ ~ ~ O O O |
       5 | | ~ ~ ~ ~ | | | |
       4 | | ~ ~ O ~ | | | |
       3 | | O O | ~ | | | | O O
       2 | | | | | O | | | | | |
       1 | | | | | | | | | | | |
       0 | | | | | | | | | | | |
         +----------------------
           0 1 2 3 4 5 6 7 8 9 0
                               1
   * </pre>
   */
  @Test
  public void givenLandscapeVariant2_whenCalculate_thenReturnCorrectValue() {

    // given
    final int[] landscape = { 6, 3, 3, 4, 2, 6, 6, 6, 7, 3, 3 };

    // when
    final long amount = calculator.calculateWaterAmount(landscape);

    // then
    assertThat(amount)
	.isEqualTo(12);
  }

  /**
   * The result should be '22'
   *
   * <pre>
      8 |           O
      7 |           | ~ ~ ~ ~ O
      6 | O ~ ~ ~ ~ | ~ ~ ~ ~ |
      5 | | ~ ~ ~ ~ | O O O ~ |
      4 | | ~ ~ ~ ~ | | | | ~ |
      3 | | O O O O | | | | O |
      2 | | | | | | | | | | | |
      1 | | | | | | | | | | | |
      0 | | | | | | | | | | | |
        +----------------------
          0 1 2 3 4 5 6 7 8 9 0
                              1
   * </pre>
   */
  @Test
  public void givenLandscapeVariant3_whenCalculate_thenReturnCorrectValue() {

    // given
    final int[] landscape = { 6, 3, 3, 3, 3, 8, 5, 5, 5, 3, 7 };

    // when
    final long amount = calculator.calculateWaterAmount(landscape);

    // then
    assertThat(amount)
	.isEqualTo(22);
  }

  /**
   * The result should be '2'
   *
   * <pre>
      4 | O
      3 | | ~ O
      2 | | ~ |
      1 | | O |
      0 | | | |
        +------
          0 1 2
   * </pre>
   */
  @Test
  public void givenLandscapeVariant4_whenCalculate_thenReturnCorrectValue() {

    // given
    final int[] landscape = { 4, 1, 3 };

    // when
    final long amount = calculator.calculateWaterAmount(landscape);

    // then
    assertThat(amount)
	.isEqualTo(2);
  }

  /**
   * The result should be '0'
   *
   * <pre>
      7 |           O
      6 |           |
      5 |           | O O O
      4 |           | | | |
      3 |   O O O O | | | | O
      2 |   | | | | | | | | |
      1 |   | | | | | | | | |
      0 | O | | | | | | | | | O
        +----------------------
          0 1 2 3 4 5 6 7 8 9 0
                              1
   * </pre>
   */
  @Test
  public void givenLandscapeVariant5_whenCalculate_thenReturnCorrectValue() {

    // given
    final int[] landscape = { 0, 3, 3, 3, 3, 7, 5, 5, 5, 3, 0 };

    // when
    final long amount = calculator.calculateWaterAmount(landscape);

    // then
    assertThat(amount)
	.isZero();
  }

  /**
   * The result should be '8'
   *
   * <pre>
      7 |         O
      6 |         | ~ O
      5 |       O | ~ |
      4 | O ~ ~ | | ~ |
      3 | | ~ O | | ~ |
      2 | | O | | | ~ |
      1 | | | | | | O |
      0 | | | | | | | |
        +--------------
          0 1 2 3 4 5 6
   * </pre>
   */
  @Test
  public void givenLandscapeVariant6_whenCalculate_thenReturnCorrectValue() {

    // given
    final int[] landscape = { 4, 2, 3, 5, 7, 1, 6 };

    // when
    final long amount = calculator.calculateWaterAmount(landscape);

    // then
    assertThat(amount)
	.isEqualTo(8);
  }

  /**
   * The result should be '21'
   *
   * <pre>
      7 |               O
      6 |               | ~ O    1
      5 |       O ~ ~ ~ | ~ |    4
      4 | O ~ ~ | ~ ~ ~ | ~ |    6
      3 | | ~ O | ~ ~ ~ | ~ |    5
      2 | | O | | ~ O ~ | ~ |    3
      1 | | | | | ~ | ~ | O |    2
      0 | | | | | O | O | | |    0
        +--------------------
          0 1 2 3 4 5 6 7 8 9
   *
   * </pre>
   */
  @Test
  public void givenLandscapeVariant7_whenCalculate_thenReturnCorrectValue() {

    // given 0 1 2 3 4 5 6 7 8 9
    final int[] landscape = { 4, 2, 3, 5, 0, 2, 0, 7, 1, 6 };

    // when
    final long amount = calculator.calculateWaterAmount(landscape);

    // then
    assertThat(amount)
	.isEqualTo(21);
  }

  /**
   * The result should be '9'
   *
   * <pre>
    O ~ ~ ~ O
    | ~ ~ O | O
    | ~ O | | | ~ O
    | O | | | | ~ |
    | | | | | | ~ | O
    | | | | | | O | |
   *
   * </pre>
   */
  @Test
  public void givenLandscapeVariantFromTheTask_whenCalculate_thenReturnCorrectValue() {

    // given
    final int[] landscape = { 5, 2, 3, 4, 5, 4, 0, 3, 1 };

    // when
    final long amount = calculator.calculateWaterAmount(landscape);

    // then
    assertThat(amount)
	.isEqualTo(9);
  }

  @Test
  public void givenNegativeLandscapeValue_whenCalculate_thenThrowIllegalArgumentException() {

    // given
    final int[] landscape = new int[] { 1, 2, 3, 0, -100, 5 };

    // when
    final Throwable thrown = catchThrowable(() -> calculator.calculateWaterAmount(landscape));

    // then
    assertThat(thrown)
	.isInstanceOf(IllegalArgumentException.class)
	.hasStackTraceContaining("Wrong landscape height value")
	.hasNoCause();
  }

  @Test
  public void givenNullLandscape_whenCalculate_thenThrowNullPointerException() {

    // given
    final int[] landscape = null;

    // when
    final Throwable thrown = catchThrowable(() -> calculator.calculateWaterAmount(landscape));

    // then
    assertThat(thrown)
	.isInstanceOf(NullPointerException.class)
	.hasNoCause();

  }

  @Test
  public void givenSignleValueLandscape_whenCalculate_thenReturnZeroValue() {

    // given
    final int[] landscape = { 1 };

    // then
    final long amount = calculator.calculateWaterAmount(landscape);

    // when
    assertThat(amount)
	.isZero();
  }

  @Test
  public void givenTooLargeLandscapeValue_whenCalculate_thenThrowIllegalArgumentException() {

    // given
    final int[] landscape = new int[] { 1, 320_001, 3, 0, 100, 5 };

    // when
    final Throwable thrown = catchThrowable(() -> calculator.calculateWaterAmount(landscape));

    // then
    assertThat(thrown)
	.isInstanceOf(IllegalArgumentException.class)
	.hasStackTraceContaining("Wrong landscape height value")
	.hasNoCause();

  }

  @Test
  public void givenToolLargeLandscapeArray_whenCalculate_thenThrowIllegalArgumentException() {

    // given
    final int[] landscape = new int[32_000 + 1];

    // when
    final Throwable thrown = catchThrowable(() -> calculator.calculateWaterAmount(landscape));

    // then
    assertThat(thrown)
	.isInstanceOf(IllegalArgumentException.class)
	.hasStackTraceContaining("Wrong landscape length")
	.hasNoCause();
  }
}
