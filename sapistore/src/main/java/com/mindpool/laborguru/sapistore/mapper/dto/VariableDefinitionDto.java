package com.mindpool.laborguru.sapistore.mapper.dto;

import lombok.Data;

@Data
public class VariableDefinitionDto {

    private Long id;
    private String name;
    private Integer variableIndex;
    private Double average;

}
