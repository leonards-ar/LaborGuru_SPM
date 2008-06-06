package com.laborguru.acegi.authorization;

import java.util.Iterator;

import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.ConfigAttributeEditor;
import org.acegisecurity.intercept.web.AbstractFilterInvocationDefinitionSource;

import com.laborguru.service.security.AuthorizationServiceBean;
import com.laborguru.service.security.model.SecureObjectRole;
import com.laborguru.service.security.model.SecureResource;

public class DatabaseDriverFilterInvocationDefinitionSource extends AbstractFilterInvocationDefinitionSource {

	private AuthorizationServiceBean authorizationService;
	
	@Override
	public ConfigAttributeDefinition lookupAttributes(String url) {
		int firstQuestionMarkIndex = url.indexOf("?");
		
		if(firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
			
		}
		//check if the resource exists.
		SecureResource secureResourceObject = authorizationService.getSecureObject(url);
		if(secureResourceObject == null) {
			return null;
		}
		//get the roles or permissions associated to that resource
		SecureObjectRole secureObjectPermission = authorizationService.getSecureObjcetRoles(secureResourceObject);
		if(secureObjectPermission != null ) {
			ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
			configAttrEditor.setAsText(secureObjectPermission.getKey());
			return (ConfigAttributeDefinition)configAttrEditor.getValue();
		}
		return null;
	}

	public Iterator getConfigAttributeDefinitions() {
		// Not implemented
		return null;
	}

}
