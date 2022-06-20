package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.print("Generate automatically? [Y/N]: ");
        String yn = in.nextLine();
        int topCount, random;
        int max = 1; // max is for a beautiful output))
        int[][] matrixWeight;
        int[][] matrixToOutput;
        char yes = yn.charAt(0);
        /*---------------------Making matrix---------------------------*/
        // - Generating
        if (yes == 'y' || yes == 'Y' || yes == '+' || yes == '1') {
            System.out.println("You chose automatic generation!");
            System.out.print("Enter the number of graph vertices: ");
            topCount = in.nextInt();
            matrixWeight = new int[topCount][topCount];
            matrixToOutput = new int[topCount][topCount];
            for (int i = 0; i < topCount; i++) {
                for (int j = 0; j < topCount; j++) {
                    matrixWeight[i][j] = (int) (Math.random() * 101 + 1);
                    matrixToOutput[i][j] = matrixWeight[i][j];
                    /*if (i <= j) {
                        random = (int) (Math.random() * 18);
                        if (random == 0) {
                            matrixWeight[i][j] = (int) (Math.random() * 101 + 1);
                            matrixToOutput[i][j] = matrixWeight[i][j];
                        } else if (random == 1) {
                            matrixWeight[j][i] = (int) (Math.random() * 101 + 1);
                            matrixWeight[i][j] = 0;
                            matrixToOutput[i][j] = matrixWeight[i][j];
                            if (max < matrixWeight[j][i]) max = matrixWeight[j][i];
                        } else if (random > 1 && random < 16) {
                            matrixWeight[i][j] = (int) (Math.random() * 101 + 1);
                            matrixWeight[j][i] = matrixWeight[i][j];
                            matrixToOutput[i][j] = matrixWeight[i][j];
                        } else {
                            matrixWeight[i][j] = (int) (Math.random() * 101 + 1);
                            matrixToOutput[i][j] = matrixWeight[i][j];
                        }
                    }*/
                    if (max < matrixWeight[i][j]) max = matrixWeight[i][j];
                }
            }
        }
        // - Reading
        else {
            System.out.println("You chose manual generation!");
            File file = new File("Graph.txt");
            Scanner scan = new Scanner(file);
            topCount = scan.nextInt();
            System.out.println("\nYour graph has " + topCount + " vertices");
            matrixWeight = new int[topCount][topCount];
            matrixToOutput = new int[topCount][topCount];
            for (int i = 0; i < topCount; i++) {
                for (int j = 0; j < topCount; j++) {
                    matrixWeight[i][j] = scan.nextInt();
                    matrixToOutput[i][j] = matrixWeight[i][j];
                    if (max < matrixWeight[i][j]) max = matrixWeight[i][j];
                }
            }
        }
        /*---------------------Matrix done---------------------------*/
        /*---------------------Output matrix---------------------------*/
        int len;
        int maxLen = (int) (Math.log10(max) + 1);
        for (int i = 0; i < topCount; i++) {
            for (int j = 0; j < topCount; j++) {
                if (matrixWeight[i][j] == 0) len = 1;
                else len = (int) (Math.log10(matrixWeight[i][j]) + 1);
                System.out.print(matrixWeight[i][j]);
                for (int k = 0; k < (maxLen - len + 3); k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        /*---------------------Wrote matrix---------------------------*/
        /*---------------------Using algorithm---------------------------*/
        Algorithm a = new Algorithm(matrixWeight);
        int[][] assignment = a.findOptimalAssignment();

        System.out.println();

        if (assignment.length > 0) {
            for (int[] ints : assignment) {
                System.out.print("Col" + (ints[0]+1) + " => Row" + (ints[1]+1) + " (" + matrixToOutput[ints[1]][ints[0]] + ")");
                System.out.println();
            }
        }
        else {
            System.out.println("no assignment found!");
        }
    }
}
