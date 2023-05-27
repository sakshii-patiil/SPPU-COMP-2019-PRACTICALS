package com.java.ai;

public class n_queen_backtracking {

    public static void nQueensArrangement(String arr[][], int rows) {
        if (rows == arr.length) {
            printBoard(arr);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (isSafe(arr, rows, i)) {
                arr[rows][i] = "Q"; // Initially Q is set
                nQueensArrangement(arr, rows + 1);
                arr[rows][i] = "."; // Q is replaced with '.' after backtracking
            }
        }
    }

    public static boolean isSafe(String arr[][], int rows, int col) {
        for (int i = rows - 1; i >= 0; i--) {
            if (arr[i][col] == "Q") {
                return false;
            }
        }

        for (int i = col - 1, j = rows - 1; i >= 0 && j >= 0; i--, j--) {
            if (arr[j][i] == "Q") {
                return false;
            }
        }

        for (int i = rows - 1, j = col + 1; i >= 0 && j < arr.length; i--, j++) {
            if (arr[i][j] == "Q") {
                return false;
            }
        }
        return true;
    }

    public static void printBoard(String arr[][]) {
        System.out.println("\n---------------- Print Board ----------------- ");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        String board[][] = new String[8][8];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ".";
            }
        }

        nQueensArrangement(board, 0);
    }
}