package com.kb04.starroad.Repository;

import com.kb04.starroad.Dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDto, Integer> {

}
