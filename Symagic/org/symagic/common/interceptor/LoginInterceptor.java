package org.symagic.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.symagic.common.SessionUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8321742460145638100L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		// 获得session map
		Map<String, Object> session = invocation.getInvocationContext()
				.getSession();

		// 检查是否已经登陆
		if (SessionUtil.is_login(session)) {
			return invocation.invoke();
		}

		// 获得请求url
		// 获得请求的url request
		HttpServletRequest request = (HttpServletRequest) invocation
				.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		StringBuffer preURL = request.getRequestURL();

		// 设置url
		invocation
				.getInvocationContext()
				.getValueStack()
				.getContext()
				.put("to_url",
						preURL.substring(preURL.toString().lastIndexOf('/') + 1));

		return "enforce_login";
	}

}
