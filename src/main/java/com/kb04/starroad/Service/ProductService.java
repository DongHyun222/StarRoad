package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.SubscriptionDto;
import com.kb04.starroad.Dto.product.ProductResponseDto;
import com.kb04.starroad.Entity.Product;
import com.kb04.starroad.Entity.Subscription;
import com.kb04.starroad.Repository.ProductRepository;
import com.kb04.starroad.Repository.Specification.ProductSpecification;
import com.kb04.starroad.Repository.SubscriptionRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SubscriptionRepository subscriptionRepository;
    public ProductService(ProductRepository productRepository, SubscriptionRepository subscriptionRepository) {
        this.productRepository = productRepository;
        this.subscriptionRepository = subscriptionRepository;
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
        Specification<Product> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(ProductSpecification.orderByMaxRateDescMaxRatePeriodDesc(spec));

        List<Product> productListAll = productRepository.findAll(spec);
        List<ProductResponseDto> list = makeProductResponseDtoList(productListAll);
        return list;
    }


    public List<ProductResponseDto> getProductList(Double monthlyAvailablePrice) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(ProductSpecification.lessThanOrEqualToMinPrice(monthlyAvailablePrice));
        spec = spec.and(ProductSpecification.greaterThanOrEqualToMaxPrice(monthlyAvailablePrice));
        spec = spec.and(ProductSpecification.orderByMaxRateDescMaxRatePeriodDesc(spec));

        List<Product> productListAll = productRepository.findAll(spec);
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

        spec = spec.and(ProductSpecification.orderByMaxRateDescMaxRatePeriodDesc(spec));
        List<Product> productListAll = productRepository.findAll(spec);
        List<ProductResponseDto> list = makeProductResponseDtoList(productListAll);

        return list;
    }

    public List<SubscriptionDto> getSubscriptions(MemberDto memberDto) {
        return subscriptionRepository.findByMember(memberDto.toMemberEntity()).stream().map(Subscription::toSubscriptionDto).collect(Collectors.toList());
    }
}
