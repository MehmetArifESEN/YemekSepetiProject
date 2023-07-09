package com.yemeksepeti.controller;

import com.yemeksepeti.dto.request.OrderRequestDto;
import com.yemeksepeti.dto.response.AllOrderListResponseDto;
import com.yemeksepeti.dto.response.CustomerOrderResponseDto;
import com.yemeksepeti.dto.response.RestaurantOrderListResponseDto;
import com.yemeksepeti.repository.entity.Order;
import com.yemeksepeti.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/new-order")
    public ResponseEntity<Order> save(OrderRequestDto dto){
        return ResponseEntity.ok(orderService.save(dto));
    }
    @GetMapping("/customer-order-list")
    public List<CustomerOrderResponseDto> findCustomerOrder(Long id){
        return orderService.findCustomerOrder(id);
    }
    @GetMapping("/restaurant-order-list")
    public List<RestaurantOrderListResponseDto> findRestaurantOrder(Long id){
        return orderService.findRestaurantOrder(id);
    }
    @GetMapping("/all-order-list")
    public List<AllOrderListResponseDto> findAllOrder(){
        return orderService.findAllOrder();
    }
}
