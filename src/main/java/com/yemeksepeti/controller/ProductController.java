package com.yemeksepeti.controller;

import com.yemeksepeti.dto.request.ProductListRequestDto;
import com.yemeksepeti.dto.request.ProductSaveRequestDto;
import com.yemeksepeti.dto.response.ProductListResponseDto;
import com.yemeksepeti.repository.entity.Product;
import com.yemeksepeti.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping("/save")
    public Product save (@RequestBody @Valid ProductSaveRequestDto dto){
        return productService.save(dto);
    }
    @GetMapping("/product-list-restaurant")
    public List<ProductListResponseDto> productListRestaurant(ProductListRequestDto dto) {
        return productService.productListRestaurant(dto);
    }
}
