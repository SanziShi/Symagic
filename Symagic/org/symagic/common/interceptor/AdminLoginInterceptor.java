package org.symagic.common.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.symagic.admin.utilty.AdminSessionUtilty;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 管理员操作的login拦截器
 * 
 * @author hao
 */
public class AdminLoginInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2638872275691843170L;

	/**
	 * 被认为是非法请求的URL集合
	 */
	private Set<String> guestIllegalURL;

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

		String contextPath = request.getContextPath();
		int illegalCheckStartIndex = preURL.indexOf(contextPath);
		String illegalCheckPath = preURL.substring(illegalCheckStartIndex + 1);

		// 设置url
		if (guestIllegalURL.contains(illegalCheckPath)) {
			invocation.getInvocationContext().getValueStack().getContext()
					.put("toURL", "index");
		} else {

			invocation
					.getInvocationContext()
					.getValueStack()
					.getContext()
					.put("toURL",
							preURL.substring(preURL.toString().lastIndexOf('/') + 1));
		}

		return "enforceLogin";
	}

	public Set<String> getGuestIllegalURL() {
		return guestIllegalURL;
	}

	public void setGuestIllegalURL(Set<String> guestIllegalURL) {
		this.guestIllegalURL = guestIllegalURL;
	}

}
