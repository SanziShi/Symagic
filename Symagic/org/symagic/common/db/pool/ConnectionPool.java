package org.symagic.common.db.pool;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Stack;

import net.sf.json.JSONObject;

import org.apache.struts2.json.JSONUtil;
import org.symagic.common.db.func.Util;

public class ConnectionPool {

	Stack<Connection> st = new Stack<Connection>(); // 创建一个堆栈，作为池子
	private static ConnectionPool instance = new ConnectionPool(); // 单例模式
	public int initialPoolSize = 5;			// 初始化连接池中连接实例的数量
	public String dbName = "bookshop";			// 连接的数据库名
	public String userName = "root";			// 连接的用户名
	public String userPasswd = "root";			// 连接的密码
	public String server = "localhost";
	public String port = "3306";

	/**
	 * 说明：连接池私有构造方法，用于单例模式。
	 * 功能：初始化连接池
	 */
	private ConnectionPool() {
		String path = this.getClass().getClassLoader().getResource("jdbc.json").getPath();
		
		String sets=Util.readFile(path);
		try {
			java.util.HashMap<String, String> map = (java.util.HashMap<String, String>) JSONUtil.deserialize(sets);
			
			dbName = map.get("dbname");
			userName = map.get("username");
			userPasswd = map.get("password");
			server = map.get("server");
			port = map.get("port");
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
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
	 * @return	Connection 数据库连接对象
	 * @throws Exception
	 */
	public synchronized Connection getConnection() throws Exception {
		if (instance.st.size() > 0)
			return instance.st.pop();
		else
			return createConnection();
	}

	
	synchronized void returnConnection(Connection conn) {
		instance.st.push(conn);
	}

	private Connection createConnection() throws Exception { // 以mysql为例　创建数据库连接

		String url = "jdbc:mysql://" + server + ":" + port +  "/" + dbName + "?useUnicode=true&characterEncoding=utf8";
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(url, userName, userPasswd);
		connection.setAutoCommit(false);		// 设置事务不自动提交
		ConnectionProxy handler = new ConnectionProxy(connection);		// 创建代理类
		
		Connection proxy = (Connection) Proxy.newProxyInstance( // 创建代理
				connection.getClass().getClassLoader(), new Class[]{Connection.class}, handler);
		return proxy;
	}
	
	public static ConnectionPool getInstance()
	{
		return instance;
	}
}