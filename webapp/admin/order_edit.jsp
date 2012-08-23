<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Symagic商城</title>
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
            	<!--清空详细搜索的隐藏表单-->>
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
					<img src="../image/ico_site.jpg" id="ico_site" /> 网站路径：订单修改
				</div>
			</div>
			<form action="order_edit_submit" method="post"
				enctype="multipart/form-data" name="form1">
				<div id="double1">

					<div id="doublehead1">
						<strong>配送信息</strong>
					</div>

					<div id="doublecontent1">

						<table id="doublecontenttable3">
							<tr>
								<td>订单号</td>
								<td><input type="text" id="inputGray" readonly="readonly"
									value="<s:property value='orderID'/>" />
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
								<td><s:if test="orderStatus!='交易成功'||'交易失败'">
										<input type="text" value="<s:property value='receiver'/>"
											name="receiver" />
									</s:if> <s:else>
										<s:property value="receiver" />
									</s:else></td>
								<td>收货人地址</td>
								<td colspan="3"><select
									onchange="get_district(this)"
									id="level1ID" name="level1ID">
										<option value="s1">请选择</option>
										<s:iterator value="level1District" var='iter'>
											<s:if test="%{selectedLevel1DistrictID==#iter.ID}">
												<option value="<s:property value='#iter.ID'/>"
													selected="selected">
													<s:property value='#iter.name' />
												</option>
											</s:if>
											<s:else>
												<option value="<s:property value='#iter.ID'/>">
													<s:property value='#iter.name' />
												</option>
											</s:else>
										</s:iterator>
								</select> <select
									onchange="get_district(this)"
									id="level2ID" name="level2ID">
										<option value="s2">请选择</option>
										<s:iterator value="level2District" var='iter'>
											<s:if test="%{selectedLevel2DistrictID==#iter.ID}">
												<option value="<s:property value='#iter.ID'/>"
													selected="selected">
													<s:property value='#iter.name' />
												</option>
											</s:if>
											<s:else>
												<option value="<s:property value='#iter.ID'/>">
													<s:property value='#iter.name' />
												</option>
											</s:else>
										</s:iterator>
								</select> <select id="level3ID" name="level3ID">
										<option value="s3">请选择</option>
										<s:iterator value="level3District" var='iter'>
											<s:if test="%{selectedLevel3DistrictID==#iter.ID}">
												<option value="<s:property value='#iter.ID'/>"
													selected="selected">
													<s:property value='#iter.name' />
												</option>
											</s:if>
											<s:else>
												<option value="<s:property value='#iter.ID'/>">
													<s:property value='#iter.name' />
												</option>
											</s:else>
										</s:iterator>
								</select> <input type="text" value="<s:property value='addressDetail'/>"
									name="addressDetail" /></td>
							</tr>

							<tr>
								<td>邮政编码</td>
								<td><s:if test="orderStatus!='交易成功'||'交易失败'">
										<input type="text" value="<s:property value='zipcode'/>"
											name="zipcode" />
									</s:if> <s:else>
										<s:property value="zipcode" />
									</s:else></td>

								<td>收货人手机</td>
								<td><s:if test="orderStatus!='交易成功'||'交易失败'">
										<input type="text" name="mobileNumber"
											value="<s:property value='receiverMobile'/>" />
									</s:if> <s:else>
										<s:property value="mobileNumber" />
									</s:else></td>
								<td>收货人电话</td>
								<td><s:if test="orderStatus!='交易成功'||'交易失败'">
										<input type="text" value="<s:property value='receiverPhone'/>"
											name="phoneNumber" />
									</s:if> <s:else>
										<s:property value="phoneNumber" />
									</s:else></td>
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
									<th>数量</th>
									<th>商品小计</th>
								</tr>
							</thead>
							<tbody>
								<!--开始对客户购买的商品条项进行迭代-->
								<s:iterator value="items" var="itemArray" status="stat">
									<tr>
										<td><span>
										<s:property value="#itemArray.itemID" />
										<input type="hidden" name="items[<s:property value="#stat.index"/>].itemID" value="<s:property value="#itemArray.itemID" />"/>
										</span>
										</td>
										<td><a
											href="item_detail?itemID=<s:property value = '#itemArray.itemID'/>">
											<s:property value="#itemArray.name" />
										</a>
										 
										 </td>
										<td >￥<span id="singlePrice<s:property value = '#itemArray.itemID'/>"><s:property value="#itemArray.price" /></span>
										</td>
										<td><s:if test="orderStatus!='交易成功'||'交易失败'">
												<span><a class="reduce"
													onclick="reduce(<s:property value = '#itemArray.itemID'/>);"
													href="javascript:void(0)">-</a> <input type="text"
													name="items[<s:property value="#stat.index"/>].itemNumber"
													class="amount"
													value="<s:property value = '#itemArray.itemNumber'/>"
													id="<s:property value = '#itemArray.itemID'/>"
													onkeyup="amount_modify(this)" /> <a class="reduce"
													onclick="add(<s:property value = '#itemArray.itemID'/>);"
													href="javascript:void(0)">+</a> </span>
											</s:if> <s:else>
												<s:property value="itemNumber" />
											</s:else></td>
										<td >￥<span id="totalPrice<s:property value = '#itemArray.itemID'/>"><s:property value="#itemArray.itemTotalPrice" /></span>
										</td>
									</tr>
								</s:iterator>
								<!--商品条项迭代结束-->
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td><s:if test="orderStatus!='交易成功'||'交易失败'">
											<input type="submit" value="提交修改" />
										</s:if><input type="hidden"
										name="orderID" value="<s:property value='orderID'/>" />
									</td>


								</tr>
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
