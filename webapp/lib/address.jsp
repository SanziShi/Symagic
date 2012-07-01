<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="address-content">
                <!--修改地址迭代开始-->
                <div id="test" class="address-edit">
                <h2>修改地址<span class="close" onclick="close_address(this)"></span></h2>
                <table width="100%" cellspacing="0" border="0">
                			<tbody>
                    		<tr><td width="80" valign="middle" align="right"><font color="red">*</font>收货人姓名：</td><td><input class="txt" type="text"/></td></tr>
                        	<tr>
                        	  <td valign="middle" align="right"><font color="red">*</font>所在地区：</td><td><select><option value="">请选择</option></select><select><option value="">请选择</option></select><select><option value="">请选择</option></select></td></tr>
                    		<tr>
                    		  <td valign="middle" align="right"><font color="red">*</font>详细地址：</td><td><span id="consign_address"></span><input type="text" class="txt long"></td></td></tr>
                    		<tr><td valign="middle" align="right"><font color="red">*</font>手机号码：</td><td><input type="text" class="txt"/>或者&nbsp;&nbsp;或&nbsp;&nbsp;固定电话：<input type="text" class="txt"></td></tr>
                    		<tr><td valign="middle" align="right">邮箱地址：</td><td>43234@qq.com</td></tr>
                    		<tr><td valign="middle" align="right"><font color="red">*</font>邮政编码：</td><td><input type="text" class="txt short"/></td></tr>
                    		</tbody>
                		</table>
                  </div>
                  <!--修改地址迭代结束-->
                	<div id="address-content-head">
                    <h2>新增收货地址</h2>
                    </div>
                    <div id="address-content-body">
                    	<table width="100%" cellspacing="0" border="0">
                			<tbody>
                    		<tr><td width="80" valign="middle" align="right"><font color="red">*</font>收货人姓名：</td><td><input class="txt" type="text"/></td></tr>
                        	<tr>
                        	  <td valign="middle" align="right"><font color="red">*</font>所在地区：</td><td><select><option value="">请选择</option></select><select><option value="">请选择</option></select><select><option value="">请选择</option></select></td></tr>
                    		<tr>
                    		  <td valign="middle" align="right"><font color="red">*</font>详细地址：</td><td><span id="consign_address"></span><input type="text" class="txt long"></td></td></tr>
                    		<tr><td valign="middle" align="right"><font color="red">*</font>手机号码：</td><td><input type="text" class="txt"/>或者&nbsp;&nbsp;或&nbsp;&nbsp;固定电话：<input type="text" class="txt"></td></tr>
                    		<tr><td valign="middle" align="right">邮箱地址：</td><td>43234@qq.com</td></tr>
                    		<tr><td valign="middle" align="right"><font color="red">*</font>邮政编码：</td><td><input type="text" class="txt short"/></td></tr>
                    		</tbody>
                		</table>
                    </div>
                    <div id="address-bottom">
                    <h2>已保存地址</h2>
                    <div class="address-table">
                    	<table width="100%" >
                        	<tbody>
                             <tr class="tr"><td width="11%">收货人</td><td width="25%">所在地区</td><td width="30%" >详细地址</td><td width="8%">邮编</td><td width="13%">手机/电话</td><td width="13%">操作</td></tr>
                			<!--地址迭代-->
                            <s:iterator  var="iter">
                    		<tr ><td><s:property value='#iter.itemId'/></td><td>广东省 深圳市 南山区</td><td class="detail">南山区南油路海辉大厦金波阁25B常常隧道发生的过再长一点儿在场一点儿</td><td>510006</td><td class="detail">13599999999<br/>2332623636</td><td><a href="" id="t1" onclick="">修改</a>&nbsp;|&nbsp;<a href="">删除</a></td></tr>
                    		</s:iterator>
                            <!--地址迭代结束-->
                            </tbody>
                        </table>
                    </div>
                    </div>
                </div>