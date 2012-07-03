<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Symagic商场安装</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <p>Symagic安装</p>
  <form action="setup" method="post">
  <table>
  <tr>
  <td>数据库域名：</td>
  <td><input name="databaseHost"/></td>
  </tr>
  <tr>
  <td>数据库名：</td>
  <td><input name="databaseName"/></td>
  </tr>
  <tr>
  <td>数据库端口：</td>
  <td><input name="databasePost"/></td>
  </tr>
  <tr>
  <td>数据库用户名：</td>
  <td><input name="databaseUser"/></td>
  </tr>
  <tr>
  <td>数据库密码：</td>
  <td><input name="databasePassword"/></td>
  </tr>
  <tr>
  <td>
  <input type="submit" value="安装" />
  </td>
  </tr>
  </table>
  </form>
  </body>
</html>
