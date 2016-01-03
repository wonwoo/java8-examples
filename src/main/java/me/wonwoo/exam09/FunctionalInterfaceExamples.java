package me.wonwoo.exam09;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by wonwoo on 15. 12. 29..
 */
public class FunctionalInterfaceExamples {
    public static void main(String[] args) {
        Product productA = new Product(1L, "A", new BigDecimal("10.00"));
        Product productB = new Product(2L, "B", new BigDecimal("55.50"));
        Product productC = new Product(3L, "C", new BigDecimal("17.45"));
        Product productD = new Product(4L, "D", new BigDecimal("20.00"));
        Product productE = new Product(5L, "E", new BigDecimal("110.99"));
        final List<Product> products = Arrays.asList(
                productA,
                productB,
                productC,
                productD,
                productE
        );
        final BigDecimal twenty = new BigDecimal("20");
        System.out.println("products >= $20: " + filter(products, product -> product.getPrice().compareTo(twenty) >= 0));

        System.out.println("products <= $10: " + filter(products, product -> product.getPrice().compareTo(new BigDecimal("10")) <= 0));


        final List<Product> expensiveProducts = filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) > 0);

        final List<DiscountedProduct> discountedProducts = map(filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) > 0),
                product -> new DiscountedProduct(product.getId(), product.getName(), product.getPrice().multiply(new BigDecimal("0.5"))));


        System.out.println(" expensive products: " + expensiveProducts);
        System.out.println("discounted products: " + discountedProducts);

        final Predicate<Product> lessThanOrEqualTo30 = product -> product.getPrice().compareTo(new BigDecimal("30")) <= 0;

        System.out.println("discounted products (<= $30): " +
                        filter(discountedProducts, lessThanOrEqualTo30)
        );

        System.out.println("discounted products (<= $30): " +
                        filter(products, lessThanOrEqualTo30)
        );

        final List<BigDecimal> prices = map(products, product -> product.getPrice());
        BigDecimal total = BigDecimal.ZERO;
        for (final BigDecimal price : prices) {
            total = total.add(price);
        }
        System.out.println("   total: " + total);

        final BigDecimal newTotal = total(products, product -> product.getPrice());
        System.out.println("newTotal: " + newTotal);

        final BigDecimal discountedTotal = total(discountedProducts, product -> product.getPrice());
        System.out.println("discountedTotal: " + discountedTotal);

        Order order = new Order(1L, "on-1234", Arrays.asList(
                new OrderedItem(1L, productA, 2),
                new OrderedItem(2L, productC, 1),
                new OrderedItem(3L, productD, 10)
        ));

        BigDecimal orderTotal = BigDecimal.ZERO;
        for(OrderedItem item : order.getItems()){
            orderTotal = orderTotal.add(item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));
        }

        System.out.println("order total in old way: " + orderTotal);
        System.out.println("           order total: " + order.totalPrice());

    }

    private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        final List<T> result = new ArrayList<>();
        for (final T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    private static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal total = BigDecimal.ZERO;
        for (final T t : list) {
            total = total.add(mapper.apply(t));
        }
        return total;
    }

    @AllArgsConstructor
    @Data
    static class Product {
        private Long id;
        private String name;
        private BigDecimal price;
    }

    @ToString(callSuper = true)
    static class DiscountedProduct extends Product {

        public DiscountedProduct(Long id, String name, BigDecimal price) {
            super(id, name, price);
        }
    }

    @AllArgsConstructor
    @Data
    static class OrderedItem {
        private Long id;
        private Product product;
        private int quantity;

        public BigDecimal getItemTotal(){
            return product.getPrice().multiply(new BigDecimal((quantity)));
        }
    }

    @Data
    @AllArgsConstructor
    static class Order {
        private Long id;
        private String orderNumber;
        List<OrderedItem> items;

        public BigDecimal totalPrice() {
            return total(items, item -> item.getItemTotal());
        }
    }
}

