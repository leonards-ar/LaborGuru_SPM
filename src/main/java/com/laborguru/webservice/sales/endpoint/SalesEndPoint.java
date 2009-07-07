package com.laborguru.webservice.sales.endpoint;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.model.HistoricSales;
import com.laborguru.service.store.StoreService;
import com.laborguru.webservice.sales.binding.EchoMessage;
import com.laborguru.webservice.sales.binding.ImportSalesRequest;




/**
 *
 * @author <a href="fbarreraoro">Federico Barrera Oro</a>
 * @version 1.1
 * @since SPM 1.1
 *
 */
@Endpoint
public class SalesEndPoint {
	private static Logger logger = Logger.getLogger(SalesEndPoint.class);
	
	private StoreService storeService;
	
	
	@PayloadRoot(localPart = "importSalesRequest", namespace = "http://www.laborguru.com/webservices/sales")
	public void importSales(ImportSalesRequest importSales) {
		
		try{
			List<HistoricSales> historicSales = importSales.bindDb(getStoreService());
			System.out.println(historicSales);
		} catch(Exception e) {
			throw new SpmUncheckedException(e.getMessage(), ErrorEnum.GENERIC_ERROR);
		}
		
		
		
	}
	
	@PayloadRoot(localPart = "echo", namespace = "http://www.laborguru.com/webservices/sales")
	public EchoMessage myEcho(EchoMessage echo) {

		System.out.println("echo: " + echo.getMessage());
		String message = "Response echo: " + echo.getMessage();
		EchoMessage response = new EchoMessage();
		response.setMessage(message);
	
		return response;
		
	}

	/**
	 * @return the storeService
	 */
	public StoreService getStoreService() {
		return storeService;
	}

	/**
	 * @param storeService the storeService to set
	 */
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	
}
