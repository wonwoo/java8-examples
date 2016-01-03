package me.wonwoo.exam11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by wonwoo on 16. 1. 2..
 */
public class StreamPrelude {
    public static void main(String[] args) {

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        System.out.println("mapOld(numbers, i -> i * 2)");
        System.out.println(
                mapOld(numbers, i -> i * 2)
        );

        System.out.println("\nmapOld(numbers, null)");
        System.out.println(
                mapOld(numbers, null)
        );

        System.out.println("\n");
        System.out.println("map(numbers , i -> i *2)\n" +
                        mapOld2(numbers, i -> i * 2)
        );

        System.out.println("\nmap(numbers, null)\n" +
                        mapOld2(numbers, null)
        );


        System.out.println("\n");
        System.out.println("map(numbers , i -> i *2)\n" +
                        map(numbers, i -> i * 2)
        );

        System.out.println("\nmap(numbers, t -> t)\n" +
                        map(numbers, t -> t)
        );

        System.out.println("\nmap(numbers, Function.identity())\n" +
                        map(numbers, Function.identity())
        );
    }


    private static <T, R> List<R> map(final List<T> list, final Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }

    private static <T, R> List<R> mapOld2(final List<T> list, final Function<T, R> mapper) {
        final Function<T, R> function;
        if (mapper != null) {
            function = mapper;
        } else {
            function = t -> (R) t;
        }
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    private static <T, R> List<R> mapOld(final List<T> list, final Function<T, R> mapper) {
        final List<R> result;
        if (mapper != null) {
            result = new ArrayList<>();
        } else {
            result = new ArrayList<>((List<R>) list);
        }
        if (result.isEmpty()) {
            for (final T t : list) {
                result.add(mapper.apply(t));
            }
        }
        return result;
    }
}
