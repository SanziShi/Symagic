package org.symagic.common.interceptor;

import java.util.Map;
import java.util.Set;

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
	
	private Set<String> guest_illegal_url;

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
		StringBuffer pre_url = request.getRequestURL();
		
		//过滤对于未登录用户而言非法的URL请求
		String context_path = request.getContextPath();
		int illegal_check_start_index = pre_url.indexOf(context_path);
		String illegal_check_path = pre_url.substring(illegal_check_start_index + 1);
		
		

		// 设置url
		invocation
				.getInvocationContext()
				.getValueStack()
				.getContext()
				.put("to_url",
						pre_url.substring(pre_url.toString().lastIndexOf('/') + 1));

		return "enforce_login";
	}

}
