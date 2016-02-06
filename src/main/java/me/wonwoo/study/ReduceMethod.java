package me.wonwoo.study;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * Created by wonwoo on 2016. 2. 7..
 */
public class ReduceMethod {
    public static void main(String[] args) {


        System.out.println(
                Arrays.asList(100, 200, 300, 400)
                        .stream()
                        .reduce((s1, s2) -> s1 + s2)
                        .get()
        );

        System.out.println(
                Arrays.asList(new BigDecimal(100), new BigDecimal(200), new BigDecimal(300), new BigDecimal(400))
                        .stream()
                        .reduce(BigDecimal::add)
                        .get()
        );

        Predicate<Integer> predicate = i -> i > 4;
//        IntPredicate intPredicate = i -> i > 5;
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .stream()
                .filter(predicate)
                .forEach(System.out::println);

        BinaryOperator<String> stringBinaryOperator = (s1, s2) -> s1.concat(s2);
        System.out.println(stringBinaryOperator.apply("wonwoo", "lee"));

    }
}
