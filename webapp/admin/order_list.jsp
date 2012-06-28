<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>BC2商城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css">
<link href="css/gz.css" rel="stylesheet" type="text/css">
<script language="javascript" src="js/checkform.js"></script>
<script language="javascript" src="js/gz.js"></script>
<script src="js/jquery.js" type="text/javascript" language="javascript"></script>



</head>

<body>
<div id="container">
	<div id="logalleft">
		<div id="logalright">
		<a href="" target="_parent">管理员 已登录|</a>
	 	<a href="index.html" target="_parent">退出</a>
		</div>
	</div>
	<div id="globallink">
		<ul>
			<li><a href="index.html">首页</a></li>
                        <li><a href="category_list.html">目录管理</a></li>
			<li><a href="item_list.html">商品管理</a></li>
			<li><a href="order_admin.html">订单管理</a></li>
			<li><a href="salesdata_admin.html">销售量统计</a></li>
			<li><a href="user_admin.html">会员管理</a></li>
			<li><a href="comment_list.html">评论管理</a></li>
			<li><a href="" class="nouseful">&nbsp;</a></li>
		</ul>
	</div>
<!--	<div id="banner"></div>-->
	<div id="main">
		<div id="search2">
			<div id="searchleft">
				<img src="../image/ico_site.jpg" id="ico_site"/>
				网站路径：<a href="order_admin.html">订单管理</a></div>
		
		</div>
<!--        <div id="sendnote">-->
            <div  class="user_note">
			<div id="sendnotehead"><strong>订单查询</strong></div>
            <div class="fliter"><span id="search_banner" class="collapse" onclick="show_item_search(this);"></span></div>
            <div id="item_search1" class="user_note_content hide">
			<div id="sendnotecontent">
            <form action="" method="post" name = "form0">
            <table id = tradequery>
            <tr>
					<th>订&nbsp;&nbsp;单&nbsp;&nbsp;号：	</th>
					<td><input type="text" class="inputtext" name="orderID" onFocus="nextfield='webUserEntity.order'" maxlength="25"></td>
					</tr>
                    </table>
            </form>
			<form action="order_list" method="post" enctype="multipart/form-data" name="form1">
				<table id="tradequery">
					
					<tr>
					<th>用&nbsp;&nbsp;户&nbsp;&nbsp;名：</th>
					<td><input type="text" class="inputtext" name="userName" onFocus="nextfield='webUserEntity.order'" maxlength="25"></td>
					</tr>
                    <tr>
					<th>订单状态：&nbsp;</th>
					<td>
                    <select name="orderState">
                    <option>未指定</option>
                    <option>已下单</option>
                    <option>已审核</option>
                    <option>交易成功</option>
                    <option>交易失败</option>
                    </td>
					</tr>
				   <tr>
					<th>起始时间：&nbsp;</th>
            		<td>
						<select name="startTime.year" id="sYear">
              				<!--<option>2012</option>
              				<option>2011</option>
                            <option>2010</option>
                            <option>2009</option>
                            <option>2008</option>-->
              			</select>
						&nbsp;年&nbsp;
						<select name="startTime.month" id="sMonth">
              				<!--<option>01</option>
              				<option>02</option>
              				<option>03</option>
              				<option>04</option>
			  				<option>05</option>
              				<option>06</option>
                            <option>07</option>
              				<option>09</option>
              				<option>10</option>
              				<option>11</option>
			  				<option>12</option>  -->
            			</select>
						&nbsp;月&nbsp;
						<select name="startTime.day" id="sDay">
              				<!--<option>01</option>
              				<option>02</option>
              				<option>03</option>
              				<option>04</option>
			  				<option>05</option>
              				<option>06</option>
                            <option>07</option>
              				<option>09</option>
              				<option>10</option>
              				<option>11</option>
			  				<option>12</option> -->              
            			</select>
						&nbsp;日
					</td>
<script type="text/javascript">
var selYear = window.document.getElementById("sYear");
var selMonth = window.document.getElementById("sMonth");
var selDay = window.document.getElementById("sDay");

// 新建一个DateSelector类的实例，将三个select对象传进去
//new DateSelector(selYear, selMonth ,selDay, 2004, 2, 29);
// 也可以试试下边的代码
var dt = new Date(2012, 0, 1);
new DateSelector(sYear, sMonth ,sDay, dt);
</script>
          		 </tr>
				 <tr>
				 <th>结束时间：&nbsp;</th>
            		<td>
						<select name="endTime.year" id="eYear">
              				
              			</select>
						&nbsp;年&nbsp;
						<select name="endTime.month" id="eMonth">
              				
            			</select>
						&nbsp;月&nbsp;
						<select name="endTime.day" id="eDay">
              				            
            			</select>
						&nbsp;日
					</td>
