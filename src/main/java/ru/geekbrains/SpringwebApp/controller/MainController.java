package ru.geekbrains.SpringwebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.SpringwebApp.model.entity.Product;
import ru.geekbrains.SpringwebApp.model.repository.ProductDao;
import ru.geekbrains.SpringwebApp.model.repository.ProductRepository_old;

@Controller
public class MainController {

    private ProductDao pd;

    public MainController(ProductDao pd) {
        this.pd = pd;
    }

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Product> products = pd.findAll();
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        return "addProduct";
    }

    @PostMapping("/")
    public String postAddRequest(Model model,
                                 @RequestParam String name,
                                 @RequestParam double cost) {
        final Product product = new Product();
        product.setName(name);
        product.setCost(cost);
        pd.save(product);
//        ProductRepository_old.add(name, cost);
        return "redirect:/";
    }
}
