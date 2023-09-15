package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.BaseRateDto;
import com.kb04.starroad.Dto.ConditionDto;
import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.SubscriptionDto;
import com.kb04.starroad.Dto.product.ProductResponseDto;
import com.kb04.starroad.Entity.BaseRate;
import com.kb04.starroad.Entity.MemberCondition;
import com.kb04.starroad.Entity.Product;
import com.kb04.starroad.Entity.Subscription;
import com.kb04.starroad.Repository.*;
import com.kb04.starroad.Repository.Specification.BaseRateSpecification;
import com.kb04.starroad.Repository.Specification.MemberConditionSpecification;
import com.kb04.starroad.Repository.Specification.ProductSpecification;
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
    private final BaseRateRepository baseRateRepository;

    public ProductService(ProductRepository productRepository, SubscriptionRepository subscriptionRepository,
                          MemberConditionRepository memberConditionRepository, ConditionRepository conditionRepository, BaseRateRepository baseRateRepository) {
        this.productRepository = productRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.memberConditionRepository = memberConditionRepository;
        this.baseRateRepository = baseRateRepository;
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
        Specification<Product> spec = findByFormSpec(type, period, name);
        spec = spec.and(ProductSpecification.orderByMaxRateDescMaxRatePeriodDesc(spec));

        List<Product> productListAll = productRepository.findAll(spec);
        List<ProductResponseDto> list = makeProductResponseDtoList(productListAll);

        return list;
    }

    public List<ProductResponseDto> findByFormAndMember(Character type, String period, String name, Double monthlyAvailablePrice) {

        Specification<Product> spec = findByFormSpec(type, period, name);
        spec = spec.and(ProductSpecification.lessThanOrEqualToMinPrice(monthlyAvailablePrice));
        spec = spec.and(ProductSpecification.orderByMaxRateTimesPeriodDesc(spec));

        List<Product> productListAll = productRepository.findAll(spec);
        List<ProductResponseDto> list = makeProductResponseDtoList(productListAll);

        return list;
    }

    private Specification<Product> findByFormSpec(Character type, String period, String name) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> null;
        if (name != null)
            spec = spec.and(ProductSpecification.containsName(name));
        if (period != null)
            spec = spec.and(ProductSpecification.lessThanOrEqualToMinPeriod(Integer.parseInt(period)));
        if (type != null)
            spec = spec.and(ProductSpecification.equalsType(type));
        return spec;
    }

    public List<SubscriptionDto> getSubscriptions(MemberDto memberDto) {
        return subscriptionRepository.findByMember(memberDto.toMemberEntity()).stream().map(Subscription::toSubscriptionDto).collect(Collectors.toList());
    }

    public List<ConditionDto> getMemberConditions(MemberDto loginMember) {
        Specification<MemberCondition> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(MemberConditionSpecification.equalsMemberNo(loginMember.toMemberEntity()));
        List<MemberCondition> memberConditions = memberConditionRepository.findAll(spec);

        List<ConditionDto> result = new ArrayList<>();
        for (MemberCondition memberCondition : memberConditions) {
            result.add(memberCondition.getCondition().toConditionDto());
        }
        return result;
    }

    public List<BaseRateDto> getBaseRates(int period) {
        Specification<BaseRate> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(BaseRateSpecification.maxRateSpecification(period));
        List<BaseRate> baseRates = baseRateRepository.findAll(spec);
        List<BaseRateDto> list = new ArrayList<>();
        for (BaseRate baseRate : baseRates) {
            BaseRateDto dto = baseRate.toBaseRateDto();
            list.add(dto);
        }
        return list;
    }


}
