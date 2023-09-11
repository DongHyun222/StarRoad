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

    public List<ProductResponseDto> makeProductResponseDtoList(List<Product> productListAll) {
        List<ProductResponseDto> list = new ArrayList<>();
        for (Product product : productListAll) {
            ProductResponseDto dto = product.toProductResponseDto();
            list.add(dto);
        }
        return list;
    }

    public List<ProductResponseDto> getProductList() {
        List<Product> productListAll = productRepository.findAll();
        List<ProductResponseDto> list = makeProductResponseDtoList(productListAll);
        return list;
    }

    public List<ProductResponseDto> findByForm(String type, int maxPeriod, String query) {
        List<Product> productListQuery = productRepository.findByName(query);
        List<ProductResponseDto> list = makeProductResponseDtoList(productListQuery);
        return list;
    }
}
