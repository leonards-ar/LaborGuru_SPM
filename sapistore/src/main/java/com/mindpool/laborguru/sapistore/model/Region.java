package com.mindpool.laborguru.sapistore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="tbl_regions")
public class Region {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="region_id")
    private Long id;
    @Column(name="name")
    private String name;
    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonManagedReference
    private Customer customer;
    @OneToMany(mappedBy = "region")
    @JsonBackReference
    private Set<Area> areas;
}
