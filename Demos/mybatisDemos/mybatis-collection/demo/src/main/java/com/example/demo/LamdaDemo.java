package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author beyondlov1
 * @date 2020/01/18
 */
public class LamdaDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> collect = list.stream().filter(x -> x == 2).collect(Collectors.toList());
        System.out.println(collect);
    }
}
