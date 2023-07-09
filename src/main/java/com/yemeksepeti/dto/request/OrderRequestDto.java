package com.yemeksepeti.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    private String productname;
    private String restaurantname;
    private String cardDetails;
    private String address;
    private String email;
}
