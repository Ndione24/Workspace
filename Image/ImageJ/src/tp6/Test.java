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
        int println = mat[0].length;
        int width = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
//                System.out.print("[" + i + "," + j + "]=" + mat[i][j] + " ");
                if (mat[i][j] > 0) {
                    ++width;
                    System.out.print(mat[i][j] + " ");
                    if(println == width) {
                        System.out.println();
                        width = 0;
                    }
                }
            }
//            System.out.println();
        }
    }
}
