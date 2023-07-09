package com.yemeksepeti.controller;

import com.yemeksepeti.dto.request.ActivateRequestDto;
import com.yemeksepeti.dto.request.CustomerLoginRequestDto;
import com.yemeksepeti.dto.request.CustomerRegisterRequestDto;
import com.yemeksepeti.dto.response.CustomerListResponseDto;
import com.yemeksepeti.dto.response.CustomerRegisterResponseDto;
import com.yemeksepeti.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerRegisterResponseDto> register(@RequestBody CustomerRegisterRequestDto dto){
        return ResponseEntity.ok(customerService.register(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody CustomerLoginRequestDto dto){
        return ResponseEntity.ok(customerService.login(dto));
    }

    @PostMapping("/activate-status")
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto){
        return ResponseEntity.ok(customerService.activateStatus(dto));
    }
    @GetMapping("/find-all-customer")
    public List<CustomerListResponseDto> customerList() {
        return customerService.customerList();
    }
}
