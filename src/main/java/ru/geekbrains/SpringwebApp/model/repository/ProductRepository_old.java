package ru.geekbrains.SpringwebApp.model.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.SpringwebApp.model.entity.Product_old;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository_old {
    private static final List<Product_old> PRODUCT_OLDS = new ArrayList<>();
    private static int idCounter = 1;

    public static Product_old findById(int id) {
        return PRODUCT_OLDS.stream().filter(it -> it.getId() == id).findFirst().orElse(null);
    }

    public static List<Product_old> showAll() {
        return PRODUCT_OLDS;
    }

    public static void add(String name, double cost) {
        PRODUCT_OLDS.add(new Product_old(idCounter++, name, cost));

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

