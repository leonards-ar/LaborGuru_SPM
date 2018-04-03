package com.mindpool.laborguru.sapistore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="tbl_operation_times")
public class OperationTime {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="operation_time_id")
    private Long id;

    @Column(name="open_hour")
    private Date openHour;
    @Column(name="close_hour")
    private Date closeHour;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @Column(name="opening_extra_hours")
    private Integer openingExtraHours;
    @Column(name="closing_extra_hours")
    private Integer closingExtraHours;
    @Column(name="day_of_week")
    private Integer dayOfWeek;

}
