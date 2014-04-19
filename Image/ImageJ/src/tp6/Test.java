package tp6;

/**
 * Created by melkir on 18/04/14.
 */
public class Test {

    public static void main(String[] args) {
        int[][] mat = new int[2][2];
        mat[1][1] = 2;
        mat[0][0] = 2;
        printMat(mat);
    }

    public static void printMat(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print("[" + i + "," + j + "]=" + mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
