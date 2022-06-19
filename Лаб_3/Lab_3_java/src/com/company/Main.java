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
        int max = 1, sum = 1; // sum to increase efficiency (not write Integer.MAX_VALUE)
        // max is for a beautiful output))
        int[][] matrixWeight;
        char yes = yn.charAt(0);
        /*---------------------Making matrix---------------------------*/
        // - Generating
        if (yes == 'y' || yes == 'Y' || yes == '+' || yes == '1') {
            System.out.println("You chose automatic generation!");
            System.out.print("Enter the number of graph vertices: ");
            topCount = in.nextInt();
            matrixWeight = new int[topCount][topCount];
            for (int i = 0; i < topCount; i++) {
                for (int j = 0; j < topCount; j++) {
                    if (i < j) {
                        random = (int) (Math.random() * 4);
                        if (random == 0) {
                            matrixWeight[i][j] = (int) (Math.random() * 51 + 1);
                        } else if (random == 1) {
                            matrixWeight[j][i] = (int) (Math.random() * 51 + 1);
                            matrixWeight[i][j] = 0;
                            if (max < matrixWeight[j][i]) max = matrixWeight[j][i];
                        } else if (random == 2) {
                            matrixWeight[i][j] = (int) (Math.random() * 51 + 1);
                            matrixWeight[j][i] = matrixWeight[i][j];
                            sum += matrixWeight[i][j];
                        } else {
                            matrixWeight[i][j] = 0;
                        }
                    }
                    if (max < matrixWeight[i][j]) max = matrixWeight[i][j];
                    sum += matrixWeight[i][j];
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
            for (int i = 0; i < topCount; i++) {
                for (int j = 0; j < topCount; j++) {
                    matrixWeight[i][j] = scan.nextInt();
                    if (max < matrixWeight[i][j]) max = matrixWeight[i][j];
                    sum += matrixWeight[i][j];
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
        /*---------------------Find the shortest distance to each---------------------------*/
        System.out.print("\nEnter start vertex: ");
        int start = in.nextInt() - 1;
        int[] minDist = new int[topCount];
        int[] vert = new int[topCount];
        int minIndex, min;
        for (int i = 0; i < topCount; i++) {
            minDist[i] = sum;
            vert[i] = 1;
        }
        minDist[start] = 0;
        do {
            minIndex = sum;
            min = minIndex;
            for (int i = 0; i < topCount; i++) {
                if ((vert[i] == 1) && (minDist[i] < min)) {
                    min = minDist[i];
                    minIndex = i;
                }
            }
            if (minIndex != sum) {
                for (int i = 0; i < topCount; i++) {
                    if (matrixWeight[minIndex][i] > 0) {
                        if (min + matrixWeight[minIndex][i] < minDist[i]) {
                            minDist[i] = min + matrixWeight[minIndex][i];
                        }
                    }
                }
                vert[minIndex] = 0;
            }
        } while (minIndex < sum);
        /*---------------------Found---------------------------*/
        /*---------------------Output distances---------------------------*/
        System.out.println("\nShortest distance to each vertex:");
        for (int i = 0; i < topCount; i++) {
            System.out.print(minDist[i] + "   ");
        }
        /*---------------------Wrote distances---------------------------*/
        /*---------------------Looking for a way to our top---------------------------*/
        System.out.print("\n\nEnter end vertex: ");
        int end = in.nextInt() - 1;
        int endToOutput = end;
        int[] way = new int[topCount];
        int k = 1; // индекс предыдущей вершины
        int weight = minDist[end]; // вес конечной вершины
        way[0] = end + 1;

        while (end != start)
        {
            for (int i = 0; i < topCount; i++) { // просматриваем все вершины
                if (matrixWeight[i][end] != 0)
                {
                    if (weight - matrixWeight[i][end] == minDist[i])
                    {
                        weight = weight - matrixWeight[i][end]; // сохраняем новый вес
                        end = i;       // сохраняем предыдущую вершину
                        way[k] = i + 1;
                        k++;
                    }
                }
            }
        }
        /*---------------------Found a way---------------------------*/
        /*---------------------Output way---------------------------*/
        System.out.println("\nShortest way " + (start + 1) + " -> " + (endToOutput + 1) + ":");
        for (int i = k - 1; i >= 0; i--) {
            System.out.print(way[i] + "  ");
        }
    }
}
