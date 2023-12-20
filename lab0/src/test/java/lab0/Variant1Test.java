package lab0;

import org.example.lab0.Variant1;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.assertEquals;


public class Variant1Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test(dataProvider = "meterData")
    public void meterTest(int L, int expected) {
        int actual = new Variant1().inMeter(L);
        assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] meterData() {
        return new Object[][]{
                {100, 1},
                {200, 2},
                {1000, 10},
                {2000, 20},
                {200000, 2000}
        };
    }

    @Test(dataProvider = "positiveNumberData")
    public void testIsPositiveNumber(int n, boolean expected) {
        boolean actual = new Variant1().isPositiveNumber(n);
        assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] positiveNumberData() {
        return new Object[][]{
                {1, true},
                {0, false},
                {-7, false},
                {234, true},
                {-224, false}
        };
    }

    @Test(dataProvider = "modifyNumberData")
    public void testModifyNumber(int input, int expected) {
        int actual = new Variant1().modifyNumber(input);
        assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] modifyNumberData() {
        return new Object[][]{
                {2, 3},
                {-25, -25},
                {0, 0},
                {150, 151},
                {-400, -400}
        };
    }

    @Test(dataProvider = "dayOfWeekData")
    public void testGetDayOfWeek(int day, String expected) {
        String actual = new Variant1().getDayOfWeek(day);
        assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] dayOfWeekData() {
        return new Object[][]{
                {1, "monday"},
                {2, "tuesday"},
                {3, "wednesday"},
                {4, "thursday"},
                {5, "friday"},
                {6, "saturday"},
                {7, "sunday"},
                {0, "undefined"},
                {8, "undefined"}
        };
    }

    @BeforeMethod
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        outContent.reset();
    }

    @Test(dataProvider = "numberData")
    public void testPrintNumberNTimes(int K, int N, String expectedOutput) {
        new Variant1().printNumberNTimes(K, N);
        assertEquals(expectedOutput, outContent.toString());
    }

    @DataProvider
    public Object[][] numberData() {
        return new Object[][]{
                {2, 3, "222"},
                {5, 5, "55555"},
                {6, 1, "6"},
                {18, -32, "N > 0"},
        };
    }

    @Test(dataProvider = "remainingLengthData")
    public void testFindRemainingLength(int A, int B, int expected) {
        int actual = new Variant1().findRemainingLength(A, B);
        assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] remainingLengthData() {
        return new Object[][]{
                {15, 4, 3},
                {10, 3, 1},
                {7, 2, 1},
                {8, 5, 3},
                {20, 7, 6},
                {100, 25, 0},
                {1, 1, 0},
        };
    }

    @Test(dataProvider = "oddNumbersData")
    public void testGenerateOddNumbers(int N, int[] expected) {
        int[] actual = new Variant1().generateOddNumbers(N);
        assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] oddNumbersData() {
        return new Object[][]{
                {9, new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17}},
                {3, new int[]{1, 3, 5}},
                {5, new int[]{1, 3, 5, 7, 9}},
                {1, new int[]{1}},
        };
    }

    @Test(dataProvider = "matrixData")
    public void testCreateMatrix(int M, int N, int[][] expectedMatrix) {
        int[][] actualMatrix = new Variant1().createMatrix(M, N);

        assertEquals(expectedMatrix.length, actualMatrix.length);
        for (int i = 0; i < expectedMatrix.length; i++) {
            assertEquals(expectedMatrix[i].length, actualMatrix[i].length);
        }

        for (int i = 0; i < expectedMatrix.length; i++) {
            for (int j = 0; j < expectedMatrix[i].length; j++) {
                assertEquals(expectedMatrix[i][j], actualMatrix[i][j]);
            }
        }
    }

    @DataProvider
    public Object[][] matrixData() {
        return new Object[][]{
                {3, 3, new int[][]{{10, 10, 10}, {20, 20, 20}, {30, 30, 30}}},
                {2, 4, new int[][]{{10, 10, 10, 10}, {20, 20, 20, 20}}},
                {4, 2, new int[][]{{10, 10}, {20, 20}, {30, 30}, {40, 40}}},
        };
    }

    @Test(dataProvider = "minMaxData")
    public void testFindMinMax(int[] n, int[] expected) {
        int[] actual = new Variant1().findMinMax(n);
        assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] minMaxData() {
        return new Object[][]{
                {new int[]{1, 3, 5, 7, 9, 11, 13}, new int[]{1, 13}},
                {new int[]{1, 3, 5, 7, 9}, new int[]{1, 9}},
                {new int[]{5, 8, 2, 12, 6}, new int[]{2, 12}},
                {new int[]{42}, new int[]{42, 42}},
        };
    }
}
