package com.mindpool.laborguru.sapistore.mapper.dto;

import lombok.Data;

@Data
public class DayOfWeekDataDto {

    private Long id;
    private Double fixedFlexible;
    private Double fixedOpening;
    private Double fixedPostRush;
    private Double fixedClosing;
    private String dayOfWeek;

}
