package com.mindpool.laborguru.sapistore.mapper.dto;

import lombok.Data;

@Data
public class PositionDto {

    private Long id;
    private String name;
    private boolean manager;
    private boolean guestService;

}
