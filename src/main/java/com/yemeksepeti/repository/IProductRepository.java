package com.yemeksepeti.repository;

import com.yemeksepeti.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByRestaurantid(Long id);
    Optional<Product> findByNameAndRestaurantid(String name,Long restaurantid);

}
