package com.laborguru.service.security;

import com.laborguru.service.Service;
import com.laborguru.service.security.model.SecureObjectRole;
import com.laborguru.service.security.model.SecureResource;

public class AuthorizationServiceBean implements Service {

	public SecureResource getSecureObject(String url) {
		// TODO implement this method
		return null;
	}

	public SecureObjectRole getSecureObjcetRoles(SecureResource secureObject) {
		return secureObject.getPermission();

	}
}
