package chapter1;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class Exercise {
    public static String InttoBinaryString(int num) {
        String s = "";
        for(int n = num; n > 0; n /= 2)
            s = (n % 2) + s;
        return s;
    }

    public static int [][] TransposeMatrix(int[][] a, int m, int n) {
        int [][] b = new int[n][];
        for(int i = 0; i < n; i ++) {
            b[i] = new int[m];
            for(int j = 0; j < m; j ++) {
                b[i][j] = a[j][i];
            }
        }

        return b;
    }

    public static void printMatrix(int [][] a) {
        for (int i = 0; i < a.length; i ++) {
            StdOut.println(Arrays.toString(a[i]));
        }
    }

    public static int lg(int N) {
        // Return the number
        int n = 0;
        for(int i = 0; i < N; i ++) {
            
        }

        return n;
    }

    public static void main(String[] args) {
        // 1.1.1
        StdOut.println("(0 + 15 ) / 2 = " + (0 + 15) / 2);
        StdOut.println("2.0e-6 * 100000000.1 = " + 2.0e-6 * 100000000.1);
        StdOut.println("true && false || true && true = " + (true && false || true && true));

        // 1.1.2
        StdOut.println("(1 + 2.236) / 2 = " + (1 + 2.236) / 2);
        StdOut.println("1 + 2 + 3 + 4.0 = " + (1 + 2 + 3 + 4.0));
        StdOut.println("4.1 >= 4 = " + (4.1 >= 4));
        StdOut.println("1 + 2 + \"3\" = " + (1 + 2 + "3"));

        // 1.1.6
        int f = 0;
        int g = 1;
        for(int i = 0; i <= 15; i ++) {
            f = f + g;
            g = f - g;
        }
        StdOut.println("f = " + f);
        StdOut.println("g = " + g);

        // 1.1.7
        double t = 9.0;
        while(Math.abs(t - 9.0/t) > .001)
            t = (9.0 / t + t) / 2.0;
        StdOut.printf("%.5f\n", t);

        int sum = 0;
        for(int i = 1; i < 1000; i ++)
            for(int j = 0; j < i; j ++)
                sum++;
        StdOut.println("Sum = " + sum);

        sum = 0;
        for(int i = 1; i < 1000; i *= 2)
            for(int j = 0; j < 1000; j ++)
                sum++;
        StdOut.println("Sum = " + sum);

        // 1.1.9
        sum = 15;
        String snum = InttoBinaryString(sum);
        StdOut.println("Binary string of 15 is " + snum);
        sum = 33;
        snum = InttoBinaryString(sum);
        StdOut.println("Binary string of 33 is " + snum);

        // 1.1.12
        int[] a = new int[10];
        for(int i = 0; i < 10; i ++)
            a[i] = 9 - i;
        for(int i = 0; i < 10; i ++)
            a[i] = a[a[i]];
        for(int i = 0; i < 10; i ++)
            System.out.println("a[i] = " + a[i]);
    
        // 1.1.13
        int[][] matrix = new int[3][];
        for(int i = 0; i < 3; i ++) {
            matrix[i] = new int[2];

            for(int j = 0; j < 2; j ++) {
                matrix[i][j] = i * 10 + j;
            }
        }
        printMatrix(matrix);
        int[][] matrix2 = TransposeMatrix(matrix, 3, 2);
        printMatrix(matrix2);
    }
}
