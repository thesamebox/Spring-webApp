package ru.geekbrains.SpringwebApp.product;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private static final List<Product> products = new ArrayList<>();
    private static int idCounter = 1;

    public static Product findById(int id) {
        return products.stream().filter(it -> it.getId() == id).findFirst().orElse(null);
    }

    public static List<Product> showAll() {
        return products;
    }

    public static void add(String name, double cost) {
        products.add(new Product(idCounter++, name, cost));

    }
    @PostConstruct
    public void init() {

        add("Булочки из маны", 30.5);
        add("Кишка великана Николая", 110);
        add( "Крендель судьбы", 59);
        add( "Загадочный пирог", 87.87);
        add( "Стиранная пастила", 87.65);
    }


}

