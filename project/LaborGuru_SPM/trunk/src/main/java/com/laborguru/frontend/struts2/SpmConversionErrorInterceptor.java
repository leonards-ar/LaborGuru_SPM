package com.laborguru.frontend.struts2;

import org.apache.struts2.interceptor.StrutsConversionErrorInterceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * Custom interceptor to handle the error conversion.
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class SpmConversionErrorInterceptor extends StrutsConversionErrorInterceptor {

	private static final long serialVersionUID = 1L;

    /**
     * Overrides default implementation
     * 
     * @param invocation
     * @param value
     * @return
     * @see org.apache.struts2.interceptor.StrutsConversionErrorInterceptor#getOverrideExpr(com.opensymphony.xwork2.ActionInvocation, java.lang.Object)
     */
    protected Object getOverrideExpr(ActionInvocation invocation, Object value) {
        ValueStack stack = invocation.getStack();

        try {
            stack.push(value);

            return "'" + stack.findValue("top", String.class) + "'";
        } finally {
            stack.pop();
        }
    }
}
