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

	private Set<String> guestIllegalURL;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		// 获得session map
		Map<String, Object> session = invocation.getInvocationContext()
				.getSession();

		// 检查是否已经登陆
		if (SessionUtil.isLogin(session)) {
			return invocation.invoke();
		}

		// 获得请求url
		// 获得请求的url request
		HttpServletRequest request = (HttpServletRequest) invocation
				.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		StringBuffer preURL = request.getRequestURL();

		// 过滤对于未登录用户而言非法的URL请求
		String contextPath = request.getContextPath();
		int illegalCheckStartIndex = preURL.indexOf(contextPath);
		String illegalCheckPath = preURL
				.substring(illegalCheckStartIndex + 1);

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
							preURL.substring(preURL.toString().lastIndexOf(
									'/') + 1));
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
