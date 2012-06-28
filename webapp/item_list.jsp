<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BC2商城</title>
<link href="css/home.css" rel="stylesheet" type="text/css">
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
			<li><a href="index.html">首页</a></li>
			<li><a href="item_list.html">商品列表</a></li>
			<li><a href="favorite.html">收藏夹</a></li>
			<li><a href="address.html">地址簿</a></li>
			<li><a href="tradequery.html">交易查询</a></li>
			<li><a href="send_notes.html">送货说明</a></li>
            <li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
		</ul>
	</div>
	<!--<div id="banner"></div>-->
	<div id="main">
		<div id="search2">
			<div id="searchleft">
				<img src="image/ico_site.jpg"  id="ico_site"/>
				网站路径：<a href="index.html">首页</a>&gt;&gt;<a href="">商品搜索</a>
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
		
        <div class="clear"></div>
        <div id="item_left">
        <dl>
        <dt>文学作品</dt>
        <div ><dd>诗词歌曲</dd></div>
        <div ><dd>中国文学</dd></div>
        <div ><dd>儿童文学</dd></div>
        <div ><dd>民间文学</dd></div>
        <dt>金融与投资</dt>
        <div ><dd>诗词歌曲</dd></div>
        <div ><dd>中国文学</dd></div>
        <div ><dd>儿童文学</dd></div>
        <div ><dd>民间文学</dd></div>
        <br/>
        <dt></dt>
        </dl>
        </div>
        <div  class="user_note">
			<div class="fliter"><font>商品搜索</font><span class="collapse" onclick="show_item_search(this);"></span></div>
			<div id="item_search1" class="user_note_content hide">
				<table>
				<tr>
					<th>书&nbsp;&nbsp;&nbsp;名：</th>
            		<td>
					<input type="text" />
					</td>
                   <th>作&nbsp;&nbsp;&nbsp;者：</th>
                    <td>
					<input type="text"/>
					</td>
          		 </tr>
				<tr>
					<th>出版社：</th>
            		<td>
					<input type="text"/>
					</td>
                    <th>书籍描述：</th>
            		<td>
					<input type="text"/>
					</td>
          		 </tr>
				 <tr>
					<th>书籍类别：</th>
            		<td>
						<select name="catogory" class="midselect">
              				<option>所有类别</option>
             				<option>文学</option>
              				<option>艺术</option>
              				<option>金融与投资</option>
			  				<option></option>
            			</select>
					</td>
                   <th>出版时间：</th>
            		<td>
						<select name="publish_time" class="midselect">
              				<option>不论时间</option>
             				<option>2012</option>
              				<option>2011</option>
              				<option>2010</option>
			  				<option>2009</option>
            			</select>
					</td> 
          		 </tr>
				
				 <tr>
					<th>页数范围：</th>
            		<td>
						<select name="catogory" class="midselect">
              				<option>0-200</option>
             				<option>200-400</option>
              				<option>400-600</option>
			  				<option>600以上</option>
            			</select>
					</td>
					<th>版&nbsp;&nbsp;&nbsp;次：</th>
            		<td>
						<select name="catogory" class="midselect">
              				<option>全部版次</option>
             				<option>1</option>
              				<option>2</option>
              				<option>3</option>
			  				<option>4</option>
            			</select>
					</td>
          		 </tr>
				 <tr>
					
					<th>装&nbsp;&nbsp;&nbsp;帧：</th>
            		<td>
						<select name="catogory" class="midselect">
              				<option>不论</option>
             				<option>平装</option>
              				<option>精装</option>
            			</select>
					</td>
                    <th>开&nbsp;&nbsp;&nbsp;本：</th>
            		<td>
						<select name="catogory" class="midselect">
              				<option>不论</option>
             				<option>32</option>
              				<option>16</option>
							<option>8</option>
            			</select>
					</td>
          		 </tr>
				 <tr>
					
				 <th>价&nbsp;&nbsp;&nbsp;格：</th>
            		<td>
						<select name="price" class="midselect">
              				<option>不指定</option>
             				<option>0-10元</option>
              				<option>10-30元</option>
              				<option>30-50元</option>
			  				<option>50-100元</option>
              				<option>100元以上</option>
            			</select>
					</td>
                    <th>商品折扣：</th>
            		<td>
					  	<select name="discount" class="midselect">
							<option>所有折扣</option>
              				<option>3折以下</option>
              				<option>3-5折</option>
							<option>5-7折</option>
							<option>7折及以上</option>
            			</select> 
					</td>
          		 </tr>
				 <tr>
				 <th></th>
				 	<td><input type="button" value="搜索" onClick="">
				 </tr>
        </table>	
				
			</div>
		</div>
 <div class="user_note">
 	<!--<div class="head"><strong>商品列表</strong></div>-->
   <div id="item_container">
   <div class="fliter"></div>
   <!--商品迭代div-->
   <div class="item"><div class="p-img"><div class="i-img"><a  href="item_info.html"><img src="image/ssbs.jpg"></a></div></div><div class="dl"><div class="p-name"><a target="_blank" href="">BVLGARI Chinese-English Edn (PB)(宝格丽)</a></div>

   <div class="p-info">作　　者：<a href="">Amanda Triossi（阿曼达·特廖西）</a> 著&nbsp;&nbsp;&nbsp;&nbsp;</div>
   <div class="p-info">出&nbsp;版&nbsp;社：<a href="">SKIRA EDITORE</a>&nbsp;&nbsp;&nbsp;&nbsp;</div>
   <div class="p-info"><span class="date-pub">出版时间：2011年09月</span><span class="fl">顾客评价：</span>(已有4人评价)</div>
   <div class="p-info"><div class="mark-price">定　　价：<del>￥533.90</del></div>商城价：<strong>￥220.00</strong><span>（7.8折）</span></div>
   <div class="btns"><div class="add_to_cart1"></div></div>
   </div>
   </div>
   <!--商品迭代div---end-->
   <!--商品迭代div-->
   <div class="item"><div class="p-img"><div class="i-img"><a target="_blank" href=""><img src="image/ssbs.jpg"></a></div></div><div class="dl"><div class="p-name"><a target="_blank" href="">BVLGARI Chinese-English Edn (PB)(宝格丽)</a></div>

   <div class="p-info">作　　者：<a href="">Amanda Triossi（阿曼达·特廖西）</a> 著&nbsp;&nbsp;&nbsp;&nbsp;</div>
   <div class="p-info">出&nbsp;版&nbsp;社：<a href="">SKIRA EDITORE</a>&nbsp;&nbsp;&nbsp;&nbsp;</div>
   <div class="p-info"><span class="date-pub">出版时间：2011年09月</span><span class="fl">顾客评价：</span>(已有4人评价)</div>
   <div class="p-info"><div class="mark-price">定　　价：<del>￥533.90</del></div>商城价：<strong>￥220.00</strong><span>（7.8折）</span></div>
   <div class="btns"><div class="add_to_cart1"></div></div>
   </div>
   </div>
   <!--商品迭代div---end-->
   </div>
 </div>
 
 <div id="footer">
	  <span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 | 电子邮件：admin@163.com </span>
	  <span id="footerright"> B2C商城  Power by IBM &nbsp;</span>
	</div>
</div>

</body>
</html>