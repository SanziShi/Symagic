<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
	
	
	<div id="container">
	<div id="logalleft">
		<div id="logalright">
		</div>
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
      <H1><s:property value="errorHeader"/></H1>
      错误：<s:property value="errorSpecification"/>
      

     <!-- <P>☉ 请尝试以下操作：</P>
      <UL>
        <LI>检查您输入的用户名 
        <LI>检查您输入的密码
        <LI>单击<A href="javascript:history.back(1)"><FONT 
        color=#ff0000>后退</FONT></A>按钮尝试另一个链接。       </LI></UL>-->

      <P>☉ 如果您对本站有任何疑问、意见、建议、咨询，请联系管理员 
      <BR>
      &nbsp;&nbsp;&nbsp;<BR>
      </P><!------------End this!---------------></TD></TR></TBODY></TABLE>
</div>
<div id="footer">
		<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 | 电子邮件：Symagics@gmail.com </span>
		<span id="footerright"> Symagic网上书城  Power by Symagic &nbsp;</span>
	</div>
    </div>
    
   
</body>
</html>