package com.mindpool.laborguru.sapistore.services;

import com.mindpool.laborguru.sapistore.model.Position;
import com.mindpool.laborguru.sapistore.model.Store;
import com.mindpool.laborguru.sapistore.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionRepository positionRepository;

    public Position findById(Long id){
        return positionRepository.getById(id);
    }

    public List<Position> findByStore(Long storeId) {

        Store store = new Store();
        store.setId(storeId);

        return positionRepository.findByStore(store);
    }

    public List<Position> saveOrUpdate(Store store, List<Position> positions) {

        for(Position position: positions) {
            position.setStore(store);
            positionRepository.save(position);
        }

        for(Position position: store.getPositions()) {
            if(!positions.contains(position)) {
                positionRepository.delete(position);
            }
        }

        return positions;

    }


}
