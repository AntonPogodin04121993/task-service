package com.task.taskservice.mapper;

import com.task.taskservice.dto.requestdto.RequestProductDto;
import com.task.taskservice.entity.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromRequestProductDto(RequestProductDto productDto, @MappingTarget Product product);
}
