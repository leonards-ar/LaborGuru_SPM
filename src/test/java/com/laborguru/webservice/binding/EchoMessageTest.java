package com.laborguru.webservice.binding;

import java.io.StringReader;

import junit.framework.TestCase;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;

import com.laborguru.webservice.sales.binding.EchoMessage;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class EchoMessageTest extends TestCase {

	public EchoMessageTest(String name) {
		super(name);
	}
	
	public void testMarshall() throws Exception {
		
		Document document = DocumentHelper.createDocument();
		Namespace namespace = DocumentHelper.createNamespace("sal", "http://www.laborguru.com/spm/webservices/sales");
		Element echoElement = document.addElement("echoRequest");
		echoElement.add(namespace);
		Element messageElement = echoElement.addElement("message");
		messageElement.add(namespace);
		messageElement.addText("Hola");
		
		IBindingFactory bfact = BindingDirectory.getFactory(EchoMessage.class);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        StringReader reader = new StringReader(echoElement.asXML());
        EchoMessage message = (EchoMessage)uctx.unmarshalDocument(reader);
        
        assertEquals("Hola", message.getMessage());
        
        IMarshallingContext mctx = bfact.createMarshallingContext();
        
        mctx.setOutput(System.out, null);
        mctx.marshalDocument(message);
        

	}
	
	
}
