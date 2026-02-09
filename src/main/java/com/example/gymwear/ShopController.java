package com.example.gymwear;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ShopController {

    private List<Map<String, Object>> products = List.of(
        Map.of("id",1,"name","FlexFit T-Shirt","price",999,
               "image","https://images.unsplash.com/photo-1593032465171-8f7e9d2e8c8c"),
        Map.of("id",2,"name","PowerMove Joggers","price",1499,
               "image","https://images.unsplash.com/photo-1584865288642-42078afe6942"),
        Map.of("id",3,"name","ProLift Shorts","price",799,
               "image","https://images.unsplash.com/photo-1600185365483-26d7a4cc7519"),
        Map.of("id",4,"name","Muscle Tank","price",699,
               "image","https://images.unsplash.com/photo-1520975918318-7b2b8c3c5a6c")
    );

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("products", products);
        List<Map<String,Object>> cart = 
            (List<Map<String,Object>>) session.getAttribute("cart");
        model.addAttribute("cartCount", cart == null ? 0 : cart.size());
        return "index";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam int id, HttpSession session) {
        List<Map<String,Object>> cart = 
            (List<Map<String,Object>>) session.getAttribute("cart");
        if(cart == null) cart = new ArrayList<>();
        for(Map<String,Object> p : products){
            if((int)p.get("id") == id){
                cart.add(p);
                break;
            }
        }
        session.setAttribute("cart", cart);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        List<Map<String,Object>> cart = 
            (List<Map<String,Object>>) session.getAttribute("cart");
        model.addAttribute("cart", cart == null ? new ArrayList<>() : cart);
        return "cart";
    }
}
