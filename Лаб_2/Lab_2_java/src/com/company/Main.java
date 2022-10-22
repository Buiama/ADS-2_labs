package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in); // для считывания с консоли
        System.out.print("Name of text file: ");
        String n = in.nextLine();
        File file = new File(n+".txt"); // открываем заданный файл

        Scanner scan = new Scanner(file); // для считывания с файла
        int u = scan.nextInt();
        System.out.println("\nU = "+u);
        int m = scan.nextInt();
        System.out.println("M = "+m);

        int[][] matrixD = new int[u][m+1]; // считываем матрицу данных D
        for(int i = 0; i < u; i++) {
            for(int j = 0; j< m+1; j++) {
                matrixD[i][j] = scan.nextInt();
            }
        }

        System.out.println("\nYour file:");
        output(matrixD, u, m+1);

        System.out.print("\nSome user number x =  ");
        int x = in.nextInt();

        int[][] matrix = new int[u-1][m]; // матрица с нужными данными (без нумерации и заданного пользователя)
        for(int i = 0; i < x-1; i++) {
            System.arraycopy(matrixD[i], 1, matrix[i], 0, m);
            // for(int j = 0;j<m; j++) { matrix[i][j] = matrixD[i][j+1]; }
        }
        // чтобы без лишних условий, разобьем на два цикла
        for(int i = x-1; i < u-1; i++) {
            System.arraycopy(matrixD[i + 1], 1, matrix[i], 0, m);
            // for(int j = 0; j<m; j++) { matrix[i][j] = matrixD[i+1][j+1]; }
        }


        System.out.println("\nNew matrix:");
        output(matrix, u-1, m);

        combSort(matrix, u-1, m, x, matrixD); // сортируем рассческой,
        // потому что нет повторяющихся элементов, а значит устойчивость не нужна

        System.out.println("\n\nComb sorted matrix:");
        output(matrix,u-1,m);


        int[][] inv = new int[u-1][2]; // финальная матрица с номером пользователя и кол-вом инверсий

        for(int i = 0; i < x-1; i++) {
            inversion = 0; // обнуляем счетчик инверсий
            matrix[i] = mergeSort(matrix[i]); // сортируем слиянием и считаем инверсии
            inv[i][0] = i+1; // нумерация пользователей
            inv[i][1] = inversion;
            System.out.print("\nInversions ["+(i+1)+"] = "+inversion);
        }
        // чтобы без лишних условий, разобьем на два цикла
        for(int i = x-1; i < u-1; i++) {
            inversion = 0;
            matrix[i] = mergeSort(matrix[i]); // сортируем слиянием и считаем инверсии
            inv[i][0] = i+2;
            inv[i][1] = inversion;
            System.out.print("\nInversions ["+(i+2)+"] = "+inversion);
        }


        bubbleSort(inv, u-1); // сортируем пузырьком,
        // потому что есть повторяющиеся элементы, а значит устойчивость нужна, расческа не подойдет

        System.out.println("\n\nBubble sorted array:");
        output(inv, u-1, 2);

        // записываем в файл
        FileWriter writer = new FileWriter("ip15_Buialo_05_output_3.txt");
        writer.write(x +"\n");
        for(int i = 0; i < u-1; i++) {
            for(int j = 0; j < 2; j++) {
                writer.write(inv[i][j]+" ");
            }
            writer.write("\n");
        }
        writer.write(x +"");
        writer.close(); // закрываем поток
    }


    public static void output(int[][] matrix, int n, int m) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }


    public static void combSort(int[][] matrix, int u, int m, int x, int[][] matrixD) {
        int step;
        double constant = 1.24733095;
        int[] arrX = new int[m];

        for(int i = 0; i < u; i++) {

            System.arraycopy(matrixD[x - 1], 1, arrX, 0, m);
            // for(int j=0; j<m; j++) { arrX[j] = matrixD[x-1][j+1];}
            step = m;
            while (step > 1) {
                step = (int) (step/constant);
                for(int j = 0; step+j < m; j++) {
                    // сортируем списки пользователей, будто список пользователя х
                    if(arrX[j] > arrX[j+step]) {
                        matrix[i][j + step] += matrix[i][j]; // buf = matrix[i][j];
                        matrix[i][j] = matrix[i][j + step] - matrix[i][j]; // matrix[i][j] = matrix[i][j+step];
                        matrix[i][j + step] -= matrix[i][j]; // matrix[i][j+step] = buf;

                        arrX[j + step] += arrX[j];
                        arrX[j] = arrX[j + step] - arrX[j];
                        arrX[j + step] -= arrX[j];
                    }
                }
            }
        }
    }


    public static void bubbleSort(int[][] inv, int u) {
        int[] buf = new int[2];
        int k = 0;
        boolean isSorted = false; // проверка на отсортированность, залог естественности

        while(!isSorted) { // идеальный пузырек личной разработки
            isSorted = true;

            for (int i = u - 1; i > k; i--) {//int i = 0; i <arr.length - k - 1; i++  тогда в свапе тоже с плюсом
                if (inv[i - 1][1] > inv[i][1]) { // сортируем по второму столбцу матрицы (степень похожести)
                    isSorted = false;

                    for (int j = 0; j < 2; j++) {
                        buf[j] = inv[i - 1][j];
                        inv[i - 1][j] = inv[i][j];
                        inv[i][j] = buf[j];
                    }
                }
            }
            k++;
        }
    }


    private static int inversion = 0;

    public static int[] merge(int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        int l = left.length;
        int r = right.length;
        int[] c = new int[l + r];

        while (i < l || j < r) {
            if (j == r || (i < l && left[i] <= right[j])) {
                c[k] = left[i];
                k++;
                i++;
                inversion += k - i; // считаем инверсии
            }
            else {
                c[k] = right[j];
                k++;
                j++;
            }
        }
        return c;
    }


    public static int[] mergeSort(int[] array) {
        int a = array.length;
        if (a == 1) return array;
        int k = 0; // счетчик задействуется и в левой части, и в правой

        int[] left = new int[a/2]; // new int[(int) Math.ceil(a / 2)];
        for (int i = 0; i < left.length; i++) {
            left[i] = array[k];
            k++;
        }

        int[] right = new int[a - left.length];
        for (int i = 0; i < right.length; i++) {
            right[i] = array[k];
            k++;
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }
}



