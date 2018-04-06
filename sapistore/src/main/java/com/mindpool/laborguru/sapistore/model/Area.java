package com.mindpool.laborguru.sapistore.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="tbl_areas")
public class Area {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="area_id")
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="region_id", nullable = false)
    private Region region;

    @OneToMany(mappedBy = "area")
    private Set<Store> stores;


    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , id)
                .append("name",name)
                .toString();
    }

    /**
     * @return
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.name)
                .append(this.region!=null?this.region.getId():null)
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

        final Area other = (Area) obj;

        return new EqualsBuilder()
                .append(this.name, other.name)
                .append(this.region!=null?this.region.getId():null, other.region!=null?other.region.getId():null)
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }
}
