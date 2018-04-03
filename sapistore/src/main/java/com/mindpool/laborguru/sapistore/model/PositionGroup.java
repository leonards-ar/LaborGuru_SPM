package com.mindpool.laborguru.sapistore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "tbl_position_groups")
public class PositionGroup {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="position_group_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @OneToMany(mappedBy = "positionGroup")
    private Set<Position> positions;

}
