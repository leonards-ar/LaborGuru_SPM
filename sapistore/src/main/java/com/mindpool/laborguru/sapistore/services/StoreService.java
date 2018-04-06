package com.mindpool.laborguru.sapistore.services;

import com.mindpool.laborguru.sapistore.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StoreService {

    Page<Store> getStores(Pageable pageable);

    Store findById(Long id);

    Store saveOrUpdate(Store store);

    Store updateStore(Store store);

    Page<Store> findDemoStores(Pageable pageable);

    void delete(Store store);
}
