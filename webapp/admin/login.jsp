<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BC2商城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css"/>
<script language="javascript" src="js/checkform.js"></script>
<script language="javascript" src="gz.js"></script>
</head>

<body>
<div id="container">
	<div id="logalleft">
		<div id="logalright">
		</div>
	</div>
	
<!--	<div id="banner"></div>-->
	<div id="main">
		<div id="search2">
			<div id="searchleft">
				<img src="../image/ico_site.jpg"  id="ico_site"/>
				网站路径：</a>&gt;&gt;<a href="">管理员登录</a>
			</div>
		</div>
		<div id="sendnote">
			<div id="sendnotehead"><strong> 管理员登录</strong></div>
			<div id="sendnotecontent">

			<s:form action="login" method="post" >
				<table id="itemsearch">
				   <tr>
					<th class="itemsearchth" >用户名：</th>
            		<td class="itemsearchtd1">
					<s:textfield name="userName" />
					</td>
					<td class="itemsearchtd2">
					</td>
          		 </tr>
				 <tr>
				 <th class="itemsearchth">密&nbsp;&nbsp;&nbsp;码：</th>
            		<td class="itemsearchtd1">
					<s:password  name="password"  maxlength="20" />
					</td>
					<td class="itemsearchtd2">&nbsp;</td>
                    <td><input type="hidden" name="toURL"  value="<s:property value="toURL"/>"/></td>
          		 </tr>
                  <tr>
				 <th class="itemsearchth">验证码：</th>
            		<td class="itemsearchtd1">
						<s:textfield name="captchaValue" />
					</td>
					<td>
					 <img  src="captcha_get_captcha" onclick="change_captcha(this)"/>	
					</td>
          		 </tr>
				
				 <tr>
				 <th></th>
				 	<td> <s:submit value="登录" />
						&nbsp;
					</td>
				 </tr>
        </table>
		</s:form>	
			</div>
		</div>
	
	<div id="footer">
		<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 | 电子邮件：Symagics@gmail.com </span>
		<span id="footerright"> Symagic网上商城  Power by Symagic &nbsp;</span>
	</div>
</div>

</body>
</html>
