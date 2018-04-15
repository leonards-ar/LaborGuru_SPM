package com.mindpool.laborguru.sapistore.controller;

import com.mindpool.laborguru.sapistore.mapper.dto.OperationTimeDto;
import com.mindpool.laborguru.sapistore.model.OperationTime;
import com.mindpool.laborguru.sapistore.model.Store;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/store/{id}")
public class StoreOperationController extends BaseController {


    @RequestMapping(value="/operationTimes", method = RequestMethod.GET)
    public List<OperationTimeDto> getHoursOfOperation(@PathVariable Long id) {
        return mapper.mapAsList(storeService.findById(id).getOperationTimes(), OperationTimeDto.class);
    }

    @RequestMapping(value="/operationTimes", method = RequestMethod.POST)
    public void save(@PathVariable Long id, @RequestBody List<OperationTimeDto> operationsTimeDto, HttpServletResponse response) {

        List<OperationTime> operationTimes = mapper.mapAsList(operationsTimeDto, OperationTime.class);
        Store store = storeService.findById(id);
        store.setOperationTimes(operationTimes);
        storeService.saveOrUpdate(store);

        response.setStatus(HttpServletResponse.SC_CREATED);

    }

    @RequestMapping(value="/operationTimes", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody List<OperationTimeDto> operationsTimeDto, HttpServletResponse response) {

        List<OperationTime> operationTimes = mapper.mapAsList(operationsTimeDto, OperationTime.class);
        Store store = storeService.findById(id);
        store.setOperationTimes(operationTimes);
        storeService.saveOrUpdate(store);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);

    }
}
