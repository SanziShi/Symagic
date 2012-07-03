<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Symagic网上书城</title>
<link href="css/home.css" rel="stylesheet" type="text/css">
<link href="css/error.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/yf_ADS.js"></script>
</head>

<body>
<div id="container">
	
<div id="cart">
	<span id="cart_loading"></span>
	<div id="cart_none">您的购物车中还没有商品，请选购！</div>
	<div id="cart_container"></div>
	
</div>
	<div id="logalleft">
		<div id="top-overlay"></div>
    <!----吊顶栏------>
	<div class="top">
    <div class="top_right">
    <ul>
    <s:if test="#session.nickname!=null">
    <li><s:property value="#session.nickname"/>！&nbsp;&nbsp;欢迎回到Symagic！</li>
    <li id="logout_top" onclick="logout();"><a>安全退出</a></li>
    </s:if>
    <s:else>
    <li>欢迎来到Symagic！</li>
    <li id="login_top" onclick="load_login();"><a>登录</a></li>
    <li id="regist_top" onclick="load_regist();"><a>免费注册</a></li>
    </s:else>
    <li class="division">|</li><li id="mymall"><a href="user"><span id="mymall_icon"></span>我的商城</a></li><li class="division">|</li>
    <li id="cart_top"><a id="cart_a" href="cart">
    <span id="cart_icon"></span>购物车 <strong id="cart_num"><s:property value='#session.totalNumber'/></strong> 件</a>
    </li>
    </ul>
    </div>
    </div>
  <!------吊顶结束----->
	</div>
	<div id="globallink">
		<ul>
			<li><a href="index">首页</a></li>
			<li><a href="item_list">商品列表</a></li>
			<li><a href="favorite.html">收藏夹</a></li>
			<li><a href="address.html">地址簿</a></li>
			<li><a href="tradequery.html">交易查询</a></li>
			<li><a href="send_notes.jsp">购物指引</a></li>
            <li><a href="credit_query.html">积分查询</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
		</ul>
	</div>
<!--	<div id="banner"></div>-->
     <div id="error">
<TABLE cellSpacing=0 width=600 align=center border=0 cepadding="0">
  <TBODY>
  <TR colspan="2">
    <TD vAlign=top align=middle><IMG height=100 src="image/error.jpg" 
      width=100 border=0> 
    <TD>
    <TD><!--------System Return Begin------------>
      <H1>
      <s:if test="errorHeader">
      	<s:property value="errorHeader"/>
      </s:if>
      <s:else>
      出错啦~~
      </s:else>
      </H1>
      错误信息：
      <s:if test="errorSpecification">
      	<s:property value="errorSpecification"/> 
      </s:if>
      <s:else>
      您请求的页面非法或已被删除！！
      </s:else>
      <HR noShade SIZE=0>
      <P>☉ 请尝试以下操作：</P>
      <UL>
        <LI>确保浏览器的地址栏中显示的网站地址的拼写和格式正确无误。 
        <LI>如果通过单击链接而到达了该网页，请与网站管理员联系，通知他们该链接的格式不正确。 
        <LI>单击<A href="javascript:history.back(1)"><FONT 
        color=#ff0000>后退</FONT></A>按钮尝试另一个链接。       </LI></UL>
<HR noShade SIZE=0>

      <P>☉ 如果您对本站有任何疑问、意见、建议、咨询，请联系管理员 
      <BR>
      &nbsp;&nbsp;&nbsp;<BR>
      </P><!------------End this!---------------></TD></TR></TBODY></TABLE>
</div>
    </div>
   
</body>
</html>