package org.symagic.common.interceptor;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.symagic.common.db.pool.ConnectionPool;
import org.symagic.common.service.RecommendService;

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
		
		File recommendConf = new File(FileUtils.getUserDirectoryPath() + "/recommend.json");
		
		if( !jdbcConf.exists() || !recommendConf.exists() ) return "setup";
		
		ConnectionPool.init();
		RecommendService.init();
		
		return arg0.invoke();
	}

}
