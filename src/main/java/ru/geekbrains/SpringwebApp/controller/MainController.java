package ru.geekbrains.SpringwebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.SpringwebApp.model.entity.Product;
import ru.geekbrains.SpringwebApp.model.entity.User;
import ru.geekbrains.SpringwebApp.model.repository.ProductDao;
import ru.geekbrains.SpringwebApp.model.repository.UserDao;

@Controller
public class MainController {

    private final ProductDao pd;
    private final UserDao ud;

    public MainController(ProductDao pd, UserDao ud) {
        this.pd = pd;
        this.ud = ud;
    }

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Product> products = pd.findAll();
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
        pd.save(product);
        return "redirect:/";
    }


    @PostMapping("/del")
    public String delete(Model model,
                                @RequestParam long id) {
        pd.delete(id);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(Model model,
                         @RequestParam long id,
                         @RequestParam String name,
                         @RequestParam double cost) {
        pd.update(id, name, cost);
        return "redirect:/";
    }

    @GetMapping("/sold")
    public String findWhoBought(Model model,
                                @RequestParam long id) {
        Iterable<User> user = pd.whoBought(id);
        model.addAttribute("users", user);
        return "sold";
    }

    @GetMapping("/bought")
    public String findWhatBought(Model model,
                                @RequestParam long id) {
        Iterable<Product> p = ud.whoBought(id);
        model.addAttribute("products", p);
        return "bought";
    }
}
