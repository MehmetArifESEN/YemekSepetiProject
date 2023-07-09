package com.yemeksepeti.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSaveRequestDto {
    String name;
    String category;
    Integer cost;
    Long restaurantid;
}
