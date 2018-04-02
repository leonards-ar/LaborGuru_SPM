package com.mindpool.spm.report.controller;

import com.mindpool.spm.report.model.TotalHour;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/performanceEfficiency")
public class PerformanceEfficiencyController {

    @RequestMapping(value = "/{store_id}", method = RequestMethod.GET)
    public List<TotalHour> getPerformanceEfficiency(@PathVariable("store_id") Integer storeId, @RequestParam("start_date") String start, @RequestParam("end_date") String end) {


        TotalHour th = new TotalHour();
        th.setDay(new Date());
        th.setSales(new BigDecimal(10));
        th.setSchedule(new BigDecimal(20));
        th.setTarget(new BigDecimal(10));

        LinkedList<TotalHour> ths = new LinkedList<TotalHour>();
        ths.add(th);
        return ths;

    }
}
