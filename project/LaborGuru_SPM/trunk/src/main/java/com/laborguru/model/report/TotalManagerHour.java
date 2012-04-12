package com.laborguru.model.report;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.laborguru.model.SpmObject;
import com.laborguru.util.SpmConstants;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.1
 * @since SPM 1.1
 *
 */
public abstract class TotalManagerHour extends SpmObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 723035706927140054L;
	BigDecimal sales;
	BigDecimal schedule;
	BigDecimal target;

	/**
	 * @return the sales
	 */
	public BigDecimal getSales() {
		return sales;
	}

	/**
	 * @param sales the sales to set
	 */
	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}

	/**
	 * @return the schedule
	 */
	public BigDecimal getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(BigDecimal schedule) {
		this.schedule = schedule;
	}

	/**
	 * @return the target
	 */
	public BigDecimal getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(BigDecimal target) {
		this.target = target;
	}

	public BigDecimal getDifference() {
		return schedule.subtract(target);
	}
	
	/**
	 * @return the percentage
	 */
	public BigDecimal getPercentage() {
		if(target.compareTo(SpmConstants.BD_ZERO_VALUE) == 0) {
			return SpmConstants.BD_ZERO_VALUE;
		}
		return getDifference().divide(target, 2, SpmConstants.ROUNDING_MODE).multiply(new BigDecimal(100));
	}

	public BigDecimal getScheduleMPH(){
		 if(schedule.compareTo(SpmConstants.BD_ZERO_VALUE) == 0) {
			 return SpmConstants.BD_ZERO_VALUE;
		 }
		 return sales.divide(schedule, 2, SpmConstants.ROUNDING_MODE);
	}
	
	public BigDecimal getTargetMPH(){
		 if(target.compareTo(SpmConstants.BD_ZERO_VALUE) == 0) {
			 return SpmConstants.BD_ZERO_VALUE;
		 }
		 return sales.divide(target, 2, SpmConstants.ROUNDING_MODE);
	}
	
	public String toString() {
        return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
        .append("sales" , sales)
        .append("schedule", schedule)
        .append("target", target)
        .toString();
	    
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sales == null) ? 0 : sales.hashCode());
        result = prime * result
                + ((schedule == null) ? 0 : schedule.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TotalManagerHour other = (TotalManagerHour) obj;
        if (sales == null) {
            if (other.sales != null)
                return false;
        } else if (!sales.equals(other.sales))
            return false;
        if (schedule == null) {
            if (other.schedule != null)
                return false;
        } else if (!schedule.equals(other.schedule))
            return false;
        if (target == null) {
            if (other.target != null)
                return false;
        } else if (!target.equals(other.target))
            return false;
        return true;
    }
	
	
	
}
