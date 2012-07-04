package org.symagic.common.interceptor;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class FormRecoverInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2444884578185221664L;

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		HttpServletRequest request = (HttpServletRequest) invocation
				.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);

		ValueStack stack = invocation.getInvocationContext().getValueStack();

		String param = (String) request.getSession()
				.getAttribute("savedForm");

		if (param != null) {

			try{
				JSON json = JSONSerializer.toJSON(param);
				JSONObject object = (JSONObject)json;
				Iterator<String> keys = object.keys();
				while( keys.hasNext() ){
					String key = keys.next();
					
					JSONArray array = object.getJSONArray(key);
					String [] values = new String[array.size()];
					for( int i = 0; i < array.size(); i++ ){
						values[i] = array.getString(i);
					}
					
					///
					
				}
			}
			catch( Exception e ){
				e.printStackTrace();
			}
		}

		return invocation.invoke();
	}

}
