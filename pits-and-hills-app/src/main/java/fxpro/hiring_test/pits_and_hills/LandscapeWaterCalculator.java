package fxpro.hiring_test.pits_and_hills;

public interface LandscapeWaterCalculator {

  /**
   * Takes a landscape as parameter and calculate how many water could be
   * collected inside pits only. Max number of positions of the landscape is
   * 32000. Height must be between 0 and 32000.
   * 
   * @param landscape presented as an array of heights
   * @return amount of water which could be collected
   */
  long calculateWaterAmount(final int[] landscape);

}
