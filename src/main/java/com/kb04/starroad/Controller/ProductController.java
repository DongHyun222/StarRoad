package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.ProductDto;
import com.kb04.starroad.Repository.ProductRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/starroad/product")
    public ModelAndView product(Model model) {

        ArrayList<ProductDto> productDtos = new ArrayList<>();
        ProductDto dto = new ProductDto(1, 'D', "상품1", "상품 설명1", "자유적립식", 3, 6, 10, 1000, "#",3.3, 10 );
        ProductDto dto2 = new ProductDto(2, 'D', "상품2", "상품 설명2", "자유적립식", 6, 9, 13, 2000, "#",3.3, 10 );
        ProductDto dto3 = new ProductDto(3, 'I', "상품3", "상품 설명3", "추가입금불가", 9, 10, 14, 3000, "#",3.3, 10 );
//        productDtos.add(dto);
//        productDtos.add(dto2);
//        productDtos.add(dto3);

        productRepository.save(dto);
        productRepository.save(dto2);
        productRepository.save(dto3);

        productDtos.addAll(productRepository.findAll());

        model.addAttribute("product_items", productDtos);

        ModelAndView mav = new ModelAndView("product/product");
        return mav;
    }

    @GetMapping("/api/product")
    public String product_list(Model model) {

        return "product";
    }

//    @PostConstruct
//    public void initialize() {
//
//        for (int i = 0; i < 5; i++) {
//            ProductDto dto = ProductDto.builder()
//
//                    .name("")
//                    .build();
//
//        }
//    }

}
