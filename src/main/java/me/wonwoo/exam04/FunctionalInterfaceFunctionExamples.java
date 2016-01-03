package me.wonwoo.exam04;

import java.util.function.Function;

/**
 * Created by wonwoo on 15. 12. 28..
 */
public class FunctionalInterfaceFunctionExamples {

    public static void main(String[] args) {

        //anonymous class
        Function<String, Integer> toInt = new Function<String, Integer>() {
            @Override
            public Integer apply(String value) {
                return Integer.parseInt(value);
            }
        };
        final Integer number = toInt.apply("100");
        System.out.println(number);


        //lambda
        Function<String, Integer> toIntLambda = value -> Integer.parseInt(value);

        final Integer number2 = toIntLambda.apply("100");
        System.out.println(number2);

        final Function<Integer, Integer> identity = Function.identity();
        final Integer identityInt = identity.apply(999);
        System.out.println(identityInt);


        final Function<Integer, Integer> identityLambda = t -> t;
        final Integer identityIntLambda = identityLambda.apply(999);
        System.out.println(identityIntLambda);

    }
}
