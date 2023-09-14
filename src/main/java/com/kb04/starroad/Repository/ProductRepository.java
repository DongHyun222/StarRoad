package com.kb04.starroad.Repository;

import com.kb04.starroad.Dto.product.ProductResponseDto;
import com.kb04.starroad.Entity.Product;
import net.bytebuddy.TypeCache;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {


    List<Product> findAll(Specification<Product> spec);

}
