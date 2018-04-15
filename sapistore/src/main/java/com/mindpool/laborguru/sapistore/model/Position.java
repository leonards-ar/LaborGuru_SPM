package com.mindpool.laborguru.sapistore.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , id)
                .append("name",name)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.name)
                .append(this.store != null? this.store.getId():null)
                .toHashCode();
    }

    /**
     * @param obj
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final Position other = (Position) obj;

        return new EqualsBuilder().append(this.name, other.name)
                .append(this.store != null? this.store.getId():null, other.store != null? other.store.getId():null)
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<DayOfWeekData> getDayOfWeekData() {
        return dayOfWeekData;
    }

    public void setDayOfWeekData(List<DayOfWeekData> dayOfWeekData) {
        this.dayOfWeekData = dayOfWeekData;
    }

    public Set<DayPartData> getDayPartData() {
        return dayPartData;
    }

    public void setDayPartData(Set<DayPartData> dayPartData) {
        this.dayPartData = dayPartData;
    }

    public PositionGroup getPositionGroup() {
        return positionGroup;
    }

    public void setPositionGroup(PositionGroup positionGroup) {
        this.positionGroup = positionGroup;
    }

    public Double getUtilizationBottom() {
        return utilizationBottom;
    }

    public void setUtilizationBottom(Double utilizationBottom) {
        this.utilizationBottom = utilizationBottom;
    }

    public Double getUtilizationTop() {
        return utilizationTop;
    }

    public void setUtilizationTop(Double utilizationTop) {
        this.utilizationTop = utilizationTop;
    }

    public Integer getUtilizationMinimum() {
        return utilizationMinimum;
    }

    public void setUtilizationMinimum(Integer utilizationMinimum) {
        this.utilizationMinimum = utilizationMinimum;
    }

    public Integer getUtilizationMaximum() {
        return utilizationMaximum;
    }

    public void setUtilizationMaximum(Integer utilizationMaximum) {
        this.utilizationMaximum = utilizationMaximum;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public boolean isGuestService() {
        return guestService;
    }

    public void setGuestService(boolean guestService) {
        this.guestService = guestService;
    }

    public Double getVariable2Opening() {
        return variable2Opening;
    }

    public void setVariable2Opening(Double variable2Opening) {
        this.variable2Opening = variable2Opening;
    }

    public Double getVariable2Flexible() {
        return variable2Flexible;
    }

    public void setVariable2Flexible(Double variable2Flexible) {
        this.variable2Flexible = variable2Flexible;
    }

    public Double getVariable3Opening() {
        return variable3Opening;
    }

    public void setVariable3Opening(Double variable3Opening) {
        this.variable3Opening = variable3Opening;
    }

    public Double getVariable3Flexible() {
        return variable3Flexible;
    }

    public void setVariable3Flexible(Double variable3Flexible) {
        this.variable3Flexible = variable3Flexible;
    }

    public Double getVariable4Opening() {
        return variable4Opening;
    }

    public void setVariable4Opening(Double variable4Opening) {
        this.variable4Opening = variable4Opening;
    }

    public Double getVariable4Flexible() {
        return variable4Flexible;
    }

    public void setVariable4Flexible(Double variable4Flexible) {
        this.variable4Flexible = variable4Flexible;
    }

    public Integer getPositionIndex() {
        return positionIndex;
    }

    public void setPositionIndex(Integer positionIndex) {
        this.positionIndex = positionIndex;
    }

}
