package com.mindpool.laborguru.sapistore.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_day_part_values")
public class DayPartData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="day_part_value_id")
    private Integer id;

    @Column(name="weekday_guest_service")
    private Double weekdayGuestService;
    @Column(name="weekend_guest_service")
    private Double weekendGuestService;
    @Column(name="variable_flexible")
    private Double variableFlexible;
    @Column(name="variable_opening")
    private Double variableOpening;
    @Column(name="fixed_guest_service")
    private Double fixedGuestService;
    @Column(name="minimum_staffing")
    private Integer minimunStaffing;
    @ManyToOne
    @JoinColumn(name="position_id")
    private Position position;
    @ManyToOne
    @JoinColumn(name="day_part_id")
    private DayPart dayPart;

}
