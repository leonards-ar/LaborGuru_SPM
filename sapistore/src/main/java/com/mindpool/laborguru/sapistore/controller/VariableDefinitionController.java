package com.mindpool.laborguru.sapistore.controller;

import com.mindpool.laborguru.sapistore.mapper.dto.VariableDefinitionDto;
import com.mindpool.laborguru.sapistore.model.Store;
import com.mindpool.laborguru.sapistore.model.StoreVariableDefinition;
import com.mindpool.laborguru.sapistore.services.StoreVariableDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/store/{id}/storeVariables")
public class VariableDefinitionController extends BaseController {

    @Autowired
    StoreVariableDefinitionService storeVariableDefinitionService;

    @RequestMapping(method= RequestMethod.GET)
    List<VariableDefinitionDto> getStoreVariables(@PathVariable Long id) {
        return mapper.mapAsList(storeVariableDefinitionService.findByStore(id), VariableDefinitionDto.class);
    }

    @RequestMapping(method=RequestMethod.PUT)
    public void updatePositions(@PathVariable Long id, @RequestBody List<VariableDefinitionDto> positionsDto, HttpServletResponse response) {
        Store store = storeService.findById(id);
        List<StoreVariableDefinition> variables = mapper.mapAsList(positionsDto, StoreVariableDefinition.class);

        storeVariableDefinitionService.saveOrUpdate(store, variables);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
