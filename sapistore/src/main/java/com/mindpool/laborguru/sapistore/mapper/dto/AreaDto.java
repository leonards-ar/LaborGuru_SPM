package com.mindpool.laborguru.sapistore.mapper.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"region"})
public class AreaDto {

    private Long id;
    private String name;
    private RegionDto region;

}