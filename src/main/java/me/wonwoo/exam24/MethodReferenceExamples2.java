package me.wonwoo.exam24;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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

        System.out.println("\n===============================================");
        System.out.println("methodReference03();");
        System.out.println("\n-----------------------------------------------");
        methodReference03();
    }

    //first class function
    // 1. function(function())
    // 2. return function(p)
    // 3. F f = function()
    // List<F> fs = Arrays.asList(function);
    //              list.add(function)

    private static void methodReference03() {
        /* First class Function */

        /**
         A function can be passed as a parameter to another function
         */

        /*
            Using Lambda Expression
         */
        System.out.println(
                testFirstClassFunction1(3, i -> String.valueOf(i * 2))
        );
        /*
            Using Method Reference
         */
        System.out.println(
                testFirstClassFunction1(3, MethodReferenceExamples2::doubleThenToString)
        );

        /**
         * A function can be returned as the result of another function.
         */
        /*
            Using Lambda Expression
         */


        final Function<Integer, String> fl = getDoubleThenToStringUsingLambdaExpression();
        final String resultFromFl = fl.apply(3);
        System.out.println(resultFromFl);

        /*
            Using Method Reference
         */

        final Function<Integer, String> fmr = getDoubleThenToStringUsingMethodReference();
        final String resultFromFmr = fmr.apply(3);
        System.out.println(resultFromFmr);

        System.out.println("\n-----------------------------------------------");
        /**
         * A function can be stored in the data structure
         */
        /*
            Using Lambda Expression
         */
        final List<Function<Integer, String>> fsL = Arrays.asList(i -> String.valueOf(i * 2));
        for (final Function<Integer, String> f : fsL) {
            final String result = f.apply(3);
            System.out.println(result);
        }

        /*
            Using Method Reference
         */

        final List<Function<Integer, String>> fsMr = Arrays.asList(MethodReferenceExamples2::doubleThenToString);
        for (final Function<Integer, String> f : fsMr) {
            final String result = f.apply(3);
            System.out.println(result);
        }
        System.out.println("\n-----------------------------------------------");

        /*
            Using Lambda Expression
         */
        final Function<Integer, String> fl2 = i -> String.valueOf(i * 2);
        final String resultFl2 = fl2.apply(5);
        System.out.println(resultFl2);

        /*
            Using Method Reference
         */
        final Function<Integer, String> fmr2 = MethodReferenceExamples2::doubleThenToString;
        final String resultFmr2 = fmr2.apply(5);
        System.out.println(resultFmr2);

        System.out.println("\n-----------------------------------------------");
        
        /*
        Both Lambda Expression and Method Reference
         */

        List<Function<Integer, String>> fsBoth =
                Arrays.asList(
                        i -> String.valueOf(i * 2),
                        MethodReferenceExamples2::doubleThenToString,
                        MethodReferenceExamples2::addHashPrefix);
        for (final Function<Integer, String> f : fsBoth) {
            String result = f.apply(7);
            System.out.println(result);
        }

    }

    private static String doubleThenToString(int i) {
        return String.valueOf(i * 2);
    }

    private static String addHashPrefix(int number){
        return "#" + number;
    }

    private static String testFirstClassFunction1(int n, Function<Integer, String> f) {
        return "The result is " + f.apply(n) + ".";
    }

    private static Function<Integer, String> getDoubleThenToStringUsingLambdaExpression() {
        return i -> String.valueOf(i * 2);
    }

    private static Function<Integer, String> getDoubleThenToStringUsingMethodReference() {
        return MethodReferenceExamples2::doubleThenToString;
    }
}


class BigDecimalUtil {
    public static int compare(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2);
    }
}