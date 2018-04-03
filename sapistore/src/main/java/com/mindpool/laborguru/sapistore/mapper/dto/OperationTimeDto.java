package com.mindpool.laborguru.sapistore.mapper.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OperationTimeDto {

    private String dayOfWeek;
    private Date openHour;
    private Date closeHour;
    private Integer openingExtraHours;
    private Integer closingExtraHours;
}
