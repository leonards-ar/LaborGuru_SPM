package com.mindpool.laborguru.sapistore.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="tbl_regions")
public class Region {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="region_id")
    private Long id;
    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<Area> areas;

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
        return new HashCodeBuilder(17, 37).append(this.name)
                .append(this.customer!=null?this.customer.getId():null)
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

        final Region other = (Region) obj;

        return new EqualsBuilder()
                .append(this.name, other.name)
                .append(this.customer!=null?this.customer.getId():null, other.customer!=null?other.customer.getId():null)
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Area> getAreas() {
        return areas;
    }

    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }
}
