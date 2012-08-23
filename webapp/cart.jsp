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
<link href="css/cart.css" rel="stylesheet" type="text/css">
<link href="css/frame.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script><script type="text/javascript" src="js/yf_ADS.js"></script>
<script type="text/javascript" src="js/cart.js"></script>
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
			<li><a href="javascript:document.getElementById('clear_search').submit()">商品列表</a></li>
            <!--清空详细搜索的隐藏表单-->
            <form action="item_list" method="post" id="clear_search" style="display:none">
            <input name="page" value="1"/>
            <input name="name" />
            <input name="author"/>
            <input name="publisher"/>
            <input name="catalogID" value="0"/>
            <input name="publishTime" value="0"/>
            <input name="searchPage" value="0"/>
            <input name="edition" value="0"/>
            <input name="binding" value="0"/>
            <input name="booksize" value="0"/>
            <input name="price" value="0"/>
            <input name="discount" value="0"/>
            <input />
            </form>
            <!--清空详细搜索的隐藏表单-->
			<li><a href="cart">我的购物车</a></li>
			<li><a href="user">我的商城</a></li>
			<li><a href="send_notes">购物指引</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
		</ul>
	</div>
	<div id="main">
		<div id="search2">
			<div id="searchleft">
				<img src="image/ico_site.jpg" id="ico_site"/>
				网站路径：<a href="index">首页</a>&gt;&gt;<a href="#">购物车</a>			</div>
			<form action="quick_search" >
			<div id="searchright2">
            	<input style="display:none" name="page" value="1">
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
        <div class="clear"></div>
        <form id="checkout" action="order" method="post" onsubmit="return checkout();">
        <div id="cart-a">
        	<h2>我的购物车</h2>
        	<div id="cart_table">
        		<div id="cart_table_head">
                	<div class="column checkbox">
                		<input c="1" checked="checked" onclick="check_box_all_change(1,this)" type="checkbox"/>
                	</div>
                    <div class="column p-name">商品信息</div>
                    <div class="column p-price">商城价</div>
                    <div class="column quantity">数量</div>
                    <div class="column total">小计</div>
                    <div class="column action">操作</div>
            	</div>
            	<div id="cart_table_content">
                	<!--购物车迭代-->
                     <s:iterator value="items" var='iter' status='st'>
                    <div class="each">
                    <div class="cell checkbox">
                		<input c="1" checked="checked" name="items[<s:property value='#st.index'/>].itemID" type="checkbox" id="<s:property value='#iter.itemID'/>" value="<s:property value='#iter.itemID'/>" />
                	</div>
                    <div class="cell p-name">
                    	<div class="p-img">
                        	<a href="item_detail?itemID=<s:property value='#iter.itemID'/>"><img src="<s:property value='#request.get("javax.servlet.forward.context_path")'/><s:property value='#iter.picturePath'/>"></a>
                        </div>
                      <div class="p-name-de"><a href="item_detail?itemID=<s:property value='#iter.itemID'/>"><s:property value='#iter.name'/></a></div>
                    </div>
                    <div class="cell p-price p-price-p">
                    	<del>￥<span><s:property value='#iter.marketPrice'/></span></del>
                        <br>￥<span><s:property value='#iter.price'/></span>
                        <br><font class="red">为您节省：<span>￥<s:property value='#iter.savePrice'/></span></font>
                    </div>
                    <div class="cell quantity">
                    	<span>
                        	<a class="reduce" onclick="reduce(<s:property value='#iter.itemID'/>)" href="javascript:void(0)">-</a>
        					<input default="<s:property value='#iter.itemNumber'/>" onblur="check_item_num(<s:property value='#iter.itemID'/>,this)" name="items[<s:property value='#st.index'/>].itemNumber" type="text" value="<s:property value='#iter.itemNumber'/>" id="<s:property value='#iter.itemID'/>amount" onkeyup="amount_modify(this)">
        					<a class="reduce" onclick="add(<s:property value='#iter.itemID'/>)" href="javascript:void(0)">+</a>
        				</span>
                    </div>
         			<div class="cell total">￥<span id="<s:property value='#iter.itemID'/>total_price"><s:property value='#iter.itemTotalPrice'/></span></div>
                    <div class="cell action"><a onclick="add_to_favorite(<s:property value='#iter.itemID'/>)" href="javascript:void(0)">收藏</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="delete_from_cart(<s:property value='#iter.itemID'/>,'p')">删除</a></div>
                    </div>
                    </s:iterator>
                    <!--购物车迭代--end-->
            	</div>
            	<div id="cart_table_bottom">
                <input c="1" checked="checked" onclick="check_box_all_change(1,this)" type="checkbox"/>
                全选&nbsp;&nbsp;
                <a href="javascript:void(0);" onclick="delete_form_cart('checkout')">删除选中的商品</a>
                &nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="add_to_favorites()">添加至收藏夹</a>
                <div id="total-price">总计:<span id="final-price">￥<s:property value='totalPrice'/></span></div>
            	</div>
        	</div>
        </div>
        <div class="cart-button"><a onclick="checkout()" href="javascript:void(0);" class="checkout"></a></div>
        </form>
        <div id="recommend"></div>
	<div id="footer">
				<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 |
					电子邮件：Symagics@gmail.com </span> <span id="footerright"> Symagic网上书城
					Power by Symagic &nbsp;</span>
			</div>
</div>

</body>
</html>
