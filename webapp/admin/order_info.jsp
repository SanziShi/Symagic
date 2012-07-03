<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Symagic网上书城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css" />
<link href="css/gz.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="js/gz.js"></script>
</head>

<body>
	<div id="container">
		<div id="logalleft">
			<div id="logalright">
				<a href="" target="_parent">管理员 已登录|</a> <a href="logout"
					target="_parent">退出</a>
			</div>
		</div>
		<div id="globallink">
			<ul>
				<li><a href="index">首页</a>
				</li>
				<li><a href="catalog_manager">目录管理</a>
				</li>
				<li><a href="item_manager">商品管理</a>
				</li>
				<li><a href="order_list">订单管理</a>
				</li>
				<li><a href="order_statistics">销售量统计</a>
				</li>
				<li><a href="user_level">会员管理</a>
				</li>
				<li><a href="" class="nouseful">&nbsp;</a>
				</li>
				<li><a href="" class="nouseful">&nbsp;</a>
				</li>
			</ul>
		</div>
		<!--	<div id="banner"></div>-->
		<div id="main">
			<div id="search2">
				<div id="searchleft">
					<img src="../image/ico_site.jpg" id="ico_site" /> 网站路径：订单详细信息
				</div>
			</div>
			<form action="" method="post" enctype="multipart/form-data"
				name="form1">
				<div id="double1">
					<div id="doublehead1">
						<strong>配送信息</strong>
					</div>
					<div id="doublecontent1">

						<table id="doublecontenttable3">
							<tr>
								<td>订单号</td>
								<td><s:property value="orderID" />
								</td>
								<td>用户名</td>
								<td><s:property value="userName" />
								</td>
								<td>下单时间</td>
								<td><s:property value="orderTime" />
								</td>
							</tr>
							<tr>
								<td>订单金额</td>
								<td><s:property value="price" />
								</td>
								<td>支付方式</td>
								<td><s:property value="payment" />
								</td>
								<td>送货方式</td>
								<td><s:property value="deliverWay" />
								</td>
							</tr>
							<tr>
								<td>收货人</td>
								<td><s:property value="receiver" /></td>
								<td>收货人地址</td>
								<td colspan="3"><s:property value="receiverAddress" /></td>
							</tr>

							<tr>
								<td>邮政编码</td>
								<td><s:property value="zipcode" /></td>

								<td>收货人手机</td>
								<td><s:property value="mobileNumber" /></td>
								<td>收货人电话</td>
								<td><s:property value="phoneNumber" /></td>
							</tr>
							<tr>

								<td>订单状态</td>
								<td><s:property value="orderStatus" />
								</td>

							</tr>
						</table>
					</div>
				</div>
				<div id="double2">
					<div id="doublehead2">
						<strong>商品信息</strong>
					</div>
					<div id="doublecontent2">
						<table id="favorite">
							<thead>
								<tr>
									<th>商品编号</th>
									<th>商品名称</th>
									<th>商城价</th>
									<th>在架状态</th>
									<th>数量</th>
									<th>商品小计</th>
								</tr>
							</thead>
							<tbody>
								<!--开始对客户购买的商品条项进行迭代-->
								<s:iterator value="items" var="itemArray">
									<tr>
										<td><span><s:property value="#itemArray.itemID" />
										</span>
										</td>
										<td><a
											href="item_detail?itemID=<s:property value = '#itemArray.itemID'/>">
										</a>
										<s:property value="#itemArray.name" /></td>
										<td>￥<s:property value="#itemArray.price" />
										</td>
										<td><s:if test="%{#itemArray.offline}">
					下架
					</s:if> <s:else>
					在架
					</s:else></td>
										<td><s:property value="itemNumber" /></td>
										<td>￥<s:property value="#itemArray.itemTotalPrice" />
										</td>
									</tr>
								</s:iterator>
								<!--商品条项迭代结束-->
							</tbody>
						</table>
					</div>
				</div>
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
