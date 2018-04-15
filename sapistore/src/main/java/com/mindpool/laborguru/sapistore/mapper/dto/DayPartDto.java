package com.mindpool.laborguru.sapistore.mapper.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DayPartDto {

    private Long id;
    private Date startHour;
    private Integer positionIndex;
    private String name;
}
