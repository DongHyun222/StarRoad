package com.kb04.starroad.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProductController {
    @GetMapping("/starroad/product")
    public ModelAndView product() {
        ModelAndView mav = new ModelAndView("product/product");
        return mav;
    }

    @GetMapping("/api/product")
    public String product_list() {
        return "product";
    }
}
