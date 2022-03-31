package com.company;

import java.util.Scanner;

import static com.company.BubbleSort.bubbleSort;
import static com.company.CombSort.combSort;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n]; // кол-во элементов в массиве

        for(int i = 0; i < n; i++) {
            arr[i] = n-i;//in.nextInt();//arr[i] = (int)(Math.random() * (double)(n + 1));
        }

        System.out.print("Random array:");
        output(n, arr);

        //bubbleSort(arr);
        combSort(arr);

        System.out.print("\nSorted array:");
        output(n, arr);
    }


    public static void output(int n, int[] arr) {
        System.out.print("\n[");

        for(int i = 0; i < n - 1; i++) {
            System.out.print(arr[i] + ", ");
            if (i != 0 && i % 27 == 0) {
                System.out.println();
            }
        }

        System.out.print(arr[n - 1] + "]\n");
    }
}




/*
        boolean isSorted = false;
        while(!isSorted) {
            isSorted = true;

            for(int i = 0; i < arr.length - 1; i++) {
                comparison++;
                if (arr[i] > arr[i + 1]) {
                    isSorted = false;

                    buf = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = buf;
                    swap++;
                }
            }
        }
*/

/*
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                comparison++;
                if (arr[j - 1] > arr[j]) {
                    buf = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = buf;
                    swap++;
                }
            }
        }
*/