<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Symagic网上书城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css"/>
<link href="css/gz.css" rel="stylesheet" type="text/css"/>
<link href="css/home.css" rel="stylesheet" type="text/css"/>
<script language="javascript" src="js/checkform.js">
</script>
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

<div id="left">
		<div id="catelog_admin_left">
			<ul>
                <li><a href="user_admin.html">查看目录</a></li>
				<li><a href="user_admin.html">添加和更改目录</a></li>
			</ul>
      </div>
	</div>
   
	<div id="main">
		<div id="search1">
			<div id="mysearchleft">
				<img src="../image/ico_site.jpg"  id="ico_site"/>
				网站路径：<a href="index">首页</a>&gt;&gt;<a href="catalog_manager">商品目录</a>&gt;&gt;<a href="">添加和更改目录</a>
			</div>
            </div>


		<div id="mydouble">
			<div id="mydoublehead1"><strong>编辑商品目录</strong></div>
			<div id="doublecontent1">
			<form action="catalog_edit_submit" method="post" enctype="multipart/form-data">
				<table id="catalog_itemsearch">
				   <tr>
					<th class="itemsearchth" >目录名：</th>
            		<td class="itemsearchtd1">
					 <input type="text" class="inputtext" name="name" onfocus="nextfield='password'" value="<s:property value='catalogName'/>" maxlength="25"/> 
					</td>
					<td class="itemsearchtd2">
						<span class="red">*&nbsp;必填项</span></td>
          		 </tr>
				 <tr>
				 <th class="itemsearchth">父目录：</th>
            		<td class="itemsearchtd1">
						<select>
                        <!--若是根目录迭代开始-->
                       <s:if test="%{<s:property value='upID'/>==0}">
                       <option value="0" selected="selected">根目录</option>
                       <s:iterator value="catalog" var ="outer">
                       <option value="<s:property value='#outer.id'/>"><s:property value = "#outer.name"/></option>
                       </s:iterator>
                       </s:if>
                        <!--若是根目录迭代结束-->
                        <!--所选为非根目录迭代开始-->
                       <s:else>
                       <s:iterator value="catalog" var='outer'>
                       <option value="<s:property value='#outer.id'/>"><s:property value = "#outer.name"/></option>
                       <s:if test="%{<s:property value='upID'/>==#ourer.id}">
                       <option selected="selected"><s:property value ="#outer.name"/></option>
                       </s:if>
                       </s:iterator>
                       </s:else>
						</select>
					</td>
					<td class="itemsearchtd2">&nbsp;</td>
          		 </tr>
				 <tr>
				 <th class="itemsearchth"><span class="inputHeader">目录描述：</span></th>
            		<td class="itemsearchtd1">
				<textarea name="catalogDesc" class="textAreaStyle"><s:property value = "catalogDesc"/>
                </textarea>
					</td>
					<td class="itemsearchtd2">&nbsp;</td>

          		 </tr>
				 <tr>
				 <th></th>
				 	<td><input type="submit" name="button2" value="提交修改" onclick="checkcategoryform()"/>
						&nbsp;
					</td>
				 </tr>
        </table>
		</form>
				
			</div>
        </div>
        
        <div id="mydouble">
			<div id="mydoublehead1"><strong>添加目录</strong></div>
			<div id="doublecontent2">
			<form action="catalog_add_submit" method="post">
				<table id="catalog_itemsearch">
				   <tr>
					<th class="itemsearchth" >目录名：</th>
            		<td class="itemsearchtd1">
					 <input type="text" class="inputtext" name="catalogName" onfocus="nextfield='password'" 	/> 
					</td>
					<td class="itemsearchtd2">
						<span class="red">*&nbsp;必填项</span></td>
          		 </tr>
				 <tr>
				 <th class="itemsearchth">父目录：</th>
            		<td class="itemsearchtd1">
						<select>
					   <option value="0" selected="selected">根目录</option>
                       <s:iterator value="catalog" var ="outer">
                       <option value="<s:property value='#outer.id'/>"><s:property value = "#outer.name"/></option>
                       </s:iterator>
						</select>
					</td>
					<td class="itemsearchtd2">&nbsp;</td>
          		 </tr>
				 <tr>
				 <th class="itemsearchth"><span class="inputHeader">目录描述：</span></th>
            		<td class="itemsearchtd1">
				<textarea name="catalogDesc" class="textAreaStyle"></textarea>
					</td>
					<td class="itemsearchtd2">&nbsp;</td>

          		 </tr>
				 <tr>
				 <th></th>
				 	<td><input type="submit" name="button2" value="添加" onclick="checkcategoryform()"/>
						&nbsp;<input type="reset" name="button1" value="重填" onclick="clear()"/>
					</td>
				 </tr>
        </table>
		</form>		
			</div>
            </div>
      </div>
     

		
	<div id="footer">
		<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 | 电子邮件：Symagics@gmail.com </span>
		<span id="footerright"> Symagic网上书城  Power by Symagic	 &nbsp;</span>
	</div>
</div>
</body>
</html>
