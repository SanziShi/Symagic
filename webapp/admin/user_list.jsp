<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Symagic网上书城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<link href="css/gz.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="js/checkform.js"></script>
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

		<div id="left_user_admin">
			<div id="admin_user_left">


				<ul>
					<li><a href="user_list">查询会员</a></li>
					<li><a href="user_level">设置会员等级规定</a></li>
				</ul>
			</div>
		</div>

		<!--	<div id="banner"></div>-->

		<div id="main">
			<div id="search1">
				<div id="mysearchleft">
					<img src="image/ico_site.jpg" id="ico_site" /> 网站路径： <a
						href="user_level">会员管理</a>&gt;&gt;查询会员
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
							value="" /> <input type="submit" value="搜索" onclick="" />
					</form>
				</div>
			</div>
			<div id="mydouble">
				<div id="doublehead2">
					<strong>精确查询</strong>
				</div>
				<div id="doublecontent2">
					<form action="user_list?page=1" method="post">
						<table width="100%">
							<tr>
								<td width="3%" class="inputHeader">&nbsp;</td>
								<td width="16%" class="inputHeader">用户名：</td>
								<td width="39%" class="inputContent"><input type="text"
									name="userName" onfocus="nextfield='password'"
									value="<s:property value='userName'/>" size="15" />
								</td>
								<td>
								<input type="submit" value="查询" />
								<input type="hidden" value="0" name="userLevel"/>
								<input type="hidden" value="<s:property value="searchStartYear"/> " name="startTime.year"/>
								<input type="hidden" value="1" name="startTime.month"/>
								<input type="hidden" value="1" name="startTime.day"/>
								<input type="hidden" value="<s:property value="searchEndYear"/>" name="endTime.year"/>
								<input type="hidden" value="12" name="endTime.month"/>
								<input type="hidden" value="31" name="endTime.day"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div id="mydouble">
				<div id="doublehead2">
					<strong>条件查询</strong>
				</div>
				<div id="doublecontent2">
					<form action="user_list?page=1" method="post">
						<table width="100%">
							<tr>
								<td width="42%" class="inputContent">&nbsp;</td>
							</tr>
						</table>

						<table width="100%">
							<tr>
								<td width="3%" class="inputHeader">&nbsp;</td>
								<td class="inputHeader">会员级别：</td>
								<td class="inputContent"><select name="userLevel">
										<s:if test="%{userLevel==0}">
											<option value="0" selected="selected">所有会员</option>
										</s:if>
										<s:else>
											<option value="0">所有会员</option>
										</s:else>
										<!--会员等级迭代开始-->
										<s:iterator value="levelList" var="level">
											<s:if test="%{userLevel==#level.levelID}">
												<option value="<s:property value='#level.levelID'/>"
													selected="selected">
													<s:property value='#level.levelName' />
												</option>
											</s:if>
											<s:else>
												<option value="<s:property value='#level.levelID'/>">
													<s:property value='#level.levelName' />
												</option>
											</s:else>
										</s:iterator>
										<!--会员等级迭代结束-->
								</select></td>
								<td class="inputContent">&nbsp;</td>
							</tr>
							<tr>
								<td class="inputHeader">&nbsp;</td>
								<td width="16%" class="inputHeader">注册起始时间：</td>
								<td width="39%" class="inputContent"><select
									name="startTime.year" id="sYear">

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
									var defaultYear = <s:property value="startTime.year" default="2007" />;
									var defaultMonth = <s:property value="startTime.month" default="1"/> - 1;
									var defaultDay = <s:property value="startTime.day" default="1"/>;
									var dt = new Date(defaultYear,
											defaultMonth, defaultDay);
									new DateSelector(selYear, selMonth ,selDay, <s:property value="searchStartYear"/> , <s:property value="searchEndYear"/>,
											dt);
								</script>
								</td>
								<td class="inputContent">&nbsp;</td>
							</tr>
							<tr>
								<td class="inputHeader">&nbsp;</td>
								<td class="inputHeader">注册结束时间：</td>
								<td class="inputContent"><select name="endTime.year"
									id="eYear">

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
									var defaultYear = <s:property value="endTime.year" default="2012"/>;
									var defaultMonth = <s:property value="endTime.month" default="12"/> - 1;
									var defaultDay = <s:property value="endTime.day" default="31"/>;
									var dt = new Date(defaultYear,
											defaultMonth, defaultDay);
									new DateSelector(selYear, selMonth ,selDay, <s:property value="searchStartYear"/> , <s:property value="searchEndYear"/>,
											dt);
								</script>
								</td>
								<td width="40%" class="inputContent"><input type="submit"
									class="bt2" name="button222" value="查询"
									onClick="javascript:window.location.href='user_list.html'" />&nbsp;&nbsp;
									&nbsp;
									<input type="hidden" name="userName" value=""/></td>
							</tr>
						</table>
					</form>
				</div>
			</div>

			<div id="mydouble">
				<div id="sendnotehead">
					<strong>查询结果</strong>
				</div>
				<div id="sendnotecontent">
					<table id="favorite">
						<thead>
							<tr>
								<th>会员号</th>
								<th>会员级别</th>
								<th>积分</th>
								<th>注册时间</th>

							</tr>
						</thead>
						<tbody>
							<!--会员迭代开始-->
							<s:iterator value="userList" var="user">
								<tr>
									<td><s:property value="#user.userName" /></td>
									<td><s:property value="#user.levelName" /></td>
									<td><s:property value="#user.score" /></td>
									<td><s:property value="#user.registerDate" /></td>
								</tr>
							</s:iterator>
							<!--会员迭代结束-->
						</tbody>
						<tr>
							<td></td>
							<td></td>
							<td><s:if test="%{totalPage==1}"></s:if> <s:elseif
									test="%{page==1}">
									<a
										href="item_detail?itemID=<s:property value="itemID"/>&page=${ page + 1 }">下一页</a>
								</s:elseif> <s:elseif test="%{page==totalPage}">
									<a
										href="item_detail?itemID=<s:property value="itemID"/>&page=${ page - 1 }">上一页</a>
								</s:elseif> <s:else>
									<a
										href="item_detail?itemID=<s:property value="itemID"/>&page=${ page - 1 }">上一页</a>
									<a
										href="item_detail?itemID=<s:property value="itemID"/>&page=${ page + 1 }">下一页</a>
								</s:else>
							</td>
							<td>当前第<s:property value="page" />页，共<s:property
									value="totalPage" />页，每页<s:property value="lines" />条</td>
						</tr>
					</table>
				</div>
			</div>
		</div>


		<div id="footer">
			<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 |
				电子邮件：Symagics@gmail.com </span> <span id="footerright"> Symagic网上书城
				Power by Symagic &nbsp;</span>
		</div>
	</div>

</body>
</html>

