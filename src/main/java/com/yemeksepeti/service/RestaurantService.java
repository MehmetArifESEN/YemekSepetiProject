package com.yemeksepeti.service;

import com.yemeksepeti.repository.IRestaurantRepository;
import com.yemeksepeti.repository.entity.Restaurant;
import com.yemeksepeti.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService extends ServiceManager<Restaurant,Long> {
    private final IRestaurantRepository repository;

    public RestaurantService(IRestaurantRepository repository) {
        super(repository);
        this.repository=repository;
    }
    public Restaurant save(Restaurant restaurant){
        return repository.save(restaurant);
    }

    public Optional<Restaurant> findByName(String name){
        return repository.findByName(name);
    }
}
