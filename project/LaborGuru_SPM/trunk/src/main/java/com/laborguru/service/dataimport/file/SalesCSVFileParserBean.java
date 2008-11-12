package com.laborguru.service.dataimport.file;

import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang.math.RandomUtils;

import com.laborguru.model.HistoricSales;
import com.laborguru.model.Store;
import com.laborguru.service.store.StoreService;

/**
 * Implements a parser for a CSV sales file
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class SalesCSVFileParserBean implements SalesFileParser {

	private static final int TEST_MAX = 100;
	private StoreService storeService;
	private Store store;
	private int count;
	
	
	public SalesCSVFileParserBean(){
		
	}	
	
	public SalesFileParser assembleSalesFileParser(File fileToParse) {
		
		return this;
	}

	public String getFilename() {
		return "testFilename1";
	}

	public HistoricSales getNextRecord() {
		HistoricSales test = null;
		
		if (this.store == null){
			Store auxStore = new Store();
			auxStore.setId(1);			
			this.store = storeService.getStoreById(auxStore);
		}
		
		if (count < TEST_MAX){		
			test = new HistoricSales();
			Calendar today = Calendar.getInstance();
			today.add(Calendar.HOUR_OF_DAY, (0-(RandomUtils.nextInt()%24)));
			
			test.setDateTime(today.getTime());
			
			test.setDayOfWeek(today.get(Calendar.DAY_OF_WEEK));
			test.setMainValue(BigDecimal.valueOf(RandomUtils.nextDouble() * 1000));
			test.setStore(store);
			count++;
		}
		
		return test;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	/**
	 * @return the storeService
	 */
	public StoreService getStoreService() {
		return storeService;
	}

	public boolean isFileValid() {
		return true;
	}

}
