package com.mindpool.laborguru.sapistore.controller;

import com.mindpool.laborguru.sapistore.exception.ResourceNotFound;
import com.mindpool.laborguru.sapistore.mapper.dto.StoreDetailsDto;
import com.mindpool.laborguru.sapistore.mapper.dto.StoreDto;
import com.mindpool.laborguru.sapistore.model.DistributionType;
import com.mindpool.laborguru.sapistore.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormatSymbols;
import java.util.Arrays;

@RestController
@RequestMapping("/api/store")
public class StoreController extends BaseController {



    @GetMapping()
    @Transactional(readOnly=true)
    public Page<StoreDto> listStores(Pageable pageable){
        Page<Store> stores = storeService.getStores(pageable);

        return new PageImpl<>(mapper.mapAsList(stores.getContent(), StoreDto.class), pageable, stores.getTotalElements());

    }

    @RequestMapping(method = RequestMethod.POST)
    public StoreDetailsDto save(@RequestBody StoreDetailsDto storeCreate, HttpServletResponse response) {
        Store store = mapper.map(storeCreate, Store.class);
        Long newId = storeService.saveOrUpdate(store).getId();

        response.setStatus(HttpServletResponse.SC_CREATED);
        return mapper.map(storeService.findById(newId), StoreDetailsDto.class);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public StoreDetailsDto getStore(@PathVariable Long id){
        Store store = storeService.findById(id);
        if(store == null) {
            throw new ResourceNotFound("Store Not Found");
        }

        return mapper.map(store, StoreDetailsDto.class);
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

    @RequestMapping(value="/{id}/firstDayOfWeek", method = RequestMethod.GET)
    @GetMapping
    public String getStoreFirstDayOfWeek(@PathVariable Long id){
        Integer firstDayOfWeek = storeService.findById(id).getFirstDayOfWeek();

        return DateFormatSymbols.getInstance().getWeekdays()[firstDayOfWeek + 1];
    }

    @RequestMapping(value="/{id}/firstDayOfWeek", method = RequestMethod.PUT)
    public void updateStoreFirstDayOfWeek(@PathVariable Long id, @RequestParam String dayOfWeek, HttpServletResponse response){
        Store store = storeService.findById(id);
        store.setFirstDayOfWeek(Arrays.asList(DateFormatSymbols.getInstance().getWeekdays()).indexOf(dayOfWeek) - 1);

        storeService.saveOrUpdate(store);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @RequestMapping(value="/{id}/projections", method = RequestMethod.GET)
    @GetMapping
    public String getStoreProjection(@PathVariable Long id){
        Store store = storeService.findById(id);

        return store.getDistributionType() != null? store.getDistributionType().name():null;
    }

    @RequestMapping(value="/{id}/projections", method = RequestMethod.PUT)
    public void updateStoreProjection(@PathVariable Long id, @RequestParam String distributionType, HttpServletResponse response){
        Store store = storeService.findById(id);
        store.setDistributionType(DistributionType.valueOf(distributionType));

        storeService.saveOrUpdate(store);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

}
