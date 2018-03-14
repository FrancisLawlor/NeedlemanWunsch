/**
 * Main class for demonstrating functionality of the system.
 *
 * @author Francis Lawlor
 */
public class Main {
  /**
   * This is the starting point for the application. Here, the specified strings
   * are provided, along with the values for the scoring system.
   *
   * The NeedlemanWunsch class' static methods are utilised for first computing
   * the similarity matrix, and then the optimal alignment of the two strings
   * by upmost order.
   *
   * @param args
   */
  public static void main(String[] args) {
    String sequence1 = "AAGTGCCTCAAGATA";
    String sequence2 = "CCGTCTCAGCAATA";
    float matchScore = 1;
    float mismatchScore = -1;
    float indelScore = -2;

    float[][] computedMatrix = NeedlemanWunsch.computeMatrix(sequence1, sequence2, matchScore, mismatchScore, indelScore);

    System.out.println("Global max similarity matrix: ");

    for (int i = 0; i <= sequence2.length(); i++) {
        if (i == 0) {
          System.out.print("\t\t");
        } else {
          System.out.print(sequence2.charAt(i - 1) + "\t");
        }
    }

    System.out.println();

    for (int i = 0; i < computedMatrix.length; i++) {
      if (i == 0) {
        System.out.print("\t");
      } else {
        System.out.print(sequence1.charAt(i - 1) + "\t");
      }
      for (int j = 0; j < computedMatrix[0].length; j++) {
        System.out.print(String.format("%.2f\t", computedMatrix[i][j]));
      }
      System.out.println();
    }

    System.out.println("\nScore: \n" + computedMatrix[computedMatrix.length - 1][computedMatrix[0].length - 1] + "\n");
    System.out.println("Best global downmost alignment: ");
    System.out.println(NeedlemanWunsch.obtainOptimalAlignmentByUpmostOrder(computedMatrix, sequence1, sequence2, matchScore, mismatchScore, indelScore));
  }
}
