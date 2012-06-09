package org.symagic.common.db.pool;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Stack;

public class ConnectionPool {

	Stack<Connection> st = new Stack<Connection>(); // 创建一个堆栈，作为池子
	private static ConnectionPool instance = new ConnectionPool(); // 单例模式
	public static int initialPoolSize = 5;			// 初始化连接池中连接实例的数量
	public static String dbName = "bookshop";			// 连接的数据库名
	public static String userName = "root";			// 连接的用户名
	public static String userPasswd = "root";			// 连接的密码

	/**
	 * 说明：连接池私有构造方法，用于单例模式。
	 * 功能：初始化连接池
	 */
	private ConnectionPool() {
		for (int i = 0; i < initialPoolSize; i++)
			// 初始化缓冲池
			try {
				createConnection().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * 说明：
	 * 功能：获取连接池中一个未被分配的连接
	 * @return
	 * @throws Exception
	 */
	public synchronized static Connection getConnection() throws Exception {
		if (instance.st.size() > 0)
			return instance.st.pop();
		else
			return createConnection();
	}

	
	synchronized static void returnConnection(Connection conn) {
		instance.st.push(conn);
	}

	private static Connection createConnection() throws Exception { // 以mysql为例　创建数据库连接
		String url = "jdbc:mysql://localhost:3306/" + dbName + "?user=" + userName
				+ "&password=" + userPasswd;
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(url);		// 获取Connection实例
		ConnectionProxy handler = new ConnectionProxy(connection);		// 创建代理类
		
		Connection proxy = (Connection) Proxy.newProxyInstance( // 创建代理
				connection.getClass().getClassLoader(), new Class[]{Connection.class}, handler);
		return proxy;
	}
}