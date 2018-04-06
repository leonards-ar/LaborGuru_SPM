package com.mindpool.laborguru.sapistore.controller;

import com.mindpool.laborguru.sapistore.mapper.dto.StoreDetailsDto;
import com.mindpool.laborguru.sapistore.mapper.dto.StoreDto;
import com.mindpool.laborguru.sapistore.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/store")
public class StoreController extends BaseController {



    @GetMapping()
    @Transactional(readOnly=true)
    public Page<StoreDto> listStores(Pageable pageable){
        Page<Store> stores = storeService.getStores(pageable);

        return new PageImpl<>(mapper.mapAsList(stores.getContent(), StoreDto.class), pageable, stores.getTotalElements());

    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public StoreDetailsDto getStore(@PathVariable Long id){
        return mapper.map(storeService.findById(id), StoreDetailsDto.class);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public void updateStore(@PathVariable Long id, @RequestBody StoreDetailsDto storeDetailsDto, HttpServletResponse response){
        Store store = mapper.map(storeDetailsDto, Store.class);
        storeService.updateStore(store);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);

    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteStore(@PathVariable Long id, HttpServletResponse response){
        Store store = new Store();
        store.setId(id);
        storeService.delete(store);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @RequestMapping(value="/demo", method = RequestMethod.GET)
    public Page<StoreDto> listDemoStores(Pageable pageable) {
        Page<Store> stores = storeService.findDemoStores(pageable);

        return new PageImpl<>(mapper.mapAsList(stores.getContent(), StoreDto.class), pageable, stores.getTotalElements());
    }


}
