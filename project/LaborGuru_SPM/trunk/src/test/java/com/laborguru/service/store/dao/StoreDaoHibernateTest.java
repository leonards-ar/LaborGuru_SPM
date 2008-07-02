/*
package com.laborguru.service.store.dao;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//specifies the Spring configuration to load for this test fixture
@ContextConfiguration(locations={"/applicationContext.xml"})
public class StoreDaoHibernateTest {

    private IDatabaseTester databaseTester;
    private DataSource myDataSourcePool;
    
    @Before
    public void setUp() throws Exception
    {
        databaseTester = new DataSourceDatabaseTester(myDataSourcePool);
        databaseTester.setSchema("spm");
        
        // initialize your dataset here
        IDataSet dataSet = new XmlDataSet(this.getClass().getResourceAsStream("dataset_store.xml"));
               
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.REFRESH);
        databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
        
        //It will call default setUpOperation
        databaseTester.onSetup();
    }

    @After
    public void tearDown() throws Exception
    {
    	//It will call default tearDownOperation
        databaseTester.onTearDown();
    }    
    
    @Test
	public void testFindAll(){
    	//StoreDao storeDao = new StoreDaoHibernate();
    	
	}

}

*/