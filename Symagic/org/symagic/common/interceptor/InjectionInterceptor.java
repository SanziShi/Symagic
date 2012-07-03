package org.symagic.common.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class InjectionInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2254864288986012673L;

	// js脚本正则表达式
	private Pattern scripterPattern;

	// html过滤
	private Pattern htmlPattern;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		HttpServletRequest request = (HttpServletRequest) invocation
				.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);

		Map<String, String[]> parameters = request.getParameterMap();

		Iterator<Entry<String, String[]>> itr = parameters.entrySet()
				.iterator();

		while (itr.hasNext()) {
			Entry<String, String[]> parameter = itr.next();

			for (String value : parameter.getValue()) {

				// 过滤html标签
				if (htmlPattern.matcher(value).find())
					return "error";

				// 过滤script脚本
				if (scripterPattern.matcher(value).find())
					return "error";

				// 过滤sql转换函数
				if (value.contains("ascii(") || value.contains("ascii (")
						|| value.contains("chr(") || value.contains("chr ("))
					return "error";

				// 过滤sql关键字
				if (value.contains("alter ") || value.contains("create ")
						|| value.contains("truncate ")
						|| value.contains("drop ")
						|| value.contains("lock table")
						|| value.contains("insert ")
						|| value.contains("update ")
						|| value.contains("delete ")
						|| value.contains("select ")
						|| value.contains("grant "))
					return "error";

			}
		}

		return invocation.invoke();
	}

	@Override
	public void init() {

		scripterPattern = Pattern.compile("<script.*>.*<\\/script\\s*>");

		htmlPattern = Pattern.compile("<[^>]+>");

		super.init();
	}

}
