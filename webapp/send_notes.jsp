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
<link href="css/frame.css" rel="stylesheet" type="text/css"/>
<link href="css/item.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/yf_ADS.js"></script>
<script type="text/javascript" src="js/item_list.js"></script>
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
			<li><a href="send_notes.jsp">购物指引</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
                        <li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
		</ul>
	</div>
	<div id="main">
		<div id="search2">
			<div id="searchleft">
				<img src="image/ico_site.jpg" id="ico_site"/>
				网站路径：<a href="index.html">首页</a>&gt;&gt;<a href="send_notes.jsp">送货说明</a>
			</div>
			<div id="searchright2">
			  <input type="text" name="product" id="textInput"/>
			  <input type="button" name="Submit" value="搜索" id="searchbutton" onClick="javascript:window.open('item_search_list.html','_parent','')">
			</div>
			<div id="searchright1">
			  <select name="category" id="searchrightcategory">
				<option value="5">所有类别</option>
                <option value="1">图书音像</option>
                <option value="2">时尚生活</option>
                <option value="3">饰品配件</option>
                <option value="4">数码产品</option>
              </select>
		  </div>
		</div>
		<div id="sendnote">
			<div id="sendnotehead"><strong>购物指引</strong></div>
			<div id="sendnotecontent">
			<ul>
			<li>1.系统将会根据您的送货地址来收取送货费：本地5元，外地10元；</li>
			<li>2.会员可以使用积分代替现金，系统会将您在结帐时输入的积分数来换算成现金；</li>
			<li>3.对于本地会员，当购买金额大于300元时，将免收送货费；对于外地会员，当购买金额大于800时，将免收送货费；</li>
			<li>4.对于优惠商品，会员可以获得适当优惠；</li>
			<li>5.最终订单金额将由 商品商城价格 + 送货费 - 积分代现金 - 优惠 计算得出。</li>
			</ul>
			</div>
		</div>
		
	<div id="footer">
		<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 | 电子邮件：admin@163.com </span>
		<span id="footerright"> B2C商城  Power by IBM &nbsp;</span>
	</div>
</div>

</body>
</html>
