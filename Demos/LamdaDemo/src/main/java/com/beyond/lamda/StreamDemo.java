package com.beyond.lamda;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author beyondlov1
 * @date 2019/10/15
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream()
                .filter(string -> !string.isEmpty())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(filtered);

        strings.forEach(System.out::println);

        String joined = strings.stream().collect(Collectors.joining(","));
        System.out.println(joined);

        IntSummaryStatistics intSummaryStatistics = strings.stream().mapToInt(String::length).summaryStatistics();
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getMax());

        List<String> collect = strings.stream().map(s -> s + s).collect(Collectors.toList());
        System.out.println(collect);

        Map<String, List<String>> collect1 = strings.stream().map(s -> {
            List<String> list = new ArrayList<>();
            list.add(s + s);
            return list;
        }).collect(Collectors.toMap(
                ls -> ls.toString() + Math.random(),
                ls -> ls
        ));
        System.out.println(collect1);

        LinkedHashMap<String, List<String>> linkedHashMap = strings.stream().map(s -> {
            List<String> list = new ArrayList<>();
            list.add(s + s);
            return list;
        }).collect(Collectors.toMap(Object::toString, ls -> ls, (ov, nv) -> nv, LinkedHashMap::new));
        System.out.println(linkedHashMap);
    }
}
