package com.ms.productservice.repo;

import com.ms.productservice.dto.ProductDto;
import com.ms.productservice.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    Optional<Products> findProductsByName(String name);
}
