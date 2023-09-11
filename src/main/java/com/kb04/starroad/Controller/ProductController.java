package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.product.ProductResponseDto;
import com.kb04.starroad.Service.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    private static final int ITEMS_PER_PAGE = 3;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/starroad/product")
    public ModelAndView product(
            Model model,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String period,
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "1") int page) {

        List<ProductResponseDto> productList = null;
        if(type!=null || period!=null || query != null)
            productList = productService.findByForm(type, Integer.parseInt(period), query);
        if(productList == null) {
            productList  = productService.getProductList();
        }
        int startIndex = (page - 1) * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, productList.size());

        model.addAttribute("productItems", productList.subList(startIndex, endIndex));
        model.addAttribute("pageEndIndex", Math.ceil(productList.size()/Double.valueOf(ITEMS_PER_PAGE)));
        model.addAttribute("currentPage", page);
        model.addAttribute("user", "장서우");
        model.addAttribute("price", 10000);

        ModelAndView mav = new ModelAndView("product/product");
        return mav;
    }
}
