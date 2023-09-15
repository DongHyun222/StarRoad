package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.ConditionDto;
import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.SubscriptionDto;
import com.kb04.starroad.Dto.product.ProductResponseDto;
import com.kb04.starroad.Entity.MemberCondition;
import com.kb04.starroad.Entity.Product;
import com.kb04.starroad.Entity.Subscription;
import com.kb04.starroad.Repository.ConditionRepository;
import com.kb04.starroad.Repository.MemberConditionRepository;
import com.kb04.starroad.Repository.ProductRepository;
import com.kb04.starroad.Repository.Specification.MemberConditionSpecification;
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
    private final MemberConditionRepository memberConditionRepository;
    private final ConditionRepository conditionRepository;

    public ProductService(ProductRepository productRepository, SubscriptionRepository subscriptionRepository,
                          MemberConditionRepository memberConditionRepository, ConditionRepository conditionRepository) {
        this.productRepository = productRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.memberConditionRepository = memberConditionRepository;
        this.conditionRepository = conditionRepository;
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
        spec = spec.and(ProductSpecification.orderByMaxRateTimesPeriodDesc(spec));

        List<Product> productListAll = productRepository.findAll(spec);
        List<ProductResponseDto> list = makeProductResponseDtoList(productListAll);
        return list;
    }

    public List<ProductResponseDto> findByForm(Character type, String period, String name) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> null;
        if (name != null)
            spec = spec.and(ProductSpecification.containsName(name));
        if (period != null)
            spec = spec.and(ProductSpecification.lessThanOrEqualToMinPeriod(Integer.parseInt(period)));
        if (type != null)
            spec = spec.and(ProductSpecification.equalsType(type));
        spec = spec.and(ProductSpecification.orderByMaxRateDescMaxRatePeriodDesc(spec));

        List<Product> productListAll = productRepository.findAll(spec);
        List<ProductResponseDto> list = makeProductResponseDtoList(productListAll);

        return list;
    }

    public List<ProductResponseDto> findByFormAndMember(Character type, String period, String name, Double monthlyAvailablePrice) {

        Specification<Product> spec = (root, query, criteriaBuilder) -> null;
        if (name != null)
            spec = spec.and(ProductSpecification.containsName(name));
        if (period != null)
            spec = spec.and(ProductSpecification.lessThanOrEqualToMinPeriod(Integer.parseInt(period)));
        if (type != null)
            spec = spec.and(ProductSpecification.equalsType(type));
        spec = spec.and(ProductSpecification.lessThanOrEqualToMinPrice(monthlyAvailablePrice));
        spec = spec.and(ProductSpecification.orderByMaxRateTimesPeriodDesc(spec));

        List<Product> productListAll = productRepository.findAll(spec);
        List<ProductResponseDto> list = makeProductResponseDtoList(productListAll);

        return list;
    }

    public List<SubscriptionDto> getSubscriptions(MemberDto memberDto) {
        return subscriptionRepository.findByMember(memberDto.toMemberEntity()).stream().map(Subscription::toSubscriptionDto).collect(Collectors.toList());
    }

    public List<ConditionDto> getMemberConditions(MemberDto loginMember) {
        Specification<MemberCondition> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(MemberConditionSpecification.equalsMemberNo(loginMember.toMemberEntity()));
        List<MemberCondition> memberConditions = memberConditionRepository.findAll(spec);

        List<ConditionDto> result = new ArrayList<>();
        for (MemberCondition memberCondition: memberConditions) {
            result.add(memberCondition.getCondition().toConditionDto());
        }
        return result;
    }


}
