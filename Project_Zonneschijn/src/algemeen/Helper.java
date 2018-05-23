package algemeen;

/**
 *
 * @author Olivier PC
 */
public class Helper {

    /**
     * Helper-method to round numbers.
     *
     * @param value - value to be rounded.
     * @param decimals - amount of significant figures.
     * @return - the rounded value.
     */
    public static double round(double value, double decimals) {
        double round = Math.pow(10.0, decimals);
        if (decimals != 0) {
            return Math.round(value * round) / round;
        } else {
            throw new IllegalArgumentException("Tweede parameter was 0.");
        }
    }

}
