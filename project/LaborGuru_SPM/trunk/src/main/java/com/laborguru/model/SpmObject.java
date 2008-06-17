package com.laborguru.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * Spm Object
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class SpmObject implements Serializable {

	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Implementation of toString for SpmObject
	 * This method prints out the contents of SpmObject 
	 * @see java.lang.Object#toString(java.lang.Object)
	 */
	public String toString()
	{
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
	/**
	 * Implementation of Equals for SpmObject
	 * This method compares everything in SpmObject
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other)
	{
		return EqualsBuilder.reflectionEquals(this,other);
	}
	
	/**
	 * Implementation of HashCode for SpmObject.
	 * follows equals in using reflection.
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
