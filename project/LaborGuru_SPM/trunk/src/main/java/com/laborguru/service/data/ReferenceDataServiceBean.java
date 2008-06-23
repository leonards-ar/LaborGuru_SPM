/*
 * File name: ReferenceDataServiceBean.java
 * Creation date: 22/06/2008 20:36:13
 * Copyright Mindpool
 */
package com.laborguru.service.data;

import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ReferenceDataServiceBean implements ReferenceDataService {
	private final BeanFactory beanFactory = ContextSingletonBeanFactoryLocator.getInstance().useBeanFactory("reference-data").getFactory();

	/**
	 * 
	 */
	public ReferenceDataServiceBean() {
	}

	/**
	 * @param country
	 * @return
	 * @see com.laborguru.service.data.ReferenceDataService#getStates(java.lang.String)
	 */
	public Map<String, String> getStates(String country) {
		return (Map<String, String>) beanFactory.getBean("states-" + country.toLowerCase());
	}

	/**
	 * @return
	 * @see com.laborguru.service.data.ReferenceDataService#getStatus()
	 */
	public Map<String, String> getStatus() {
		return (Map<String, String>) beanFactory.getBean("status");
	}

}
