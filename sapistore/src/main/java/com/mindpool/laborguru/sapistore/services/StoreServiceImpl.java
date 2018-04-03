package com.mindpool.laborguru.sapistore.services;

import com.mindpool.laborguru.sapistore.model.Store;
import com.mindpool.laborguru.sapistore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Page<Store> getStores(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }

    public Store findById(Long id) {
        return storeRepository.findFirstById(id);
    }
}
