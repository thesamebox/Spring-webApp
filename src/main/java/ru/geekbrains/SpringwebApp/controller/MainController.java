package ru.geekbrains.SpringwebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.geekbrains.SpringwebApp.product.Product;
import ru.geekbrains.SpringwebApp.product.ProductRepository;

@Controller
public class MainController {
    ProductRepository pr;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Product> products = ProductRepository.showAll();
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        return "addProduct";
    }

    @PostMapping("/addProd")
    public String postAddRequest(Model model, @RequestBody Product product) {
        return "product";
    }
}
