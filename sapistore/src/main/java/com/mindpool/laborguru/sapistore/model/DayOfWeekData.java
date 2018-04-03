package com.mindpool.laborguru.sapistore.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tbl_day_of_week_values")
public class DayOfWeekData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "day_of_week_value_id")
    private Long id;

    @Column(name="fixed_flexible")
    private Double fixedFlexible;
    @Column(name="fixed_opening")
    private Double fixedOpening;
    @Column(name="fixed_post_rush")
    private Double fixedPostRush;
    @Column(name="fixed_closing")
    private Double fixedClosing;
    @Column(name="day_of_week")
    private Integer dayOfWeek;
    @ManyToOne
    @JoinColumn(name="position_id")
    private Position position;
}
