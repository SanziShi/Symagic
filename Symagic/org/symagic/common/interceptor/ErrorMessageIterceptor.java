package org.symagic.common.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class ErrorMessageIterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2955662936186032588L;
	
	private String defaultErrorHeader;
	private String defaultErrorSpecification;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		String result = arg0.invoke();
		
		if( result.equals("error") || result.equals("input") ){
			
			ValueStack stack = arg0.getInvocationContext().getValueStack();
			
			if( !stack.getContext().containsKey("errorHeader") || !stack.getContext().containsKey("errorSpecification") ){
				stack.getContext().put("errorHeader", defaultErrorHeader);
				stack.getContext().put("errorSpecification", defaultErrorSpecification);
			}
			
		}
		
		return result;
	}

	public String getDefaultErrorHeader() {
		return defaultErrorHeader;
	}

	public void setDefaultErrorHeader(String defaultErrorHeader) {
		this.defaultErrorHeader = defaultErrorHeader;
	}

	public String getDefaultErrorSpecification() {
		return defaultErrorSpecification;
	}

	public void setDefaultErrorSpecification(String defaultErrorSpecification) {
		this.defaultErrorSpecification = defaultErrorSpecification;
	}

}
