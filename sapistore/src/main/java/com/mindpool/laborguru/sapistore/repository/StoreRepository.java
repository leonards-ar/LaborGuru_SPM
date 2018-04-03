package com.mindpool.laborguru.sapistore.repository;

import com.mindpool.laborguru.sapistore.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends PagingAndSortingRepository<Store, Integer> {

    Store findFirstById(Long id);

    Page<Store> findByDemo(boolean isDemo, Pageable pageable);


}
