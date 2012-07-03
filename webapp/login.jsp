<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Symagic网上书城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css"/>
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
			<li><a href="send_notes.jsp">购物指引</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
                        <li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
		</ul>
	</div>
	<div id="banner"></div>
  <div id="main">
		<div id="search2">
			<div id="searchleft">
				<img src="image/ico_site.jpg"  id="ico_site"/>
				网站路径：<a href="index.html">首页</a>&gt;&gt;<a href=""> 个人信息维护</strong></a>
			</div>
			<form action="quick_search" method="post">
			<div id="searchright2">
			  <input type="text" name="keyword" id="textInput"/>
			  <input type="submit" value="搜索" id="searchbutton" />
			</div>
			<div id="searchright1">
			 <select name="catalogID" >
			  <option value="0">所有类别</option>
			  <s:iterator value="catalog" var='outer'>
				<option value="<s:property value='#outer.ID'/>"><s:property value='#outer.name'/></option>
				<s:iterator value="#outer.childCatalog" var="inner">
				<option value="<s:property value='#inner.ID'/>">&nbsp;&nbsp;&nbsp;<s:property value='#inner.name'/></option>
				</s:iterator>
			   </s:iterator>
              </select> 
              </div>
		  </form>
		</div>
        <div id="sendnote">
			<div id="sendnotehead"><strong><font color="#FFFFFF">您未登录，请先登录</font></strong></div>
			<div id="sendnotecontent">
			<form action="" method="post" enctype="multipart/form-data" name="form1" onsubmit="return login(this);">
				<table id="itemsearch">
				   <tr>
					<th width="30%" style="text-align:right">用户名：</th>
            		<td class="itemsearchtd1">
					<input type="Text" class="inputtext_gray" name="userName" id="userName" onFocus="onfocus_check(this,'邮箱地址')" onblur="onblur_check(this,'邮箱地址')" value="邮箱地址" maxlength="30">
					</td>
					<td class="itemsearchtd2">
					</td>
          		 </tr>
				 <tr>
				 <th style="text-align:right">密&nbsp;&nbsp;码：</th>
            		<td class="itemsearchtd1">
					<input type="password" id="psaaword" class="inputtext hide" name="password" onblur="password_onblur(this)" value="" maxlength="20" >
                    <input type="test" class="inputtext_gray" onFocus="passwordtext_onfocus(this)" value="输入6至20位密码" >
					</td>
					<td class="itemsearchtd2"></td>
                     </tr>
          		 <tr class="">
				 <th style="text-align:right">验证码：</th>
            		<td class="itemsearchtd1">
						<input type="text" name="captchaValue" class="inputtext"/>
					</td>
					<td style="text-align:left">
					 <img id="cap" src="captcha_get_captcha" onclick="change_captcha(this)"/>
					</td>
          		 </tr>
				
				<tr>
                 <td></td>
                 <td>
                 <a href="forget_password"><span class="red left_float">忘记密码？</span></a></td>
                 <td></td>
                 </tr>
				 <tr>
				 <th></th>
				 	<td><input type="submit" value="登陆" onClick="">
						
						&nbsp;<input type="Button"  value="注册" onClick="clear_notice();load_regist();">
					
					</td>
				 </tr>
        </table>
		</form>		
			</div>
		</div>
  </div>
  <div id="footer">
	  <span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 | 电子邮件：admin@163.com </span>
	  <span id="footerright"> B2C商城  Power by IBM &nbsp;</span>
	</div>
</div>

</body>
</html>

