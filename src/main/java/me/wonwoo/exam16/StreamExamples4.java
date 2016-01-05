package me.wonwoo.exam16;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * Created by wonwoo on 16. 1. 5..
 */
public class StreamExamples4 {

    public static void main(String[] args) {
        Product productA = new Product(1L, "A", new BigDecimal("100.50"));
        Product productB = new Product(2L, "B", new BigDecimal("23.00"));
        Product productC = new Product(3L, "C", new BigDecimal("31.45"));
        Product productD = new Product(4L, "D", new BigDecimal("80.20"));
        Product productE = new Product(5L, "E", new BigDecimal("7.50"));
        final List<Product> products = Arrays.asList(
                productA,
                productB,
                productC,
                productD,
                productE
        );

        System.out.println("Products.price >= 30: \n" +
                        products.stream()
                                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                                .collect(toList())
        );

        System.out.println("\n====================================");
        System.out.println("Products.price >= 30 (with joining(\"\\n\")): \n" +
                        products.stream()
                                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                                .map(product -> product.toString())
                                .collect(joining("\n"))
        );

        System.out.println("\n====================================");
        System.out.println("IntStream.sum: " +
                        IntStream.of(1, 2, 3, 4, 5)
                                .sum()
        );

        //
        System.out.println("\n====================================");
        System.out.println("Total Price: " +
                        products.stream()
                                .map(product -> product.getPrice())
                                        //    initial value,  arity? 이전값,
                                .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
//                        .reduce(BigDecimal.ZERO, (product1, product2) -> product1.getPrice().add(product2.getPrice()))
        );

        System.out.println("\n====================================");
        //이렇게 할수 도 있다.
        System.out.println("Total Price: " +
                        products.stream()
                                .reduce(BigDecimal.ZERO, (price, product) -> price.add(product.getPrice()), (price1, price2) -> price1.add(price2))
        );

        System.out.println("\n====================================");
        //이렇게 할수 도 있다.
        System.out.println("Total Price of Product.price >= 30: " +
                        products.stream()
                                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                                .map(product -> product.getPrice())
//                                .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        System.out.println("\n====================================");
        System.out.println("# of Products.price >= 30: " +
                        products.stream()
                                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                                .count()
        );


        final OrderedItem item1 = new OrderedItem(1L,products.get(0), 1);
        final OrderedItem item2 = new OrderedItem(2L,products.get(2), 3);
        final OrderedItem item3 = new OrderedItem(3L,products.get(4), 10);

        final Order order = new Order(1L, Arrays.asList(item1,item2,item3));

        System.out.println("\n====================================");
        System.out.println("order.totalPrice(): " + order.totalPrice());


    }
}

@AllArgsConstructor
@Data
class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}

@AllArgsConstructor
@Data
class OrderedItem {

    private Long id;
    private Product product;
    private int quantity;

    public BigDecimal getTotalPrice(){
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}

@Data
@AllArgsConstructor
class Order {
    private Long id;
    List<OrderedItem> items;

    public BigDecimal totalPrice(){
        return items.stream()
                .map(item -> item.getTotalPrice())
                .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2));
    }
}