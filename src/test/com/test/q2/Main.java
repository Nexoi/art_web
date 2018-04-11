package com.test.q2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int arr[] = {3, 2, 4, 5};
        int[] result = itr(arr, arr.length, 0);
    }

    public static int[] itr(int[] arr, int arrLength, int index, int... params) {
        for (int i = 0; i < arr[index]; i++) {
            dosomething(params);
            int[] newParams = Arrays.copyOf(params, params.length + 1);
            newParams[params.length] = i;
            dosomething(newParams);
            return itr(Arrays.copyOfRange(arr, 1, arr.length - 1),  arrLength,index + 1, newParams);
        }
        return null;
    }

    public static void dosomething(int... params) {
        for (int i : params) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}


