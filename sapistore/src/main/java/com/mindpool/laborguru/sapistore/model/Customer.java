package com.mindpool.laborguru.sapistore.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="tbl_customers")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="customer_id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="code")
    private String code;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Region> regions;

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.name).toHashCode();
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

        final Customer other = (Customer) obj;

        return new EqualsBuilder().append(this.name, other.name).isEquals();
    }

    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , id)
                .append("name",name)
                .append("code", code)
                .toString();
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

    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }
}
