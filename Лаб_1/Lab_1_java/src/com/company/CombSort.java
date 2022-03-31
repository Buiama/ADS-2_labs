package com.company;

public class CombSort {

    public static void combSort(int[] arr) {
        int buf, comparison = 0, swap = 0;
        int step = arr.length;
        double constant = 1.24733095;

        while (step>1) {
            step = (int) (step/constant);
            for(int i = 0; step+i<arr.length; i++) {
                comparison++;
                if(arr[i]>arr[i+step]) {
                    buf = arr[i];
                    arr[i] = arr[i+step];
                    arr[i+step] = buf;
                    swap++;
                }
            }
        }
        System.out.println("\nComparisons = " + comparison + "; Swaps = " + swap);
    }
}
