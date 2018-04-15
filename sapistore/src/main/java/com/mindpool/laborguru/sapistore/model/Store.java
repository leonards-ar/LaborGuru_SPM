package com.mindpool.laborguru.sapistore.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @CreationTimestamp
    private Date creationDate;
    @Column(name="last_update_date")
    @UpdateTimestamp
    private Date lastUpdateDate;
    @OneToMany(mappedBy = "store")
    private List<Position> positions;
    @OneToMany(mappedBy = "store")
    private List<OperationTime> operationTimes;
    @OneToMany(mappedBy = "store")
    private List<DayPart> dayParts;
    @Column(name="all_pos_utilization")
    private Double allPositionsUtilization;
    @OneToMany(mappedBy = "store")
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



    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , id)
                .append("name",name)
                .append("code",code)
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.name)
                .append(this.area != null? this.area.getId():null)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final Store other = (Store) obj;

        return new EqualsBuilder()
                .append(this.area != null? this.area.getId():null, other.area != null? other.area.getId():null)
                .append(this.name, other.name)
                .isEquals();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public void setFirstDayOfWeek(Integer firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Integer getDailyProjectionsWeeksDefault() {
        return dailyProjectionsWeeksDefault;
    }

    public void setDailyProjectionsWeeksDefault(Integer dailyProjectionsWeeksDefault) {
        this.dailyProjectionsWeeksDefault = dailyProjectionsWeeksDefault;
    }

    public Integer getHalfHourProjectionsWeeksDefault() {
        return halfHourProjectionsWeeksDefault;
    }

    public void setHalfHourProjectionsWeeksDefault(Integer halfHourProjectionsWeeksDefault) {
        this.halfHourProjectionsWeeksDefault = halfHourProjectionsWeeksDefault;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public List<OperationTime> getOperationTimes() {
        return operationTimes;
    }

    public void setOperationTimes(List<OperationTime> operationTimes) {
        this.operationTimes = operationTimes;
    }

    public List<DayPart> getDayParts() {
        return dayParts;
    }

    public void setDayParts(List<DayPart> dayParts) {
        this.dayParts = dayParts;
    }

    public Double getAllPositionsUtilization() {
        return allPositionsUtilization;
    }

    public void setAllPositionsUtilization(Double allPositionsUtilization) {
        this.allPositionsUtilization = allPositionsUtilization;
    }

    public Set<PositionGroup> getPositionGroups() {
        return positionGroups;
    }

    public void setPositionGroups(Set<PositionGroup> positionGroups) {
        this.positionGroups = positionGroups;
    }

    public Double getScheduleInefficiency() {
        return scheduleInefficiency;
    }

    public void setScheduleInefficiency(Double scheduleInefficiency) {
        this.scheduleInefficiency = scheduleInefficiency;
    }

    public Double getFillInefficiency() {
        return fillInefficiency;
    }

    public void setFillInefficiency(Double fillInefficiency) {
        this.fillInefficiency = fillInefficiency;
    }

    public Double getTrainingFactor() {
        return trainingFactor;
    }

    public void setTrainingFactor(Double trainingFactor) {
        this.trainingFactor = trainingFactor;
    }

    public Double getEarnedBreakFactor() {
        return earnedBreakFactor;
    }

    public void setEarnedBreakFactor(Double earnedBreakFactor) {
        this.earnedBreakFactor = earnedBreakFactor;
    }

    public Double getFloorManagementFactor() {
        return floorManagementFactor;
    }

    public void setFloorManagementFactor(Double floorManagementFactor) {
        this.floorManagementFactor = floorManagementFactor;
    }

    public Integer getMinimumFloorManagementHours() {
        return minimumFloorManagementHours;
    }

    public void setMinimumFloorManagementHours(Integer minimumFloorManagementHours) {
        this.minimumFloorManagementHours = minimumFloorManagementHours;
    }

    public List<StoreVariableDefinition> getVariableDefinitions() {
        return variableDefinitions;
    }

    public void setVariableDefinitions(List<StoreVariableDefinition> variableDefinitions) {
        this.variableDefinitions = variableDefinitions;
    }

    public DistributionType getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(DistributionType distributionType) {
        this.distributionType = distributionType;
    }

    public boolean isDemo() {
        return demo;
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }

    public boolean isInTimeOnly() {
        return inTimeOnly;
    }

    public void setInTimeOnly(boolean inTimeOnly) {
        this.inTimeOnly = inTimeOnly;
    }
}
