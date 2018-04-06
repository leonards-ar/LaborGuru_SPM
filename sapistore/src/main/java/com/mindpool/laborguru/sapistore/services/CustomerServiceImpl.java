package com.mindpool.laborguru.sapistore.services;

import com.mindpool.laborguru.sapistore.model.Customer;
import com.mindpool.laborguru.sapistore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Page<Customer> list(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer findById(Long id){
        return customerRepository.findFirstById(id);
    }
}
