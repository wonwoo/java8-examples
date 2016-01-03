package me.wonwoo.exam14;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by wonwoo on 16. 1. 3..
 */
public class StreamExamples3 {

//    stream lazy
//    중간단계 Stream 을 리턴하기 때문에 계속 Method Chaining을 통해 무엇을 해야 할지 스트림에게 지시 할 수 있다.
//    Intermediate Operation Method
//    끝내는 메소드 호출하는 순간 요청했던 Intermediate Method 진행
//    Terminal Operation Method

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream();

        //[#6, #6, #10, #10]
        System.out.println("collect(toList(): " +
//                        Stream.of(1, 2, 3, 4, 5)
                        Stream.of(1, 3, 3, 5, 5)
                                .filter(i -> i > 2)
                                .map(i -> i * 2)
                                .map(i -> "#" + i)
                                .collect(toList())
        );

        //[#10, #6]
        System.out.println("collect(toSet(): " +
                        Stream.of(1, 3, 3, 5, 5)
                                .filter(i -> i > 2)
                                .map(i -> i * 2)
                                .map(i -> "#" + i)
                                .collect(toSet())
        );

        //#6#6#10#10
        System.out.println("collect(joining(): " +
                        Stream.of(1, 3, 3, 5, 5)
                                .filter(i -> i > 2)
                                .map(i -> i * 2)
                                .map(i -> "#" + i)
                                .collect(joining())
        );


        //#6, #6, #10, #10
        System.out.println("collect(joining(\", \"): " +
                        Stream.of(1, 3, 3, 5, 5)
                                .filter(i -> i > 2)
                                .map(i -> i * 2)
                                .map(i -> "#" + i)
                                .collect(joining(", "))
        );

        //[#6, #6, #10, #10]
        System.out.println("collect(joining(\", \", \"[\", \"]\"): " +
                        Stream.of(1, 3, 3, 5, 5)
                                .filter(i -> i > 2)
                                .map(i -> i * 2)
                                .map(i -> "#" + i)
                                .collect(joining(", ", "[", "]"))
        );

        //[#6, #10]
        System.out.println("distinct().collect(joining(\", \", \"[\", \"]\") : " +
                        Stream.of(1, 3, 3, 5, 5)
                                .filter(i -> i > 2)
                                .map(i -> i * 2)
                                .map(i -> "#" + i)
                                .distinct() // 유일한 값
                                .collect(joining(", ", "[", "]"))
        );

        //[#6, #10] List
        System.out.println("distinct().collect(toList()): " +
                        Stream.of(1, 3, 3, 5, 5)
                                .filter(i -> i > 2)
                                .map(i -> i * 2)
                                .map(i -> "#" + i)
                                .distinct() // 유일한 값
                                .collect(toList())
        );

        //new Integer(1) ??
        //Integer.valueOf(1)

        //java -Djava.lang.Integer.IntegerCache.high=255

        final Integer integer3 = 3;
        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .filter(i -> i == integer3)
                        .findFirst()
        );

        final Integer integer127 = 127;
        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 127)
                        .filter(i -> i == integer127)
                        .findFirst()
        );

        final Integer integer128 = 128;
        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 128)
                        .filter(i -> i == integer128)
                        .findFirst()
        );

        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 128)
                        .filter(i -> i.equals(integer128))
                        .findFirst()
        );

        System.out.println(".filter(i -> i > integer3).count(): " +
                        Stream.of(1, 2, 3, 4, 5)
                                .filter(i -> i > integer3)
                                .count()
        );


        System.out.println("for (Integer i : numbers): ");
        for (Integer i : numbers) {
            System.out.print("i = " + i + " ");
        }

        //stream은 Internal Iterator
        System.out.println("\nforEach(i -> System.out.println(i)): ");
        Stream.of(1, 2, 3, 4, 5)
                .forEach(i -> System.out.print(i + " "));

    }
}
