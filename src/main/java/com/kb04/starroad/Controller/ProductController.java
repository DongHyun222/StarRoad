package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.product.ProductResponseDto;
import com.kb04.starroad.Entity.Product;
import com.kb04.starroad.Service.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    private static final int ITEMS_PER_PAGE = 3;

//    private List<ProductResponseDto> productList;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/starroad/product")
    public ModelAndView product(
            Model model,
            @RequestParam(defaultValue = "1") int page) {

        List<ProductResponseDto> productList  = productService.getProductList();

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

    @GetMapping("/api/product")
    public String product_list(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam String type,
            @RequestParam String period,
            @RequestParam String query
            ) {
        String data = "";


        return data;
    }

//    @PostConstruct
//    public void initialize() {
//        productDtos = new ArrayList<>();
//        ProductDto dto = new ProductDto(1, 'D', "상품1", "상품 설명1", "자유적립식", 3, 6, 10, 1000, "#",3.3, 10 );
//        ProductDto dto2 = new ProductDto(2, 'D', "상품2", "상품 설명2", "자유적립식", 6, 9, 13, 2000, "#",3.3, 10 );
//        ProductDto dto3 = new ProductDto(3, 'I', "상품3", "상품 설명3", "추가입금불가", 9, 10, 14, 3000, "#",3.3, 10 );
//        ProductDto dto4 = new ProductDto(4, 'D', "상품4", "상품 설명1", "자유적립식", 3, 6, 10, 1000, "#",3.3, 10 );
//        ProductDto dto5 = new ProductDto(5, 'D', "상품5", "상품 설명2", "자유적립식", 6, 9, 13, 2000, "#",3.3, 10 );
//
//        productRepository.save(dto);
//        productRepository.save(dto2);
//        productRepository.save(dto3);
//        productRepository.save(dto4);
//        productRepository.save(dto5);
//    }
}
