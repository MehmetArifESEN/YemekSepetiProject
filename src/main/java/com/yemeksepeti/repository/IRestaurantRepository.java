package com.yemeksepeti.repository;

import com.yemeksepeti.repository.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant,Long> {
    Optional <Restaurant> findByName(String name);
}
