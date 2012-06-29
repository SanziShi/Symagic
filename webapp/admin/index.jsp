
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Symagic网上书城</title>
<link href="css/gz.css" rel="stylesheet" type="text/css" />
<link href="css/frame.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/yf_ADS.js"></script>
<script type="text/javascript" src="js/gz.js"></script>
</head>
<body>
	<div id="container">
		<div id="logalleft">
			<div id="logalright">
				<a href="" target="_parent">管理员 已登录|</a> <a href="index.html"
					target="_parent">退出</a>
			</div>
		</div>
		<a name="0"></a>
		<div id="globallink">
			<ul>
				<li><a href="index">首页</a></li>
				<li><a href="category_list.html">目录管理</a></li>
				<li><a href="item_manager">商品管理</a></li>
				<li><a href="order_admin.html">订单管理</a></li>
				<li><a href="order_statistics">销售量统计</a></li>
				<li><a href="user_admin.html">会员管理</a></li>
				<li><a href="comment_list.html">评论管理</a></li>
				<li><a href="" class="nouseful">&nbsp;</a></li>
			</ul>
		</div>
		<!-- <div class="clear"></div>
   
        <div id="item_left">
         <div id="categoryFixed">
        <dl>
        <dt>文学作品</dt>
        <div ><dd>诗词歌曲</dd></div>s 	
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
        </div>
