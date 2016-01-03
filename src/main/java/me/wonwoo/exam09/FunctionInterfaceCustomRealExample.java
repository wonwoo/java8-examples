package me.wonwoo.exam09;

import java.math.BigDecimal;

/**
 * Created by wonwoo on 16. 1. 3..
 */
public class FunctionInterfaceCustomRealExample {
    public static void main(String[] args) {

        BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();
        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));

        final InvalidFunctionalInterface anonymousClass = new InvalidFunctionalInterface() {
            @Override
            public <T> String mkString(T value) {
                return value.toString();
            }
        };

        System.out.println("anonymous class : " + anonymousClass.mkString(123));

//        final InvalidFunctionalInterface invalidFunctionalInterface = value -> value.toString();
//        System.out.println(invalidFunctionalInterface.mkString(123));
    }
}

@FunctionalInterface
interface BigDecimalToCurrency{

    String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface InvalidFunctionalInterface{

    //제네릭 메소드는 사용불가
    <T> String mkString(T value);
}
