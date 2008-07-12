package com.laborguru.model.comparator;

import java.util.Comparator;


/**
 * @author fb21734
 *
 */
public class SpmComparator implements Comparator {
	
	public SpmComparator(){
	}
	
	/**
	 * This methods has to have a method getId() and they should return an Integer
	 * @param o1
	 * @param o2
	 * @return
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		return ((ComparableObject)o1).getId().compareTo(((ComparableObject)o2).getId());
	}

}
