package com.laborguru.service.security;

import com.laborguru.service.Service;
import com.laborguru.service.security.model.SecureObjectRole;
import com.laborguru.service.security.model.SecureResource;

public interface AuthorizationService extends Service {
	
	/**
	 * Gets the secure resource finding it by url.
	 * @param url url where it wants to check security.
	 * @return The ResourceObject found with this url.
	 */
	public SecureResource getSecureObject(String url);
	
	/**
	 * Gets the permission that the resource has.
	 * @param secureObject secureObject that has a permission.
	 * @return String with the permission name.
	 */
	public SecureObjectRole getSecureObjcetRoles(SecureResource secureObject);

}
