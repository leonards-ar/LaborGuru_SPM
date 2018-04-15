package com.mindpool.laborguru.sapistore.repository;

import com.mindpool.laborguru.sapistore.model.PositionGroup;
import com.mindpool.laborguru.sapistore.model.Store;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PositionGroupRepository extends PagingAndSortingRepository<PositionGroup, Integer> {

    List<PositionGroup> findByStore(Store store);
}
