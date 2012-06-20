package org.symagic.common.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 
 * @author hao

 * 阻止未登陆用户请求会员专有页面的时候的拦截器。
 */
public class UserLoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -8321742460145638100L;

	/**
	 * 
	
	/**
	 * 被认为是非法请求的URL集合
	 */
	private Set<String> guestIllegalURL;

	/**
	 * struts2 框架处理拦截的入口
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		// 检查是否已经登陆
		if (UserSessionUtilty.isLogin()) {
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

	/**
	 * 返回对于未登陆用户而言非法的URL集合。
	 * @return 非法URL集合
	 */
	public Set<String> getGuestIllegalURL() {
		return guestIllegalURL;
	}

	/**
	 * 设置对于未登录用户而言非法的URL集合
	 * @param guestIllegalURL
	 */
	public void setGuestIllegalURL(Set<String> guestIllegalURL) {
		this.guestIllegalURL = guestIllegalURL;
	}

}
