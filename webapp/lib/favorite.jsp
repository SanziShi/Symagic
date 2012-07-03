<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="favorite-content">
			<form id="favorite_form">
        		<div id="favorite-content-head">
                	<div class="column checkbox">
                		<input checked="checked" c="2" type="checkbox" onclick="check_box_all_change(2,this)"/>
                	</div>
                    <div class="column p-name">商品信息</div>
                    <div class="column p-price">商城价</div>
                    <div class="column inventory">库存</div>
                    <div class="column action">操作</div>
            	</div>
                <div id="favorite-content-body">
                <!--收藏夹商品迭代-->
                <s:iterator value="items" var="iter" status='st' >
                	<div class="cell checkbox">
                		<input c="2" name="items[<s:property value='#st.index'/>].itemID" checked="checked" value="<s:property value='#iter.itemID'/>" type="checkbox" />
                        <input style="display:none"  name="items[<s:property value='#st.index'/>].itemNumber" value="1" />
                	</div>
                    <div class="cell p-name">
                    	<div class="p-name-img"><a href="item_detail?itemID=<s:property value='#iter.itemID'/>"><img src="<s:property value='#request.get("javax.servlet.forward.context_path")'/><s:property value='#iter.picturePath'/>"/></a></div><div class="p-name-n"><a href="item_detail?itemID=<s:property value='#iter.itemID'/>"><s:property value='#iter.name'/></a>
                    	<br>综合评分：<span class="star"><span class="sa<s:property value='#iter.rating'/>"></span></span>&nbsp;&nbsp;（&nbsp;<s:property value='#iter.rating'/>&nbsp;分&nbsp;）
                    	</div>
                       </div>
                   <div class="cell p-price p-price-p"><del>￥<span><s:property value='#iter.marketPrice'/></span></del><br>￥<span><s:property value='#iter.price'/></span><br><font class="red">为您节省：<span>￥<s:property value='#iter.savePrice'/></span></font></div>
                    <div class="cell inventory"><s:property value='#iter.inventory'/></div>
                    <div class="cell action"><span onclick="add_to_cart(<s:property value='#iter.itemID'/>)" class="fav-add"></span>
                    <a href="javascript:void(0)" onclick="delete_from_favorite(<s:property value='#iter.itemID'/>)" class="fav_cancel">取消收藏</a></div>
            	</s:iterator >
                <!--收藏夹商品迭代-->
                </div>   
                <div id="favorite-content-bottom">
                	<input type="checkbox" checked="checked" c="2" onclick="check_box_all_change(2,this)" />全选&nbsp;&nbsp;
                	<span onclick="adds_to_cart('favorite_form')" class="fav-add-b"></span>
                    <a href="javascript:void(0)" onclick="delete_favorites()">取消收藏</a>
                </div>
                </form>
        	</div>