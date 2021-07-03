package ru.geekbrains.SpringwebApp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.SpringwebApp.model.entity.Product;
import ru.geekbrains.SpringwebApp.model.entity.User;
import ru.geekbrains.SpringwebApp.model.repository.ProductRepository;
import ru.geekbrains.SpringwebApp.model.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                                 @RequestParam String name, @RequestParam double cost) {
        final Product product = new Product();
        product.setName(name);
        product.setCost(cost);
        pr.save(product);
        return "redirect:/";
    }


    @GetMapping("/del/{id}")
    public String deleteObj(Model model,
                            @PathVariable(value = "id") long id) {
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
    @RequestMapping(value = "/product-list/page/{page}", method = RequestMethod.GET)
    public String ProductsPagination(Model model,
                                               @PathVariable(value = "page") int page) {
        ModelAndView modelAndView = new ModelAndView("product-list-paging");
        PageRequest pageable = PageRequest.of(1, 10);
        Page<Product> productPage = pr.findAll(pageable);
        int totalPages = productPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("activeProductList", true);
        modelAndView.addObject("productList", productPage.getContent());
        return "redirect:/";
    }

}