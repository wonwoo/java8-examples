package me.wonwoo.exam07;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Created by wonwoo on 15. 12. 28..
 */
public class FunctionalInterfaceSupplierExamples {

    public static void main(String[] args) {

        final Supplier<String> helloSupplier = () -> "Hello ";
        System.out.println(helloSupplier.get() + "World");

        printIfValidIndex(0, "wonwoo");
        printIfValidIndex(1, "wonwoo");
        printIfValidIndex(-1, "wonwoo");

        final long start = System.currentTimeMillis();

        //기존의 자바
//        printIfValidIndex(0, getVeryExpensiveValue());
//        printIfValidIndex(-1, getVeryExpensiveValue());
//        printIfValidIndex(-2, getVeryExpensiveValue());

//        System.out.println("It took " + ((System.currentTimeMillis() - start) / 1000) + "seconds");

//        printIfValidIndexSupplier(0, new Supplier<String>() {
//            @Override
//            public String get() {
//                return getVeryExpensiveValue();
//            }
//        });

        //Lazy Evaluation
        printIfValidIndexSupplier(0, () -> getVeryExpensiveValue());
        printIfValidIndexSupplier(-1, () -> getVeryExpensiveValue());
        printIfValidIndexSupplier(-2, () -> getVeryExpensiveValue());

        System.out.println("It took " + ((System.currentTimeMillis() - start) / 1000) + "seconds");
    }

    private static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Let's just say it has very expensive calculation here!
        return "wonwoo";
    }

    private static void printIfValidIndexSupplier(int number, Supplier<String> valueSupplier) {
        if (number >= 0) {
            System.out.println("The value is " + valueSupplier.get() + ".");
        } else {
            System.out.println("Invalid");
        }
    }

    private static void printIfValidIndex(int number, String value) {
        if (number >= 0) {
            System.out.println("The value is " + value + ".");
        } else {
            System.out.println("Invalid");
        }
    }
}
