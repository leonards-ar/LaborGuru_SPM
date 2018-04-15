package com.mindpool.laborguru.sapistore.controller;

import com.mindpool.laborguru.sapistore.mapper.dto.DayPartDto;
import com.mindpool.laborguru.sapistore.model.DayPart;
import com.mindpool.laborguru.sapistore.model.Store;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value="/api/store/{id}")
public class DayPartController extends BaseController {

    @RequestMapping(value="/dayPart", method = RequestMethod.GET)
    public List<DayPartDto> getDayParts(@PathVariable Long id) {

        Store store = storeService.findById(id);
        return mapper.mapAsList(store.getDayParts(), DayPartDto.class);

    }

    @RequestMapping(value="/dayPart", method = RequestMethod.POST)
    public void saveDayParts(@PathVariable Long id, @RequestBody List<DayPartDto> dayPartsDto, HttpServletResponse response) {

        Store store = storeService.findById(id);
        List<DayPart> dayParts = mapper.mapAsList(dayPartsDto, DayPart.class);
        store.setDayParts(dayParts);

        storeService.saveOrUpdate(store);

        response.setStatus(HttpServletResponse.SC_CREATED);

    }

    @RequestMapping(value="/dayPart", method = RequestMethod.PUT)
    public void updateDayParts(@PathVariable Long id, @RequestBody List<DayPartDto> dayPartsDto, HttpServletResponse response) {

        Store store = storeService.findById(id);
        List<DayPart> dayParts = mapper.mapAsList(dayPartsDto, DayPart.class);
        store.setDayParts(dayParts);

        storeService.saveOrUpdate(store);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);

    }
}
