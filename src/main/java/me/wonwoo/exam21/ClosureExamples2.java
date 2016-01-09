package me.wonwoo.exam21;

import java.util.Comparator;

/**
 * Created by wonwoo on 16. 1. 9..
 */
public class ClosureExamples2 {

    private int number = 999;

    private static Comparator<Integer> CMP = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 < o2 ? -1 : o1.equals(02) ? 0 : 1;
        }
    };

    public static void main(String[] args) {
        new ClosureExamples2().test();
    }

    private void test() {
//        int number = 100;

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(number);
//            }
//        };
//        runnable.run();

        Runnable runnable1 = () -> System.out.println(number);
        runnable1.run();
    }

//  javap -c -p ClosureExamples2
//  javap -c -p -v ClosureExamples2
//    public class me.wonwoo.exam21.ClosureExamples2 {
//        private int number;
//
//        public me.wonwoo.exam21.ClosureExamples2();
//        Code:
//        0: aload_0
//        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//        4: aload_0
//        5: sipush        999
//        8: putfield      #2                  // Field number:I
//        11: return
//
//        public static void main(java.lang.String[]);
//        Code:
//        0: invokestatic  #3                  // Method test:()V
//        3: return
//
//        private static void test();
//        Code:
//        0: bipush        100
//        2: istore_0
//        3: iload_0
//        4: invokedynamic #4,  0              // InvokeDynamic #0:run:(I)Ljava/lang/Runnable;
//        9: astore_1
//        10: aload_1
//        11: invokeinterface #5,  1            // InterfaceMethod java/lang/Runnable.run:()V
//        16: return
//
//        private static void lambda$test$0(int);
//        Code:
//        0: getstatic     #6                  // Field java/lang/System.out:Ljava/io/PrintStream;
//        3: iload_0
//        4: invokevirtual #7                  // Method java/io/PrintStream.println:(I)V
//        7: return
//    }

//    Method Handle은 다나순히 메소드 저장이 아니라 직접 접근 실행 할 수 있는 메소드 레퍼런스로 재사용이 가능한 메소드는 저장했다가 바로 꺼내 쓸 수도 있다

//    Runnable $r = indy[bootstrap=LambdaMetaFactory,
//                                staticargs=[Runnable, lambda$0],
//                                dynargs=[number]]
//    private static void lambda$0(int number){
//        System.out.println(number);
//    }
}
