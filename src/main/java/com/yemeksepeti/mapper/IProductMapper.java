package com.yemeksepeti.mapper;

import com.yemeksepeti.dto.request.ProductSaveRequestDto;
import com.yemeksepeti.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper {
    IProductMapper INSTANCE= Mappers.getMapper(IProductMapper.class);
    Product fromDtotoProduct(final ProductSaveRequestDto dto);
}
