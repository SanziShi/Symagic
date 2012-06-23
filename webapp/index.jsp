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
<link href="css/home.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/yf_ADS.js"></script>
</head>
<body>
<div id="container">
<div id="cart" ><span class="cart_loading"></span></div>
	<div id="logalleft">
    <div id="top-overlay"></div>
    <!----吊顶栏------>
	<div class="top">
    <li>
    <span class="cart_icon"></span>
    </li>
    </div>
  <!------吊顶结束----->
		<div id="logalright">欢迎你<s:property value="#session.username"/><s:property value="#session.nickname"/><li><a href="#" onclick="load_regist();" >注册</a>&nbsp;</li>|<li> <a href="#" id="load_login" onclick="load_login();" >登录</a>&nbsp;</li>|
		<li> <a  href="user.html" target="_parent">个人信息</a>&nbsp;</li>|<li id="cart_li"> 
		<a id="cart_a"  href="cart_list.html" target="_parent">购物车 <strong id="cart_num">0</strong> 件</a>&nbsp;</li>|<li> <a href="#" target="_parent">退出</a></li>
		</div>
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
    <div class="clear"></div>
	<div id="left">
		<div id="category">
			<h4><span>文学</span></h4>
			<ul>
				<li><a href="item_list.html">散文/随笔/书信</a></li>
				<li><a href="#">诗词歌曲</a></li>
				<li><a href="#">中国文学</a></li>
				<li><a href="#">外国文学</a></li>
				<li><a href="#">名家作品</a></li>
				<li><a href="#">儿童文学</a></li>
                <li><a href="#">纪实文学</a></li>
				<li><a href="#">民间文学</a></li>
				<li><a href="#">影视文学</a></li>
                <li><a href="#">文学评论与研究</a></li>
				<li><a href="#">文学史</a></li>
			</ul>
			<h4><span>艺术</span></h4>
			<ul>
				<li><a href="#">摄影</a></li>
				<li><a href="#">设计</a></li>
				<li><a href="#">绘画</a></li>
				<li><a href="#">影视</a></li>
				<li><a href="#">动画</a></li>
				<li><a href="#">音乐</a></li>
                <li><a href="#">舞蹈</a></li>
				<li><a href="#">雕塑</a></li>
				<li><a href="#">鉴赏与收藏</a></li>
				<li><a href="#">书法与篆刻</a></li>
			</ul>
			<h4><span>金融与投资</span></h4>
			<ul>
				<li><a href="#">个人理财</a></li>
                <li><a href="#">投资</a></li>
				<li><a href="#">期货</a></li>
				<li><a href="#">股票基金</a></li>
				<li><a href="#">证券</a></li>
				<li><a href="#">企业并购</a></li>
                <li><a href="#">金融理论</a></li>                
			</ul>
			<h4><span>励志与成功</span></h4>
			<ul>
				<li><a href="#">人在职场</a></li>
				<li><a href="#">名人励志</a></li>
				<li><a href="#">心灵鸡汤</a></li>
				<li><a href="#">成功学</a></li>
				<li><a href="#">人际与社交</a></li>
			</ul>
      </div>
	</div>
	<div id="main">
		<div id="search1">
			<div id="searchleft">
				<img src="image/ico_site.jpg" id="ico_site"/>
				网站路径：<a href="index.html">首页</a>			</div>
			<div id="searchright">
			  <select name="category" id="searchrightcategory">
				<option value="5">所有类别</option>
                <option value="1">图书音像</option>
                <option value="2">时尚生活</option>
                <option value="3">饰品配件</option>
                <option value="4">数码产品</option>
              </select>
			  <input type="text" name="quick_search" id="quick_search" class="gray" value="书名快速搜索...."onFocus="onfocus_check(this,'书名快速搜索....')" onblur="onblur_check(this,'书名快速搜索....')" />
			  <input type="button" name="Submit" value="搜索" id="searchbutton" onClick="javascript:window.open('item_search_list.html','_parent','')">
				
			</div>
		</div>
		
		
		<div id="recommend">
			<ul>
				<li><a href="item_info.html"><img src="image/dxwl4.jpg"><br><span class="inputHeader">大学物理学.第四册：<br>波动与光学(第2版)<br>
	     <strong>￥8.50</strong> 85折</span></li>
		 		<li><a href="#"><img src="image/wj.jpg"><br><span class="inputHeader">美瑞贝尔皇冠★最新<br>韩版丝麻质感围巾<br>
	     <strong>￥33.00</strong> 75折</span></a></li>
		 		<li><a href="#"><img src="image/ssbs.jpg"><br><span class="inputHeader">时尚芭莎(2007年<br>12月总第162期)<br>
	     <strong>￥16.00 </strong> 80折</span></a></li>
		 		<li><a href="#"><img src="image/mm.jpg"><br><span class="inputHeader">晶冻啤酒酵母晚安<br>冻膜(60ML)<br>
	     <strong>￥54.99 </strong> 80折</span></a></li>
		 		 		<li><a href="#"><img src="image/mp3.jpg"><br><span class="inputHeader">创新小石头<br>1GB黑色<br>
	     <strong>￥99.00 </strong> 66折</span></a></li>
			</ul>
			<br>&nbsp;
	    </div>
		<div id="new">
			<ul>
				<li><a href="#"><br><span class="inputHeader">下一代移动系统<br>
	     <strong>￥8.50</strong></span></li>
		 		<li><a href="#"><img src="image/yxqygl.jpg"><br><span class="inputHeader">影响企业管理的<br>125个故事<br>
	     <strong>￥33.00</strong></span></a></li>
		 		<li><img src="image/xydydxt.jpg" /><a href="#"><img src="image/PPT.jpg"><br><span class="inputHeader">别告诉我你懂PPT<br>
	     <strong>￥16.00 </strong></span></a></li>
		 		<li><a href="#"><img src="image/bubaoyuan.jpg"><br><span class="inputHeader">不抱怨的世界<br>
	     <strong>￥54.99 </strong> </span></a></li>
		 		<li><a href="#"><img src="image/dsn.jpg"><br><span class="inputHeader">迪斯尼的精彩世界<br>
	     <strong>￥130.00 </strong> </span></a></li>
			</ul>
			<br>&nbsp;
		</div>
		
		<div id="life">
			<ul>
				<li><a href="#"><img src="image/sssh.gif"><br><span class="inputHeader">18k白金30分钻戒<br>
	     <strong>￥1990</strong></span></a></li>
		 		<li><a href="#"><img src="image/jsq.jpg"><br>
		 		<span class="inputHeader">加湿器<br>
	     <strong>￥133.00</strong></span></a></li>
		 		<li><a href="#"><img src="image/yf.jpg"><br><span class="inputHeader">波斯登羽绒服<br>
	     <strong>￥316.00 </strong></span></a></li>
		 		<li><a href="#"><img src="image/dzh.jpg"><br><span class="inputHeader">花形紫水晶吊坠<br>
	     <strong>￥129.99 </strong> </span></a></li>
		 		<li><a href="#"><img src="image/dhj.jpg"><br><span class="inputHeader">ZIPPO打火机<br>
	     <strong>￥115.00 </strong> </span></a></li>
			</ul>
			<br>&nbsp;
		</div>
	</div>
	<div id="footer">
		<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 | 电子邮件：admin@163.com </span>
		<span id="footerright"> B2C商城  Power by IBM &nbsp;</span>
	</div>
</div>

</body>
</html>
