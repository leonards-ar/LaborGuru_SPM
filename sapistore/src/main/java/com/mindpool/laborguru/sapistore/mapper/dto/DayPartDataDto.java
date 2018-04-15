package com.mindpool.laborguru.sapistore.mapper.dto;

import lombok.Data;

@Data
public class DayPartDataDto {

    private DayPartDto dayPart;
    private Double weekdayGuestService;
    private Double weekendGuestService;
    private Double variableFlexible;
    private Double variableOpening;
    private Double fixedGuestService;
    private Integer minimunStaffing;
}
