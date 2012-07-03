package org.symagic.common.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.symagic.common.utilty.session.SessionUtilty;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class FormDataRecoverIntercepter extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3363779542665934595L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ValueStack stack = invocation.getStack();
		
		Map<String, String[]> formData = SessionUtilty.getInterceptedFormData();
		
		if( formData != null ){
			Iterator<Entry<String, String[]>> itr = formData.entrySet().iterator();
			
			while( itr.hasNext() ){
				Entry<String, String[]> entry = itr.next();
				
				if( entry != null ){
					stack.set(entry.getKey(), entry.getValue());
				}
				
			}
		}
		
		return invocation.invoke();
	}

}
