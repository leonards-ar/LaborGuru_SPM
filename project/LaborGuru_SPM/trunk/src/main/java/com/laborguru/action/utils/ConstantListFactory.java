package com.laborguru.action.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for constants list.
 * TODO: Discuss how to incorporate these lists in the model.
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ConstantListFactory {
	public static List<KeyValuePair> createStatusList(){
		ArrayList<KeyValuePair> statusList = new ArrayList<KeyValuePair>();
		statusList.add(new KeyValuePair("0","Enabled"));
		statusList.add(new KeyValuePair("1","Disabled"));
		
		return statusList;
	}
}
