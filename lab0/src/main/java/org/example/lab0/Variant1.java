package org.example.lab0;

public class Variant1 {
    public int inMeter(int l) {
        return l / 100;
    }
    public boolean isPositiveNumber(int n) {
        return n > 0;
    }

    public int modifyNumber(int n) {
        if (n > 0) {
            n += 1;
        }
        return n;
    }

    public String getDayOfWeek(int day) {
        return switch (day) {
            case 1 -> "monday";
            case 2 -> "tuesday";
            case 3 -> "wednesday";
            case 4 -> "thursday";
            case 5 -> "friday";
            case 6 -> "saturday";
            case 7 -> "sunday";
            default -> "undefined";
        };
    }

    public void printNumberNTimes(int K, int N) {
        if (N <= 0) {
            System.out.print("N > 0");
            return;
        }

        for (int i = 0; i < N; i++) {
            System.out.print(K);
        }
    }

    public int findRemainingLength(int A, int B) {
        if (A <= 0 || B <= 0) {
            throw new IllegalArgumentException("A and B are positive.");
        }

        while (A >= B) {
            A -= B;
        }
        return A;
    }

    public int[] generateOddNumbers(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N > 0");
        }

        int[] oddNumbers = new int[N];
        int currentOdd = 1;

        for (int i = 0; i < N; i++) {
            oddNumbers[i] = currentOdd;
            currentOdd += 2;
        }
        return oddNumbers;
    }


    public int[][] createMatrix(int M, int N) {
        int[][] matrix = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = 10 * (i + 1);
            }
        }
        return matrix;
    }


    public int[] findMinMax(int[] n) {
        if (n == null || n.length == 0) {
            return new int[]{};
        }

        int min = n[0];
        int max = n[0];

        for (int number : n) {
            if (number < min) {
                min = number;
            }
            if (number > max) {
                max = number;
            }
        }
        return new int[]{min, max};
    }
}
