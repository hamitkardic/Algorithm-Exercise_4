package org.example;

import java.util.Arrays;

public class SortingAlgorithms {

    static int shellSort(int arr[]) {
        int n = arr.length;
        int operations = 0;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                    operations++;
                }
                arr[j] = temp;
            }
        }

        return operations;
    }

    static int mergeSort(int arr[]) {
        int n = arr.length;
        int operations = mergeSortHelper(arr, 0, n - 1);
        return operations;
    }

    static int mergeSortHelper(int arr[], int l, int r) {
        int operations = 0;
        if (l < r) {
            int m = (l + r) / 2;
            operations += mergeSortHelper(arr, l, m);
            operations += mergeSortHelper(arr, m + 1, r);
            operations += merge(arr, l, m, r);
        }
        return operations;
    }

    static int merge(int arr[], int l, int m, int r) {
        int operations = 0;
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = Arrays.copyOfRange(arr, l, l + n1);
        int R[] = Arrays.copyOfRange(arr, m + 1, m + 1 + n2);

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
            operations++;
        }

        while (i < n1) {
            arr[k++] = L[i++];
            operations++;
        }

        while (j < n2) {
            arr[k++] = R[j++];
            operations++;
        }

        return operations;
    }

    public static void main(String args[]) {
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 1000);
        }

        int[] arrayCopyShellSort = Arrays.copyOf(array, array.length);
        int[] arrayCopyMergeSort = Arrays.copyOf(array, array.length);

        int shellSortOperations = shellSort(arrayCopyShellSort);
        System.out.println("Shell Sort Operations: " + shellSortOperations);

        int mergeSortOperations = mergeSort(arrayCopyMergeSort);
        System.out.println("Merge Sort Operations: " + mergeSortOperations);
    }
}
