package com.mindpool.laborguru.sapistore.mapper.dto;

import lombok.Data;

@Data
public class AreaDto {

    private Long id;
    private String name;
    private RegionDto region;

}
