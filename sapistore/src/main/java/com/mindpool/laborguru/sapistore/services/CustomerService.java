package com.mindpool.laborguru.sapistore.services;

import com.mindpool.laborguru.sapistore.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<Customer> list(Pageable pageable);

    Customer findById(Long id);
}