-->
		<!--<div id="banner"></div>-->
		<div id="left">
			<div id="categoryFixed">
				<h4>
					<span>后台首页</span>
				</h4>
				<ul>
					<li><a href="index.jsp">后台首页</a></li>

				</ul>
				<h4>
					<span>销售管理</span>
				</h4>
				<ul>
					<li><a href="salesdata_admin.html">查询销售情况</a></li>
					<li><a href="salesdata_list.html">销售量统计</a></li>
				</ul>
				<h4>
					<span>目录管理</span>
				</h4>
				<ul>
					<li><a href="category_list.html">编辑商品目录</a></li>
					<li><a href="category_list.html">添加商品目录</a></li>
				</ul>
				<h4>
					<span>商品管理</span>
				</h4>
				<ul>
					<li><a href="item_list.html">添加商品</a></li>
					<li><a href="item_list.html">修改商品信息</a></li>
					<li><a href="item_search.html">商品搜索</a></li>
				</ul>
				<h4>
					<span>订单管理</span>
				</h4>
				<ul>
					<li><a href="order_list">订单审核</a></li>
					<li><a href="order_admin.html">查询订单</a></li>
				</ul>
				<h4>
					<span>会员管理</span>
				</h4>
				<ul>
					<li><a href="user_admin.html">查询会员</a></li>
					<li><a href="user_admin.html">设置会员等级规定</a></li>
				</ul>
			</div>
		</div>
		<div id="main">
			<div id="search1">
				<div id="searchleft">
					<img src="image/ico_site.jpg" id="ico_site" /> 网站路径：<a
						href="index.jsp">后台</a>
				</div>
				<div id="searchright">
					<form action="quick_search">
						<select name="catalogID">
							<option value="0">所有类别</option>
							<s:iterator value="catalog" var='outer'>
								<option value="<s:property value='#outer.ID'/>">
									<s:property value='#outer.name' />
								</option>
								<s:iterator value="#outer.childCatalog" var="inner">
									<option value="<s:property value='#inner.ID'/>">
										&nbsp;&nbsp;&nbsp;
										<s:property value='#inner.name' />
									</option>
								</s:iterator>
							</s:iterator>
						</select> <input type="text" name="keyword" id="keyword" class="gray"
							value="" /> <input type="submit"
							value="搜索" onclick=""/>
					</form>
				</div>
			</div>

			<table width="100% " id="bgWhite">
				<tr>
					<td class="mywidth">
						<table width="95%" style="display:inline ">
							<tr>
								<div id="saleAmount"></div>
							</tr>
							<tr>
								<td id="myfont" width="60%">总销售量:&nbsp;&nbsp;</td>
								<td id="myfont" class="mywidth" align="right"><span
									class="redStrong"><s:property value="totalSalesAmount" />
								</span>本</td>
							</tr>
							<tr>
								<td id="myfont" class="mywidth">销售总额:&nbsp;&nbsp;</td>
								<td id="myfont" class="mywidth" align="right"><span
									class="redStrong"><s:property value="totalSalesRevenue" />
								</span>元</td>
							</tr>
							<tr>
								<td id="myfont" width="60%">注册用户:&nbsp;&nbsp;</td>
								<td id="myfont" class="mywidth" align="right"><s:property
										value="userNum" />个</td>
							</tr>
							<tr>
								<td id="myfont" width="60%">产品:&nbsp;&nbsp;</td>
								<td id="myfont" class="mywidth"><s:property
										value="productNum" />个</td>
							</tr>
						</table>
					</td>

					<td class="mywidth">
						<table width="100% " style="display:inline ">
							<tr>
								<div id="orderAmount"></div>
							</tr>
							<tr>
								<td id="myfont" width="60%">总订单:&nbsp;&nbsp;</td>
								<td id="myfont"><span class="redStrong"><s:property
											value="totalOrderAmout" /> </span>个</td>
							</tr>
							<tr>
								<td id="myfont" width="60%">今日订单:&nbsp;&nbsp;</td>
								<td id="myfont"><span class="redStrong"><s:property
											value="" /> </span>个</td>
							</tr>
							<tr>
								<td id="myfont" width="60%">待审核订单:&nbsp;&nbsp;</td>
								<td id="myfont"><span class="redStrong"><s:property
											value="unauditedOrderAmount" /> </span>个</td>
							</tr>
							<tr>
								<td id="myfont" width="60%">完成订单:&nbsp;&nbsp;</td>
								<td id="myfont"><span class="redStrong"><s:property
											value="finishOrderAmount" /> </span>个</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<!--      <table width="100%">
        <tr >
        <td class="mywidth">
        <table  style= "display:inline " >
        <tr>
        <div id="saleAmount"></div>
        </tr>
        <tr>
        <td id="myfont" class="mywidth">销售量：</td>
        <td id="myfont"><span class="red">580</span>本 </td>
        </tr>
        </table>
        </td>
        <td>
        <table  style= "display:inline ">
        <tr>
        <div id="orderAmount"></div>
        </tr>
        <tr>
        </tr>
        </table>
        </td>
        </tr>
        </table>-->
			<div id="bgWhite">
				<form action="" method="post" enctype="multipart/form-data"
					name="form1">
					<table id="latestOrder" align="left">

						<thead>
							<tr>
								<div id="new"></div>
							</tr>

							<tr>

								<th></th>

								<th class="autoWidth">订单号</th>

								<th class="autoWidth">用户名</th>

								<th>下单时间</th>

								<th>订单状态</th>



								<th>详情</th>

							</tr>

						</thead>

						<tbody>


							<s:iterator value="LatestOrders" var="orders">

								<tr id="<s:property value = '#orders.orderID'/>">

									<td><input type="checkbox" name="orders"
										value="<s:property value = '#orders.orderID'/>" />
									</td>

									<td class="antoWidth"><s:property value="#orders.orderID" />
									</td>

									<td class="qWidth"><s:property value="#orders.userName" />
									</td>

									<td class="qWidth"><s:property value="#orders.orderTime" />
									</td>

									<td class="autoWidth"><s:property
											value="#orders.orderStatus" /></td>
									<td><s:if test="#orders.orderStatus=='已下单' ">
											<a
												href="order_detail?orderID=<s:property value = '#orders.orderID'/>">详情</a>&nbsp;
											<a href="">审核</a>&nbsp;
 <a
												onclick="ajax_delete_order(
												<s:property value = '#orders.orderID'/>)"
												href="order/delete_order?orderID=<s:property value='#orders.orderID'/>">删除</a>&nbsp;
 </s:if> <s:elseif test="#orders.orderStatus=='已审核'">
											<a
												href="order_detail?orderID=<s:property value = '#orders.orderID'/>">详情</a>&nbsp;
											<a
												href="order_detail?orderID=<s:property value = '#orders.orderID'/>">修改</a>&nbsp;
<a
												onclick="ajax_delete_order(
												<s:property value = "#orders.orderID"/>)"
												href="order/delete_order?orderID=<s:property value='#orders.orderID'/>">删除</a>&nbsp;
 </s:elseif> <s:elseif test="#orders.orderStatus=='交易成功'">
											<a
												href="order_detail?orderID=<s:property value = '#orders.orderID'/>">详情</a>&nbsp;
  </s:elseif> <s:else>

											<a
												href="order_detail?orderID=<s:property value = '#orders.orderID'/>">详情</a>&nbsp;
<a
												onclick="ajax_delete_order(
												<s:property value = '#orders.orderID'/>)"
												href="order/delete_order?orderID=<s:property value='#orders.orderID'/>">删除</a>&nbsp;
  </s:else>
									</td>


								</tr>
							</s:iterator>
						</tbody>

					</table>

				</form>

			</div>


			<div id="footer">
				<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 |
					电子邮件：Symagics@gmail.com </span> <span id="footerright"> Symagic网上书城
					Power by Symagic &nbsp;</span>
			</div>
		</div>
</body>
</html>
