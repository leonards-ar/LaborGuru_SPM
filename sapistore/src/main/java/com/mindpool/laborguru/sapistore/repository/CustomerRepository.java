package com.mindpool.laborguru.sapistore.repository;

import com.mindpool.laborguru.sapistore.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

    Customer findFirstById(Long id);

}
