package org.symagic.common.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.symagic.admin.utilty.AdminSessionUtilty;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 管理员操作的login拦截器
 * @author hao 
 */
public class AdminLoginInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2638872275691843170L;
	
	/**
	 * 拦截未登录管理员账户的客户端发来的请求
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		// 检查管理员是否已经登录
		if (AdminSessionUtilty.isLogin()) {
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
				.put("toURL",
						preURL.substring(preURL.toString().lastIndexOf('/') + 1));

		return "enforceLogin";
	}

}
