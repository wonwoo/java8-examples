package me.wonwoo.exam22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Created by wonwoo on 16. 1. 9..
 */
public class HigherOrderFunctionExamples {

    //function ( other function ) higher-order function 고계함수 고차 함수 HOF
    //return function();

    //Function<Function<Integer, String>, String>
    //f = x -> x.apply(10)
    //f.apply(i -> "#" + i)

    //Function<Integer, Function<Integer, Integer>>
    //f = i -> i2 -> i + i2
    //f.apply(1).apply(9)

    public static void main(String[] args) {

        //f = higher-order Function
        final Function<Function<Integer, String>, String> f = g -> g.apply(10);
        //(i -> "#" + i) -> 10 -> "#" + 10 ==
        System.out.println(
                f.apply(i -> "#" + i) // "#10"
        );

//        final Function<Integer, Function<Integer, Integer>> f2 = i -> (i2 -> i + i2);
        final Function<Integer, Function<Integer, Integer>> f2 = i -> i2 -> i + i2;

        System.out.println(
                f2.apply(1).apply(9) // 10
        );

        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<String> mappedList = map(list, i -> "#" + i);
        System.out.println(
                mappedList
        );

        System.out.println(
                list.stream()
                        .filter(i -> i > 2)
                        .map(i -> "#" + i)
                        .collect(toList())
        );

        //return function
        //Function.identity();

        Function<Integer, Function<Integer, Function<Integer, Integer>>> f3 =
                i1 -> i2 -> i3 -> i1 + i2 + i3;

        System.out.println(
            "f3.apply(1).apply(2).apply(3) = " + f3.apply(1).apply(2).apply(3)
        );

//        Function<Integer, Function<Integer, Integer>> applied1 = f3.apply(1);
//        Function<Integer, Integer> applied2 = applied1.apply(2);
//        Integer sum = applied2.apply(3);
//        System.out.println(sum);

        Function<Integer, Function<Integer, Integer>> plus10 = f3.apply(10);
        System.out.println(
                "plus10.apply(1).apply(1) = " + plus10.apply(1).apply(1)
        );



    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }
}