//Рассческа для сортировки массива с инверсиями
/*int step = u-1;
double constant = 1.24733095;

while (step>1) {
    step = (int) (step/constant);
    for(int i = 0; step+i<u-1; i++) {
        if(inv[i][1]>inv[i+step][1]) {
            for (int j = 0; j < 2; j++) {
                buf[j] = inv[i][j];
                inv[i][j] = inv[i+step][j];
                inv[i+step][j] = buf[j];
            }
        }
    }
}*/


// Пузырёк для сортировки массива по заданному пользователю
/*int k; // int buf;
int comparison = 0, swap = 0;
boolean isSorted; // проверка на отсортированность, залог естественности
for(int i=0;i<u;i++) {
    k = -1;
    isSorted = false;
    while (!isSorted) { // идеальный пузырек личной разработки
        isSorted = true;
        k++;// самодельный счетчик

        for (int j = 1; j < m - k; j++) {
            comparison++; // кол-во сравнений
            if (arr[w - 1][j] > arr[w - 1][j + 1]) {
                isSorted = false; // если без/c использования третьей переменной:

                arr[i][j + 1] += arr[i][j]; // buf = arr[i][j];
                arr[i][j] = arr[i][j + 1] - arr[i][j]; // arr[i][j] = arr[i][j+1];
                arr[i][j + 1] -= arr[i][j]; // arr[i][j+1] = buf;
                swap++; // кол-во перестановок
                if(i!=w-1) {
                    arr[w - 1][j + 1] += arr[w - 1][j];
                    arr[w - 1][j] = arr[w - 1][j + 1] - arr[w - 1][j];
                    arr[w - 1][j + 1] -= arr[w - 1][j];
                }
            }
        }
    }

    for(int j = 1; j < m+1; j++) { //System.arraycopy(arrW, 0, arr[w - 1], 1, m + 1 - 1);
        arr[w-1][j]=arrW[j-1];
    }
}*/