<script type="text/javascript">
var selYear = window.document.getElementById("eYear");
var selMonth = window.document.getElementById("eMonth");
var selDay = window.document.getElementById("eDay");

// 新建一个DateSelector类的实例，将三个select对象传进去
//new DateSelector(selYear, selMonth ,selDay, 2004, 2, 29);
// 也可以试试下边的代码
var dt = new Date(2012, 0, 1);
new DateSelector(eYear, eMonth ,eDay, dt);
</script>                
          		 </tr>
				 <tr>
				 <th></th>
				 	<td><input type="submit" name="button22" value="查询"/>
					<input type="reset" class="bt2" name="button2" value="重填">
                    </td>
				 </tr>
        </table>
		</form>  		
			</div>
            </div>
		</div>
		<div id="sendnote">
			<div id="sendnotehead"><strong>查询结果</strong></div>
			<div id="sendnotecontent">
			<form action="" method="post" enctype="multipart/form-data" name="form1">
				<table id="favorite">
				<thead>
					<tr>
						<th></th>
						<th>订单号</th>
						<th>客户号</th>
						<th>下单时间</th>
						<th>订单状态</th>
						
						<th>详情</th>
					</tr>
				</thead>
				<tbody>
                <!--订单条项迭代开始-->
                 <s:iterator value = "orderList" var = "orders">
				 <tr id="<s:property value = '#orders.orderID'/>">
				  	<td><input type="checkbox" name="ordersn" value="<s:property value = '#orders.orderID'/>"/></td>
					<td><s:property value = "#orders.orderID"/></td>
            		<td><s:property value = "#orders.userName"/></td>
					<td><s:property value = "#orders.orderTime"/></td>
					<td><s:property value = "#orders.orderStatus"/></td>
					
					<td><s:if test="#order.orderStatus=='已下单'">
<a href="order_detail?orderID=<s:property value = '#orders.orderID'/>">详情</a>&nbsp;
<a href="order/pass?orderID=<s:property value = '#orders.orderID'/>">审核</a>&nbsp; 
<a  onclick="ajax_delete_order("<s:property value = "#orders.orderID"/>");"  href="order/delete_order?orderID=<s:property value='#orders.orderID'/>">删除</a>&nbsp;
 </s:if>
 <s:elseif test="#order.orderStatus=='已审核'">
 <a href="order_detail?orderID=<s:property value = '#orders.orderID'/>">详情</a>&nbsp;
 <a href="order_detail?orderID=<s:property value = '#orders.orderID'/>">修改</a>&nbsp;
<a onclick="ajax_delete_order("<s:property value = "#orders.orderID"/>")"  href="order/delete_order?orderID=<s:property value='#orders.orderID'/>">删除</a>&nbsp;
 </s:elseif>
 <s:elseif test="#order.orderStatus=='交易成功'">
 <a href="order_detail?orderID=<s:property value = '#orders.orderID'/>">详情</a>&nbsp;
  </s:elseif>
  <s:else>
  <a href="order_detail?orderID=<s:property value = '#orders.orderID'/>">详情</a>&nbsp;
<a  onclick="ajax_delete_order("<s:property value = "#orders.orderID"/>")"  href="order/delete_order?orderID=<s:property value='#orders.orderID'/>">删除</a>&nbsp;
  </s:else>
  </td>
          		  </tr>
                  </s:iterator>
                  <!--订单迭代结束-->
				
			<!--	  <tr>
				  	<td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="3"><span style=" float:right"><input type="Button" class="bt2" value="交易成功"/>&nbsp;<input type="Button" class="bt2" value="交易失败"  />&nbsp;<input type="Button" class="bt2" name="button22" value="审核" onclick="checkorderselect()" />&nbsp;<input type="Button" class="bt2"  value="删除"  />&nbsp;                    </span></td>
                  </tr>-->
				
				 </tbody>
            </table>
			</form> 
			</div>
		</div>
		
	<div id="footer">
		<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 | 电子邮件：Symagics@gmail.com </span>
		<span id="footerright"> Symagic网上书城  Power by Symagic	 &nbsp;</span>
	</div>
</div>

</body>
</html>
