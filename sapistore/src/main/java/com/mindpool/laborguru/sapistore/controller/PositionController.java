package com.mindpool.laborguru.sapistore.controller;

import com.mindpool.laborguru.sapistore.exception.ResourceNotFound;
import com.mindpool.laborguru.sapistore.mapper.dto.DayOfWeekDataDto;
import com.mindpool.laborguru.sapistore.mapper.dto.DayPartDataDto;
import com.mindpool.laborguru.sapistore.mapper.dto.PositionDto;
import com.mindpool.laborguru.sapistore.model.DayOfWeekData;
import com.mindpool.laborguru.sapistore.model.DayPartData;
import com.mindpool.laborguru.sapistore.model.Position;
import com.mindpool.laborguru.sapistore.model.Store;
import com.mindpool.laborguru.sapistore.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/store/{id}/position")
public class PositionController extends BaseController {

    @Autowired
    PositionService positionService;

    @RequestMapping(method= RequestMethod.GET)
    public List<PositionDto> getPositions(@PathVariable Long id) {
        return mapper.mapAsList(positionService.findByStore(id), PositionDto.class);
    }


    @RequestMapping(method=RequestMethod.PUT)
    public void updatePositions(@PathVariable Long id, @RequestBody List<PositionDto> positionsDto, HttpServletResponse response) {
        Store store = storeService.findById(id);
        List<Position> positions = mapper.mapAsList(positionsDto, Position.class);

        positionService.saveOrUpdate(store, positions);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @RequestMapping(value="/{positionId}/dayPart", method=RequestMethod.GET)
    public List<DayPartDataDto> getDayPartData(@PathVariable Long id, @PathVariable Long positionId) {
        Position position = positionService.findById(positionId);
        if(position == null) {
            throw new ResourceNotFound("Position Not Found");
        }

        Set<DayPartData> data = position.getDayPartData();

        return mapper.mapAsList(data, DayPartDataDto.class);
    }

    @RequestMapping(value="/{positionId}/dayOfWeek", method=RequestMethod.GET)
    public List<DayOfWeekDataDto> getDayOfWeekData(@PathVariable Long id, @PathVariable Long positionId) {
        Position position = positionService.findById(positionId);
        if(position == null) {
            throw new ResourceNotFound("Position Not Found");
        }

        List<DayOfWeekData> data = position.getDayOfWeekData();
        return mapper.mapAsList(data, DayOfWeekDataDto.class);

    }



}
