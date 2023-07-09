package com.yemeksepeti.controller;

import com.yemeksepeti.repository.entity.Restaurant;
import com.yemeksepeti.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    @PostMapping("/save")
    public Restaurant save(Restaurant restaurant){
        return restaurantService.save(restaurant);
    }
}
