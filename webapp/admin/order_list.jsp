<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Symagic网上书城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css" />
<link href="css/gz.css" rel="stylesheet" type="text/css" />
<line href="css/order_operation.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="js/checkform.js"></script>
<script language="javascript" src="js/gz.js"></script>
<script src="js/jquery.js" type="text/javascript" language="javascript"></script>
<script src="js/cart.js" type="text/javascript" language="javascript"></script>



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
					<img src="../image/ico_site.jpg" id="ico_site" /> 网站路径：<a
						href="order_list">订单管理</a>
				</div>

			</div>
			<!--        <div id="sendnote">-->
			<div class="user_note">
				<div id="sendnotehead">
					<strong>订单精确查询</strong>
				</div>
				<div class="fliter">
					<span id="search_banner" class="collapse"
						onclick="show_item_search(this);"></span>
				</div>
				<div id="item_search1" class="user_note_content hide">
					<div id="sendnotecontent">
						<form action="order_detail" method="post" name="form0">
							<table id=tradequery>
								<tr>
									<th>订&nbsp;&nbsp;单&nbsp;&nbsp;号：</th>
									<td><input type="text" class="inputtext" name="orderID"
										maxlength="25" onkeyup="amount_modify(this)"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="提交" />
									</td>
									
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>

			<div class="user_note">
				<div id="sendnotehead">
					<strong>订单条件查询</strong>
				</div>
				<div class="fliter">
					<span id="search_banner" class="collapse"
						onclick="show_item_search2(this);"></span>
				</div>
				<div id="item_search2" class="user_note_content hide">
					<div id="sendnotecontent">
						<form action="order_list" method="post"
							enctype="multipart/form-data" name="form1">
							<table id="tradequery">

								<tr>
									<th>用&nbsp;&nbsp;户&nbsp;&nbsp;名：</th>
									<td><input type="text" class="inputtext" name="userName"
										maxlength="25" value="<s:property value='userName'/>" />
									</td>
								</tr>
								<tr>
									<th>订单状态：&nbsp;</th>
									<td><select name="orderState">
											<s:if test="%{orderState==0}">
												<option value="0" selected="selected">未指定</option>
											</s:if>
											<s:else>
												<option value="0">未指定</option>
											</s:else>
											<s:if test="%{orderState==1}">
												<option value="1" selected="selected">已下单</option>
											</s:if>
											<s:else>
												<option value="1">已下单</option>
											</s:else>
											<s:if test="%{orderState==2}">
												<option value="2" selected="selected">已审核</option>
											</s:if>
											<s:else>
												<option value="2">已审核</option>
											</s:else>
											<s:if test="%{orderState==3}">
												<option value="3" selected="selected">交易成功</option>
											</s:if>
											<s:else>
												<option value="3">交易成功</option>
											</s:else>
											<s:if test="%{orderState==4}">
												<option value="4" selected="selected">交易失败</option>
											</s:if>
											<s:else>
												<option value="4">交易失败</option>
											</s:else>
									</select></td>
								</tr>
								<tr>
									<th>起始时间：&nbsp;</th>
									<td><select name="startTime.year" id="sYear">

									</select> &nbsp;年&nbsp; <select name="startTime.month" id="sMonth">

									</select> &nbsp;月&nbsp; <select name="startTime.day" id="sDay">

									</select> &nbsp;日</td>
									<script type="text/javascript">
var selYear = window.document.getElementById("sYear");
var selMonth = window.document.getElementById("sMonth");
var selDay = window.document.getElementById("sDay");

// 新建一个DateSelector类的实例，将三个select对象传进去
//new DateSelector(selYear, selMonth ,selDay, 2004, 2, 29);
// 也可以试试下边的代码
var defaultYear = <s:property value="startTime.year" default="2007"/>;
									var defaultMonth = <s:property value="startTime.month" default="1"/> - 1;
									var defaultDay = <s:property value="startTime.day" default="1"/>;
									var dt = new Date(defaultYear,
											defaultMonth, defaultDay);
									new DateSelector(selYear, selMonth, selDay, <s:property value="searchStartYear"/> , <s:property value="searchEndYear"/>,dt);
</script>
								</tr>
								<tr>
									<th>结束时间：&nbsp;</th>
									<td><select name="endTime.year" id="eYear">

									</select> &nbsp;年&nbsp; <select name="endTime.month" id="eMonth">

									</select> &nbsp;月&nbsp; <select name="endTime.day" id="eDay">

									</select> &nbsp;日</td>
									<script type="text/javascript">
var selYear = window.document.getElementById("eYear");
var selMonth = window.document.getElementById("eMonth");
var selDay = window.document.getElementById("eDay");

