package com.laborguru.webservice;

import org.apache.log4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.XPathParam;

import com.laborguru.webservice.binding.EchoMessage;


/**
 *
 * @author <a href="fbarreraoro">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@Endpoint
public class SalesEndPoint {

	private static Logger logger = Logger.getLogger(SalesEndPoint.class);
	
	
	@PayloadRoot(localPart = "importSales", namespace = "http://www.laborguru.com/spm/webservices/sales")
	public void importSales(@XPathParam("/sal:importSales/sal:message") String historicSales) {
		
		System.out.println(historicSales);
		
	}
	
	@PayloadRoot(localPart = "echo", namespace = "http://www.laborguru.com/spm/webservices/sales")
	public EchoMessage myEcho(EchoMessage echo) {

		System.out.println("echo: " + echo.getMessage());
		String message = "Response echo: " + echo.getMessage();
		EchoMessage response = new EchoMessage();
		response.setMessage(message);
	
		return response;
		
	}
	
}
