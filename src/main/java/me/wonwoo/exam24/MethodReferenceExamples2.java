package me.wonwoo.exam24;

import java.math.BigDecimal;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;

/**
 * Created by wonwoo on 16. 1. 9..
 */
public class MethodReferenceExamples2 {
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5)
                .forEach(System.out::println);
//                .forEach(i -> System.out.println(i));

        System.out.println(
                Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                        .stream()
                        .sorted(BigDecimalUtil::compare)
//                        .sorted((bd1, bd2) -> bd1.compareTo(bd2))
                        .collect(toList())
        );

        System.out.println(
                Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                        .stream()
                        .sorted(BigDecimal::compareTo)
//                        .sorted((bd1, bd2) -> bd1.compareTo(bd2))
                        .collect(toList())
        );

        final String targetString = "c";
        System.out.println(
                Arrays.asList("a", "b", "c", "d")
                        .stream()
//                        .anyMatch(targetString::equals)
                        .anyMatch("c"::equals)
//                        .anyMatch(x -> x.equals("c"))
        );
    }
}


class BigDecimalUtil{
    public static int compare(BigDecimal bd1, BigDecimal bd2){
        return bd1.compareTo(bd2);
    }
}