<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Symagic网上书城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css" />
<link href="css/gz.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
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
				<li><a href="javascript:document.getElementById('clear_search').submit()">商品管理</a></li>
                <!--清空详细搜索的隐藏表单-->
            	<form action="item_manager" method="post" id="clear_search" style="display:none">
           	 	<input name="page" value="1"/>
            	<input name="name" />
            	<input name="author"/>
            	<input name="publisher"/>
            	<input name="catalogID" value="0"/>
            	<input name="publishTime" value="0"/>
            	<input name="searchPage" value="0"/>
            	<input name="edition" value="0"/>
            	<input name="binding" value="0"/>
            	<input name="booksize" value="0"/>
            	<input name="price" value="0"/>
            	<input name="discount" value="0"/>
            	<input />
            	</form>
            	<!--清空详细搜索的隐藏表单-->
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

		<!--     <div style="text-align:left; font-size:16px; background-color: #FFF""><span style="padding-right:20px;padding-left:2px;"><a href="item_list.html">[会员级别设置]</a></span><span style="padding-right:20px;padding-left:2px;"><a href="item_list.html">[会员查询]</a></span></div>-->
		<div id="main">
			<div id="search1">
				<div id="mysearchleft">
					<img src="image/ico_site.jpg" id="ico_site" /> 网站路径： <a
						href="user_level">会员管理</a>&gt;&gt;设置会员级别
				</div>
				<div id="searchright">
					<form action="quick_search?page=1" method="post">
						<select name="qcatalogID">
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
				<div id="mydoublehead1">
					<strong>会员级别说明</strong>
				</div>
				<div id="doublecontent1">
					<table id="xialabiao">
						<tr>
							<td class="inputHeader">&nbsp;</td>
							<td align="center" class="inputHeader">会员级别&nbsp;&nbsp;</td>
							<td align="center" width="50%" class="inputContent ">积分下限</td>
							<td width="20%" align="center" class="inputContent ">积分比例</td>
							<td align="center" class="inputContent">&nbsp;</td>
						</tr>
						<!--会员等级说明迭代开始-->
						<s:iterator value="levelList" var="levelInfo">
							<tr>
								<td class="inputHeader">&nbsp;</td>
								<td align="center" class="inputHeader"><s:property
										value="#levelInfo.levelName" />&nbsp;&nbsp;</td>
								<td align="center" class="inputContent"><s:property
										value="#levelInfo.low" /></td>
								<td align="center" class="inputContent"><s:property
										value="#levelInfo.scoreRate" /></td>
								<td align="center" class="inputContent">&nbsp;</td>
							</tr>
						</s:iterator>
						<!--会员等级说明迭代结束-->
					</table>
				</div>
				<div id="mydoublehead1">
					<strong>设置会员级别</strong>
				</div>
				<div id="doublecontent1">
					<form action="user_score_rate_submit" method="post">
						<table id="xialabiao">
							<tr>
								<td colspan="6" class="titlegrey">&nbsp;</td>
							</tr>
							<tr>
								<td width="4%" class="inputHeader">&nbsp;</td>
								<td width="12%" align="center" class="inputHeader">会员级别：</td>
								<td width="25%" align="left" class="inputContent"><select
									name="levelID">
										<!--会员等级迭代开始-->
										<s:iterator value="levelList" var="level">
										<s:if test="%{#level.levelID!=1}">
											<option value="<s:property value='#level.levelID'/>">
												<s:property value='#level.levelName' />
											</option>
										</s:if>
										</s:iterator>
										<!--会员等级迭代结束-->
								</select></td>
								<td colspan="2" align="center" class="inputContent"><font
									color="#CC0000">&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</font></td>
								<td width="8%" align="center" class="inputContent">&nbsp;</td>
							</tr>
							<tr>
								<td class="inputHeader"><label></label></td>
								<td align="center" class="inputHeader">积分下限：</td>
								<td align="left" class="inputContent"><input type="text"
									class="inputtext" name="low" onfocus="nextfield='credit'"
									maxlength="25" />
								</td>
								<td colspan="2" align="left" class="inputContent"><font
									color="#CC0000">当积分大于相应等级的积分下限时，会员将自动升级</font></td>
								<td align="center" class="inputContent">&nbsp;</td>
							</tr>
							<tr>
								<td class="inputHeader">&nbsp;</td>
								<td align="center" class="inputHeader">积分比例：</td>
								<td align="left" class="inputContent"><input type="text"
									size="6" name="scoreRate" onfocus="nextfield='ratio'" /></td>
								<td colspan="2" align="left" class="inputContent"><font
									color="#CC0000">会员购买物品时获得的积分占购买金额的比例<br/>(以1为基准)</font></td>
								<td align="center" class="inputContent">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" class="inputHeader">&nbsp;</td>
								<td colspan="3" align="left" class="inputContent"><input
									type="submit" class="bt2" name="button22" value="提交"
									onclick="checkusersetform()" />&nbsp; &nbsp;<input
									type="reset" class="bt2" name="button12" value="重填" /></td>
								<td align="center" class="inputContent">&nbsp;</td>
							</tr>

						</table>
					</form>
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
