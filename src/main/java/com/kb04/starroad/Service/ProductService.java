package com.kb04.starroad.Service;

import com.google.common.collect.ImmutableList;
import com.kb04.starroad.Dto.product.ProductResponseDto;
import com.kb04.starroad.Entity.Product;
import com.kb04.starroad.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.ImmutableList.builder;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<ProductResponseDto> getProductList() {
        List<ProductResponseDto> list = new ArrayList<>();
        List<Product> productListAll = productRepository.findAll();
        for (Product product : productListAll) {
            ProductResponseDto dto = ProductResponseDto.builder()
                    .type(product.getType())
                    .name(product.getName())
                    .attribute(product.getAttribute())
                    .explain(product.getExplain())
                    .maxRate(product.getMaxRate())
                    .maxRatePeriod(product.getMaxPeriod())
                    .link(product.getLink())
                    .build();
            list.add(dto);
        }
        return list;
    }
}
