package com.mindpool.laborguru.sapistore.services;

import com.mindpool.laborguru.sapistore.model.Store;
import com.mindpool.laborguru.sapistore.model.StoreVariableDefinition;
import com.mindpool.laborguru.sapistore.repository.StoreVariableDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreVariableDefinitionServiceImpl implements StoreVariableDefinitionService {

    @Autowired
    StoreVariableDefinitionRepository storeVariableDefinitionRepository;

    public List<StoreVariableDefinition> findByStore(Long id) {

        Store store = new Store();
        store.setId(id);

        return storeVariableDefinitionRepository.findByStoreOrderByVariableIndex(store);

    }

    public List<StoreVariableDefinition> saveOrUpdate(Store store, List<StoreVariableDefinition> variableDefinitions) {

        for(StoreVariableDefinition svd: variableDefinitions) {
            svd.setStore(store);
            storeVariableDefinitionRepository.save(svd);
        }

        for(StoreVariableDefinition svd: store.getVariableDefinitions()) {
            if(!variableDefinitions.contains(svd)) {
                storeVariableDefinitionRepository.delete(svd);
            }
        }

        return variableDefinitions;
    }
}
