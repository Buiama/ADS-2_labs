package com.company;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Algorithm {
    int[][] matrix;

    int[] row, col, rowCovered, colCovered, zeroRow;

    public Algorithm(int[][] matrix) {
        this.matrix = matrix;
        row = new int[matrix.length];
        col = new int[matrix[0].length];

        rowCovered = new int[matrix.length];
        colCovered = new int[matrix[0].length];
        zeroRow = new int[matrix.length];
        Arrays.fill(zeroRow, -1);
        Arrays.fill(row, -1);
        Arrays.fill(col, -1);
    }

    public int[][] findOptimalAssignment() {
        stepOne();
        stepTwo();
        stepThree();

        while (!allColumnsAreCovered()) {
            int[] mainZero = stepFour();
            while (mainZero == null) {
                stepSeven();
                mainZero = stepFour();
            }
            if (row[mainZero[0]] == -1) {
                stepSix(mainZero);
                stepThree();
            } else {
                // step five
                rowCovered[mainZero[0]] = 1;  // cover row of mainZero
                colCovered[row[mainZero[0]]] = 0;  // uncover column of mainZero
                stepSeven();
            }
        }

        int[][] optimalAssignment = new int[matrix.length][];
        for (int i = 0; i < col.length; i++) {
            optimalAssignment[i] = new int[]{i, col[i]};
        }
        return optimalAssignment;
    }

    private boolean allColumnsAreCovered() {
        for (int i : colCovered) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    private void stepOne() {
        for (int i = 0; i < matrix.length; i++) {
            // find the min value of the current row
            int currentRowMin = Integer.MAX_VALUE;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < currentRowMin) {
                    currentRowMin = matrix[i][j];
                }
            }
            // subtract min value from each element of the current row
            for (int k = 0; k < matrix[i].length; k++) {
                matrix[i][k] -= currentRowMin;
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            // find the min value of the current column
            int currentColMin = Integer.MAX_VALUE;
            for (int[] ints : matrix) {
                if (ints[i] < currentColMin) {
                    currentColMin = ints[i];
                }
            }
            // subtract min value from each element of the current column
            for (int k = 0; k < matrix.length; k++) {
                matrix[k][i] -= currentColMin;
            }
        }
    }

    private void stepTwo() {
        int[] rowHasSquare = new int[matrix.length];
        int[] colHasSquare = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0 && rowHasSquare[i] == 0 && colHasSquare[j] == 0) {
                    rowHasSquare[i] = 1;
                    colHasSquare[j] = 1;
                    row[i] = j;
                    col[j] = i;
                }
            }
        }
    }

    private void stepThree() {
        for (int i = 0; i < col.length; i++) {
            colCovered[i] = col[i] != -1 ? 1 : 0;
        }
    }

    private void stepSeven() {
        // Find the smallest uncovered value in the matrix
        int minUncoveredValue = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (rowCovered[i] == 1) {
                continue;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (colCovered[j] == 0 && matrix[i][j] < minUncoveredValue) {
                    minUncoveredValue = matrix[i][j];
                }
            }
        }

        if (minUncoveredValue > 0) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (rowCovered[i] == 1 && colCovered[j] == 1) {
                        matrix[i][j] += minUncoveredValue;
                    } else if (rowCovered[i] == 0 && colCovered[j] == 0) {
                        matrix[i][j] -= minUncoveredValue;
                    }
                }
            }
        }
    }

    private int[] stepFour() {
        for (int i = 0; i < matrix.length; i++) {
            if (rowCovered[i] == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 0 && colCovered[j] == 0) {
                        zeroRow[i] = j;
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

    private void stepSix(int[] mainZero) {
        int i;
        int j = mainZero[1];

        Set<int[]> K = new LinkedHashSet<>();
        K.add(mainZero);
        boolean found = false;
        do {
            if (col[j] != -1) {
                K.add(new int[]{col[j], j});
                found = true;
            } else {
                found = false;
            }
            if (!found) {
                break;
            }
            i = col[j];
            j = zeroRow[i];
            if (j != -1) {
                K.add(new int[]{i, j});
                found = true;
            } else {
                found = false;
            }

        } while (found);

        for (int[] zero : K) {
            if (col[zero[1]] == zero[0]) {
                col[zero[1]] = -1;
                row[zero[0]] = -1;
            }
            if (zeroRow[zero[0]] == zero[1]) {
                row[zero[0]] = zero[1];
                col[zero[1]] = zero[0];
            }
        }

        Arrays.fill(zeroRow, -1);
        Arrays.fill(rowCovered, 0);
        Arrays.fill(colCovered, 0);
    }
}