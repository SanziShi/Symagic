<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="address-content">
                <!--修改地址迭代开始-->
                <s:iterator value='addressList' var="iter">
                <div id="address<s:property value='#iter.ID'/>" class="address-edit">
                <h2>修改地址<span class="close" onclick="close_address_edit(address<s:property value='#iter.ID'/>)"></span></h2>
                	<table width="100%" cellspacing="0" border="0">
                			<tbody>
                    		<tr>
                            	<td width="80" valign="middle" align="right"><font color="red">*</font>收货人姓名：</td>
                            	<td><input name="receiverName" class="txt" type="text" value="<s:property value='#iter.receiverName'/>"/></td>
                            </tr>
                        	<tr>
                        	  	<td valign="middle" align="right"><font color="red">*</font>所在地区：</td>
                              	<td>
                                <!--默认地址选项迭代--->
                                	<select onchange="get_district(this)" id="level1ID" name="districtLevel1ID" >
                                			<option value="s1" >请选择</option>
                               				<s:iterator value="#iter.level1Districts" var='middle'>
                                			<option value="<s:property value='#middle.ID'/>" <s:if test="#middle.ID=='<s:property value='#iter.level1DistrictDefaultID'/'>">selected="selected" </s:if> ><s:property value='#middle.name'/></option>
                                			</s:iterator>
                                		</select>
                                		<select onchange="get_district(this)" id="level2ID" name="districtLevel2ID">
                                			<option value="s2">请选择</option>
                                		</select>
                                		<select id="level3ID" name="districtLevel3ID">
                                			<option value="">请选择</option>
                                		</select>
                                <!---默认地址选项迭代结束--->
                                </td>
                            </tr>
                    		<tr>
                    		  	<td valign="middle" align="right"><font color="red">*</font>详细地址：</td>
                              	<td><span id="consign_address"></span><input name="addressDetail" type="text" class="txt long" value="<s:property value='#iter.addressDetail'/>"></td>
                            </tr>
                    		<tr>
                            	<td valign="middle" align="right"><font color="red">*</font>手机号码：</td>
                            	<td><input name="mobileNum" type="text" class="txt" value="<s:property value='#iter.mobileNum'/>"/>或者&nbsp;&nbsp;或&nbsp;&nbsp;固定电话：<input name="phoneNum" type="text" class="txt" value="<s:property value='#iter.phoneNum'/>"></td>
                            </tr>
                    		<tr>
                            	<td valign="middle" align="right">邮箱地址：</td><td><s:property value='#session.userName'/></td>
                            </tr>
                    		<tr>
                            	<td valign="middle" align="right"><font color="red">*</font>邮政编码：</td>
                            	<td><input name="zipcode" type="text" class="txt short" value="<s:property value='#iter.zipcode'/>"/></td>
                            </tr>
                    		</tbody>
                		</table>
                  </div>
                  </s:iterator>
                  <!--修改地址迭代结束-->
                	<div id="address-content-head">
                    <h2>新增收货地址</h2>
                    </div>
                    <div id="address-content-body">
                    	<form action="submit_address" onsubmit="return add_address(this)">
                        <table width="100%" cellspacing="0" border="0">
                			<tbody>
                    		<tr>
                            	<td width="80" valign="middle" align="right"><font color="red">*</font>收货人姓名：</td>
                            	<td><input class="txt" type="text"/></td>
                            </tr>
                        	<tr>
                        	  	<td valign="middle" align="right"><font color="red">*</font>所在地区：</td>
                              	<td>
                                	<select onchange="get_district(this)" id="level1ID" name="districtLevel1ID">
                                			<option value="s1">请选择</option>
                               				<s:iterator value="level1Districts" var='iter'>
                                			<option value="<s:property value='#iter.ID'/>"><s:property value='#iter.name'/></option>
                                			</s:iterator>
                                		</select>
                                		<select onchange="get_district(this)" id="level2ID" name="districtLevel2ID">
                                			<option value="s2">请选择</option>
                                		</select>
                                		<select id="level3ID" name="districtLevel3ID">
                                			<option value="">请选择</option>
                                		</select>
                                </td>
                            </tr>
                    		<tr>
                    		  		<td valign="middle" align="right"><font color="red">*</font>详细地址：</td>
                                    <td><span id="consign_address"></span><input name="addressDetail" type="text" class="txt long"></td>
                                </tr>
                    			<tr>
                                	<td valign="middle" align="right"><font color="red">*</font>手机号码：</td>
                                    <td><input name="mobileNum" type="text" class="txt"/>或者&nbsp;&nbsp;或&nbsp;&nbsp;固定电话：<input name="phoneNum" type="text" class="txt"></td>
                                </tr>
                    			<tr>
                                	<td valign="middle" align="right">邮箱地址：</td><td><s:property value='#session.userName'/></td></tr>
                    			<tr>
                                	<td valign="middle" align="right"><font color="red">*</font>邮政编码：</td>
                                    <td><input name="zipcode" type="text" class="txt short"/></td></tr>
                    			<tr>
                                	<td></td>
                                    <td><input type="submit" value="添加至地址簿"/></td>
                                </tr>
                    		</tbody>
                		</table>
                    </form>
                    </div>
                    <div id="address-bottom">
                    <h2>已保存地址</h2>
                    <div class="address-table">
                    	<table width="100%" >
                        	<tbody>
                             <tr class="tr"><td width="11%">收货人</td><td width="25%">所在地区</td><td width="30%" >详细地址</td><td width="8%">邮编</td><td width="13%">手机/电话</td><td width="13%">操作</td></tr>
                			<!--地址迭代-->
                            <s:iterator value='addressList' var="iter">
                    		<tr >
                            	<td><s:property value='#iter.receiverName'/></td>
                            	<td><s:property value='#iter.addressSummary'/></td>
                            	<td class="detail"><s:property value='#iter.addressDetail'/></td>
                            	<td><s:property value='#iter.zipcode'/></td>
                            	<td class="detail"><s:property value='#iter.mobileNum'/><br/><s:property value='#iter.phoneNum'/></td>
                            	<td><a href="javascript:void(0)" onclick="address_edit(<s:property value='#iter.ID'/>)">修改</a>&nbsp;|&nbsp;<a href="javascript:void(0)" onclick="">删除</a></td>
                            </tr>
                    		</s:iterator>
                            <!--地址迭代结束-->
                            </tbody>
                        </table>
                    </div>
                    </div>
                </div>