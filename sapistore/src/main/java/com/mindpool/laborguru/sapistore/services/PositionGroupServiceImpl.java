package com.mindpool.laborguru.sapistore.services;

import com.mindpool.laborguru.sapistore.model.PositionGroup;
import com.mindpool.laborguru.sapistore.model.Store;
import com.mindpool.laborguru.sapistore.repository.PositionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionGroupServiceImpl implements PositionGroupService {

    @Autowired
    PositionGroupRepository positionGroupRepository;

    public List<PositionGroup> findByStore(Long storeId) {

        Store store = new Store();
        store.setId(storeId);
        return positionGroupRepository.findByStore(store);
    }

    public List<PositionGroup> saveOrUpdate(Store store, List<PositionGroup> positionGroups) {

        for(PositionGroup positionGroup: positionGroups) {
            positionGroupRepository.save(positionGroup);
        }

        //remove Positiongn Groups
        for(PositionGroup positionGroup: store.getPositionGroups()){
            if(!positionGroups.contains(positionGroup)){
                positionGroupRepository.delete(positionGroup);
            }
        }

        return positionGroups;
    }
}
