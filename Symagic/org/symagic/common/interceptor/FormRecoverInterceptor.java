package org.symagic.common.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class FormRecoverInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2444884578185221664L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		HttpServletRequest request = (HttpServletRequest) invocation
				.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);

		ValueStack stack = invocation.getStack();

		String param = (String) request.getSession()
				.getAttribute("savedForm");

//		if (param != null) {
//
//			Iterator<Entry<String, String[]>> itr = param.entrySet().iterator();
//
//			while (itr.hasNext()) {
//				Entry<String, String[]> entry = itr.next();
//				stack.setValue(entry.getKey(), entry.getValue());
//			}
//		}

		return invocation.invoke();
	}

}
