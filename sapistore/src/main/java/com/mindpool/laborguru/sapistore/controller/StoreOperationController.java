package com.mindpool.laborguru.sapistore.controller;

import com.mindpool.laborguru.sapistore.mapper.dto.OperationTimeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormatSymbols;
import java.util.List;

@RestController
@RequestMapping("/api/{id}")
public class StoreOperationController extends BaseController {



    @GetMapping
    @RequestMapping("/operationTimes")
    public List<OperationTimeDto> getHoursOfOperation(@PathVariable Long id) {
        return mapper.mapAsList(storeService.findById(id).getOperationTimes(), OperationTimeDto.class);
    }

    @GetMapping
    @RequestMapping("firstDayOfWeek")
    public String getStoreDayOfWeek(@PathVariable Long id){
        Integer firstDayOfWeek = storeService.findById(id).getFirstDayOfWeek();

        return DateFormatSymbols.getInstance().getWeekdays()[firstDayOfWeek + 1];
    }
}
