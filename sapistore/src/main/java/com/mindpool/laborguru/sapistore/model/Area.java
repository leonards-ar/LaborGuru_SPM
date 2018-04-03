package com.mindpool.laborguru.sapistore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="tbl_areas")
public class Area {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="area_id")
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="region_id")
    @JsonManagedReference
    private Region region;

    @OneToMany(mappedBy = "area")
    @JsonBackReference
    private Set<Store> stores;
}
