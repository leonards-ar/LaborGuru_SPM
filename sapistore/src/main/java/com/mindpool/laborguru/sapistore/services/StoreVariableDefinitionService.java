package com.mindpool.laborguru.sapistore.services;

import com.mindpool.laborguru.sapistore.model.Store;
import com.mindpool.laborguru.sapistore.model.StoreVariableDefinition;

import java.util.List;

public interface StoreVariableDefinitionService {

    List<StoreVariableDefinition> findByStore(Long id);

    List<StoreVariableDefinition> saveOrUpdate(Store store, List<StoreVariableDefinition> variableDefinitions);
}
