package com.mindpool.laborguru.sapistore.mapper.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"regions"})
public class CustomerDetailsDto {

    private Long id;
    private String name;
    private String code;

    List<RegionDetailsDto> regions;

}
