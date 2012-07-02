<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BC2商城</title>
<link href="css/frame.css" rel="stylesheet" type="text/css" />
<link href="css/gz.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="js/checkform.js"></script>
<script language="javascript" src="js/gz.js"></script>
<script language="javascript" src="js/jquery.js"></script>

</head>

<body>
	<div id="container">
		<div id="logalleft">
			<div id="logalright">
				<a href="" target="_parent">管理员 已登录|</a> <a href="index.html"
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
					<img src="../image/ico_site.jpg" id="ico_site" /> 网站路径：<a
						href="category_list.html">商品管理</a>&gt;&gt;<a href="item_list.html">商品列表</a>&gt;&gt;<a
						href="">商品编辑</a>
				</div>
			</div>
			<div id="sendnote">
				<div id="sendnotehead">
					<strong>编辑商品</strong>
				</div>
				<div id="sendnotecontent">
					<form action="item_modify_submit" method="post" enctype="multipart/form-data">
						<table id="tradequery">
							<tr>
								<th width="181" id="tr_align">图书名称：</th>

								<td width="390"><input type="text" class="inputttextlarge"
									name="name" value="<s:property value='book.bookName'/>"
									onfocus="nextfield='name'" /></td>

								<td width="211"><span class="red">*必填项</span></td>
							</tr>
							<tr>
								<th id="tr_align">商品描述：</th>
								<td><textarea name="description" class="textAreaStyle">
										<s:property value="book.bookDesc" />
									</textarea></td>
								<td></td>
							</tr>
							<tr>
								<th id="tr_align"><span class="inputHeader">商品图片：</span></th>
								<td><img id="img_format"
									src="<s:property value="#request.get('javax.servlet.forward.context_path')"/><s:property value='book.picturePath'/>" />
									<input type="file" name="picture" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th id="tr_align">作者：</th>
								<td><input type="text" name="author"
									value="<s:property value='book.author'/>" /></td>
								<td><span class="red">*必填项</span></td>
							</tr>

							<tr>
								<th id="tr_align">出版社：</th>
								<td><input type="text" name="publisher"
									value="<s:property value='book.publisher'/>" /></td>
								<td><span class="red">*必填项</span></td>
							</tr>


							<tr>
								<th id="tr_align">出版时间：</th>
								<td><select name="publishTime.year" id="sYear">
										<!--<option>2012</option>
              				<option>2011</option>
                            <option>2010</option>
                            <option>2009</option>
                            <option>2008</option>-->
								</select> &nbsp;年&nbsp; <select name="publishTime.month" id="sMonth">
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
								</select> &nbsp;月&nbsp; <select name="publishTime.day" id="sDay">
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
									var defaultYear = <s:property value="book.parseTime.year" />;
									var defaultMonth = <s:property value="book.parseTime.month" /> - 1;
									var defaultDay = <s:property value="book.parseTime.day" />;
									var dt = new Date(defaultYear,
											defaultMonth, defaultDay);
									new DateSelector(sYear, sMonth, sDay, dt);
								</script>
								<td>#选填项</td>
							</tr>
							<tr>
								<th id="tr_align">ISBN：</th>
								<td><input type="text" name="ISBN"
									value="<s:property value='book.isbn'/>" /></td>
								<td><span class="red">*必填项</span></td>
							</tr>
							<tr>
								<th id="tr_align">页数：</th>
								<td><input type="text" name="page" class="smallinputext"
									value="<s:property value='book.page'/>" /></td>
								<td>#选填项</td>

							</tr>
							<tr>
								<th id="tr_align">版次：</th>
								<td><input type="text" name="edition" class="smallinputext"
									value="<s:property value='book.version'/>" /></td>
								<td>#选填项</td>

							</tr>
							<tr>
								<th id="tr_align">开本：</th>
								<td><input type="text" name="size" class="smallinputext"
									value="<s:property value='book.size'/>" />
								</td>
								<td>#选填项</td>
							</tr>
							<tr>
								<th id="tr_align">装帧：</th>
								<td><select name="binding">
										<option>
											<s:property value="book.binding" />
										</option>
										<s:if test="<s:property value='binding'/>=='精装'">
											<option>平装</option>
										</s:if>
										<s:else>
											<option>精装</option>
										</s:else>
								</select></td>
								<td><span class="red">*必填项</span></td>
							</tr>
							<tr>
								<th id="tr_align">分类标签：</th>
								<td><select name="bookClassify">
										<option value="0">不分类</option>
										<s:iterator value="catalog" var='outer'>
											<s:iterator value="#outer.childCatalog" var="inner">
												<s:if test="%{book.parseCatalog.ID==#inner.ID}">
													<option value="<s:property value='#inner.ID'/>"
														selected="selected">
														<s:property value='#outer.name' />
														--
														<s:property value='#inner.name' />
													</option>
												</s:if>
												<s:else>
													<option value="<s:property value='#inner.ID'/>">
														<s:property value='#outer.name' />
														--
														<s:property value='#inner.name' />
													</option>
												</s:else>
											</s:iterator>
										</s:iterator>
								</select>
								</td>
								<td>#选填项</td>
							</tr>
							<tr>
								<th id="tr_align">市场价：</th>
								<td><input type="text" name="marketPrice"
									class="smallinputext"
									value="<s:property value='book.marketPrice'/>" /></td>
								<td><span class="red">*必填项</span></td>
							</tr>
							<tr>
								<th id="tr_align">折扣：</th>
								<td><input type="text" name="discount"
									class="smallinputext"
									value="<s:property value='book.discout'/>" /></td>
								<td><span class="red">*必填项</span></td>
							</tr>
							<tr>
								<th id="tr_align">库存量：</th>
								<td><input type="text" name="inventory"
									class="smallinputext"
									value="<s:property value='book.inventory'/>" /></td>
								<td><span class="red">*必填项</span></td>
							</tr>

							<tr>
								<th></th>
								<td><input type="submit" name="button2" value="提交修改"
									onclick="checkitemform()" /> <input type="button"
									name="button22" value="返回"
									onclick="javascript:window.location.href='item_list.jsp'" />
								</td>
								<td><input type="hidden" name="itemID"
									value="<s:property value="itemID"/>" /></td>
							</tr>
						</table>
					</form>
					<!--<form action="" method="post" enctype="multipart/form-data" name="form1">
				<table id="itemsearch">
				   <tr>
					<th width="180">商品名称：</th>
            		<td width="360">
					 <input type="Text" class="inputttextlarge" name="name" onFocus="nextfield='name'" value="大学物理学.第四册：波动与光学（第2版）" maxlength="25"></td>
					 
					 <td width="142"><span class="red">*必填项</span></td>
          		 </tr>
				 <tr>
				 <th>商品描述：</th>
				 <td><textarea name="desc" class="textAreaStyle">本书是清华大学教材《大学物理学》的第四册，讲述振动与波的一般基本规律和波动光学的基本原理，包括光的干涉、衍射和偏振。除了基本内容外，还专题介绍了全息照相、光学信息处理、液晶等今日物理趣闻和着名物理学家托马斯·杨和菲涅耳的传略。基本内容扼要，附加内容通俗易懂。 本书可作为高等院校的大学物理教材，也可以作为中学物理教师教学或其他读者自学的参考书。</textarea></td>
				 <td></td>
          		 </tr>
				 <tr>
				 <th><span class="inputHeader">商品图片：</span></th>
					<td><img src="../image/dxwl4.jpg" alt="大学物理第4册" class="picture1"><br>
         	 	    <input type="file" name="pic">&nbsp;<input type="submit" name="Submit" value="上传"></td>
					<td></td>
          		 </tr>
                 <tr>
				 <th>作者：</th>
					<td><input type="text" name="price" class="smallinputext" value=""></td>
					<td><span class="red">*必填项</span></td>
          		 </tr>
                   <tr>
				 <th>版次：</th>
					<td><input type="text" name="price"size="1" value=""></td>
					<td><span class="red">*必填项</span></td>
          		 </tr>
                 <tr>
				 <th>出版社：</th>
					<td><input type="text" name="price"  value=""></td>
					<td><span class="red">*必填项</span></td>
          		 </tr>
                  <tr>
				 <th>出版时间：</th>
					<td><input type="text" name="price"  value=""></td>
					<td><span class="red">*必填项</span></td>
          		 </tr>
                 <tr>
				 <th>ISBN：</th>
					<td><input type="text" name="price"value=""></td>
					<td><span class="red">*必填项</span></td>
          		 </tr>
                  <tr>
				 <th>装帧：</th>
					<td><select>
                    <option>精装</option>
                     <option>平装</option>
                    </select></td>
					<td><span class="red">*必填项</span></td>
          		 </tr>
                  <tr>
				 <th>分类标签：</th>
					<td><input type="text" name="price"size="12" value=""></td>
					<td><span class="red">*必填项，可多个</span></td>
          		 </tr>
				  <tr>
				 <th>市场价：</th>
					<td><input type="text" name="price" class="smallinputext" value="10"></td>
					<td><span class="red">*必填项</span></td>
          		 </tr>
				 <tr>
				 <th>库存量：</th>
				 <td>
				 <input type="text" name="storage" class="smallinputext" value="20">				 </td>
				 <td><span class="red">*必填项</span></td>
				 <tr>
				 <th>折扣：</th>
				 <td>
				 <input type="text" name="discount" class="smallinputext" value="0.85">				 </td>
				  <td><span class="red">*必填项</span></td>
				 </tr>
				 <tr>
				 <th></th>
			 	  <td>
				 <input type="Button" name="button2" value="提交" onClick="checkitemform()">
				 	<input type="Reset" name="button1" value="重填" onClick="clear()">
				 <input type="Button" name="button22" value="返回" onClick="javascript:window.location.href='item_list.html'">				  </td> 
				  <td></td>
				  </tr>
        </table>
		</form>-->

				</div>
			</div>
		</div>
	</div>

	<div id="footer">
		<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 |
			电子邮件：Symagics@gmail.com </span> <span id="footerright"> Symagic网上书城
			Power by Symagic &nbsp;</span>
	</div>


</body>
</html>
