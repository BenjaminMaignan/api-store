package com.bmaignan.apistore.customer.mapper;

import com.bmaignan.apistore.customer.dto.CustomerRequestDto;
import com.bmaignan.apistore.customer.dto.CustomerResponseDto;
import com.bmaignan.apistore.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequestDto customerDTO);

    @Mapping(target = "cart", source = "cart")
    CustomerResponseDto toResponseDto(Customer customer);
}
