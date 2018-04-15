package com.mindpool.laborguru.sapistore.services;

import com.mindpool.laborguru.sapistore.model.PositionGroup;
import com.mindpool.laborguru.sapistore.model.Store;

import java.util.List;

public interface PositionGroupService {

    List<PositionGroup> findByStore(Long store);

    List<PositionGroup> saveOrUpdate(Store store, List<PositionGroup> positionGroup);
}
