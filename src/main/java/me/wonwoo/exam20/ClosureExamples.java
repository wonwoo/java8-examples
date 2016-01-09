package me.wonwoo.exam20;

/**
 * Created by wonwoo on 16. 1. 9..
 */
public class ClosureExamples {

    //First Class Function
    //Function = First class Citizen
    //parameter 넘길수가 있고, return 할수도 있고, variable 저장 할수 있고 혹은 Data Stracture 저장 할 수 있다.

    //anonymous Function === 익명메소드 == lambda Expression
    //method(i -> i + 1);


    //Closure
    // int i = 100;  //non-local variable == Free variable
    //someMethod(x -> x * 2 + i); x는 파라미터 2는 상수 i? 람다 밖에 == Closure


    private int number = 999;

    public static void main(String[] args) {
        new ClosureExamples().test3();
    }

    private void test() {

        //Effectively Fianl
        int number = 100; //java8 final 아니어도 접근은 가능하나 값을 변경시키리면 컴파일 에러가 난다. 사실 final 이다
//        number = 1;
        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
//                number = 1;
//                System.out.println(this.number); //이거의 this 는 Runnable 의 this
                System.out.println(number);
            }
        });

//        number = 1;
//        testClosure("Lambda Expression",() -> System.out.println(ClosureExamples.this.number));
        testClosure("Lambda Expression", () -> System.out.println(number)); //this는 ClosureExamples의 this
//        number = 2;
    }

    private void test1() {
        int number = 100;
        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println(ClosureExamples.this.number);
            }
        });
        testClosure("Lambda Expression", () -> System.out.println(this.number));
    }

    private void test2() {
        int number = 100;

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println("this.toString(): " + this.toString());
            }
        });

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println("ClosureExamples.this.toString(): " + ClosureExamples.this.toString());
            }
        });
        testClosure("Lambda Expression", () -> System.out.println("this.toString(): " + this.toString())); //this는 ClosureExamples의 this
    }

    private void test3() {

        System.out.println("ClosureExamples calling toString(): " + toString());
        System.out.println("ClosureExamples calling toString(int ,String): " + toString(1, "Hello"));

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
//                System.out.println("toString(int, String): " + toString(1, "Test"));
                System.out.println("toString(int, String) causes compile-time error");
                System.out.println("ClosureExamples.toString(int, String): " + ClosureExamples.toString(1, "Test"));
            }
        });

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
//                System.out.println("toString(int): " + toString(1));
                System.out.println("toString(int) causes compile-time error");
                System.out.println("ClosureExamples.this.toString(int): " + ClosureExamples.this.toString(1));
            }
        });

        //scope 확장   ClosureExamples calling toString()과 같다.
        testClosure("Lambda Expression", () -> System.out.println("this.toString(int, String): " + this.toString(1, "Test"))); //this는 ClosureExamples의 this

        testClosure("Lambda Expression", () -> System.out.println("toString(int, String): " + toString(1, "Test"))); //this는 ClosureExamples의 this

        testClosure("Lambda Expression", () -> System.out.println("this.toString(int): " + this.toString(1))); //this는 ClosureExamples의 this

        testClosure("Lambda Expression", () -> System.out.println("toString(int): " + toString(1))); //this는 ClosureExamples의 this

    }

    private void test4() {

        int number = 100;

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                int number = 50;  //no compile-time error
                System.out.println(number);
                //another code
                int x = number + 100;
                System.out.println(x);
            }
        });


        testClosure("Lambda Expression", () -> {
//            int number = 50; // compile-time error
            System.out.println(this.number);
        });
    }

    @Override
    public String toString() {
        return new StringBuffer("ClosureExamples{")
                .append("number=").append(number)
                .append('}')
                .toString();
    }

    public static <T> String toString(final int number, T value) {
        return "[ " + number + " ] The value is " + String.valueOf(value) + ".";
    }

    public String toString(final int number) {
        return "#" + number;
    }

    private static void testClosure(final String name, final Runnable runnable) {
        System.out.println("=====================================");
        System.out.printf(name + ": ");
        runnable.run();
        System.out.println("=====================================");
    }
}
