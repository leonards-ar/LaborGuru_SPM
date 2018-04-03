package com.mindpool.laborguru.sapistore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="tbl_day_parts")
public class DayPart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="day_part_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name="start_hour")
    private Date startHour;
    @Column(name="position_index")
    private Integer positionIndex;
    @Column(name="name")
    private String name;
}
