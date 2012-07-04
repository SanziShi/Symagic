<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="cart_head"><h4>最近加入的商品</h4></div>
        <div id="cart_content">
            <ul>	
            <s:iterator value="items" var="item">
            <!--购物车内容迭代-->
                <li id="ct<s:property value='#item.itemId'/>">
                	<div class="cart_img"><a href="item_detail?itemID=<s:property value='#item.itemID'/>"><img src="<s:property value='#request.get("javax.servlet.forward.context_path")'/><s:property value='#item.picturePath'/>"/></a></div>
                    <div class="cart_name"><a href="item_detail?itemID=<s:property value='#item.itemID'/>"/><s:property value='#item.name'/></a></div>
                    <div class="cart_price"><strong>￥<s:property value='#item.price'/></strong><span>x<s:property value='#item.itemNumber'/></span><br><a href="javascript:void(0)" onClick="delete_from_cart(<s:property value='#item.itemID'/>)" class="delete">删除</a></div>
                </li>
            <!--购物车内容迭代结束-->
            </s:iterator>
            </ul>
        </div>
<div id="cart_button">共&nbsp;<strong id="cart_total_num"><s:property value='totalNumber'/></strong>&nbsp;件商品&nbsp;&nbsp;&nbsp;共计&nbsp;<strong>￥<s:property value='totalPrice'/></strong>
<br/>
<a href="cart">去购物车结算</a><div class="clear"></div></div>