package com.mindpool.laborguru.sapistore.repository;

import com.mindpool.laborguru.sapistore.model.Position;
import com.mindpool.laborguru.sapistore.model.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PositionRepository extends CrudRepository<Position, Integer> {

    List<Position> findByStore(Store store);

    Position getById(Long id);
}
