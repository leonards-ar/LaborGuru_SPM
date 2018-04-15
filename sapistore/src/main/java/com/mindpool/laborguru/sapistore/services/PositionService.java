package com.mindpool.laborguru.sapistore.services;

import com.mindpool.laborguru.sapistore.model.Position;
import com.mindpool.laborguru.sapistore.model.Store;

import java.util.List;

public interface PositionService {

    List<Position> findByStore (Long storeId);

    List<Position> saveOrUpdate(Store store, List<Position> position);

    Position findById(Long id);
}
