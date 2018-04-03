package com.mindpool.laborguru.sapistore.controller;

import com.mindpool.laborguru.sapistore.mapper.dto.OperationTimeDto;
import com.mindpool.laborguru.sapistore.model.Store;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/{id}")
public class StoreOperationController extends BaseController {


    @RequestMapping(value="/operationTimes", method = RequestMethod.GET)
    public List<OperationTimeDto> getHoursOfOperation(@PathVariable Long id) {
        return mapper.mapAsList(storeService.findById(id).getOperationTimes(), OperationTimeDto.class);
    }

    @RequestMapping(value="/firstDayOfWeek", method = RequestMethod.GET)
    @GetMapping
    public String getStoreFirstDayOfWeek(@PathVariable Long id){
        Integer firstDayOfWeek = storeService.findById(id).getFirstDayOfWeek();

        return DateFormatSymbols.getInstance().getWeekdays()[firstDayOfWeek + 1];
    }

    @RequestMapping(value="/firstDayOfWeek", method = RequestMethod.PUT)
    public void updateStoreFirstDayOfWeek(@PathVariable Long id, @RequestParam String dayOfWeek, HttpServletResponse response){
        Store store = storeService.findById(id);
        store.setFirstDayOfWeek(Arrays.asList(DateFormatSymbols.getInstance().getWeekdays()).indexOf(dayOfWeek) - 1);

        storeService.saveOrUpdate(store);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }


}
