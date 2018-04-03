package com.mindpool.laborguru.sapistore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tbl_positions")

public class Position {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="position_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @OneToMany(mappedBy = "position")
    private List<DayOfWeekData> dayOfWeekData;
    @OneToMany(mappedBy = "position")
    private Set<DayPartData> dayPartData;
    @ManyToOne
    @JoinColumn(name="position_group_id")
    private PositionGroup positionGroup;

    @Column(name="utilization_bottom")
    private Double utilizationBottom;
    @Column(name="utilization_top")
    private Double utilizationTop;
    @Column(name="utilization_min")
    private Integer utilizationMinimum;
    @Column(name="utilization_max")
    private Integer utilizationMaximum;
    @Column(name="manager")
    private boolean manager;
    @Column(name="guest_service")
    private boolean guestService;

    @Column(name="variable2_opening")
    private Double variable2Opening;
    @Column(name="variable2_flexible")
    private Double variable2Flexible;
    @Column(name="variable3_opening")
    private Double variable3Opening;
    @Column(name="variable3_flexible")
    private Double variable3Flexible;
    @Column(name="variable4_opening")
    private Double variable4Opening;
    @Column(name="variable4_flexible")
    private Double variable4Flexible;

    @Column(name="position_index")
    private Integer positionIndex;

}
