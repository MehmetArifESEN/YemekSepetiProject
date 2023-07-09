package com.yemeksepeti.service;

import com.yemeksepeti.dto.request.ProductListRequestDto;
import com.yemeksepeti.dto.request.ProductSaveRequestDto;
import com.yemeksepeti.dto.response.ProductListResponseDto;
import com.yemeksepeti.mapper.IProductMapper;
import com.yemeksepeti.repository.IProductRepository;
import com.yemeksepeti.repository.entity.Product;
import com.yemeksepeti.repository.entity.Restaurant;
import com.yemeksepeti.utility.ServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService extends ServiceManager<Product,Long> {
    private final IProductRepository repository;
    private final  RestaurantService restaurantService;

    public ProductService(IProductRepository repository,RestaurantService restaurantService) {
        super(repository);
        this.repository=repository;
        this.restaurantService=restaurantService;
    }
    public Product save(ProductSaveRequestDto dto) {

        return repository.save(IProductMapper.INSTANCE.fromDtotoProduct(dto));
    }


    public List<ProductListResponseDto> productListRestaurant(ProductListRequestDto dto) {
        Optional<Restaurant> restaurant = restaurantService.findByName(dto.getName());
        return repository.findAllByRestaurantid(restaurant.get().getId()).stream().map(x->{
            return ProductListResponseDto.builder().name(x.getName()).cost(x.getCost()).build();
        }).collect(Collectors.toList());

    }
    public Optional<Product> findByNameAndRestaurantid(String name,Long id){
        return repository.findByNameAndRestaurantid(name,id);
    }

}
