package com.beyond;

public class array {
    public static void main(String[] args) {
        int[][] array = new int[3][4];
        boolean instance = int[].class.isInstance(array);
        System.out.println(instance);
    }

    private static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (int[].class.isInstance(array[i])) {
                print(int[].class.cast(array[i]));
            }
            System.out.print(array[i]);
        }
        System.out.println();
    }
}
