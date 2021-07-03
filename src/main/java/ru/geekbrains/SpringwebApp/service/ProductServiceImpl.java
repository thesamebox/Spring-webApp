package ru.geekbrains.SpringwebApp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.geekbrains.SpringwebApp.model.entity.Product;
import ru.geekbrains.SpringwebApp.model.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository pr;

    public ProductServiceImpl(ProductRepository pr) {
        this.pr = pr;
    }

    @Override
    public Page<Product> getAllProductsFromDB(Pageable pageable) {
        return pr.findAll(pageable);
    }
}
