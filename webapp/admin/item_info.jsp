<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Symagic网上书城</title>
<link href="../css/frame.css" rel="stylesheet" type="text/css" />
<link href="../css/gz.css" rel="stylesheet" type="text/css" />

<script language="javascript" src="js/jquery.js"></script>
<script language="javascript" src="js/gz.js"></script>
<script language="javascript" src="js/checkform.js"></script>
<script language="javascript" src="js/catalog.js"></script>
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
				<li><a href="javascript:document.getElementById('clear_order_search').submit()">订单管理</a>
				</li>
                <!--清空订单精确查询的隐藏表单-->
            	<form action="order_list?page=1" method="post" id="clear_order_search" enctype="multipart/form-data" style="display:none">
           	 	<input type="text" name="userName" />
            	<select name="orderState">
                <option value="0" selected="selected"></option>
                </select>
            	<select name="startTime.year" >
                <option value="2007" selected="selected"></option>
                </select>
                <select name="startTime.month" >
                <option value="1" selected="selected"></option>
                </select>
                <select name="startTime.day" >
                <option value="1" selected="selected"></option>
                </select>
                <select name="endTime.year" >
                <option value="2012" selected="selected"></option>
                </select>
                <select name="endTime.month" >
                <option value="12" selected="selected"></option>
                </select>
                <select name="endTime.day" >
                <option value="31" selected="selected"></option>
                </select>
            	</form>
            	<!--清空订单精确查询的隐藏表单-->
				<li><a href="javascript:document.getElementById('clear_static_search').submit()">销售量统计</a></li>
                <!--清空销售量查询的隐藏表单-->
            	<form action="order_statistics?page=1" method="post" enctype="multipart/form-data"  id="clear_static_search" style="display:none">
           	 	<select name="startTime.year" >
                <option value="2007" selected="selected"></option>
                </select>
                <select name="startTime.month" >
                <option value="1" selected="selected"></option>
                </select>
                <select name="startTime.day" >
                <option value="1" selected="selected"></option>
                </select>
                <select name="endTime.year" >
                <option value="2012" selected="selected"></option>
                </select>
                <select name="endTime.month" >
                <option value="12" selected="selected"></option>
                </select>
                <select name="endTime.day" >
                <option value="31" selected="selected"></option>
                </select>
                <select name="catalogID">
				<option value="0" selected="selected"></option>
                </select>
            	<input type="text" name="limit"
										value="0" />
            	</form>
            	<!--清空销售量查询的隐藏表单-->
				<li><a href="user_level">会员管理</a></li>
				<li><a href="" class="nouseful">&nbsp;</a></li>
				<li><a href="" class="nouseful">&nbsp;</a></li>
			</ul>
		</div>
		<!--	<div id="banner"></div>-->
		<div id="main">
			<div id="search2">
				<div id="searchleft">
					<img src="image/ico_site.jpg" id="ico_site" /> 网站路径：<a
						href="index">首页</a>&gt;&gt;<a href="#">商品详细信息</a>
				</div>
			</div>
			<div id="sendnote">
				<div id="sendnotehead">
					<strong>商品信息</strong>
				</div>
				<div id="sendnotecontent">
					<table id="creditquery">
						<caption>
							<s:property value="book.bookName" />
						</caption>
						<tbody>
							<tr>
								<td><img
									src="<s:property value="#request.get('javax.servlet.forward.context_path')"/><s:property value="book.picturePath"/>"
									alt="<s:property value="book.bookName"/>" class="picture1" />
								</td>
								<td colspan="4" class="tdleftalign"><s:property
										value="book.bookDesc" escape="false" />
								</td>
							</tr>
							<tr>
								<td>市场价：￥<s:property value="book.marketPrice" />
								</td>
								<td>商城价：￥<s:property value="book.price" />
								</td>
								<td>折扣：<s:property value="book.discout" />
								</td>
								<td>库存量：<s:property value="book.inventory" />
								</td>
							</tr>
							<tr>
								<td>作者：<s:property value="book.author" />
								</td>
								<td>出版社：<s:property value="book.publisher" />
								</td>
								<td>ISBN:<s:property value="book.isbn" />
								</td>
								<td>出版时间：<s:property value="book.publishDate" />
								</td>
							</tr>
							<tr>
								<td>版次：<s:property value="book.version" />
								</td>
								<td>开本：<s:property value="book.size" />
								</td>
								<td>装帧：<s:property value="book.binding" />
								</td>
								<td>页数：<s:property value="book.page" />
								</td>
								<td>在架状态：<s:if test="%{book.offline}">下架</s:if>
									<s:else>在架</s:else>
								</td>
							</tr>

							<tr style="display:none">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="sendnote">
				<div id="sendnotehead">
					<strong>用户评论</strong>
				</div>
				<div id="sendnotecontent">
					<table id="creditquery">
						<s:iterator value="commentList" var="comments">
							<div id="<s:property value='#comments.username'/>">
								<tr>
									<td class="commentr1color">会员：</td>
									<td class="commentr1color"><s:property
											value="#comments.username" /></td>
									<td class="commentr1color">发表时间：</td>
									<td class="commentr1color"><s:property
											value="#comments.commentDate" /></td>
									<td class="commentr1color">打分：</td>
									<td class="commentr1color"><img
										src="image/<s:property value='#comments.rating'/>star.JPG"
										alt="喜欢" /></td>
								</tr>
								<tr>
									<td class="commentr2color">评论：</td>
									<td colspan="4" class="commentr2color"><span class="red"><s:property
												value="#comments.content" /> </span>
									</td>
									<td><a href="javascript:void(0)"
										onclick="ajax_delete_comment('<s:property
										value="#comments.username" />','<s:property value="itemID"/>');">删除</a>
									</td>
								</tr>
							</div>
						</s:iterator>
						<tr>
							<td></td>
							<td></td>
							<td><s:if test="%{totalPage==1||totalPage==0}"></s:if> <s:elseif
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
