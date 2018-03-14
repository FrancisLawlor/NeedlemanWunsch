/**
 * Class representing the optimal alignment for two strings of amino acids.
 * Contains two fields, one for each string once the alignment is found.
 *
 * @author Francis Lawlor
 */
public class OptimalAlignment {
  private String alignment1;
  private String alignment2;

  /**
   * The constructor method for the class.
   *
   * @param alignment1 A String corresponding to one amino acid string in the optimal alignment.
   * @param alignment2 A String corresponding to second amino acid string in the optimal alignment.
   */
  public OptimalAlignment(String alignment1, String alignment2) {
    this.alignment1 = alignment1;
    this.alignment2 = alignment2;
  }

  /**
   * A method for outputting a string corresponding to the optimal alignment.
   */
  public String toString() {
    return alignment2 + "\n" + alignment1;
  }
}
