package com.laborguru.model.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.laborguru.model.Customer;


/**
 * Test for SearchStoreFilter
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class StoreFilterTest {

	@Test
	public void testGetHql_happyPath(){
		SearchStoreFilter filter = new SearchStoreFilter();
		Customer customer = new Customer();
		customer.setId(1);
		
		filter.setCustomer(customer);
		filter.setCode("code_1");
		filter.setName("name_2");
		
		System.out.println(filter.getHql());
		assertEquals("from Store store where store.area.region.customer.id=1 and store.code like '%code_1%' and store.name like '%name_2%'", filter.getHql());
	}

	@Test
	public void testGetHql_happyPath_Customer(){
		SearchStoreFilter filter = new SearchStoreFilter();
		Customer customer = new Customer();
		customer.setId(1);
		
		filter.setCustomer(customer);
		
		System.out.println(filter.getHql());
		assertEquals("from Store store where store.area.region.customer.id=1", filter.getHql());
	}

	@Test
	public void testGetHql_happyPath_NameCode(){
		SearchStoreFilter filter = new SearchStoreFilter();
		
		filter.setCode("code_1");
		filter.setName("name_2");
		
		System.out.println(filter.getHql());
		assertEquals("from Store store where store.code like '%code_1%' and store.name like '%name_2%'", filter.getHql());
	}

	@Test
	public void testGetHql_happyPath_NoSearch(){
		SearchStoreFilter filter = new SearchStoreFilter();
	
		
		System.out.println(filter.getHql());
		assertEquals("from Store store", filter.getHql());
	}
}
