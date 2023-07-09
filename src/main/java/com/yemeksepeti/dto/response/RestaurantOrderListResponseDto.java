package com.yemeksepeti.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantOrderListResponseDto {
    String productname;
    String customername;
    Integer cost;
}
