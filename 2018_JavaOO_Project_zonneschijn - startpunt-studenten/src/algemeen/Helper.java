package algemeen;

/**
 *
 * @author Olivier PC
 */
public class Helper {

    /**
     * Helper-methode om getallen af te ronden.
     *
     * @param waarde - waarde die afgerondt moet worden.
     * @param aantalNaNul - aantal gewenste beduidende cijfers.
     * @return - de afgeronde waarde wordt gereturned.
     */
    public static double afronden(double waarde, double aantalNaNul) {
        double afronding = Math.pow(10.0, aantalNaNul);
        if (aantalNaNul != 0) {
            return Math.round(waarde * afronding) / afronding;
        } else {
            throw new IllegalArgumentException("Tweede parameter was 0.");
        }
    }

}
