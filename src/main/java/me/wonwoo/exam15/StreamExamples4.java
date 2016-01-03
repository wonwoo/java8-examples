package me.wonwoo.exam15;

import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


/**
 * Created by wonwoo on 16. 1. 3..
 */
public class StreamExamples4 {
    public static void main(String[] args) {

        //Intellij
        //Preferences -> Editor -> Code style -> Java -> imports탭 -> package to Use Import with -> (+) java.util.stream.Collectors -> static click
        //Preferences -> Keymap -> optimize imports

        Stream.of(1, 2, 3, 4, 5)
                .collect(toList());

        Stream.of(1, 2, 3, 4, 5)
                .collect(toSet());

        Stream.of(1, 2, 3, 4, 5)
                .map(i -> String.valueOf(i))
                .collect(joining());


        //eclipse
        //Preferences -> Java -> Editor -> Content Assist -> Favorites -> new Type -> Collectors
        //Preferences -> Code Style -> Organize Imports -> Number of static imports needed for .* 기본값 5
    }
}
