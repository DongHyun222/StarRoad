package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
public class ProductController {
    @GetMapping("/starroad/product")
    public ModelAndView product() {
        ModelAndView mav = new ModelAndView("product/product");
        return mav;
    }

    @GetMapping("/api/product")
    public String product_list(Model model) {
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        ProductDto dto = new ProductDto();
        productDtos.add(dto);
        model.addAttribute("product_items", productDtos);
        return "product";
    }
}
