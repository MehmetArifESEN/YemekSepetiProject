package com.yemeksepeti.repository;

import com.yemeksepeti.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByCustomerid(Long id);
    List<Order> findAllByRestaurantid(Long id);

}
