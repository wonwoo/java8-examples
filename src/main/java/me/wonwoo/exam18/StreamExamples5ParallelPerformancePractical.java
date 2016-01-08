package me.wonwoo.exam18;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by wonwoo on 16. 1. 9..
 */
public class StreamExamples5ParallelPerformancePractical {

    public static final String[] priceStrings = {"1.0", "100.99", "35.75", "21.30", "88.00"};
    public static final BigDecimal[] targetPrices = {new BigDecimal("30"), new BigDecimal("20"), new BigDecimal("31")};
    public static final Random random = new Random(123);
    public static final Random targetPriceRandom = new Random(111);

    public static final List<Product> products;

    static {
        final int length = 8_000_000;
//        final List<Product> list = new ArrayList<>(length);
        final Product[] list = new Product[length];

        for (int i = 1; i <= length; i++) {
            list[i-1] = (new Product((long) i, "Product" + i, new BigDecimal(priceStrings[random.nextInt(5)])));
        }

        products = Arrays.asList(list);
    }

    private static BigDecimal imperativeSum(final List<Product> products, final Predicate<Product> predicate) {
        BigDecimal sum = BigDecimal.ZERO;
        for (final Product product : products) {
            if (predicate.test(product)) {
                sum = sum.add(product.getPrice());
            }

        }
        return sum;
    }

    private static BigDecimal streamSum(final Stream<Product> stream, final Predicate<Product> predicate) {
        return stream.filter(predicate).map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add); // (i1, i2) -> i1.add(i2)
    }

    private static void imperativeTest(BigDecimal targetPrice) {
        System.out.printf("=========================================");
        System.out.println("\nImperative Sum\n-----------------------------------------");
        final long start = System.currentTimeMillis();
        System.out.println("Sum: " +
                        imperativeSum(products, product -> product.getPrice().compareTo(targetPrice) >= 0)
        );
        System.out.println("It took " + (System.currentTimeMillis() - start) + "ms.");
        System.out.println("========================================");
    }

    private static void streamTest(BigDecimal targetPrice) {
        System.out.printf("=========================================");
        System.out.println("\nStream Sum\n-----------------------------------------");
        final long start = System.currentTimeMillis();
        System.out.println("Sum: " +
                        streamSum(products.stream(), product -> product.getPrice().compareTo(targetPrice) >= 0)
        );
        System.out.println("It took " + (System.currentTimeMillis() - start) + "ms.");
        System.out.println("========================================");

    }

    private static void parallelStreamTest(BigDecimal targetPrice) {
        System.out.printf("=========================================");
        System.out.println("\nParallelStream Sum\n-----------------------------------------");
        final long start = System.currentTimeMillis();
        System.out.println("Sum: " +
                        streamSum(products.parallelStream(), product -> product.getPrice().compareTo(targetPrice) >= 0)
        );
        System.out.println("It took " + (System.currentTimeMillis() - start) + "ms.");
        System.out.println("========================================");

    }

    public static void main(String[] args) {
        final BigDecimal targetPrice = new BigDecimal("40");

        parallelStreamTest(targetPrice);
        imperativeTest(targetPrice);
        streamTest(targetPrice);



        System.out.println("\n Ignore Tests Above\n==============================\n");
        System.out.println("Start!");
        for(int i = 0; i < 5; i++){
            BigDecimal price = targetPrices[targetPriceRandom.nextInt(3)];

            parallelStreamTest(price);
            imperativeTest(price);
            streamTest(price);
        }
    }
}

@AllArgsConstructor
@Data
class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}