package com.mindpool.laborguru.sapistore.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"area"})
@Entity
@Table(name="tbl_stores")

public class Store {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="store_id")
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name="code")
    private String code;
    @Column(name="first_day_of_week")
    private Integer firstDayOfWeek;
    @ManyToOne
    @JoinColumn(name="area_id")
    @JsonManagedReference
    private Area area;
    @Column(name="daily_projections_weeks_default")
    private Integer dailyProjectionsWeeksDefault;
    @Column(name="half_hour_projections_weeks_default")
    private Integer halfHourProjectionsWeeksDefault;
    @Column(name="creation_date")
    private Date creationDate;
    @Column(name="last_update_date")
    private Date lastUpdateDate;
    @OneToMany(mappedBy = "store")
    private List<Position> positions;
    @OneToMany(mappedBy = "store")
    private List<OperationTime> operationTimes;
    @OneToMany(mappedBy = "store")
    private List<DayPart> dayParts;
    @Column(name="all_pos_utilization")
    private Double allPositionsUtilization;
    @OneToMany(mappedBy = "store", orphanRemoval = true)
    private Set<PositionGroup> positionGroups;

    @Column(name="schedule_inefficiency")
    private Double scheduleInefficiency = null;
    @Column(name="fill_inefficiency")
    private Double fillInefficiency = null;
    @Column(name="training_factor")
    private Double trainingFactor = null;
    @Column(name="earned_break_factor")
    private Double earnedBreakFactor = null;
    @Column(name="floor_management_factor")
    private Double floorManagementFactor = null;
    @Column(name="minimum_floor_Management_hours")
    private Integer minimumFloorManagementHours = null;

    @OneToMany(mappedBy = "store", orphanRemoval = true)
    private List<StoreVariableDefinition> variableDefinitions;
    @Enumerated(EnumType.STRING)
    @Column(name="distribution_type")
    private DistributionType distributionType;

    @Column(name="demo")
    private boolean demo;
    // Default value for printing schedule. Managers can override this value before executing the report.
    @Column(name="in_time_only")
    private boolean inTimeOnly;


}
