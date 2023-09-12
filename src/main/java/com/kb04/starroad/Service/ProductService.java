package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.product.ProductResponseDto;
import com.kb04.starroad.Entity.Product;
import com.kb04.starroad.Repository.ProductRepository;
import com.kb04.starroad.Repository.Specification.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<ProductResponseDto> findByForm(Character type, Integer period, String name) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> null;
        System.out.println("type: "+type);
        System.out.println("period: "+period);
        System.out.println("name: "+name);
        if(name != null)
            spec = spec.and(ProductSpecification.containsName(name));
        if(period != null)
            spec = spec.and(ProductSpecification.lessThanOrEqualToMinPeriod(period));
        if(type != null)
            spec = spec.and(ProductSpecification.equalsType(type));

        List<Product> productListAll = productRepository.findAll(spec);
        List<ProductResponseDto> list = makeProductResponseDtoList(productListAll);

        return list;
    }
}
