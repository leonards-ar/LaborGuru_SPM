package com.mindpool.laborguru.sapistore.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tbl_store_variable_definitions")
public class StoreVariableDefinition {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="variable_definition_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;
    @Column(name="name")
    private String name;
    @Column(name="variable_index")
    private Integer variableIndex;
    @Column(name="average")
    private Double average;
}
