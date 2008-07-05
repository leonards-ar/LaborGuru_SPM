package com.laborguru.model.filter;

import java.util.ArrayList;
import java.util.List;

import com.laborguru.model.SpmObject;


/**
 * Search Store Filter
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class SearchStoreFilter extends SpmObject {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer customerId;
	private String name;
	private String code;
		
	public String getHql(){
		
		List<String> hqlParams = new ArrayList<String>();
		
		StringBuilder sb = new StringBuilder("from Store store");
		
		if (customerId != null){
			hqlParams.add("store.area.region.customer.id=" + customerId);
		}

		if (code != null){
			hqlParams.add("store.code like '%"+code+"%'");
		}
		
		if (name != null){
			hqlParams.add("store.name like '%"+name+"%'");
		}
		
		int i=0;
		for (String param: hqlParams){
			if (i==0){
				sb.append(" where " + param);
			}else {
				sb.append(" and " + param);
			}
			
			i++;
		}
		
		return sb.toString();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * Checks if there is any attributes defined for the filter
	 * @return True when there is at least one attribute set on the filter, otherwise false.
	 */
	public boolean isFilled() {		
		return (this.customerId != null) || (this.name != null) || (this.code != null);
	}

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
}
