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
<title>BC2商城</title>
<link href="css/home.css" rel="stylesheet" type="text/css">
<link href="css/order.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/yf_ADS.js"></script>
<script type="text/javascript" src="js/order.js"></script>
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
			<li><a href="send_notes.html">送货说明</a></li>
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
    <form action="order_submit" method="post">
    <div id="order_container">
    	<div id="c-info">
        	<h1>收货人信息</h1>
            <div id="c-input">
            	<div id="address_list">
                	<div class="address_list_inner">
                    	<b>常用地址：</b>
                    	<ul>
                        	<li id=""><input type="radio" id="1" onclick=""/><label for="1"><strong>收货人</strong>&nbsp;测试地址</label><span class="delete"><a href="">删除地址</a></span></li>
                        </ul>
                    </div>
                </div>
            	<table width="100%" cellspacing="0" border="0">
                	<tbody>
                    	<tr>
                        	<td width="80" valign="middle" align="right"><font color="red">*</font>收货人姓名：</td>
                            <td><input class="txt" type="text"/></td>
                        </tr>
                        <tr>
                        	<td valign="middle" align="right"><font color="red">*</font>所在地区：</td>
                            <td>
                            	<select><option value="">请选择</option></select>
                                <select><option value="">请选择</option></select>
                                <select><option value="">请选择</option></select>
                            </td>
                        </tr>
                    	<tr>
                        	<td valign="middle" align="right"><font color="red">*</font>详细地址：</td>
                            <td><span id="consign_address"></span><input type="text" class="txt long"></td>
                        </tr>
                    	<tr>
                        	<td valign="middle" align="right"><font color="red">*</font>手机号码：</td>
                            <td><input type="text" class="txt"/>或者&nbsp;&nbsp;或&nbsp;&nbsp;固定电话：<input type="text" class="txt"/></td>
                        </tr>
                    	<tr>
                        	<td valign="middle" align="right">邮箱地址：</td>
                            <td>43234@qq.com</td>
                        </tr>
                    	<tr>
                        	<td valign="middle" align="right"><font color="red">*</font>邮政编码：</td>
                            <td><input type="text" class="txt short"/></td>
                        </tr>
                    </tbody>
                </table>
                <span class="add-to-address">添加信息至地址簿</span><span>重置内容</span>
            </div>
        </div>
        <div id="payment-c">
        	<h1>支付及配送方式</h1>
            <table width="100%" cellspacing="0" border="0">
                	<tbody>
                    	<tr>
                        	<td width="100" valign="middle" align="left"><h1>支付方式</h1></td>
                            <td></td>
                        </tr>
                    	<tr>
                        	<td><input type="radio" checked="checked">货到付款</input></td>
                            <td class="comment">送货上门后付款，使用现金</td>
                        </tr>
                    </tbody>
            </table>
            <table width="100%" cellspacing="0" border="0">
                	<tbody>
                    	<tr>
                        	<td width="100" valign="middle" align="left"><h1>配送方式</h1></td>
                        	<td></td>
                        </tr>
                    	<tr>
                        	<td><input type="radio" checked="checked">快递</input></td>
                            <td class="comment">使用默认的快递方式，快捷省事</td>
                        </tr>
                    </tbody>
            </table>
        </div>
        <div id="item_list">
        	<h1>商品清单</h1>
            <div id="item_table">
            	<table >
            		<tbody>
                		<tr class="thead">
                 	   		<td width="9%">商品ID</td>
                    		<td width="37%">商品名称</td>
                    		<td width="13%">商城价</td>
                    		<td width="13%">商品数量</td>
                    		<td width="13%">商品小计</td>
                    		<td width="15%">可获积分</td>
                    	</tr>
                		<!--商品列表迭代开始-->
                        <s:iterator value="buyItems" var='iter'>
                    	<tr>
                    		<td><s:property value='#iter.itemID'/></td>
                        	<td><s:property value='#iter.name'/></td>
                        	<td><s:property value='#iter.price'/></td>
                        	<td><s:property value='#iter.itemNumber'/></td>
                        	<td><s:property value='#iter.itemTotalPrice'/></td>
                        	<td><s:property value='#iter.score'/></td>
                    	</tr>
                         </s:iterator>
                    	<!--商品列表迭代结束-->
                	</tbody>
           		</table>
            </div>
        </div>
        <div id="ware">
        	<h1>结算</h1>
        	<div class="total_price"><span id="pay">应付总额：<strong>￥200.00</strong>元</span></div>
        	<div class="clear"></div>
        	<div class="order_confirm"></div>
        	<div class="clear"></div>
        </div>     
    </div>
    </form>
  	</div>
  	<div id="footer">
	  <span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 | 电子邮件：admin@163.com </span>
	  <span id="footerright"> B2C商城  Power by IBM &nbsp;</span>
	</div>
</div>

</body>
</html>

