package com.yemeksepeti.service;

import com.yemeksepeti.dto.request.OrderRequestDto;
import com.yemeksepeti.dto.response.AllOrderListResponseDto;
import com.yemeksepeti.dto.response.CustomerOrderResponseDto;
import com.yemeksepeti.dto.response.RestaurantOrderListResponseDto;
import com.yemeksepeti.exceptions.ErrorType;
import com.yemeksepeti.exceptions.YemekSepetiException;
import com.yemeksepeti.repository.entity.Customer;
import com.yemeksepeti.repository.entity.Order;
import com.yemeksepeti.repository.IOrderRepository;
import com.yemeksepeti.repository.entity.Product;
import com.yemeksepeti.repository.entity.Restaurant;
import com.yemeksepeti.repository.enums.EStatus;
import com.yemeksepeti.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService extends ServiceManager<Order,Long> {
    private final IOrderRepository repository;
    private final CustomerService customerService;
    private final  ProductService productService;
    private final  RestaurantService restaurantService;

    public OrderService(IOrderRepository repository, CustomerService customerService, ProductService productService, RestaurantService restaurantService) {
        super(repository);
        this.repository = repository;
        this.customerService = customerService;
        this.productService = productService;
        this.restaurantService = restaurantService;
    }
    public Order save(OrderRequestDto dto){

        if(customerService.findOptionalByEmailAndCardDetails(dto.getEmail(), dto.getCardDetails()).isEmpty()){
            throw new YemekSepetiException(ErrorType.USER_NOT_FOUND);
        }
            Customer customer = customerService.findOptionalByEmailAndCardDetails(dto.getEmail(), dto.getCardDetails()).get();
        if(!customer.getStatus().equals(EStatus.ACTIVE)){
            throw new YemekSepetiException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }else{
            Restaurant restaurant = restaurantService.findByName(dto.getRestaurantname()).get();
            Product product = productService.findByNameAndRestaurantid(dto.getProductname(), restaurant.getId()).get();
            Order order=Order.builder().customerid(customer.getId()).restaurantid(restaurant.getId()).productid(product.getId()).build();
            return repository.save(order);

        }

    }
    public List<CustomerOrderResponseDto> findCustomerOrder(Long id){

        return repository.findAllByCustomerid(id).stream().map(x->{
            return CustomerOrderResponseDto.builder().productname(productService.findById(x.getProductid()).get().getName())
                    .restaurantName(restaurantService.findById(x.getRestaurantid()).get().getName())
                    .cost(productService.findById(x.getProductid()).get().getCost()).build();
        }).collect(Collectors.toList());

    }
    public List<RestaurantOrderListResponseDto> findRestaurantOrder(Long id){

        return repository.findAllByRestaurantid(id).stream().map(x->{
            return RestaurantOrderListResponseDto.builder().productname(productService.findById(x.getProductid()).get().getName())
                    .customername(customerService.findById(x.getCustomerid()).get().getName())
                    .cost(productService.findById(x.getProductid()).get().getCost()).build();
        }).collect(Collectors.toList());

    }
    public List<AllOrderListResponseDto> findAllOrder(){

        return repository.findAll().stream().map(x->{
            return AllOrderListResponseDto.builder().productname(productService.findById(x.getProductid()).get().getName())
                    .restaurantname(restaurantService.findById(x.getRestaurantid()).get().getName())
                    .customername(customerService.findById(x.getCustomerid()).get().getName())
                    .cost(productService.findById(x.getProductid()).get().getCost()).build();
        }).collect(Collectors.toList());

    }
}
