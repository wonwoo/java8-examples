package me.wonwoo.exam12;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by wonwoo on 16. 1. 2..
 */
public class StreamExamples1 {
    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> System.out.print(i + " "));
        System.out.println();
        IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(i + " "));
        System.out.println();
//        IntStream.iterate(1, i -> i + 1).forEach(i -> System.out.print(i + " "));

        Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE)).forEach(i -> System.out.print(i + " "));
    }
}
