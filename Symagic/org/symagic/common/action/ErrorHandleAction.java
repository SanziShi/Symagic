package org.symagic.common.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * 通用错误处理类
 * @author hao
 *
 */
public class ErrorHandleAction extends ActionSupport implements Preparable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1205558856396269455L;
	
	private String header;
	private String body;
	private String error_message;

	/**
	 * 传出数据的对象初始化
	 */
	@Override
	public void prepare() throws Exception {
		header = new String();
		body = new String();
		error_message = new String();
	}
	
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	
}
