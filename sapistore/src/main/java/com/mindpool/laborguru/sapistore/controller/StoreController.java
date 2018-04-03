package com.mindpool.laborguru.sapistore.controller;

import com.mindpool.laborguru.sapistore.mapper.dto.StoreDetailsDto;
import com.mindpool.laborguru.sapistore.mapper.dto.StoreDto;
import com.mindpool.laborguru.sapistore.model.Store;
import com.mindpool.laborguru.sapistore.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StoreController extends BaseController {



    @GetMapping()
    @Transactional(readOnly=true)
    public Page<StoreDto> listStores(Pageable pageable){
        Page<Store> stores = storeService.getStores(pageable);

        return new PageImpl<>(mapper.mapAsList(stores.getContent(), StoreDto.class), pageable, stores.getTotalElements());

    }

    @GetMapping
    @RequestMapping("/{id}")
    public StoreDetailsDto getStore(@PathVariable Long id){

        return mapper.map(storeService.findById(id), StoreDetailsDto.class);
    }
}
