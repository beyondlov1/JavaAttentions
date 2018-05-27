package com.beyond.test;

import java.util.Arrays;

public class FindTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a = new int[] { 1, 4, 10, 8, 30, 2, 19, 40, 34, 21 };
		Arrays.sort(a);
		for (int c : a) {
			System.out.println(c);
		}

		int binarySearch = Arrays.binarySearch(a, 34);
		System.out.println(binarySearch);

		int value = 35;
		int low = 0;
		int high = a.length - 1;

		while (low <= high) {
			int middle = (int) Math.floor((low + high) / 2);
			if (value == a[middle]) {
				System.out.println(middle);
				return;
			}

			if (value < a[middle]) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		System.out.println("没有此数");
	}

}
