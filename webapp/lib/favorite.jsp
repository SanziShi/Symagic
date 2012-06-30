<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="favorite-content">
        		<div id="favorite-content-head">
                	<div class="column checkbox">
                		<input class="" type="checkbox"/>
                	</div>
                    <div class="column p-name">商品信息</div>
                    <div class="column p-price">商城价</div>
                    <div class="column inventory">库存</div>
                    <div class="column action">操作</div>
            	</div>
                <div id="favorite-content-body">
                <!--收藏夹商品迭代-->
                <s:iterator value="items" var="iter">
                	<div class="cell checkbox">
                		<input class="" value="" type="checkbox"/>
                	</div>
                    <div class="cell p-name"><div class="p-name-img"><a href=""><img src="upload/9787214078476.png"/></a></div><div class="p-name-n"><a href=""><s:property value='#iter.name'/></a>
                    <br>综合评分：<span class="sa45"></span></div></div>
                   <div class="cell p-price p-price-p"><del>￥<span><s:property value='#iter.savePrice'/></span></del><br>￥<span><s:property value='#iter.price'/></span><br><font class="red">为您节省：<span>￥<s:property value='#iter.savePrice'/></span></font></div>
                    <div class="cell inventory"><s:property value='#iter.inventory'/></div>
                    <div class="cell action"><span class="fav-add"></span>
                    <a href="" class="fav_cancel">取消收藏</a></div>
            	</s:iterator >
                <!--收藏夹商品迭代-->
                </div>   
                <div id="favorite-content-bottom">
                	<input type="checkbox" />全选&nbsp;&nbsp;
                	<span onclick="add_to_cart()" class="fav-add-b"></span>
                    <a href="">取消收藏</a>
                </div>
        	</div>