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
	<div id="main">
		<div id="search2">
			<div id="searchleft">
				<img src="image/ico_site.jpg"  id="ico_site"/>
				网站路径：<a href="index.html">首页</a>&gt;&gt;<a href="#">商品详细信息</a>
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
        <div class="clear"></div>
        <div id="recommend-container">
        <!--购买推荐左边栏-->
      <div class="recommend">
        <div class="head"><h3>购买本书的顾客还购买了</h3></div>
        <!----推荐迭代li---->
        <s:iterator value="recommendBought" var='iter'>
       <li><div class="p-img"><a href="item_detail?itemID=<s:property value='#iter.itemID'/>" title="数学之美" target="_blank"><img width="50" height="50" src="<s:property value='#request.get("javax.servlet.forward.context_path")'/><s:property value='#iter.picturePath'/>"></a></div><div class="p-name"><a href="item_detail?itemID=<s:property value='#iter.itemID'/>" ><s:property value='#iter.name'/></a></div>
       <div class="p-price"><strong>￥<s:property value='#iter.price'/></strong></div></li>
       </s:iterator>
       <!----推荐迭代li结束---->
       
        </div>
        <!--购买推荐左边栏结束-->
        <!--购买推荐左边栏-->
      <div class="recommend">
        <div class="head"><h3>浏览本书的顾客还浏览了</h3></div>
        <!----推荐迭代li---->
        <s:iterator value="recommendView" var='iter'>
       <li><div class="p-img"><a href="item_detail?itemID=<s:property value='#iter.itemID'/>" title="数学之美" target="_blank"><img width="50" height="50" src="<s:property value='#request.get("javax.servlet.forward.context_path")'/><s:property value='#iter.picturePath'/>"></a></div><div class="p-name"><a href="item_detail?itemID=<s:property value='#iter.itemID'/>" ><s:property value='#iter.name'/></a></div>
       <div class="p-price"><strong>￥<s:property value='#iter.price'/></strong></div></li>
       </s:iterator>
       <!----推荐迭代li结束---->
       
        </div>
        <!--购买推荐左边栏结束-->
        </div>
        <!--商品详细信息-->
        <div class="item_info">
        <div class="fliter"></div>
        <div class="name"><h2><s:property value='book.bookName'/></h2></div>
        <div id="preview">
        <div id="spec-n1"><img src="<s:property value='#request.get("javax.servlet.forward.context_path")'/><s:property value='book.picturePath'/>"/></div>
        <ul>
        <li><span>总评分：</span>
        	<span class="star">
            <!--评分表现逻辑-->
            	<span class="sa<s:property value='book.averageRating'/>"></span>
            <!---评分表现逻辑--->
            </span>&nbsp;&nbsp;（&nbsp;<s:property value='book.averageRating'/>&nbsp;分&nbsp;）
        </li>
        <li><span>书籍分类：</span><s:property value='book.catalogClassify'/></li>
        <s:if test='book.offline'>
            <li><font class="red">该商品已下架，您可以收藏该商品！</font></li>
            </s:if>
        </ul>
        </div>
        <ul id="summary">
        <li><span>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：</span>&nbsp;<s:property value='book.author'/></li>
        <li><span>出&nbsp;&nbsp;版&nbsp;&nbsp;社：</span>&nbsp;<s:property value='book.publisher'/></li>
        <li><span>I&nbsp;&nbsp;S&nbsp;&nbsp;B&nbsp;&nbsp;N：</span>&nbsp;<s:property value='book.isbn'/></li>
        <li><span>出版日期：</span>&nbsp;<s:property value='book.publishDate'/></li>
        <li><span>版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次：</span>&nbsp;<s:property value='book.version'/></li>
        <li><span>开&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本：</span>&nbsp;<s:property value='book.size'/></li>
        <li><span>装&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帧：</span>&nbsp;<s:property value='book.binding'/></li>
        <li><span>页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数：</span>&nbsp;<s:property value='book.page'/></li>
        </ul>
        <ul id="book_price">
        <li><span>定&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</span><del>￥<s:property value='book.marketPrice'/></del></li>
        <li><span>商&nbsp;&nbsp;城&nbsp;&nbsp;价：</span><strong>￥<s:property value='book.price'/></strong></li>
        <li><span>为您节省：</span><strong>&nbsp;<strong>￥<s:property value='book.savePrice'/></strong></strong></li>
        <li><span>库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存：</span>&nbsp;<s:property value='book.inventory'/></li>
        </ul>
        <div id="add_to_cart">
        <div id="item_amount">
        	<span id="buy_num">购买数量：</span>
        	<span><s:if test='book.offline'>
            <a class="reduce" href="javascript:void(0)">-</a>
            <input type="text" value="1" id="amount" disabled="disabled"/>
        	<a class="reduce" href="javascript:void(0)">+</a>
            </s:if>
            <s:else>
            <a class="reduce" onclick="reduce();" href="javascript:void(0)">-</a>
            <input type="text" value="1" id="amount" onkeyup="amount_modify(this)"/>
            <a class="reduce" onclick="add();" href="javascript:void(0)">+</a>
            </s:else>
            </span>
            
        </div>
        <div class="btns">
        			<s:if test='!book.offline'>
					<a onclick="add_to_cart(<s:property value='itemID'/>)" href="javascript:void(0)" class="append" ></a>
                    </s:if>
                    <s:else>
                    <a  href="javascript:void(0)" class="disable" ></a>
                    </s:else>
                    <a onclick="add_to_favorite(<s:property value='itemID'/>)" href="javascript:void(0)" class="favorite"></a>
					<div class="clear"></div>
				</div>
        </div>
        <div class="clear"></div>
        <div class="item_desc">
        <div class="banner"><li>内容简介</li></div>
        <div class="item_desc_con">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value='book.bookDesc' escape='false'/></div>
        </div>
        <div id="comment">
        <div class="banner"><li>用户评价</li></div>
        <!--用户评价迭代-->
        <s:iterator value="commentList" var='iter'>
        	<div class="comment_item">
        		<div class="buyer">
        		<span class="buyer_name"><s:property value='#iter.userName'/>测试用户</span>
        		<span class="star"><span class="sa<s:property value='#iter.rating'/>"></span></span>
        		<span class="date-comment"><s:property value='#iter.commentDate'/></span>
        		</div>
        		<div class="comment-content"><s:property value='#iter.content'/></div>
        	</div>
        </s:iterator>
        <!--用户评价迭代-->
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
