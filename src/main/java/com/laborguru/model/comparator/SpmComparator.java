package com.laborguru.model.comparator;

import java.util.Comparator;


/**
 * @author fb21734
 *
 */
public class SpmComparator implements Comparator<ComparableObject> {
	
	public SpmComparator(){
	}
	
	/**
	 * This methods has to have a method getId() and they should return an Integer
	 * @param o1
	 * @param o2
	 * @return
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(ComparableObject o1, ComparableObject o2) {
		return o1.getId().compareTo(o2.getId());
	}

}
