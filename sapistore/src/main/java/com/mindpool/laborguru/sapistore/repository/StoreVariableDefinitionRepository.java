package com.mindpool.laborguru.sapistore.repository;

import com.mindpool.laborguru.sapistore.model.Store;
import com.mindpool.laborguru.sapistore.model.StoreVariableDefinition;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StoreVariableDefinitionRepository extends PagingAndSortingRepository<StoreVariableDefinition, Integer> {

    List<StoreVariableDefinition> findByStoreOrderByVariableIndex(Store store);
}
