<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>BC2商城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css">
<link href="css/item_info.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/yf_ADS.js"></script>
<script type="text/javascript" src="js/item_info.js"></script>
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
    <li class="division">|</li><li id="mymall"><a href="user.html"><span id="mymall_icon"></span>我的商城</a></li><li class="division">|</li>
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
			<li><a href="item_list.html">商品列表</a></li>
			<li><a href="favorite.html">收藏夹</a></li>
			<li><a href="address.html">地址簿</a></li>
			<li><a href="tradequery.html">交易查询</a></li>
			<li><a href="send_notes.html">送货说明</a></li>
            <li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
		</ul>
	</div>
<!--	<div id="banner"></div>-->
	<div id="main">
		<div id="search2">
			<div id="searchleft">
				<img src="image/ico_site.jpg"  id="ico_site"/>
				网站路径：<a href="index.html">首页</a>&gt;&gt;<a href="#">商品详细信息</a>
			</div>
			<div id="searchright2">
			  <form action="quick_search" >
			  <select name="catalogID" >
			  <option value="0">所有类别</option>
			  <s:iterator value="catalog" var='outer'>
				<option value="<s:property value='#outer.ID'/>"><s:property value='#outer.name'/></option>
				<s:iterator value="#outer.childCatalog" var="inner">
				<option value="<s:property value='#inner.ID'/>"><s:property value='#inner.name'/></option>
				</s:iterator>
				</s:iterator>
              </select>
			  <input type="text" name="keyword" id="keyword" class="gray" value="快速搜索...."onFocus="onfocus_check(this,'快速搜索....')" onblur="onblur_check(this,'快速搜索....')" />
			  <input type="button" name="Submit" value="搜索" onClick="">
			</form>
		  </div>
		</div>
        <div class="clear"></div>
        <!--购买推荐左边栏-->
      <div class="recommend">
        <div class="head"><h3>购买本书的顾客还购买了</h3></div>
        <!----推荐迭代li---->
       <li><div class="p-img"><a href="" title="数学之美" target="_blank"><img width="50" height="50" src="upload/linux.jpg"></a></div>						 		<div class="p-name"><a href="" >高性能Linux服务器构建实战</a></div>
       <div class="p-price"><strong>￥30.00</strong></div></li>
       <!----推荐迭代li结束---->
        </div>
        <!--购买推荐左边栏结束-->
        <!--商品详细信息-->
        <div class="item_info">
        <div class="fliter"></div>
        <div class="name"><h2><s:property value='#book.bookName'/></h2></div>
        <div id="preview">
        <div id="spec-n1"><img src="<s:property value='#request.get("javax.servlet.forward.context_path")'/><s:property value='#book.picturePath'/>"/></div>
        <ul>
        <li><span>总评分：</span><span class="star"><span class="sa45"></span></span></li>
        </ul>
        </div>
        <ul id="summary">
        <li><span>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：</span><s:property value='#book.author'/></li>
        <li><span>出&nbsp;&nbsp;版&nbsp;&nbsp;社：</span><s:property value='#book.publisher'/></li>
        <li><span>I&nbsp;&nbsp;S&nbsp;&nbsp;B&nbsp;&nbsp;N：</span><s:property value='#book.isbn'/></li>
        <li><span>出版日期：</span><s:property value='#book.publishDate'/></li>
        <li><span>版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次：</span><s:property value='#book.version'/></li>
        <li><span>开&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本：</span><s:property value='#book.folio'/></li>
        <li><span>装&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帧：</span><s:property value='#book.binding'/></li>
        <li><span>页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数：</span><s:property value='#book.page'/></li>
        <li><span>语&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;种：</span>中文</li>
        </ul>
        <ul id="book_price">
        <li><span>定&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</span><del><s:property value='#book.marketPrice'/></del></li>
        <li><span>商&nbsp;&nbsp;城&nbsp;&nbsp;价：</span><strong><s:property value='#book.price'/></strong></li>
        <li><span>为您节省：</span><strong>￥1.42</strong></li>
        <li><span>库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存：</span><s:property value='#book.inventory'/></li>
        </ul>
        <div id="add_to_cart">
        <div id="item_amount">
        <span id="buy_num">购买数量：</span><span><a class="reduce" onclick="reduce();" href="javascript:void(0)">-</a>
        <input type="text" value="1" id="amount" onkeyup="amount_modify(this)">
        <a class="reduce" onclick="add();" href="javascript:void(0)">+</a>
        </span></div>
        <div class="btns">
					<a onclick="add_to_cart()" href="javascript:void(0)" class="append" ></a><a onclick="favorite()" href="javascript:void(0)" class="favorite"></a>
					<div class="clear"></div>
				</div>
        </div>
        <div class="clear"></div>
        <div class="item_desc">
        <div class="banner"><li>内容简介</li></div>
        <div class="item_desc_con">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value='#book.bookDesc'/></div>
        </div>
        <div id="comment">
        <div class="banner"><li>用户评价</li></div>
        <div class="comment_item">
        <div class="buyer">
        <span class="buyer_name">测试用户</span>
        <span class="sa45"></span>
        <span class="date-comment">2012-06-21 08:41:32</span>
        </div>
        <div class="comment-content">厚厚的一本，由浅入深，知识比较全面，适合初学者学习使用</div>
        </div>
        <div class="comment_item">
        <div class="buyer">
        <span class="buyer_name">测试用户2</span>
        <span class="sa4"></span>
        <span class="date-comment">2012-06-21 08:41:32</span>
        </div>
        <div class="comment-content">很不错的书，测试测试测试测试测试测试测试测试</div>
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
