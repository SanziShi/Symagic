package org.symagic.common.db.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * 功能：代理类
 * @author wanran
 *
 */
public class ConnectionProxy implements InvocationHandler {
	Connection delegate;

	ConnectionProxy(Connection delegate) {
		this.delegate = delegate;
	}

	/*
	 * 如果调用Connection的close()方法就会进入if块中，将数据库连接返回给缓冲池。
	 * 跳过原来的close()函数
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		try {
			if ("close".equals(method.getName())) { // 在close函数处设置钩子
				ConnectionPool.returnConnection((Connection) proxy);
				// 将数据库连接归还缓冲池，跳过了原来的close()函数
				// 这里的proxy实际上就是Connection的一个代理实例
			} else
				return method.invoke(delegate, args);
		} catch (Exception e) {
		}
		return null;
	}
}
