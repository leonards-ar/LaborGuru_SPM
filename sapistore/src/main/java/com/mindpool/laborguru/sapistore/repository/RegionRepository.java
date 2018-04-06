package com.mindpool.laborguru.sapistore.repository;

import com.mindpool.laborguru.sapistore.model.Customer;
import com.mindpool.laborguru.sapistore.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RegionRepository extends PagingAndSortingRepository<Region, Integer> {

    Page<Region> findAllByCustomer(Customer customer, Pageable pageable);
}
