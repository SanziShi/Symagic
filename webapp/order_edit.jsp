<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BC2商城</title>
<link href="css/home.css" rel="stylesheet" type="text/css">
<link href="css/order.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/yf_ADS.js"></script>
<script type="text/javascript" src="js/order.js"></script>
</head>

<body>
<div id="container">
	
<div id="cart">
	<span id="cart_loading"></span>
	<div id="cart_none">您的购物车中还没有商品，请选购！</div>
	<div id="cart_container"></div>
	
</div>
	<div id="logalleft">
		<div id="top-overlay"></div>
    <!----吊顶栏------>
	<div class="top">
    <div class="top_right">
    <ul>
    <s:if test="#session.nickname!=null">
    <li><s:property value="#session.nickname"/>！&nbsp;&nbsp;欢迎回到Symagic！</li>
    <li id="logout_top" onclick="logout();"><a>安全退出</a></li>
    </s:if>
    <s:else>
    <li>欢迎来到Symagic！</li>
    <li id="login_top" onclick="load_login();"><a>登录</a></li>
    <li id="regist_top" onclick="load_regist();"><a>免费注册</a></li>
    </s:else>
    <li class="division">|</li><li id="mymall"><a href="user"><span id="mymall_icon"></span>我的商城</a></li><li class="division">|</li>
    <li id="cart_top"><a id="cart_a" href="cart">
    <span id="cart_icon"></span>购物车 <strong id="cart_num"><s:property value='#session.totalNumber'/></strong> 件</a>
    </li>
    </ul>
    </div>
    </div>
  <!------吊顶结束----->
	</div>
	<div id="globallink">
		<ul>
			<li><a href="index">首页</a></li>
			<li><a href="item_list">商品列表</a></li>
			<li><a href="favorite.html">收藏夹</a></li>
			<li><a href="address.html">地址簿</a></li>
			<li><a href="tradequery.html">交易查询</a></li>
			<li><a href="send_notes.html">送货说明</a></li>
            <li><a class="nouseful">&nbsp;</a></li>
			<li><a class="nouseful">&nbsp;</a></li>
		</ul>
	</div>
    <form action="order_submit" method="post" id="order_submit">
    <div id="order_container">
    	<div id="c-info">
        	<h1>收货人信息<span id="order_id">订单号：<font color="#FF0000"><s:property value='orderID'/></font></span></h1>
            <!--隐藏订单号传入input-->
            <input style="display:none" name="orderID" value="<s:property value='orderID'/>">
            <!---->
            <div id="c-input">
            	<div id="address_list">
                	<div class="address_list_inner">
                    	<b>常用地址：</b>
                    	<ul>
                        <!--常用地址迭代开始-->
                        <s:iterator value='addressList' var="iter">
                        	<li class="" id="normal_address<s:property value='#iter.ID'/>">
                            	<input name="adressID" onclick="detect(this,<s:property value='#iter.ID'/>)"  type="radio" id="normal_a<s:property value='#iter.ID'/>" />
                                <label for="normal_a<s:property value='#iter.ID'/>"><strong><s:property value='#iter.receiverName'/></strong>&nbsp;<s:property value='#iter.addressSummary'/><s:property value='#iter.addressDetail'/></label>
                                <span class="delete"><a onclick="delete_address(<s:property value='#iter.ID'/>)" href="javascript:void(0)">删除地址</a></span>
                            </li>
                        </s:iterator>
                        <!--常用地址迭代结束-->
                        </ul>
                    </div>
                </div>
                <!--默认送货人信息迭代-->
                <table id="order_address" width="100%" cellspacing="0" border="0">
                			<tbody>
                    		<tr>
                            	<td width="80" valign="middle" align="right"><font color="red">*</font>收货人姓名：</td>
                            	<td><input name="receiverName" class="txt" type="text" value="<s:property value='defaultAddressList.receiverName'/>"/></td>
                            </tr>
                        	<tr>
                        	  	<td valign="middle" align="right"><font color="red">*</font>所在地区：</td>
                              	<td>
                                <!--默认地址选项迭代--->
                                	<select onchange="get_district(this)" id="level1ID" name="districtLevel1ID" >
                                			<option value="s1" >请选择</option>
                               				<s:iterator value="defaultAddressList.level1Districts" var='middle'>
                                            	<s:if test='#middle.ID==defaultAddressList.level1DistrictDefaultID'>
                                				<option value="<s:property value='#middle.ID'/>" selected="selected" ><s:property value='#middle.name'/></option>
                                				</s:if>
                                                <s:else>
                                                <option value="<s:property value='#middle.ID'/>" ><s:property value='#middle.name'/></option>
                                                </s:else>
                                            </s:iterator>
                                	</select>
                                	<select onchange="get_district(this)" id="level2ID" name="districtLevel2ID">
                                		<option value="s2">请选择</option>
                                        <s:iterator value="defaultAddressList.level2Districts" var='middle'>
                                            	<s:if test='#middle.ID==defaultAddressList.level2DistrictDefaultID'>
                                				<option value="<s:property value='#middle.ID'/>" selected="selected" ><s:property value='#middle.name'/></option>
                                				</s:if>
                                                <s:else>
                                                <option value="<s:property value='#middle.ID'/>" ><s:property value='#middle.name'/></option>
                                                </s:else>
                                            </s:iterator>
                                	</select>
                                	<select id="level3ID" name="districtLevel3ID">
                                		<option value="">请选择</option>
                                        <s:iterator value="defaultAddressList.level3Districts" var='middle'>
                                            	<s:if test='#middle.ID==defaultAddressList.level3DistrictDefaultID'>
                                				<option value="<s:property value='#middle.ID'/>" selected="selected" ><s:property value='#middle.name'/></option>
                                				</s:if>
                                                <s:else>
                                                <option value="<s:property value='#middle.ID'/>" ><s:property value='#middle.name'/></option>
                                                </s:else>
                                            </s:iterator>
                                	</select>
                                <!---默认地址选项迭代结束--->
                                </td>
                            </tr>
                    		<tr>
                    		  	<td valign="middle" align="right"><font color="red">*</font>详细地址：</td>
                              	<td><span id="consign_address"></span><input name="addressDetail" type="text" class="txt long" value="<s:property value='defaultAddressList.addressDetail'/>"></td>
                            </tr>
                    		<tr>
                            	<td valign="middle" align="right"><font color="red">*</font>手机号码：</td>
                            	<td><input name="mobileNum" type="text" class="txt" value="<s:property value='defaultAddressList.mobileNum'/>"/>或者&nbsp;&nbsp;或&nbsp;&nbsp;固定电话：<input name="phoneNum" type="text" class="txt" value="<s:property value='defaultAddressList.phoneNum'/>"></td>
                            </tr>
                    		<tr>
                            	<td valign="middle" align="right">邮箱地址：</td><td><s:property value='#session.userName'/></td>
                            </tr>
                    		<tr>
                            	<td valign="middle" align="right"><font color="red">*</font>邮政编码：</td>
                            	<td><input name="zipcode" type="text" class="txt short" value="<s:property value='defaultAddressList.zipcode'/>"/></td>
                            </tr>
                            <tr>
                                	<td></td>
                                    <td><input type="submit" value="保存修改"/></td>
                            </tr>
                    		</tbody>
                		</table>
                <!--默认送货详情迭代结束-->
                <span class="add-to-address"><a>添加信息至地址簿</a></span>&nbsp;&nbsp;<span><a>重置内容</a></span>
            </div>
        </div>
        <div id="payment-c">
        	<h1>支付及配送方式</h1>
            <table width="100%" cellspacing="0" border="0">
                	<tbody>
                    	<tr>
                        	<td width="100" valign="middle" align="left"><h1>支付方式</h1></td>
                            <td></td>
                        </tr>
                    	<tr>
                        	<td><input type="radio" checked="checked">货到付款</input></td>
                            <td class="comment">送货上门后付款，使用现金</td>
                        </tr>
                    </tbody>
            </table>
            <table width="100%" cellspacing="0" border="0">
                	<tbody>
                    	<tr>
                        	<td width="100" valign="middle" align="left"><h1>配送方式</h1></td>
                        	<td></td>
                        </tr>
                    	<tr>
                        	<td><input type="radio" checked="checked">快递</input></td>
                            <td class="comment">使用默认的快递方式，快捷省事</td>
                        </tr>
                    </tbody>
            </table>
        </div>
        <div id="item_list">
        	<h1>商品清单</h1>
            <div id="item_table">
            	<table >
            		<tbody>
                		<tr class="thead">
                 	   		<td width="9%">商品ID</td>
                    		<td width="37%">商品名称</td>
                    		<td width="13%">商城价</td>
                    		<td width="13%">商品数量</td>
                    		<td width="13%">商品小计</td>
                    		<td width="15%">可获积分</td>
                    	</tr>
                		<!--商品列表迭代开始-->
                        <s:iterator value="buyItems" var='iter'>
                    	<tr>
                    		<td><s:property value='#iter.itemID'/></td>
                        	<td><s:property value='#iter.name'/></td>
                        	<td><s:property value='#iter.price'/></td>
                        	<td><s:property value='#iter.itemNumber'/></td>
                        	<td><s:property value='#iter.itemTotalPrice'/></td>
                        	<td><s:property value='#iter.score'/></td>
                    	</tr>
                         </s:iterator>
                    	<!--商品列表迭代结束-->
                	</tbody>
           		</table>
            </div>
        </div>
        <div id="ware">
        	<h1>结算</h1>
            <div id="use_score"><input type="checkbox" onclick="use_score_change(this)"/>使用积分：&nbsp;&nbsp;
            	<input onblur="check_score(this)" id="score" name="score" class="short" value="0"/>&nbsp;&nbsp;
                可用积分：&nbsp;&nbsp;<font color="red"><s:property value='useTotalScore'/></font>
            </div>
        	<div class="total_price"><span id="pay">应付总额：<strong>￥</strong><strong id="total_price"><s:property value='price'/></strong>元</span></div>
        	<div class="clear"></div>
        	<div class="order_confirm" onclick="order_submit()"></div>
        	<div class="clear"></div>
        </div>     
    </div>
   </form>
	<div id="footer">
		<span id="footerleft"> &nbsp;隐私权 | 版权 | 法律声明 | 电子邮件：admin@163.com </span>
		<span id="footerright"> B2C商城  Power by IBM &nbsp;</span>
	</div>
</div>

</body>
</html>
