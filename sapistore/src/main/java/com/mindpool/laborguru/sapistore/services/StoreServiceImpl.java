package com.mindpool.laborguru.sapistore.services;

import com.mindpool.laborguru.sapistore.model.Store;
import com.mindpool.laborguru.sapistore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    public Store saveOrUpdate(Store store){
        return storeRepository.save(store);
    }

    //This method update Main Store Data
    public Store updateStore(Store store) {
        Store foundStore = findById(store.getId());

        foundStore.setName(store.getName());
        foundStore.setCode(store.getCode());
        foundStore.setArea(store.getArea());

        return saveOrUpdate(foundStore);
    }

    public Page<Store> findDemoStores(Pageable pageable){
        return storeRepository.findByDemo(true, pageable);
    }

    public void delete(Store store) {
        Assert.notNull(store, "store must not be null");
        storeRepository.delete(store);
    }

//    public Store savePositionGroups(Store store, List<PositionGroup> positionGroups){
//
//
//    }
}
