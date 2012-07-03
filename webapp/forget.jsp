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
<link href="css/ok.css" rel="stylesheet" type="text/css"/>
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
			<li><a href="send_notes">购物指引</a></li>
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
				网站路径：<a href="index">首页</a>&gt;&gt;<a href=""> 忘记密码</strong></a>
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
			<div id="sendnotehead"><strong><font color="#FFFFFF">找回密码</font></strong></div>
			<div id="sendnotecontent">
			<form action="" method="post" enctype="multipart/form-data" onsubmit="return forget(this)">
				<table id="itemsearch">
				   <tr>
					<th class="itemsearchth" ><font class="red">*</font>用户名(E-mail地址)：</th>
            		<td class="itemsearchtd1">
					<input type="Text" class="inputtext" name="userName" >
                    </td>
                    <td class="itemsearchtd2">
						重置后的密码会发至您的邮箱，请注意查收<span class="red"></td>
				 <tr>
				 <th class="itemsearchth"><font color="red">*</font>安全提问问题：</th>
            		<td class="itemsearchtd1">
					  	<select onchange="safe_question(this)" name="securityQuestion">
                        <option >您父亲的名字是？</option>
                        <option >您母亲的名字是？</option>
                        <option >您小学就读的学校是？</option>
						 <option >您出生与哪个医院？</option>
                        <option >您高中的班主任是？</option>
                        <option >自定义问题</option>
                        </select>
                        </td>
						<td class="itemsearchtd2">
						</td>
					
          		 </tr>
                 <tr style="display:none">
                 <th class="itemsearchth"><span class="red">*</span>自定义问题：</th>
            		<td class="itemsearchtd1">
					  <input type="text" class="inputtext" name="securityQuestion" >
					</td>
					<td class="itemsearchtd2">
					</td>
          		 </tr>
                 <tr>
				 <th class="itemsearchth"><font color="red">*</font>安全回答：</th>
            		<td class="itemsearchtd1">
					<input type="text" name="securityAnswer" class="inputtext"/>
					<td class="itemsearchtd2">
						</td>
          		 </tr>
                 <tr>
					<td class="itemsearchtd2"></td>
                 <td>
					<input  align="middle" type="Button" name="button2" value="提交" onClick="checkpwdform()">
						&nbsp;<input align="middle" type="Reset" name="button1" value="重填" onClick="clear()">
						&nbsp;
                        </td>
			     </tr>
        </table>
		</form>		
			</div>
		</div>
  </div>
  <div id="footer">
				<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 |
					电子邮件：Symagics@gmail.com </span> <span id="footerright"> Symagic网上书城
					Power by Symagic &nbsp;</span>
			</div>
</div>

</body>
</html>

