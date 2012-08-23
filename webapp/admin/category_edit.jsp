<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Symagic网上书城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css" />
<link href="css/gz.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="js/checkform.js">
	
</script>
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
				<li><a href="order_list">订单管理</a></li>
				<li><a href="order_statistics">销售量统计</a></li>
				<li><a href="user_level">会员管理</a></li>
				<li><a href="" class="nouseful">&nbsp;</a></li>
                <li><a href="" class="nouseful">&nbsp;</a></li>
			</ul>
		</div>

		<div id="left">
			<div id="catelog_admin_left">
				<ul>
					<li><a href="catalog_manager">查看目录</a>
					</li>
					<li><a href="catalog_manager">添加目录</a>
					</li>
				</ul>
			</div>
		</div>

		<div id="main">
			<div id="search1">
				<div id="mysearchleft">
					<img src="../image/ico_site.jpg" id="ico_site" /> 网站路径：<a
						href="index">首页</a>&gt;&gt;<a href="catalog_manager">商品目录</a>&gt;&gt;<a
						href="">添加和更改目录</a>
				</div>
			</div>


			<div id="mydouble">
				<div id="mydoublehead1">
					<strong>编辑商品目录</strong>
				</div>
				<div id="doublecontent1">
					<form action="catalog_edit_submit" method="post"
						enctype="multipart/form-data">
						<table id="catalog_itemsearch">
							<tr>
								<th class="itemsearchth">目录名：</th>
								<td class="itemsearchtd1"><input type="text"
									class="inputtext" name="catalogName" onfocus="nextfield='password'"
									value="<s:property value='catalogName'/>" maxlength="25" /></td>
								<td class="itemsearchtd2"><span class="red">*&nbsp;必填项</span>
								</td>
							</tr>
							<tr>
								<th class="itemsearchth">父目录：</th>
								<td class="itemsearchtd1">
								<select name="upID">
										<!--若是根目录-->
										<s:if test="%{upID==0}">
											<option value="0" selected="selected">根目录</option>
										</s:if>
										<s:else>
											<option value="0">根目录</option>
										</s:else>
										<!--所选为非根目录迭代开始-->
										<s:iterator value="catalog" var='outer'>
											<s:if test="%{upID==#outer.ID}">
												<option value="<s:property value='#outer.ID'/>"
													selected="selected">
													<s:property value="#outer.name" />
												</option>
											</s:if>
											<s:else>
												<option value="<s:property value='#outer.ID'/>">
													<s:property value="#outer.name" />
												</option>
											</s:else>
										</s:iterator>
								</select></td>
								<td class="itemsearchtd2">&nbsp;</td>
							</tr>
							<tr>
								<th class="itemsearchth"><span class="inputHeader">目录描述：</span>
								</th>
								<td class="itemsearchtd1"><textarea name="catalogDesc"
										class="textAreaStyle">
										<s:property value="catalogDesc" />
                </textarea></td>
								<td class="itemsearchtd2"><span class="red">*&nbsp;必填项</span>

							</tr>
							<tr>
								<th></th>
								<td><input type="submit" name="button2" value="提交修改"
									onclick="checkcategoryform()" /> &nbsp;<input type="hidden" name="catalogID" value="<s:property value="catalogID"/>"/></td>
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
