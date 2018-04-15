package com.laborguru.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringStyle;


/**
 * Spm Object
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class SpmObject implements Serializable {
	protected static final ToStringStyle DEFAULT_TO_STRING_STYLE = ToStringStyle.MULTI_LINE_STYLE;
	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Implementation of toString for SpmObject
	 * This method prints out the contents of SpmObject 
	 * @see java.lang.Object#toString(java.lang.Object)
	 */
	public abstract String toString();


}