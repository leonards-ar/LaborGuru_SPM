package com.mindpool.laborguru.sapistore.mapper.dto;

import lombok.Data;

@Data
public class RegionDto {

    private Long id;
    private String name;
    private CustomerDto customer;
}
