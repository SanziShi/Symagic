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
                	<div class="cell checkbox">
                		<input class="" type="checkbox"/>
                	</div>
                    <div class="cell p-name"><div class="p-name-img"><a href=""><img src="upload/9787214078476.png"/></a></div><div class="p-name-n"><a href="">测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试</a>
                    <br>综合评分：<span class="sa45"></span></div></div>
                   <div class="cell p-price p-price-p"><del>￥<span>40.00</span></del><br>￥<span>现价</span><br><font class="red">为您节省：<span>￥20.00</span></font></div>
                    <div class="cell inventory">库存</div>
                    <div class="cell action"><span class="fav-add"></span>
                    <a href="" class="fav_cancel">取消收藏</a></div>
            	</div>
                <div id="favorite-content-bottom">
                	<input type="checkbox" />全选&nbsp;&nbsp;
                	<span class="fav-add-b"></span>
                    <a href="">取消收藏</a>
                </div>
        	</div>