// 新建一个DateSelector类的实例，将三个select对象传进去
//new DateSelector(selYear, selMonth ,selDay, 2004, 2, 29);
// 也可以试试下边的代码
var defaultYear = <s:property value="endTime.year" default="2012"/>;
									var defaultMonth = <s:property value="endTime.month" default="12"/> - 1;
									var defaultDay = <s:property value="endTime.day" default="31"/>;
									var dt = new Date(defaultYear,
											defaultMonth, defaultDay);
									new DateSelector(selYear, selMonth, selDay, <s:property value="searchStartYear"/> , <s:property value="searchEndYear"/>,dt);
</script>
								</tr>
								<tr>
									<th></th>
									<td><input type="submit" name="button22" value="查询" /> <input
										type="reset" class="bt2" name="button2" value="重填" /> <input
										type="hidden" name="page" value="1" /></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
			<div id="sendnote">
				<div id="sendnotehead">
					<strong>查询结果</strong>
				</div>
				<div id="sendnotecontent">
					<table id="favorite">
						<thead>
							<tr>
								<th></th>
								<th>订单号</th>
								<th>用户名</th>
								<th>下单时间</th>
								<th>订单状态</th>

								<th>详情</th>
							</tr>
						</thead>
						<tbody>
							<!--订单条项迭代开始-->
							<s:iterator value="orderList" var="orders">
								<tr id="<s:property value = '#orders.orderID'/>">
									<td><input type="checkbox" name="ordersn"
										value="<s:property value = '#orders.orderID'/>" />
									</td>
									<td><s:property value="#orders.orderID" />
									</td>
									<td><s:property value="#orders.userName" />
									</td>
									<td><s:property value="#orders.orderTime" />
									</td>
									<td><s:property value="#orders.orderStatus" />
									</td>

									<td><s:if test="#orders.orderStatus=='已下单' ">
											<input type="button" class="operation_btn" value="详情"
												onclick="location='order_detail?orderID=<s:property value = '#orders.orderID'/>'" />&nbsp;
												<input type="button" class="operation_btn" value="修改"
												onclick="location='order_edit?orderID=<s:property value = '#orders.orderID'/>'" />&nbsp;
												<input type="button"
												onclick="ajax_pass_order(<s:property value = '#orders.orderID'/>)"
												value="审核" />												&nbsp;
												<input type="button"
												onclick="ajax_delete_order(
												<s:property value = '#orders.orderID'/>)"
												value="删除" />&nbsp;
 </s:if> <s:elseif test="#orders.orderStatus=='已审核'">
											<input type="button" class="operation_btn" value="详情"
												onclick="location='order_detail?orderID=<s:property value = '#orders.orderID'/>'" />&nbsp;
											<input type="button" class="operation_btn" value="修改"
												onclick="location='order_edit?orderID=<s:property value = '#orders.orderID'/>'" />&nbsp;
	<input type="button"
												onclick="ajax_delete_order(
												<s:property value = '#orders.orderID'/>)"
												value="删除" />&nbsp;
												
<input type="button"
												onclick="ajax_success_order(<s:property value = '#orders.orderID'/>)"
												value="交易成功" />
											<input type="button"
												onclick="ajax_fail_order(<s:property value = '#orders.orderID'/>)"
												value="交易失败" />
										</s:elseif> <s:elseif test="#orders.orderStatus=='交易成功'">
											<input type="button" class="operation_btn" value="详情"
												onclick="location='order_detail?orderID=<s:property value = '#orders.orderID'/>'" />&nbsp;
  </s:elseif> <s:else>

											<input type="button" class="operation_btn" value="详情"
												onclick="location='order_detail?orderID=<s:property value = '#orders.orderID'/>'" />&nbsp;
<input type="button"
												onclick="ajax_delete_order(
												<s:property value = '#orders.orderID'/>)"
												value="删除" />&nbsp;
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
							<tr>
								<td></td>
								<td></td>
								<td><s:if test="%{totalPage==1||totalPage==0}"></s:if> <s:elseif
										test="%{page==1}">
										<a href="order_list?page=${ page + 1 }">下一页</a>
									</s:elseif> <s:elseif test="%{page==totalPage}">
										<a href="order_list?page=${ page - 1 }">上一页</a>
									</s:elseif> <s:else>
										<a href="order_list?page=${ page - 1 }">上一页</a>
										<a href="order_list?page=${ page + 1 }">下一页</a>
									</s:else></td>
								<td>当前第<s:property value="page" />页，共<s:property
										value="totalPage" />页，每页<s:property value="lines" />条</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div id="footer">
				<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 |
					电子邮件：Symagics@gmail.com </span> <span id="footerright"> Symagic网上书城
					Power by Symagic &nbsp;</span>
			</div>
		</div>
	</div>

</body>
</html>
