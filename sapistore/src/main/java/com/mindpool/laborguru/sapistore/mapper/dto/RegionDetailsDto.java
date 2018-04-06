package com.mindpool.laborguru.sapistore.mapper.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"areas"})
public class RegionDetailsDto {

    private Long id;
    private String name;
    private List<AreaDto> areas;

}
