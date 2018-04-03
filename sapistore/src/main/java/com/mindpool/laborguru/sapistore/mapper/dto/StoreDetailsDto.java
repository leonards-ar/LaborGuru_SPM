package com.mindpool.laborguru.sapistore.mapper.dto;

import lombok.Data;

@Data
public class StoreDetailsDto {

    private String name;
    private AreaDto area;
    private Long id;
    private String code;
    private boolean demo;
    private boolean inTimeOnly;
}
