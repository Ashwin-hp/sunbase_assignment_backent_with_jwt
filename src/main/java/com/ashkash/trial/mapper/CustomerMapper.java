package com.ashkash.trial.mapper;

import com.ashkash.trial.dto.CustomerDTO;
import com.ashkash.trial.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    Customer CustomerDTOToCustomer(CustomerDTO customerDTO);
    CustomerDTO CustomerToCustomerDTO(Customer customer);
}
