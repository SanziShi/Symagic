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
<title>BC2商城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css"/>
<link href="css/user.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/yf_ADS.js"></script>
<script type="text/javascript" src="js/user.js"></script>
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
        </div>
        <div class="user_left">
        	<dl>
        		<dt>我的商城</dt>
        		<div onclick="show_user_con('1')"><dd>基本信息</dd></div>
                <div onclick="show_user_con('2')"><dd>修改密码</dd></div>
        		<div onclick="show_user_con('3')"><dd>修改昵称</dd></div>
        		<div onclick="show_favorite()"><dd>我的收藏</dd></div>
        		<div onclick="show_address()"><dd>收货地址</dd></div>
        		<div onclick="show_user_con('4')"><dd>我的订单</dd></div>
        		<br/>
        		<dt></dt>
        	</dl>
        </div>
        <div id="favorite" class="user_note hide">
        	<div class="head">我的收藏商品</div>
        	<div id="favorite_loading"></div>
        	<div id="favorite-container">
        	</div>
        </div>
        <div id="address" class="user_note hide">
        	<div class="head">我的收货地址</div>
        	<div id="address_loading"></div>
        	<div id="address-container">
        	</div>
        </div>
        <div id="1" class="user_note">
        	<div class="head">基本信息</div>
        	<div class="user_note_content">
        		<p><span>用户名：</span><s:property value='#session.userName'/></p>
            	<p><span>昵&nbsp;&nbsp;&nbsp;称：</span><s:property value='#session.nickname'/></p>
            	<p><span>现有积分：</span><s:property value='totalScore'/></p>
        	</div>
        </div>
        <div id="2" class="user_note hide">
        	<div class="head">修改密码</div>
        	<div class="user_note_content">
        		
        			<p><span>原密码：</span><input id="pass_before" type="password" class="inputtext"/></p>
            		<p><span>新密码：</span><input id="pass_new" type="password" class="inputtext"/></p>
            		<p><span>密码确认：</span><input id="pass_confirm" type="password" class="inputtext"/></p>
            		<p><span><input type="button" onclick="pass_submit()" value="确定修改"/></span></p>
    			
        	</div>
        </div>
        <div id="3" class="user_note hide">
        	<div class="head">个人信息</div>
        	<div class="user_note_content">
            	<p><span>昵&nbsp;&nbsp;&nbsp;称：</span><s:property value='#session.nickname'/></p>
            	<p><span>修改昵称：</span><input name="nickname_c" id="nickname_c" class="inputtext"/></p>
            	<p><span><input type="button" onclick="nickname_c(this)" value="确定修改"/></span></p>
        	</div>
        </div>
        <div id="4" class="user_note hide">
        <div class="head">我的订单</div>
        <div class="user_note_content">
        	<table id="myorder">
				<thead>
					<tr>
						<th>订单号</th>
						<th>下单时间</th>
						<th>订单金额</th>
                        <th>订单状态</th>
						<th>获得积分</th>
                        <th>操作</th>
					</tr>
				</thead>
				<tbody>
                <!--订单迭代开始-->
                <s:iterator value="orderList" var='iter'>
				  <tr id="order_list<s:property value='#iter.orderID'/>">
            		<td>
						<a href="order_info?orderID=<s:property value='#iter.orderID'/>"><s:property value='#iter.orderID'/></a>
					</td>
					<td>
						<s:property value='#iter.orderTime'/>
					</td>
					<td>
						<s:property value='#iter.totalPrice'/>
					</td>
                    <td><s:property value='#iter.orderStatus'/></td>
					<td><s:property value='#iter.score'/></td>
                    <td>
                    <s:if test="#iter.editable">
                   		<a href="order_edit?orderID=<s:property value='#iter.orderID'/>">修改</a>&nbsp;&nbsp;
                    	<a href="javascript:void(0)" onclick="cancel_order(<s:property value='#iter.orderID'/>)">取消</a>
                    </s:if>
                    <s:else>
                    	<a href="order_detail?orderID=<s:property value='#iter.orderID'/>">查看订单</a>
                    </s:else>
                    </td>
          		  </tr>
                  </s:iterator>
				 <!--订单迭代结束--> 
				 </tbody>
            </table>
        </div>
        </div>
		
	<div id="footer">
		<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 | 电子邮件：admin@163.com </span>
		<span id="footerright"> B2C商城  Power by IBM &nbsp;</span>
	</div>
</div>

</body>
</html>

