package me.wonwoo.exam23;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by wonwoo on 16. 1. 9..
 */
public class MethodReferenceExamples {
    public static void main(String[] args) {
        //first class
        //function == method
        Arrays.asList(1, 2, 3, 4, 5)
                .forEach(System.out::println);
//                .forEach(i -> System.out.println(i));
    }
}
