package ru.geekbrains.SpringwebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.SpringwebApp.model.entity.Product;
import ru.geekbrains.SpringwebApp.model.entity.User;
import ru.geekbrains.SpringwebApp.model.repository.ProductRepository;
import ru.geekbrains.SpringwebApp.model.repository.UserRepository;

@Controller
public class MainController {

    private final ProductRepository pr;
    private final UserRepository ur;

    public MainController(ProductRepository pr, UserRepository ur) {
        this.pr = pr;
        this.ur = ur;
    }

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Product> products = pr.findAll();
        model.addAttribute("products", products);
        return "product";
    }


    @PostMapping("/")
    public String postAddRequest(Model model,
                                 @RequestParam String name,
                                 @RequestParam double cost) {
        final Product product = new Product();
        product.setName(name);
        product.setCost(cost);
        pr.save(product);
        return "redirect:/";
    }


    @PostMapping("/del")
    public String delete(Model model,
                         @RequestParam long id) {
        pr.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(Model model,
                         @RequestParam long id,
                         @RequestParam String name,
                         @RequestParam double cost) {
        pr.update(id, name, cost);
        return "redirect:/";
    }

    @GetMapping("/sold")
    public String findWhoBought(Model model,
                                @RequestParam long id) {
        Iterable<User> user = pr.whoBought(id);
        model.addAttribute("users", user);
        return "sold";
    }

    @GetMapping("/bought")
    public String findWhatBought(Model model,
                                 @RequestParam long id) {
        Iterable<Product> p = ur.whatBought(id);
        model.addAttribute("products", p);
        return "bought";
    }
}