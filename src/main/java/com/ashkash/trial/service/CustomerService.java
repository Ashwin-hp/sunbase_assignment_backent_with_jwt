package com.ashkash.trial.service;
import com.ashkash.trial.CustomerNotFoundException;
import com.ashkash.trial.dto.CustomerDTO;
import com.ashkash.trial.entity.Customer;
import com.ashkash.trial.mapper.CustomerMapper;
import com.ashkash.trial.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer savedCustomer = customerRepo.save(CustomerMapper.INSTANCE.CustomerDTOToCustomer(customerDTO));
        return CustomerMapper.INSTANCE.CustomerToCustomerDTO(savedCustomer);
    }

    public List<CustomerDTO> findAllCustomers() {
        List<Customer> CustomerList=customerRepo.findAll();
        List<CustomerDTO> CustomerDTOList = CustomerList.stream().map(CustomerMapper.INSTANCE::CustomerToCustomerDTO).collect(Collectors.toList());
        return CustomerDTOList ;
    }

    public Customer getCustomerById(int customerId) {
        return customerRepo.findById(customerId)
                .orElse(null);
    }

    public void deleteCustomerById(int customerId) throws CustomerNotFoundException {
        Customer cust = customerRepo.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customerRepo.delete(cust);
    }



    public void updateCustomerById(int customerId, CustomerDTO customerDTO) throws CustomerNotFoundException {
        Customer existingCustomer = customerRepo.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("ID not found"));

        if (customerDTO == null) {
            throw new IllegalArgumentException("Request body is empty");
        }

        existingCustomer.setFirst_name(customerDTO.getFirst_name());
        existingCustomer.setLast_name(customerDTO.getLast_name());
        existingCustomer.setStreet(customerDTO.getStreet());
        existingCustomer.setAddress(customerDTO.getAddress());
        existingCustomer.setCity(customerDTO.getCity());
        existingCustomer.setState(customerDTO.getState());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setPhone(customerDTO.getPhone());

        customerRepo.save(existingCustomer);
    }

}
