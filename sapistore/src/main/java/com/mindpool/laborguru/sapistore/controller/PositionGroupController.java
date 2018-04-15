package com.mindpool.laborguru.sapistore.controller;

import com.mindpool.laborguru.sapistore.mapper.dto.PositionGroupDto;
import com.mindpool.laborguru.sapistore.model.PositionGroup;
import com.mindpool.laborguru.sapistore.model.Store;
import com.mindpool.laborguru.sapistore.services.PositionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value="/api/store/{id}/positionGroup")
public class PositionGroupController extends BaseController {

    @Autowired
    PositionGroupService positionGroupService;

    @RequestMapping(method= RequestMethod.GET)
    public List<PositionGroupDto> getPositionGroups(@PathVariable Long id) {
        List<PositionGroup> groups = positionGroupService.findByStore(id);
        return mapper.mapAsList(groups, PositionGroupDto.class);
    }

    @RequestMapping(method= RequestMethod.PUT)
    public void updatePositionGroups(@PathVariable Long id, @RequestBody List<PositionGroupDto> positionGroupsDto, HttpServletResponse response) {
        Store store = storeService.findById(id);
        List<PositionGroup> positionGroups = mapper.mapAsList(positionGroupsDto, PositionGroup.class);
        positionGroups.forEach(positionGroup -> positionGroup.setStore(store));

        positionGroupService.saveOrUpdate(store, positionGroups);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }


}
