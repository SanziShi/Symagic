package org.symagic.common.interceptor;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.symagic.common.db.pool.ConnectionPool;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SetupInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7001095834871787989L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		File jdbcConf = new File(FileUtils.getUserDirectoryPath() + "/jdbc.json");
		
		if( !jdbcConf.exists() ) return "setup";
		
		ConnectionPool.init();
		
		return arg0.invoke();
	}

}
