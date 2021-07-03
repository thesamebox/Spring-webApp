package ru.geekbrains.SpringwebApp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.SpringwebApp.model.entity.Product;
import ru.geekbrains.SpringwebApp.model.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductRepository pr;

    public ProductController(ProductRepository pr) {
        this.pr = pr;
    }

    @GetMapping("/json")
    public List<Product> findAllProds(Model model) {
        return pr.findAll();
    }

    @GetMapping("/json/{id}")
    public Optional<Product> findById(Model model, @PathVariable long id) {
        return pr.findById(id);
    }
}
