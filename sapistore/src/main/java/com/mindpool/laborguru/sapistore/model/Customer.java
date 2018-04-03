package com.mindpool.laborguru.sapistore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="tbl_customers")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="customer_id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="code")
    private String code;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private Set<Region> regions;
}
