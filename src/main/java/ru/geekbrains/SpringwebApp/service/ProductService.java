package ru.geekbrains.SpringwebApp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.geekbrains.SpringwebApp.model.entity.Product;

public interface ProductService {

    Page<Product> getAllProductsFromDB(Pageable pageable);
}
