/**
 * A utility class used for carrying out computations pertaining to the
 * NeedlemanWunsch algorithm. Including computation of the global max
 * similarity matrix and the best global downmost alignment for two sequences.
 *
 * @author Francis Lawlor
 */
public class NeedlemanWunsch {

  /**
   * A utility method for computing the global max similarity matrix.
   *
   * @param sequence1 A String representing the first amino acid sequence.
   * @param sequence1 A String representing the second amino acid sequence.
   * @param matchScore A float representing the score assigned to matches.
   * @param mimatchScore A float representing the score assigned to mismatches.
   * @param indelScore A float represening the score assigned to insertions and deletions.
   */
  public static float[][] computeMatrix(String sequence1, String sequence2, float matchScore, float mismatchScore, float indelScore) {
    sequence1 = "-" + sequence1;
    sequence2 = "-" + sequence2;

    float[][] resultMatrix = new float[sequence1.length()][sequence2.length()];

    for (int i = 0; i < sequence1.length(); i++) {
      resultMatrix[i][0] = i * indelScore;
    }
    for (int j = 0; j < sequence2.length(); j++) {
      resultMatrix[0][j] = resultMatrix[0][j] = j * indelScore;
    }

    for (int i = 1; i < sequence1.length(); i++) {
      for (int j = 1; j < sequence2.length(); j++) {
        resultMatrix[i][j] = Math.max(resultMatrix[i - 1][j] + indelScore,
        Math.max(resultMatrix[i][j - 1] + indelScore, resultMatrix[i - 1][j - 1] +
        (sequence1.charAt(i) == sequence2.charAt(j) ? matchScore : mismatchScore)));
      }
    }

    return resultMatrix;
  }

  /**
   * A utility method for computing the best global downmost alignment for two
   * sequences.
   *
   * @param similarityMatrix A 2D-array represening the previously computed global max similarity matrix.
   * @param sequence1 A String representing the first amino acid sequence.
   * @param sequence1 A String representing the second amino acid sequence.
   * @param matchScore A float representing the score assigned to matches.
   * @param mimatchScore A float representing the score assigned to mismatches.
   * @param indelScore A float represening the score assigned to insertions and deletions.
   */
  public static OptimalAlignment obtainOptimalAlignmentByUpmostOrder(float similarityMatrix[][],
    String sequence1, String sequence2, double matchScore, double mismatchScore, double indelScore) {

    int i = similarityMatrix.length - 1;
    int j = similarityMatrix[0].length - 1;
    StringBuilder alignment1Builder = new StringBuilder();
    StringBuilder alignment2Builder = new StringBuilder();

    sequence1 = "-" + sequence1;
    sequence2 = "-" + sequence2;

    while (i != 0 && j != 0) {
      if (similarityMatrix[i][j] == (similarityMatrix[i][j - 1] + indelScore)) {
        alignment1Builder.insert(0, "-");
        alignment2Builder.insert(0, sequence2.charAt(j));
        j--;
      } else if (similarityMatrix[i][j] == (similarityMatrix[i - 1][j - 1] + (sequence1.charAt(i) == sequence2.charAt(j) ? matchScore : mismatchScore))) {
        alignment1Builder.insert(0, sequence1.charAt(i));
        alignment2Builder.insert(0, sequence2.charAt(j));
        i--;
        j--;
      } else if (similarityMatrix[i][j] == (similarityMatrix[i - 1][j] + indelScore)){
        alignment1Builder.insert(0, sequence1.charAt(i));
        alignment2Builder.insert(0, "-");
        i--;
      }
    }

    return new OptimalAlignment(alignment1Builder.toString(), alignment2Builder.toString());
  }
}
