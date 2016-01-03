package me.wonwoo.exam05;

import java.util.function.Consumer;

/**
 * Created by wonwoo on 15. 12. 28..
 */
public class FunctionalInterfaceConsumerExamples {
    public static void main(String[] args) {

        final Consumer<String> print = new Consumer<String>() {
            @Override
            public void accept(String value) {
                System.out.println(value);
            }
        };
        print.accept("Hello");


        final Consumer<String> printLambda = value -> System.out.println(value);
        printLambda.accept("Hello world");

        final Consumer<String> greeting = value -> System.out.println("Hello " + value);
        greeting.accept("wonwoo");

//        final Function<String,Void> printFunction = value -> System.out.println(value);

    }
}
