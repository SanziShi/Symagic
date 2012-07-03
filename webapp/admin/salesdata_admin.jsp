<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BC2商城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css" />
<link href="css/gz.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="js/gz.js"></script>
<script language="javascript" src="js/jquery.js"></script>

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
				<li><a href="index">首页</a></li>
				<li><a href="catalog_manager">目录管理</a></li>
				<li><a href="item_manager">商品管理</a></li>
				<li><a href="order_list">订单管理</a></li>
				<li><a href="order_statistics">销售量统计</a></li>
				<li><a href="user_level">会员管理</a></li>
				<li><a href="" class="nouseful">&nbsp;</a></li>
                <li><a href="" class="nouseful">&nbsp;</a></li>
			</ul>
		</div>
		<!--	<div id="banner"></div>-->
		<div id="main">
			<div id="search2">
				<div id="searchleft">
					<img src="../image/ico_site.jpg" id="ico_site" /> 网站路径：<a href="order_statistics">销售量统计</a>
				</div>
			</div>
			<div class="user_note">
				<div id="sendnotehead">
					<strong>销售量查询</strong>
				</div>
				<div class="fliter">
					<span id="search_banner" class="collapse"
						onclick="show_item_search(this);"></span>
				</div>
				<div id="item_search1" class="user_note_content hide">
					<div id="sendnotecontent">
						<form action="order_statistics" method="post"
							enctype="multipart/form-data" name="form1">
							<table id="tradequery">


								<tr>
									<th>起始时间：&nbsp;</th>
									<td><select name="startTime.year" id="sYear">

									</select> &nbsp;年&nbsp; <select name="startTime.month" id="sMonth">

									</select> &nbsp;月&nbsp; <select name="startTime.day" id="sDay">

									</select> &nbsp;日</td>
									<script type="text/javascript">
										var selYear = window.document
												.getElementById("sYear");
										var selMonth = window.document
												.getElementById("sMonth");
										var selDay = window.document
												.getElementById("sDay");

										// 新建一个DateSelector类的实例，将三个select对象传进去
										//new DateSelector(selYear, selMonth ,selDay, 2004, 2, 29);
										// 也可以试试下边的代码
										var dt = new Date(2012, 0, 1);
										new DateSelector(selYear, selMonth, selDay, <s:property value="searchStartYear"/> , <s:property value="searchEndYear"/>,
												dt);
									</script>
								</tr>
								<tr>
									<th>结束时间：&nbsp;</th>
									<td><select name="endTime.year" id="eYear">

									</select> &nbsp;年&nbsp; <select name="endTime.month" id="eMonth">

									</select> &nbsp;月&nbsp; <select name="endTime.day" id="eDay">

									</select> &nbsp;日</td>
									<script type="text/javascript">
										var selYear = window.document
												.getElementById("eYear");
										var selMonth = window.document
												.getElementById("eMonth");
										var selDay = window.document
												.getElementById("eDay");

										// 新建一个DateSelector类的实例，将三个select对象传进去
										//new DateSelector(selYear, selMonth ,selDay, 2004, 2, 29);
										// 也可以试试下边的代码
										var dt = new Date(2012, 0, 1);
										new DateSelector(selYear, selMonth, selDay, <s:property value="searchStartYear"/> , <s:property value="searchEndYear"/>,
												dt);
									</script>
								</tr>
								<tr>
									<th>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</th>
									<td><select name="catalogID">
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
									</select></td>
								</tr>
								<tr>
									<th>销售下限：</th>
									<td><input type="text" name="limit" /></td>
								</tr>
								<tr>
									<th></th>
									<td><input type="submit" name="button22" value="查询" /></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div id="sendnote">
					<div id="sendnotehead">
						<strong>查询结果</strong>
					</div>
					<div id="sendnotecontent">
						<table>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td>该类别总销售额：</td>
								<td><span class="red">￥<s:property
											value="totalSalesRevenue" />
								</span>
								</td>
							</tr>
						</table>
						<table id="favorite">
							<thead>
								<tr>
									<th>商品代码</th>
									<th>商品名称</th>
									<th>销售数量</th>
									<th>销售单价</th>
									<th>销售总价</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="items" var="item">
									<tr>
										<td><s:property value="#item.itemID" />
										</td>
										<td><a
											href="item_detail?itemID=<s:property value = '#item.itemID'/>"><s:property
													value="#item.itemName" />
										</a>
										</td>
										<td><s:property value="#item.sales" />
										</td>
										<td>￥<s:property value="#item.price" />
										</td>
										<td>￥<s:property value="#item.totalPrice" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>

					</div>
				</div>

				<div id="footer">
					<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 |
						电子邮件：Symagics@gmail.com </span> <span id="footerright">
						Symagic网上书城 Power by Symagic &nbsp;</span>
				</div>
			</div>
</body>
</html>
