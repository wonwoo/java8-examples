package me.wonwoo.exam06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by wonwoo on 15. 12. 28..
 */
public class FunctionalInterfacePredicateExamples {
    public static void main(String[] args) {

        Predicate<Integer> isPositive = i -> i > 0;

        System.out.println(isPositive.test(1));
        System.out.println(isPositive.test(0));
        System.out.println(isPositive.test(-1));

        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        List<Integer> positiveNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            if (isPositive.test(number)) {
                positiveNumbers.add(number);
            }
        }
        System.out.println("positive integers : " + positiveNumbers);


        Predicate<Integer> lessThan3 = i -> i < 3;
        List<Integer> numbersLessThan3 = new ArrayList<>();
        for (Integer number : numbers) {
            if (lessThan3.test(number)) {
                numbersLessThan3.add(number);
            }
        }
        System.out.println("lessThan3 integers : " + numbersLessThan3);

        System.out.println("filter positive integers : " + filter(numbers, isPositive));

        System.out.println("filter lessThan3 integers : " + filter(numbers, lessThan3));

        System.out.println("filter lessThan3 integers : " + filter(numbers, i -> i > 0));

        System.out.println("filter lessThan3 integers : " + filter(numbers, i -> i < 3));

    }

    private static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T input : list) {
            if (filter.test(input)) {
                result.add(input);
            }
        }
        return result;
    }
}
